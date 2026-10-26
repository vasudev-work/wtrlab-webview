package com.vasudev.wtrlab;

/* loaded from: classes.dex */
public final class TtsBridge$$ExternalSyntheticLambda0 implements Runnable {
    public final TtsBridge f$0;
    public final String f$1;
    public final String f$2;
    public final double f$3;
    public final double f$4;
    public final String f$5;
    public final String f$6;

    public TtsBridge$$ExternalSyntheticLambda0(TtsBridge ttsBridge, String str, String str2, double d, double d2, String str3, String str4) {
        this.f$0 = ttsBridge;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = d;
        this.f$4 = d2;
        this.f$5 = str3;
        this.f$6 = str4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.speak(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
