package com.vasudev.wtrlab;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J,\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016"}, d2 = {"com/example/wtrlab/MainActivity$onCreate$4", "Landroid/webkit/WebChromeClient;", "onPermissionRequest", "", "request", "Landroid/webkit/PermissionRequest;", "onShowFileChooser", "", "view", "Landroid/webkit/WebView;", "callback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "params", "Landroid/webkit/WebChromeClient$FileChooserParams;"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MainActivity$onCreate$4 extends WebChromeClient {
    final MainActivity this$0;

    MainActivity$onCreate$4(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    /** Open popup windows (used by sign-up / login providers) in a dialog. */
    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg) {
        try {
            final android.app.Dialog popupDialog = new android.app.Dialog(
                    view.getContext(), android.R.style.Theme_Material_NoActionBar);
            final WebView popup = new WebView(view.getContext());
            popup.getSettings().setJavaScriptEnabled(true);
            popup.getSettings().setDomStorageEnabled(true);
            popup.getSettings().setDatabaseEnabled(true);
            popup.getSettings().setLoadWithOverviewMode(true);
            popup.getSettings().setUseWideViewPort(true);
            popup.getSettings().setSupportMultipleWindows(false);
            popup.getSettings().setUserAgentString(view.getSettings().getUserAgentString());
            popup.setWebViewClient(new android.webkit.WebViewClient() {
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView popupView, android.webkit.WebResourceRequest req) {
                    try {
                        boolean on = true;
                        try {
                            on = popupView.getContext().getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                                    .getBoolean("adBlock", true);
                        } catch (Exception ignored) {
                        }
                        if (!on) {
                            return false;
                        }
                        String h = req.getUrl().getHost();
                        if (h != null) {
                            String lh = h.toLowerCase(java.util.Locale.ROOT);
                            if (lh.equals(MainActivity.HOST) || lh.endsWith(".wtr-lab.com")
                                    || MainActivity$onCreate$3.isAuthHost(lh)) {
                                return false;
                            }
                        }
                    } catch (Exception ignored) {
                    }
                    // not a login provider -> it is an ad popup: close it
                    try {
                        popupDialog.dismiss();
                    } catch (Exception ignored) {
                    }
                    return true;
                }

                @Override // android.webkit.WebViewClient
                public android.webkit.WebResourceResponse shouldInterceptRequest(WebView popupView,
                                                                                 android.webkit.WebResourceRequest req) {
                    try {
                        String h = req.getUrl().getHost();
                        boolean on = true;
                        try {
                            on = view.getContext().getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                                    .getBoolean("adBlock", true);
                        } catch (Exception ignored) {
                        }
                        if (!on) {
                            return null;
                        }
                        if (h != null) {
                            String lh = h.toLowerCase(java.util.Locale.ROOT);
                            for (String blocked : MainActivity.BLOCKED_HOSTS) {
                                if (lh.equals(blocked) || lh.endsWith("." + blocked)) {
                                    return new android.webkit.WebResourceResponse("text/plain", "utf-8",
                                            new java.io.ByteArrayInputStream(new byte[0]));
                                }
                            }
                        }
                    } catch (Exception ignored) {
                    }
                    return null;
                }
            });
            android.widget.FrameLayout holder = new android.widget.FrameLayout(view.getContext());
            holder.addView(popup, new android.widget.FrameLayout.LayoutParams(-1, -1));
            popupDialog.setContentView(holder);
            popupDialog.setCancelable(true);
            popupDialog.setOnDismissListener(d -> {
                try {
                    popup.destroy();
                } catch (Exception ignored) {
                }
            });
            popupDialog.show();
            android.view.Window win = popupDialog.getWindow();
            if (win != null) {
                win.setLayout(-1, -1);
            }
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(popup);
            resultMsg.sendToTarget();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        this.this$0.runOnUiThread(new MainActivity$onCreate$4$$ExternalSyntheticLambda0(request));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, WebChromeClient.FileChooserParams params) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(params, "params");
        ValueCallback<Uri[]> valueCallback = this.this$0.filePathCallback;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
        }
        this.this$0.filePathCallback = callback;
        try {
            this.this$0.startActivityForResult(params.createIntent(), 101);
            return true;
        } catch (ActivityNotFoundException unused) {
            this.this$0.filePathCallback = null;
            return false;
        }
    }
}
