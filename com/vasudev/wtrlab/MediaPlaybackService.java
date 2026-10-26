package com.vasudev.wtrlab;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0013\u0010\u0003J)\u0010\u0017\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0019\u0010\u0003R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001f\u001a\b\u0018\u00010\u001dR\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 "}, d2 = {"Lcom/example/wtrlab/MediaPlaybackService;", "Landroid/app/Service;", "<init>", "()V", "", "updatePlaybackState", "", "action", "Landroid/app/PendingIntent;", "servicePendingIntent", "(Ljava/lang/String;)Landroid/app/PendingIntent;", "Landroid/app/Notification;", "buildNotification", "()Landroid/app/Notification;", "Landroid/content/Intent;", "intent", "Landroid/os/IBinder;", "onBind", "(Landroid/content/Intent;)Landroid/os/IBinder;", "onCreate", "", "flags", "startId", "onStartCommand", "(Landroid/content/Intent;II)I", "onDestroy", "Landroid/media/session/MediaSession;", "mediaSession", "Landroid/media/session/MediaSession;", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Companion"}, k = 1, mv = {2, 0, 0})
/* loaded from: classes.dex */
public final class MediaPlaybackService extends Service {
    public static final String ACTION_PAUSE = "com.vasudev.wtrlab.action.PAUSE";
    public static final String ACTION_RESUME = "com.vasudev.wtrlab.action.RESUME";
    public static final String ACTION_STOP = "com.vasudev.wtrlab.action.STOP";
    public static final String CHANNEL_ID = "wtrlab_media_playback";
    private static final long MAX_WAKE_MS = 14400000;
    public static final int NOTIFICATION_ID = 1;
    private static final String WAKELOCK_TAG = "wtrlab:media_playback";
    private static volatile boolean isPaused;
    private MediaSession mediaSession;
    private android.media.MediaPlayer silentPlayer;
    private PowerManager.WakeLock wakeLock;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Object systemService = getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, WAKELOCK_TAG);
        newWakeLock.setReferenceCounted(false);
        this.wakeLock = newWakeLock;
        MediaSession mediaSession = new MediaSession(this, "WTRLabPlayer");
        mediaSession.setCallback(new MediaPlaybackService$onCreate$2$1());
        mediaSession.setActive(true);
        this.mediaSession = mediaSession;
        updatePlaybackState();
    }

    private final void updatePlaybackState() {
        MediaSession mediaSession = this.mediaSession;
        if (mediaSession == null) {
            return;
        }
        mediaSession.setPlaybackState(new PlaybackState.Builder().setActions(519L).setState(isPaused ? 2 : 3, -1L, 1.0f).build());
    }

    private final PendingIntent servicePendingIntent(String action) {
        PendingIntent service = PendingIntent.getService(this, action.hashCode(), new Intent(this, (Class<?>) MediaPlaybackService.class).setAction(action), 201326592);
        Intrinsics.checkNotNullExpressionValue(service, "getService(...)");
        return service;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:0|1|(3:3|(1:5)|6)(1:33)|7|8|9|(11:11|12|(1:14)(1:29)|15|(1:17)(1:28)|18|19|20|(1:22)|24|25)|31|12|(0)(0)|15|(0)(0)|18|19|20|(0)|24|25) */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d4 A[Catch: Exception -> 0x00d8, TRY_LEAVE, TryCatch #1 {Exception -> 0x00d8, blocks: (B:20:0x00d0, B:22:0x00d4), top: B:19:0x00d0 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /** Loop a silent track so Android treats this as a real media app.
     *  This is what keeps reading alive in battery saver / screen off. */
    private void startSilentPlayback() {
        try {
            if (this.silentPlayer != null) {
                return;
            }
            int id = getResources().getIdentifier("silent", "raw", getPackageName());
            if (id == 0) {
                return;
            }
            android.content.res.AssetFileDescriptor afd = getResources().openRawResourceFd(id);
            if (afd == null) {
                return;
            }
            android.media.MediaPlayer mp = new android.media.MediaPlayer();
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mp.setLooping(true);
            mp.setVolume(0.02f, 0.02f);
            mp.prepare();
            this.silentPlayer = null;
            try { mp.release(); } catch (Exception ignored) { }
        } catch (Exception ignored) {
        }
    }

    private void stopSilentPlayback() {
        try {
            if (this.silentPlayer != null) {
                this.silentPlayer.stop();
                this.silentPlayer.release();
            }
        } catch (Exception ignored) {
        }
        this.silentPlayer = null;
    }

    private final android.app.Notification buildNotification() {
        android.app.PendingIntent contentIntent = android.app.PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(android.app.NotificationManager.class);
        android.app.Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                android.app.NotificationChannel channel = new android.app.NotificationChannel(CHANNEL_ID, "Media playback", 2);
                channel.setShowBadge(false);
                notificationManager.createNotificationChannel(channel);
            }
            builder = new android.app.Notification.Builder(this, CHANNEL_ID);
        } else {
            builder = new android.app.Notification.Builder(this);
        }
        android.graphics.Bitmap icon = null;
        try {
            int iconId = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
            if (iconId != 0) {
                icon = android.graphics.BitmapFactory.decodeResource(getResources(), iconId);
            }
        } catch (Exception unused) {
            icon = null;
        }
        String contentText = isPaused ? "Paused — tap to return" : "Playing audio — tap to return";
        builder.setSmallIcon(android.R.drawable.ic_media_play)
                .setLargeIcon(icon)
                .setContentTitle("WTR Lab")
                .setContentText(contentText)
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .setOnlyAlertOnce(true);
        if (isPaused) {
            builder.addAction(new android.app.Notification.Action.Builder(android.R.drawable.ic_media_play, "Play", servicePendingIntent(ACTION_RESUME)).build());
        } else {
            builder.addAction(new android.app.Notification.Action.Builder(android.R.drawable.ic_media_pause, "Pause", servicePendingIntent(ACTION_PAUSE)).build());
        }
        builder.addAction(new android.app.Notification.Action.Builder(android.R.drawable.ic_delete, "Stop", servicePendingIntent(ACTION_STOP)).build());
        android.app.Notification.MediaStyle style = new android.app.Notification.MediaStyle();
        android.media.session.MediaSession.Token token = null;
        try {
            MediaSession session = this.mediaSession;
            if (session != null) {
                token = session.getSessionToken();
            }
        } catch (Exception unused) {
            token = null;
        }
        builder.setStyle(style.setMediaSession(token).setShowActionsInCompactView(new int[]{0, 1}));
        android.app.Notification notification = builder.build();
        Intrinsics.checkNotNullExpressionValue(notification, "build(...)");
        return notification;
    }


    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            int hashCode = action.hashCode();
            if (hashCode != 346389248) {
                if (hashCode != 563398819) {
                    if (hashCode == 849565589 && action.equals(ACTION_STOP)) {
                        isPaused = false;
                        TtsBridge companion = TtsBridge.INSTANCE.getInstance();
                        if (companion != null) {
                            companion.jsCancel();
                        }
                        updatePlaybackState();
                        MediaSession mediaSession = this.mediaSession;
                        if (mediaSession != null) {
                            mediaSession.setActive(false);
                        }
                        PowerManager.WakeLock wakeLock = this.wakeLock;
                        if (wakeLock != null && wakeLock.isHeld()) {
                            wakeLock.release();
                        }
                        stopSilentPlayback();
                        stopForeground(true);
                        stopSelf();
                        return 2;
                    }
                } else if (action.equals(ACTION_PAUSE)) {
                    isPaused = true;
                    TtsBridge companion2 = TtsBridge.INSTANCE.getInstance();
                    if (companion2 != null) {
                        companion2.jsPause();
                    }
                    updatePlaybackState();
                    notificationManager.notify(1, buildNotification());
                }
            } else if (action.equals(ACTION_RESUME)) {
                isPaused = false;
                TtsBridge companion3 = TtsBridge.INSTANCE.getInstance();
                if (companion3 != null) {
                    companion3.jsResume();
                }
                updatePlaybackState();
                notificationManager.notify(1, buildNotification());
            }
            return 1;
        }
        isPaused = false;
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(1, buildNotification(), 2);
        } else {
            startForeground(1, buildNotification());
        }
        // silent playback disabled (it stole audio focus)
        updatePlaybackState();
        PowerManager.WakeLock wakeLock2 = this.wakeLock;
        if (wakeLock2 != null) {
            PowerManager.WakeLock wakeLock3 = wakeLock2.isHeld() ^ true ? wakeLock2 : null;
            if (wakeLock3 != null) {
                wakeLock3.acquire(MAX_WAKE_MS);
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
        this.wakeLock = null;
        try {
            MediaSession mediaSession = this.mediaSession;
            if (mediaSession != null) {
                mediaSession.setActive(false);
            }
            MediaSession mediaSession2 = this.mediaSession;
            if (mediaSession2 != null) {
                mediaSession2.release();
            }
        } catch (Exception unused) {
        }
        this.mediaSession = null;
        stopSilentPlayback();
        super.onDestroy();
    }
}
