package com.vasudev.wtrlab;

import android.widget.Toast;

/* loaded from: classes.dex */
public final class TtsBridge$$ExternalSyntheticLambda2 implements Runnable {
    public final TtsBridge f$0;
    public final String f$1;

    public TtsBridge$$ExternalSyntheticLambda2(TtsBridge ttsBridge, String str) {
        this.f$0 = ttsBridge;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Toast.makeText(this.f$0.appContext, "Voice for " + this.f$1 + " is not installed on this phone. Install it: Settings > Text-to-speech > Google > Install voice data", 1).show();
    }
}
