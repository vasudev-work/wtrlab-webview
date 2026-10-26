package com.vasudev.wtrlab;

import android.webkit.WebView;

/* loaded from: classes.dex */
public final class TtsBridge$$ExternalSyntheticLambda4 implements Runnable {
    public final WebView f$0;
    public final String f$1;

    public TtsBridge$$ExternalSyntheticLambda4(WebView webView, String str) {
        this.f$0 = webView;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.evaluateJavascript(this.f$1, null);
    }
}
