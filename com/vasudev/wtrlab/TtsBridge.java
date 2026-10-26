package com.vasudev.wtrlab;

import android.content.Context;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001LB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\rJ\u0015\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0015\u0010\rJ\r\u0010\u0016\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0017J)\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u000b¢\u0006\u0004\b\u001d\u0010\u0017J\r\u0010\u001e\u001a\u00020\u000b¢\u0006\u0004\b\u001e\u0010\u0017J\r\u0010\u001f\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010\u0017J\u000f\u0010 \u001a\u00020\tH\u0007¢\u0006\u0004\b \u0010!J?\u0010(\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0007¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u000bH\u0007¢\u0006\u0004\b*\u0010\u0017J\u000f\u0010+\u001a\u00020\u000bH\u0007¢\u0006\u0004\b+\u0010\u0017J\u000f\u0010,\u001a\u00020\u000bH\u0007¢\u0006\u0004\b,\u0010\u0017J\u000f\u0010-\u001a\u00020\u0006H\u0007¢\u0006\u0004\b-\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010.R&\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t000/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00104R\u0016\u00108\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00104R\u0016\u0010:\u001a\u0002098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u0002098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010;R\u0016\u0010=\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u00104R\u0016\u0010>\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u00104R\u0016\u0010?\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u00106R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010D\u001a\u00020C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010G\u001a\u0004\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u00106R\u0018\u0010J\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010K"}, d2 = {"Lcom/example/wtrlab/TtsBridge;", "", "Landroid/content/Context;", "appContext", "<init>", "(Landroid/content/Context;)V", "", "hostAllowed", "()Z", "", "tag", "", "notifyMissing", "(Ljava/lang/String;)V", "script", "evalJs", "Landroid/webkit/WebView;", "view", "attach", "(Landroid/webkit/WebView;)V", "url", "updateUrl", "shutdown", "()V", "type", "id", "reason", "postEvent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "jsPause", "jsResume", "jsCancel", "getVoicesJson", "()Ljava/lang/String;", "text", "lang", "", "rate", "pitch", "voiceUri", "speak", "(Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;)V", "cancel", "pause", "resume", "isReady", "Landroid/content/Context;", "", "Lkotlin/Pair;", "catalog", "Ljava/util/List;", "currentUrl", "Ljava/lang/String;", "engineReady", "Z", "lastId", "lastLang", "", "lastPitch", "F", "lastRate", "lastText", "lastVoiceUri", "listenerAttached", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "onInit", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "", "pendingRetries", "I", "Landroid/speech/tts/TextToSpeech;", "tts", "Landroid/speech/tts/TextToSpeech;", "usingGoogleEngine", "webView", "Landroid/webkit/WebView;", "Companion"}, k = 1, mv = {2, 0, 0})
/* loaded from: classes.dex */
public final class TtsBridge {
    private static final String GOOGLE_TTS_PACKAGE = "com.google.android.tts";
    public static final Companion INSTANCE = new Companion(null);
    private static volatile TtsBridge instance;
    final Context appContext;
    private final List<Pair<String, String>> catalog;
    private volatile String currentUrl;
    private volatile String currentTitle = "";
    private volatile boolean engineReady;
    private volatile String lastId;
    private volatile String lastLang;
    private volatile float lastPitch;
    private volatile float lastRate;
    private volatile String lastText;
    private volatile String lastVoiceUri;
    private volatile boolean listenerAttached;
    private volatile float volume = 1.0f;
    private final android.os.Handler handler = new android.os.Handler(android.os.Looper.getMainLooper());
    private Runnable sleepRunnable;
    private Runnable focusRunnable;
    private volatile String siteThemeCss = "";
    private volatile boolean ambientDark = false;
    private volatile String ttsState = "stopped";
    private final TextToSpeech.OnInitListener onInit;
    private volatile int pendingRetries;
    private TextToSpeech tts;
    private volatile boolean usingGoogleEngine;
    private WebView webView;

