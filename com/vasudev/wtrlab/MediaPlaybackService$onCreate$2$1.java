package com.vasudev.wtrlab;

import android.media.session.MediaSession;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016"}, d2 = {"com/example/wtrlab/MediaPlaybackService$onCreate$2$1", "Landroid/media/session/MediaSession$Callback;", "onPlay", "", "onPause", "onStop"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MediaPlaybackService$onCreate$2$1 extends MediaSession.Callback {
    MediaPlaybackService$onCreate$2$1() {
    }

    @Override // android.media.session.MediaSession.Callback
    public void onPlay() {
        TtsBridge companion = TtsBridge.INSTANCE.getInstance();
        if (companion != null) {
            companion.jsResume();
        }
    }

    @Override // android.media.session.MediaSession.Callback
    public void onPause() {
        TtsBridge companion = TtsBridge.INSTANCE.getInstance();
        if (companion != null) {
            companion.jsPause();
        }
    }

    @Override // android.media.session.MediaSession.Callback
    public void onStop() {
        TtsBridge companion = TtsBridge.INSTANCE.getInstance();
        if (companion != null) {
            companion.jsCancel();
        }
    }
}
