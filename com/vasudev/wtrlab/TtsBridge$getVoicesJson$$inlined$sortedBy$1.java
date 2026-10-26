package com.vasudev.wtrlab;

import android.speech.tts.Voice;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* loaded from: classes.dex */
public final class TtsBridge$getVoicesJson$$inlined$sortedBy$1 implements Comparator<Object> {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt.compareValues(Boolean.valueOf(((Voice) obj).isNetworkConnectionRequired()), Boolean.valueOf(((Voice) obj2).isNetworkConnectionRequired()));
    }
}
