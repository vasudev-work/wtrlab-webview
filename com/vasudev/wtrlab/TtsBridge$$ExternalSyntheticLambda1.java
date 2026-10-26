package com.vasudev.wtrlab;

import android.speech.tts.TextToSpeech;

/* loaded from: classes.dex */
public final class TtsBridge$$ExternalSyntheticLambda1 implements TextToSpeech.OnInitListener {
    public final TtsBridge f$0;

    public TtsBridge$$ExternalSyntheticLambda1(TtsBridge ttsBridge) {
        this.f$0 = ttsBridge;
    }

    @Override // android.speech.tts.TextToSpeech.OnInitListener
    public final void onInit(int i) {
        TtsBridge.onInit$lambda$0(this.f$0, i);
    }
}