    public TtsBridge(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.lastText = "";
        this.lastLang = "en-US";
        this.lastRate = 1.0f;
        this.lastPitch = 1.0f;
        this.lastId = "";
        this.lastVoiceUri = "";
        TtsBridge$$ExternalSyntheticLambda1 ttsBridge$$ExternalSyntheticLambda1 = new TtsBridge$$ExternalSyntheticLambda1(this);
        this.onInit = ttsBridge$$ExternalSyntheticLambda1;
        this.usingGoogleEngine = true;
        instance = this;
        this.tts = new TextToSpeech(appContext.getApplicationContext(), ttsBridge$$ExternalSyntheticLambda1, getEnginePackage());
        this.catalog = Arrays.asList(TuplesKt.to("English Australia", "en-AU"), TuplesKt.to("English India", "en-IN"), TuplesKt.to("English Nigeria", "en-NG"), TuplesKt.to("English United Kingdom", "en-GB"), TuplesKt.to("English United States", "en-US"), TuplesKt.to("Albanian Albania", "sq-AL"), TuplesKt.to("Assamese India", "as-IN"), TuplesKt.to("Bengali Bangladesh", "bn-BD"), TuplesKt.to("Bengali India", "bn-IN"), TuplesKt.to("Bodo India", "brx-IN"), TuplesKt.to("Bosnian Bosnia", "bs-BA"), TuplesKt.to("Bulgarian Bulgaria", "bg-BG"), TuplesKt.to("Cantonese Hong Kong", "yue-HK"), TuplesKt.to("Catalan Spain", "ca-ES"), TuplesKt.to("Chinese China", "zh-CN"), TuplesKt.to("Chinese Hong Kong", "zh-HK"), TuplesKt.to("Chinese Taiwan", "zh-TW"), TuplesKt.to("Croatian Croatia", "hr-HR"), TuplesKt.to("Czech Czechia", "cs-CZ"), TuplesKt.to("Danish Denmark", "da-DK"), TuplesKt.to("Dogri India", "doi-IN"), TuplesKt.to("Dutch Belgium", "nl-BE"), TuplesKt.to("Dutch Netherlands", "nl-NL"), TuplesKt.to("Estonian Estonia", "et-EE"), TuplesKt.to("Filipino Philippines", "fil-PH"), TuplesKt.to("Finnish Finland", "fi-FI"), TuplesKt.to("French Canada", "fr-CA"), TuplesKt.to("French France", "fr-FR"), TuplesKt.to("German Germany", "de-DE"), TuplesKt.to("Greek Greece", "el-GR"), TuplesKt.to("Gujarati India", "gu-IN"), TuplesKt.to("Hebrew Israel", "he-IL"), TuplesKt.to("Hindi India", "hi-IN"), TuplesKt.to("Hungarian Hungary", "hu-HU"), TuplesKt.to("Icelandic Iceland", "is-IS"), TuplesKt.to("Indonesian Indonesia", "id-ID"), TuplesKt.to("Italian Italy", "it-IT"), TuplesKt.to("Japanese Japan", "ja-JP"), TuplesKt.to("Kannada India", "kn-IN"), TuplesKt.to("Kazakh Kazakhstan", "kk-KZ"), TuplesKt.to("Khmer Cambodia", "km-KH"), TuplesKt.to("Kinyarwanda Rwanda", "rw-RW"), TuplesKt.to("Korean South Korea", "ko-KR"), TuplesKt.to("Kyrgyz Kyrgyzstan", "ky-KG"), TuplesKt.to("Lao Laos", "lo-LA"), TuplesKt.to("Latvian Latvia", "lv-LV"), TuplesKt.to("Lithuanian Lithuania", "lt-LT"), TuplesKt.to("Macedonian Macedonia", "mk-MK"), TuplesKt.to("Malay Malaysia", "ms-MY"), TuplesKt.to("Malayalam India", "ml-IN"), TuplesKt.to("Marathi India", "mr-IN"), TuplesKt.to("Mongolian Mongolia", "mn-MN"), TuplesKt.to("Nepali Nepal", "ne-NP"), TuplesKt.to("Norwegian Norway", "nb-NO"), TuplesKt.to("Odia India", "or-IN"), TuplesKt.to("Persian Iran", "fa-IR"), TuplesKt.to("Polish Poland", "pl-PL"), TuplesKt.to("Portuguese Brazil", "pt-BR"), TuplesKt.to("Portuguese Portugal", "pt-PT"), TuplesKt.to("Punjabi India", "pa-IN"), TuplesKt.to("Romanian Romania", "ro-RO"), TuplesKt.to("Russian Russia", "ru-RU"), TuplesKt.to("Serbian Serbia", "sr-RS"), TuplesKt.to("Sinhala Sri Lanka", "si-LK"), TuplesKt.to("Slovak Slovakia", "sk-SK"), TuplesKt.to("Slovenian Slovenia", "sl-SI"), TuplesKt.to("Spanish Spain", "es-ES"), TuplesKt.to("Spanish United States", "es-US"), TuplesKt.to("Swahili Kenya", "sw-KE"), TuplesKt.to("Tamil India", "ta-IN"), TuplesKt.to("Telugu India", "te-IN"), TuplesKt.to("Thai Thailand", "th-TH"), TuplesKt.to("Turkish Turkey", "tr-TR"), TuplesKt.to("Ukrainian Ukraine", "uk-UA"), TuplesKt.to("Urdu India", "ur-IN"), TuplesKt.to("Urdu Pakistan", "ur-PK"), TuplesKt.to("Uzbek Uzbekistan", "uz-UZ"), TuplesKt.to("Vietnamese Vietnam", "vi-VN"), TuplesKt.to("Zulu South Africa", "zu-ZA"));
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b"}, d2 = {"Lcom/example/wtrlab/TtsBridge$Companion;", "", "<init>", "()V", "GOOGLE_TTS_PACKAGE", "", "instance", "Lcom/example/wtrlab/TtsBridge;", "getInstance", "()Lcom/example/wtrlab/TtsBridge;", "setInstance", "(Lcom/example/wtrlab/TtsBridge;)V"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TtsBridge getInstance() {
            return TtsBridge.instance;
        }

