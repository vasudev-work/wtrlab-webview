package com.vasudev.wtrlab;

import android.speech.tts.UtteranceProgressListener;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.UInt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0017J\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u0016"}, d2 = {"com/example/wtrlab/TtsBridge$speak$2", "Landroid/speech/tts/UtteranceProgressListener;", "onStart", "", "utteranceId", "", "onDone", "onError", "errorCode", ""}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TtsBridge$speak$2 extends UtteranceProgressListener {
    final TtsBridge this$0;

    TtsBridge$speak$2(TtsBridge ttsBridge) {
        this.this$0 = ttsBridge;
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onStart(String utteranceId) {
        TtsBridge ttsBridge = this.this$0;
        if (utteranceId == null) {
            utteranceId = "";
        }
        TtsBridge.postEvent$default(ttsBridge, "start", utteranceId, null, 4, null);
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onDone(String utteranceId) {
        TtsBridge ttsBridge = this.this$0;
        if (utteranceId == null) {
            utteranceId = "";
        }
        TtsBridge.postEvent$default(ttsBridge, "end", utteranceId, null, 4, null);
    }

    @Override // android.speech.tts.UtteranceProgressListener
    @Deprecated(message = "Deprecated in Java")
    public void onError(String utteranceId) {
        TtsBridge ttsBridge = this.this$0;
        if (utteranceId == null) {
            utteranceId = "";
        }
        ttsBridge.postEvent("error", utteranceId, "engine-error");
    }

    @Override // android.speech.tts.UtteranceProgressListener
    public void onError(String utteranceId, int errorCode) {
        String str;
        TtsBridge ttsBridge = this.this$0;
        if (utteranceId == null) {
            utteranceId = "";
        }
        switch (errorCode) {
            case -7:
                str = "busy";
                break;
            case -6:
                str = "invalid-request";
                break;
            case -5:
                str = "network-timeout";
                break;
            case -4:
                str = "network";
                break;
            case -3:
                str = "service";
                break;
            case -2:
                str = "synthesis";
                break;
            case UInt.MAX_VALUE /* -1 */:
                str = "generic";
                break;
            default:
                str = "unknown";
                break;
        }
        ttsBridge.postEvent("error", utteranceId, "engine-error-".concat(str));
    }
}
