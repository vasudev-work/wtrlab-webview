package com.vasudev.wtrlab;

import android.webkit.PermissionRequest;

/* loaded from: classes.dex */
public final class MainActivity$onCreate$4$$ExternalSyntheticLambda0 implements Runnable {
    public final PermissionRequest f$0;

    public MainActivity$onCreate$4$$ExternalSyntheticLambda0(PermissionRequest permissionRequest) {
        this.f$0 = permissionRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.grant(this.f$0.getResources());
    }
}
