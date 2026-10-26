package com.vasudev.wtrlab;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Offline audio storage.
 *
 * Two sources feed this:
 *   - captured chunks of the site's own TTS audio (files/<chapter>/partNNN.ext)
 *   - device-TTS synthesis (files/tts/<name>.wav)
 */
public final class AudioStore {

    private AudioStore() {
    }

    /** Root folder that holds every downloaded chapter. */
    public static File root(Context c) {
        File d = new File(c.getFilesDir(), "audio");
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }

    /** Folder that holds device-TTS WAV files. */
    public static File ttsDir(Context c) {
        File d = new File(root(c), "_tts");
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }

    /** Folder name for a chapter, derived from its page URL. */
    public static String chapterKey(String url, String title) {
        String base = url == null ? "" : url;
        int q = base.indexOf('?');
        if (q > 0) {
            base = base.substring(0, q);
        }
        String[] parts = base.split("/");
        String last = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            if (parts[i].length() > 0) {
                last = parts[i];
                break;
            }
        }
        if (last.length() == 0) {
            last = "chapter";
        }
        if (title != null && title.trim().length() > 0) {
            String t = title.trim();
            if (t.length() > 28) {
                t = t.substring(0, 28);
            }
            last = last + "_" + t;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < last.length(); i++) {
            char ch = last.charAt(i);
            boolean ok = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_' || ch == '.';
            sb.append(ok ? ch : '_');
        }
        return sb.toString();
    }

    /** Save one captured chunk. Returns the file, or null on failure. */
    public static synchronized File saveChunk(Context c, String chapter, String ext, byte[] data) {
        try {
            if (data == null || data.length == 0) {
                return null;
            }
            File dir = new File(root(c), chapter);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (ext == null || ext.length() == 0) {
                ext = "bin";
            }
            File f = new File(dir, "part" + System.currentTimeMillis() + "." + ext);
            FileOutputStream out = new FileOutputStream(f);
            out.write(data);
            out.flush();
            out.close();
            return f;
        } catch (Exception e) {
            return null;
        }
    }

    /** Chapter folders (newest first). */
    public static List<String> chapters(Context c) {
        List<String> out = new ArrayList<>();
        try {
            File[] kids = root(c).listFiles();
            if (kids == null) {
                return out;
            }
            for (File f : kids) {
                if (f.isDirectory() && !f.getName().startsWith("_") && f.listFiles() != null
                        && f.listFiles().length > 0) {
                    out.add(f.getName());
                }
            }
            java.util.Collections.sort(out, new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    return b.compareTo(a);
                }
            });
        } catch (Exception ignored) {
        }
        return out;
    }

    /** Audio files of one chapter, in playback order. */
    public static File[] parts(Context c, String chapter) {
        try {
            File[] kids = new File(root(c), chapter).listFiles();
            if (kids == null) {
                return new File[0];
            }
            List<File> files = new ArrayList<>();
            for (File f : kids) {
                if (f.isFile()) {
                    files.add(f);
                }
            }
            File[] arr = files.toArray(new File[0]);
            Arrays.sort(arr, new Comparator<File>() {
                @Override
                public int compare(File a, File b) {
                    return a.getName().compareTo(b.getName());
                }
            });
            return arr;
        } catch (Exception e) {
            return new File[0];
        }
    }

    public static long chapterSize(Context c, String chapter) {
        long total = 0L;
        for (File f : parts(c, chapter)) {
            total += f.length();
        }
        return total;
    }

    public static long totalSize(Context c) {
        long total = 0L;
        try {
            total += dirSize(root(c));
        } catch (Exception ignored) {
        }
        return total;
    }

    private static long dirSize(File dir) {
        long total = 0L;
        try {
            File[] kids = dir.listFiles();
            if (kids == null) {
                return 0L;
            }
            for (File f : kids) {
                total += f.isDirectory() ? dirSize(f) : f.length();
            }
        } catch (Exception ignored) {
        }
        return total;
    }

    public static void delete(Context c, String chapter) {
        try {
            deleteRecursive(new File(root(c), chapter));
        } catch (Exception ignored) {
        }
    }

    public static void deleteAll(Context c) {
        try {
            File[] kids = root(c).listFiles();
            if (kids != null) {
                for (File f : kids) {
                    if (!f.getName().startsWith("_")) {
                        deleteRecursive(f);
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static void deleteRecursive(File f) {
        try {
            if (f.isDirectory()) {
                File[] kids = f.listFiles();
                if (kids != null) {
                    for (File k : kids) {
                        deleteRecursive(k);
                    }
                }
            }
            f.delete();
        } catch (Exception ignored) {
        }
    }

    /** Keep the offline library under the user's limit (oldest chapter first). */
    public static void enforceLimit(Context c, long limitBytes) {
        try {
            if (limitBytes <= 0) {
                return;
            }
            while (totalSize(c) > limitBytes) {
                List<String> chs = chapters(c);
                if (chs.isEmpty()) {
                    return;
                }
                delete(c, chs.get(chs.size() - 1));
            }
        } catch (Exception ignored) {
        }
    }

    /** True when the URL looks like an audio resource. */
    public static boolean looksLikeAudio(String url, String accept) {
        try {
            String u = url == null ? "" : url.toLowerCase();
            int q = u.indexOf('?');
            String path = q > 0 ? u.substring(0, q) : u;
            if (path.endsWith(".mp3") || path.endsWith(".m4a") || path.endsWith(".aac")
                    || path.endsWith(".ogg") || path.endsWith(".oga") || path.endsWith(".opus")
                    || path.endsWith(".wav") || path.endsWith(".mp4")) {
                return true;
            }
            if (accept != null && accept.toLowerCase().contains("audio")) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public static String extensionOf(String url) {
        try {
            String u = url == null ? "" : url.toLowerCase();
            int q = u.indexOf('?');
            String path = q > 0 ? u.substring(0, q) : u;
            int dot = path.lastIndexOf('.');
            if (dot > 0 && path.length() - dot <= 5) {
                String ext = path.substring(dot + 1);
                if (ext.matches("[a-z0-9]{2,4}")) {
                    return ext;
                }
            }
        } catch (Exception ignored) {
        }
        return "bin";
    }

    public static String humanSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        if (bytes < 1024 * 1024) {
            return (bytes / 1024) + " KB";
        }
        return String.format(java.util.Locale.US, "%.1f MB", bytes / 1048576f);
    }
}