        public final void setInstance(TtsBridge ttsBridge) {
            TtsBridge unused = TtsBridge.instance = ttsBridge;
        }
    }

    public static final void onInit$lambda$0(TtsBridge ttsBridge, int i) {
        ttsBridge.engineReady = i == 0;
        if (ttsBridge.engineReady) {
            TextToSpeech textToSpeech = ttsBridge.tts;
            if (textToSpeech != null) {
                textToSpeech.setLanguage(Locale.US);
                return;
            }
            return;
        }
        if (ttsBridge.usingGoogleEngine) {
            ttsBridge.usingGoogleEngine = false;
            TextToSpeech textToSpeech2 = ttsBridge.tts;
            if (textToSpeech2 != null) {
                textToSpeech2.shutdown();
            }
            ttsBridge.tts = new TextToSpeech(ttsBridge.appContext.getApplicationContext(), ttsBridge.onInit);
        }
    }

    public final void attach(WebView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.webView = view;
    }

    public final void updateTitle(String title) {
        this.currentTitle = title == null ? "" : title;
    }

    public final String currentPageUrl() {
        return this.currentUrl;
    }

    public final String currentPageTitle() {
        return this.currentTitle;
    }

    public final void updateUrl(String url) {
        this.currentUrl = url;
    }

    public final void shutdown() {
        instance = null;
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        TextToSpeech textToSpeech2 = this.tts;
        if (textToSpeech2 != null) {
            textToSpeech2.shutdown();
        }
        this.tts = null;
    }

    private final boolean hostAllowed() {
        String str = this.currentUrl;
        String str2 = null;
        if (str == null) {
            WebView webView = this.webView;
            str = webView != null ? webView.getUrl() : null;
        }
        if (str != null) {
            try {
                str2 = Uri.parse(str).getHost();
            } catch (Exception unused) {
            }
        }
        if (str2 == null || str2.equals(MainActivity.HOST)) {
            return true;
        }
        return str2.endsWith(".wtr-lab.com");
    }

    static void postEvent$default(TtsBridge ttsBridge, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        ttsBridge.postEvent(str, str2, str3);
    }

    public final void postEvent(String type, String id, String reason) {
        WebView webView = this.webView;
        if (webView == null) {
            return;
        }
        webView.post(new TtsBridge$$ExternalSyntheticLambda3(webView, type, id, reason));
    }

    private final void notifyMissing(String tag) {
        WebView webView = this.webView;
        if (webView == null) {
            return;
        }
        webView.post(new TtsBridge$$ExternalSyntheticLambda2(this, tag));
    }

    private final void evalJs(String script) {
        WebView webView = this.webView;
        if (webView == null) {
            return;
        }
        webView.post(new TtsBridge$$ExternalSyntheticLambda4(webView, script));
    }

    public final void jsPause() {
        evalJs("window.speechSynthesis && window.speechSynthesis.pause();");
    }

    public final void jsResume() {
        evalJs("window.speechSynthesis && window.speechSynthesis.resume();");
    }

    public final void jsCancel() {
        evalJs("window.speechSynthesis && window.speechSynthesis.cancel();");
    }

    private final java.util.List<String> jsErrors = new java.util.ArrayList<>();

    /** Called from the injected shim whenever the page throws a JS error. */
    @android.webkit.JavascriptInterface
    public final void logError(String msg) {
        try {
            synchronized (jsErrors) {
                if (msg != null && jsErrors.size() < 8) {
                    jsErrors.add(msg.length() > 200 ? msg.substring(0, 200) : msg);
                }
            }
        } catch (Exception ignored) {
        }
    }

    /** Read back the captured page errors (shown on the recovery page). */
    @android.webkit.JavascriptInterface
    public final String getErrors() {
        try {
            synchronized (jsErrors) {
                StringBuilder sb = new StringBuilder();
                for (String e : jsErrors) {
                    if (sb.length() > 0) {
                        sb.append(" | ");
                    }
                    sb.append(e);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return "";
        }
    }

    /** Open a URL in the phone's real browser (diagnostic / fallback). */
    @android.webkit.JavascriptInterface
    public final void openExternal(String url) {
        try {
            android.content.Intent i = new android.content.Intent(
                    android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url));
            i.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(i);
        } catch (Exception ignored) {
        }
    }

    /** Called from the recovery page: clear the WebView cache and reload.
     *  Fixes a blank/black page caused by a corrupted cached bundle. */
    @android.webkit.JavascriptInterface
    public final void clearAndReload() {
        final WebView w = this.webView;
        if (w == null) {
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    w.clearCache(true);
                } catch (Exception ignored) {
                }
                try {
                    w.clearHistory();
                } catch (Exception ignored) {
                }
                w.loadUrl("https://wtr-lab.com/");
            }
        });
    }

    /** Apply the app's theme to the loaded website (CSS injected into the page). */
    @JavascriptInterface
    public final void setSiteTheme(String css, boolean dark) {
        this.siteThemeCss = css == null ? "" : css;
        evalJs(buildThemeJs(this.siteThemeCss, dark));
        setForceDarkMode(dark);
    }

    /** The CSS the injected page script re-applies on every navigation
     *  (app theme + any custom CSS the user pasted in Settings). */
    @JavascriptInterface
    public final String getSiteTheme() {
        String base = this.siteThemeCss;
        try {
            android.content.SharedPreferences p = appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
            String typo = p.getString("typoCss", "");
            if (typo != null && typo.length() > 0) {
                base = base + "\n" + typo;
            }
            String custom = p.getString("customCss", "");
            if (custom != null && custom.length() > 0) {
                base = base + "\n" + custom;
            }
        } catch (Exception ignored) {
        }
        return base;
    }

    @JavascriptInterface
    public final boolean isSimpleTts() {
        try {
            return appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                    .getBoolean("simpleTts", true);
        } catch (Exception e) {
            return false;
        }
    }

    @JavascriptInterface
    public final boolean isBionic() {
        try {
            return appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                    .getBoolean("bionic", false);
        } catch (Exception e) {
            return false;
        }
    }

    @JavascriptInterface
    public final boolean isPerfMode() {
        try {
            return appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                    .getBoolean("perfMode", false);
        } catch (Exception e) {
            return false;
        }
    }

    /** Auto-scroll the page slowly while reading. */
    @JavascriptInterface
    public final void setAutoScroll(boolean on, int speed) {
        try {
            String js = "(function(){try{window.__wtrAutoScroll = {on:" + (on ? "true" : "false")
                    + ", px:" + Math.max(1, speed) + "};}catch(e){}})();";
            evalJs(js);
        } catch (Exception ignored) {
        }
    }

    @JavascriptInterface
    public final void reloadPage() {
        final WebView w = this.webView;
        if (w == null) {
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    w.reload();
                } catch (Exception ignored) {
                }
            }
        });
    }

    /** Toggle speech play/pause (used by the double-tap gesture). */
    public final void jsToggle() {
        evalJs("(function(){try{var s=window.speechSynthesis;if(!s)return;if(s.paused){s.resume();}else{s.pause();}}catch(e){}})()");
    }

    /** Scheduled (time window) or ambient-light theme switching. */
    public final void refreshAutoTheme() {
        try {
            android.content.SharedPreferences p = appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
            if (!p.getBoolean("siteTheme", true)) {
                setSiteTheme("", false);
                return;
            }
            boolean dark;
            if (p.getBoolean("ambient", false)) {
                dark = this.ambientDark;
            } else if (p.getBoolean("autoDark", false)) {
                int from = p.getInt("darkFrom", 20);
                int to = p.getInt("darkTo", 7);
                int hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
                dark = from <= to ? (hour >= from && hour < to) : (hour >= from || hour < to);
            } else {
                dark = (appContext.getResources().getConfiguration().uiMode
                        & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                        == android.content.res.Configuration.UI_MODE_NIGHT_YES;
            }
            setSiteTheme(p.getString(dark ? "siteCssDark" : "siteCssLight", ""), dark);
        } catch (Exception ignored) {
        }
    }

    /** The page reports what the reader is doing, so playback can continue by
     *  itself when the user returns to the app. */
    @JavascriptInterface
    public final void setState(String s) {
        this.ttsState = s == null ? "stopped" : s;
    }

    @JavascriptInterface
    public final float getVolume() {
        return this.volume;
    }

    public final String getState() {
        return this.ttsState;
    }

    public final void setAmbientDark(boolean dark) {
        if (this.ambientDark == dark) {
            return;
        }
        this.ambientDark = dark;
        refreshAutoTheme();
    }

    /** Focus / Pomodoro session: pause the reader after N minutes (0 = off). */
    @JavascriptInterface
    public final void startFocusSession(final int minutes) {
        try {
            if (this.focusRunnable != null) {
                this.handler.removeCallbacks(this.focusRunnable);
                this.focusRunnable = null;
            }
            if (minutes <= 0) {
                return;
            }
            this.focusRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        jsPause();
                    } catch (Exception ignored) {
                    }
                    try {
                        android.widget.Toast.makeText(appContext,
                                "Focus session complete - take a short break.",
                                android.widget.Toast.LENGTH_LONG).show();
                    } catch (Exception ignored) {
                    }
                }
            };
            this.handler.postDelayed(this.focusRunnable, minutes * 60000L);
        } catch (Exception ignored) {
        }
    }

    private void setForceDarkMode(final boolean dark) {
        final WebView w = this.webView;
        if (w == null) {
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (android.os.Build.VERSION.SDK_INT >= 33) {
                        w.getSettings().setAlgorithmicDarkeningAllowed(dark);
                    } else if (android.os.Build.VERSION.SDK_INT >= 29) {
                        w.getSettings().setForceDark(dark
                                ? android.webkit.WebSettings.FORCE_DARK_ON
                                : android.webkit.WebSettings.FORCE_DARK_OFF);
                    }
                } catch (Exception ignored) {
                }
            }
        });
    }

    private static String buildThemeJs(String css, boolean dark) {
        String escaped = css.replace("\\", "\\\\").replace("'", "\\'").replace("\n", " ");
        return "(function(){try{var id='wtrlab-theme';var s=document.getElementById(id);"
                + "if(!s){s=document.createElement('style');s.id=id;(document.head||document.documentElement).appendChild(s);}"
                + "s.textContent='" + escaped + "';"
                + "document.documentElement.style.colorScheme='" + (dark ? "dark" : "light") + "';"
                + "}catch(e){}})();";
    }

    private void notifyToast(final String msg) {
        try {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        android.widget.Toast.makeText(appContext, msg, android.widget.Toast.LENGTH_LONG).show();
                    } catch (Exception ignored) {
                    }
                }
            });
        } catch (Exception ignored) {
        }
    }

    /** Fallback path: generate an audio file of the current chapter with the
     *  phone's own TTS engine (works offline, no server needed). */
    @JavascriptInterface
    public final void downloadChapter(String unusedName) {
        final WebView w = this.webView;
        if (w == null) {
            notifyToast("Open a chapter first");
            return;
        }
        notifyToast("Reading the chapter text...");
        w.evaluateJavascript(
                "(function(){try{var best=null,bl=0;var els=document.querySelectorAll('div,p,article,section');"
                        + "for(var i=0;i<els.length;i++){var e=els[i];var t=e.innerText||'';"
                        + "if(t.length>bl&&e.querySelectorAll('div,p').length<80){bl=t.length;best=e;}}"
                        + "return best?best.innerText:'';}catch(e){return '';}})()",
                new android.webkit.ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        String text = decodeJson(value);
                        if (text == null || text.trim().length() < 40) {
                            notifyToast("Could not read the chapter text");
                            return;
                        }
                        synthesizeChapter(text, w.getTitle());
                    }
                });
    }

    private static String decodeJson(String v) {
        try {
            if (v == null || v.equals("null")) {
                return null;
            }
            if (v.startsWith("\"") && v.endsWith("\"") && v.length() >= 2) {
                v = v.substring(1, v.length() - 1);
            }
            return v.replace("\\n", "\n").replace("\\t", "\t")
                    .replace("\\\"", "\"").replace("\\\\", "\\");
        } catch (Exception e) {
            return null;
        }
    }

    private static String safeName(String s) {
        if (s == null || s.trim().length() == 0) {
            s = "chapter";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() && sb.length() < 32; i++) {
            char ch = s.charAt(i);
            boolean ok = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_';
            sb.append(ok ? ch : '_');
        }
        return sb.toString();
    }

    private void synthesizeChapter(String text, String title) {
        final TextToSpeech engine = this.tts;
        if (engine == null) {
            notifyToast("Text-to-speech is not ready yet");
            return;
        }
        try {
            final java.io.File out = new java.io.File(AudioStore.ttsDir(appContext),
                    safeName(title) + "_" + System.currentTimeMillis() + ".wav");
            android.os.Bundle params = new android.os.Bundle();
            params.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f);
            int res = engine.synthesizeToFile(text, params, out, "dl" + System.currentTimeMillis());
            if (res != TextToSpeech.SUCCESS) {
                notifyToast("Could not start audio generation");
                return;
            }
            notifyToast("Generating audio with the device voice... this can take a few minutes");
            pollFile(out, -1L, 0, 0);
        } catch (Exception e) {
            notifyToast("Audio generation failed");
        }
    }

    private void pollFile(final java.io.File f, final long lastLen, final int stable, final int tries) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    long len = f.length();
                    if (len > 0 && len == lastLen) {
                        if (stable >= 1) {
                            notifyToast("Saved offline audio: " + AudioStore.humanSize(len));
                            return;
                        }
                        pollFile(f, len, stable + 1, tries + 1);
                        return;
                    }
                    if (tries > 150) {
                        notifyToast("Audio generation timed out");
                        return;
                    }
                    pollFile(f, len, 0, tries + 1);
                } catch (Exception ignored) {
                }
            }
        }, 2000L);
    }

    /** Gently reduce the volume, then stop (used by the sleep timer). */
    private void fadeOutAndStop(final int steps) {
        try {
            if (steps <= 0) {
                setVolume(1.0f);
                jsCancel();
                return;
            }
            setVolume(Math.max(0.05f, steps * 0.2f));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fadeOutAndStop(steps - 1);
                }
            }, 1500L);
        } catch (Exception ignored) {
        }
    }

    /** Sleep timer: stop the reader after N minutes (0 = off). */
    @JavascriptInterface
    public final void setSleepTimer(int minutes) {
        try {
            if (this.sleepRunnable != null) {
                this.handler.removeCallbacks(this.sleepRunnable);
                this.sleepRunnable = null;
            }
            if (minutes <= 0) {
                return;
            }
            this.sleepRunnable = new Runnable() {
                @Override
                public void run() {
                    fadeOutAndStop(5);
                }
            };
            this.handler.postDelayed(this.sleepRunnable, minutes * 60000L);
        } catch (Exception ignored) {
        }
    }

    /** Playback volume (0.0 - 1.0) for speech + in-page audio. */
    @JavascriptInterface
    public final void setVolume(float v) {
        float clamped = v < 0f ? 0f : (v > 1f ? 1f : v);
        this.volume = clamped;
        evalJs("(function(){try{var v=" + clamped + ";var m=document.querySelectorAll('audio,video');for(var i=0;i<m.length;i++){try{m[i].volume=v;}catch(e){}}}catch(e){}})()");
    }

    private final java.util.ArrayDeque<String> eventLog = new java.util.ArrayDeque<>();

    /** Called from the page shim and the app so a stall can be diagnosed. */
    @JavascriptInterface
    public final void logEvent(String s) {
        try {
            String t = new java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.US)
                    .format(new java.util.Date());
            synchronized (this.eventLog) {
                this.eventLog.addLast(t + "  " + (s == null ? "" : s));
                while (this.eventLog.size() > 80) {
                    this.eventLog.removeFirst();
                }
            }
        } catch (Exception ignored) {
        }
    }

    public final String getEventLog() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.eventLog) {
            for (String s : this.eventLog) {
                sb.append(s).append('\n');
            }
        }
        return sb.toString();
    }

    private android.content.SharedPreferences uiPrefs() {
        return this.appContext.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
    }
    public final String getEnginePackage() {
        try {
            return uiPrefs().getString("ttsEngine", GOOGLE_TTS_PACKAGE);
        } catch (Exception e) {
            return GOOGLE_TTS_PACKAGE;
        }
    }
    public final void setEnginePackage(String pkg) {
        try {
            uiPrefs().edit().putString("ttsEngine", pkg).apply();
        } catch (Exception ignored) {
        }
        restartEngine(pkg);
    }
    public final void restartEngine(String pkg) {
        try {
            TextToSpeech old = this.tts;
            if (old != null) {
                old.shutdown();
            }
        } catch (Exception ignored) {
        }
        this.engineReady = false;
        try {
            if (pkg == null || pkg.length() == 0) {
                this.tts = new TextToSpeech(this.appContext.getApplicationContext(), this.onInit);
            } else {
                this.tts = new TextToSpeech(this.appContext.getApplicationContext(), this.onInit, pkg);
            }
        } catch (Exception ignored) {
        }
    }
    @JavascriptInterface
    public final float getReadRate() {
        try {
            return uiPrefs().getFloat("readRate", 1.0f);
        } catch (Exception e) {
            return 1.0f;
        }
    }
    public final void setReadRate(float r) {
        try {
            uiPrefs().edit().putFloat("readRate", r).apply();
        } catch (Exception ignored) {
        }
    }
    @JavascriptInterface
    public final String getReadVoice() {
        try {
            return uiPrefs().getString("readVoice", "");
        } catch (Exception e) {
            return "";
        }
    }
    public final void setReadVoice(String uri) {
        try {
            uiPrefs().edit().putString("readVoice", uri == null ? "" : uri).apply();
        } catch (Exception ignored) {
        }
    }
    @JavascriptInterface
    public final boolean isHighlight() {
        try {
            return uiPrefs().getBoolean("highlightPara", true);
        } catch (Exception e) {
            return true;
        }
    }
    @JavascriptInterface
    public final boolean isAutoAdvance() {
        try {
            return uiPrefs().getBoolean("autoAdvance", false);
        } catch (Exception e) {
            return false;
        }
    }
    @JavascriptInterface
    public final void startAutoRead(String url) {
        if (url == null || url.length() == 0) {
            return;
        }
        try {
            uiPrefs().edit().putBoolean("pendingAutoRead", true).apply();
        } catch (Exception ignored) {
        }
        loadUrl(url);
    }
    public final boolean consumePendingAutoRead() {
        try {
            android.content.SharedPreferences p = uiPrefs();
            if (!p.getBoolean("pendingAutoRead", false)) {
                return false;
            }
            p.edit().putBoolean("pendingAutoRead", false).apply();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public final void readPageAloud() {
        final WebView w = this.webView;
        if (w == null) {
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    w.evaluateJavascript("window.__wtrReadPage&&window.__wtrReadPage()", null);
                } catch (Exception ignored) {
                }
            }
        });
    }
    public final void fetchPageText(final PageTextCallback cb) {
        final WebView w = this.webView;
        if (w == null) {
            cb.onText(null);
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    w.evaluateJavascript("window.__wtrGetPageText&&window.__wtrGetPageText()", value -> {
                        String out = null;
                        try {
                            if (value != null && !"null".equals(value)) {
                                out = new org.json.JSONArray("[" + value + "]").getString(0);
                            }
                        } catch (Exception ignored) {
                        }
                        cb.onText(out);
                    });
                } catch (Exception e) {
                    cb.onText(null);
                }
            }
        });
    }
    public interface PageTextCallback {
        void onText(String text);
    }

    @JavascriptInterface
    public final void loadUrl(final String url) {
        final WebView w = this.webView;
        if (w == null) {
            return;
        }
        w.post(new Runnable() {
            @Override
            public void run() {
                try {
                    w.loadUrl(url);
                } catch (Exception ignored) {
                }
            }
        });
    }

    public final String getVoicesJson() {
        boolean z;
        TtsBridge ttsBridge = this;
        try {
            TextToSpeech textToSpeech = ttsBridge.tts;
            JSONArray jSONArray = new JSONArray();
            for (Pair<String, String> pair : ttsBridge.catalog) {
                String component1 = pair.component1();
                String component2 = pair.component2();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("voiceURI", component1);
                jSONObject.put("name", component1);
                jSONObject.put("lang", component2);
                jSONObject.put("localService", false);
                jSONObject.put("default", "en-US".equalsIgnoreCase(component2));
                jSONArray.put(jSONObject);
                if (textToSpeech == null) {
                    ttsBridge = this;
                } else if (ttsBridge.engineReady) {
                    Locale forLanguageTag = Locale.forLanguageTag(component2);
                    Set<Voice> voices = textToSpeech.getVoices();
                    if (voices != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Voice voice : voices) {
                            TextToSpeech textToSpeech2 = textToSpeech;
                            if (voice.getLocale().getLanguage().equalsIgnoreCase(forLanguageTag.getLanguage()) && (forLanguageTag.getCountry().length() == 0 || voice.getLocale().getCountry().equalsIgnoreCase(forLanguageTag.getCountry()))) {
                                arrayList.add(voice);
                            }
                            textToSpeech = textToSpeech2;
                        }
                        TextToSpeech textToSpeech3 = textToSpeech;
                        if (arrayList.size() > 1) {
                            List<Voice> sortedWith = CollectionsKt.sortedWith(arrayList, new TtsBridge$getVoicesJson$$inlined$sortedBy$1());
                            Iterator it = sortedWith.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                if (!((Voice) it.next()).isNetworkConnectionRequired()) {
                                    z = true;
                                    break;
                                }
                            }
                            jSONObject.put("localService", z);
                            int i = 0;
                            for (Voice voice2 : sortedWith) {
                                i++;
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("voiceURI", voice2.getName());
                                jSONObject2.put("name", component1 + " " + i);
                                jSONObject2.put("lang", component2);
                                jSONObject2.put("localService", voice2.isNetworkConnectionRequired() ^ true);
                                jSONObject2.put("default", false);
                                jSONArray.put(jSONObject2);
                            }
                        }
                        ttsBridge = this;
                        textToSpeech = textToSpeech3;
                    }
                }
            }
            return jSONArray.toString();
        } catch (Exception unused) {
            return "[]";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x013a A[Catch: Exception -> 0x019c, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x019c, blocks: (B:97:0x013a, B:41:0x017d), top: B:27:0x00c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    @JavascriptInterface
    public final void speak(final String text, final String lang, final double rate, final double pitch, final String id, final String voiceUri) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(voiceUri, "voiceUri");
        TextToSpeech tts = this.tts;
        if (tts == null) {
            postEvent("error", id, "engine-not-ready");
            return;
        }
        if (!hostAllowed()) {
            postEvent("error", id, "not-allowed");
            return;
        }
        if (!this.engineReady) {
            if (this.pendingRetries < 3) {
                this.pendingRetries++;
                WebView webView = this.webView;
                if (webView != null) {
                    webView.postDelayed(new TtsBridge$$ExternalSyntheticLambda0(this, text, lang, rate, pitch, id, voiceUri), 1200);
                }
            } else {
                postEvent("error", id, "engine-not-ready");
            }
            return;
        }
        this.pendingRetries = 0;
        this.lastText = text;
        this.lastLang = lang;
        this.lastRate = (float) rate;
        this.lastPitch = (float) pitch;
        this.lastId = id;
        this.lastVoiceUri = voiceUri;
        if (!this.listenerAttached) {
            this.listenerAttached = true;
            tts.setOnUtteranceProgressListener(new TtsBridge$speak$2(this));
        }
        tts.setSpeechRate((float) rate);
        tts.setPitch((float) pitch);

        String tag = null;
        if (voiceUri.startsWith("wtrlab-")) {
            tag = voiceUri.substring("wtrlab-".length());
        } else if (voiceUri.length() > 0) {
            for (Pair<String, String> entry : this.catalog) {
                if (Intrinsics.areEqual(entry.getFirst(), voiceUri)) {
                    tag = entry.getSecond();
                    break;
                }
            }
        }

        if (tag != null) {
            Locale locale;
            try {
                locale = Locale.forLanguageTag(tag);
            } catch (Exception e) {
                locale = Locale.US;
            }
            java.util.ArrayList<android.speech.tts.Voice> matching = null;
            try {
                java.util.Set<android.speech.tts.Voice> voices = tts.getVoices();
                if (voices != null) {
                    matching = new java.util.ArrayList<>();
                    for (android.speech.tts.Voice v : voices) {
                        if (!v.getLocale().getLanguage().equalsIgnoreCase(locale.getLanguage())) {
                            continue;
                        }
                        if (locale.getCountry().length() != 0
                                && !v.getLocale().getCountry().equalsIgnoreCase(locale.getCountry())) {
                            continue;
                        }
                        matching.add(v);
                    }
                }
            } catch (Exception e) {
                matching = null;
            }
            android.speech.tts.Voice offline = null;
            if (matching != null) {
                for (android.speech.tts.Voice v : matching) {
                    if (!v.isNetworkConnectionRequired()) {
                        offline = v;
                        break;
                    }
                }
            }
            if (offline != null) {
                try {
                    tts.setVoice(offline);
                } catch (Exception ignored) {
                }
            } else {
                int result;
                try {
                    result = tts.setLanguage(locale);
                } catch (Exception e) {
                    result = TextToSpeech.LANG_MISSING_DATA;
                }
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    notifyMissing(tag);
                    postEvent("error", id, "language-not-installed");
                    return;
                }
            }
        } else if (voiceUri.length() > 0) {
            android.speech.tts.Voice match = null;
            try {
                java.util.Set<android.speech.tts.Voice> voices = tts.getVoices();
                if (voices != null) {
                    for (android.speech.tts.Voice v : voices) {
                        if (Intrinsics.areEqual(v.getName(), voiceUri)) {
                            match = v;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                match = null;
            }
            if (match != null) {
                try {
                    tts.setVoice(match);
                } catch (Exception ignored) {
                }
            } else {
                try {
                    tts.setLanguage(Locale.forLanguageTag(lang));
                } catch (Exception e) {
                    tts.setLanguage(Locale.US);
                }
            }
        } else {
            try {
                tts.setLanguage(Locale.forLanguageTag(lang));
            } catch (Exception e) {
                tts.setLanguage(Locale.US);
            }
        }

        android.os.Bundle speakParams = new android.os.Bundle();
        speakParams.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, this.volume);
        if (tts.speak(text, TextToSpeech.QUEUE_FLUSH, speakParams, id) != TextToSpeech.SUCCESS) {
            postEvent("error", id, "speak-failed");
        }
    }


    @JavascriptInterface
    public final void cancel() {
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    private volatile boolean wasPaused;

    @JavascriptInterface

    public final void pause() {
        this.wasPaused = true;
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    @JavascriptInterface
    public final void resume() {
        if (!this.wasPaused) {
            return;                       // never replay a line we were not paused on
        }
        this.wasPaused = false;
        if (this.lastText.length() > 0) {
            speak(this.lastText, this.lastLang, this.lastRate, this.lastPitch, this.lastId, this.lastVoiceUri);
        }
    }

    @JavascriptInterface
    public final boolean getEngineReady() {
        return this.engineReady;
    }
}
