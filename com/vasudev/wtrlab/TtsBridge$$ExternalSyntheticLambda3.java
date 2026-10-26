package com.vasudev.wtrlab;

import android.webkit.WebView;

/* loaded from: classes.dex */
public final class TtsBridge$$ExternalSyntheticLambda3 implements Runnable {
    public final WebView f$0;
    public final String f$1;
    public final String f$2;
    public final String f$3;

    public TtsBridge$$ExternalSyntheticLambda3(WebView webView, String str, String str2, String str3) {
        this.f$0 = webView;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.evaluateJavascript("window.__ttsEvent && window.__ttsEvent(\"" + this.f$1 + "\",\"" + this.f$2 + "\",\"" + this.f$3 + "\");", null);
    }
}
