package com.vasudev.wtrlab;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016"}, d2 = {"com/example/wtrlab/MainActivity$onCreate$3", "Landroid/webkit/WebViewClient;", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "shouldOverrideUrlLoading", "", "onPageStarted", "", "url", "", "favicon", "Landroid/graphics/Bitmap;", "onPageFinished", "onRenderProcessGone", "detail", "Landroid/webkit/RenderProcessGoneDetail;"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MainActivity$onCreate$3 extends WebViewClient {
    final MainActivity this$0;
    private boolean blankChecked;
    private static final java.util.List<String> RECENT_CAPTURED = new java.util.ArrayList<>();

    /** Re-applies the app theme CSS + bionic reading to each loaded page. */
    private static final String THEME_JS = "\n(function(){try{var v=window.__ttsBridge.getVolume();"
            + "if(v<1){var m=document.querySelectorAll('audio,video');"
            + "for(var i=0;i<m.length;i++){try{m[i].volume=v;}catch(e){}}}}catch(e){})();\n"
            + "(function(){try{var t=window.__ttsBridge.getSiteTheme();"
            + "var s=document.getElementById('wtrlab-theme');"
            + "if(t){if(!s){s=document.createElement('style');s.id='wtrlab-theme';"
            + "(document.head||document.documentElement).appendChild(s);}s.textContent=t;}"
            + "else if(s){s.textContent='';}}catch(e){}})();\n"
            + "(function(){try{if(!window.__ttsBridge.isBionic())return;if(window.__wtrBionicDone)return;"
            + "window.__wtrBionicDone=1;var w=document.createTreeWalker(document.body,NodeFilter.SHOW_TEXT,null);"
            + "var ns=[];var n;while(n=w.nextNode()){var p=n.parentNode;if(!p)continue;"
            + "var tag=p.nodeName?p.nodeName.toUpperCase():'';"
            + "if(tag==='SCRIPT'||tag==='STYLE'||tag==='TEXTAREA'||tag==='INPUT'||tag==='CODE'||tag==='B')continue;"
            + "if(n.nodeValue&&n.nodeValue.trim().length>1)ns.push(n);}"
            + "for(var i=0;i<ns.length;i++){var node=ns[i];var str=node.nodeValue;var out='';"
            + "var re=/[A-Za-z\\u00C0-\\u024F\\u0900-\\u097F]+/g;var last=0;var m;"
            + "while((m=re.exec(str))!==null){out+=str.slice(last,m.index);var word=m[0];"
            + "var k=Math.max(1,Math.ceil(word.length*0.45));"
            + "out+='<b>'+word.slice(0,k)+'</b>'+word.slice(k);last=m.index+word.length;}"
            + "out+=str.slice(last);var sp=document.createElement('span');sp.innerHTML=out;"
            + "node.parentNode.replaceChild(sp,node);}}catch(e){}})();\n";

    private static final String READPAGE_JS = "window.__wtrActive = false;"
            + "window.__wtrPara = []; window.__wtrParaIdx = -1;"
            + "window.__wtrFinish = function() { try {"
            + "if (window.__ttsBridge.isAutoAdvance()) { var nx = window.__wtrFindNext();"
            + "if (nx) { window.__ttsBridge.startAutoRead(nx); } } } catch (e) {} };"
            + "window.__wtrReadPage = function() { try {"
            + "try { window.speechSynthesis.cancel(); } catch (e) {}"
            + "var c = document.querySelectorAll('article, main, .chapter-content, .chapter, #chapter, .entry-content, .post-content, .reading-content, .text-content');"
            + "var box = null; var best = 0;"
            + "for (var i = 0; i < c.length; i++) { var t = (c[i].innerText || '').trim(); if (t.length > best) { best = t.length; box = c[i]; } }"
            + "var ps = box ? box.querySelectorAll('p') : document.querySelectorAll('p');"
            + "var list = [];"
            + "for (var j = 0; j < ps.length; j++) { var s = (ps[j].innerText || '').trim(); if (s.length > 25) { list.push(ps[j]); } }"
            + "window.__wtrActive = true; window.__wtrParaIdx = -1;"
            + "var hl = false; try { hl = window.__ttsBridge.isHighlight(); } catch (e) {}"
            + "if (list.length > 1 && hl) { window.__wtrPara = list; window.__wtrNextPara(); return; }"
            + "var text = ''; if (box) { text = (box.innerText || '').trim(); }"
            + "if (text.length < 40) { text = ''; for (var q = 0; q < list.length; q++) { text += (list[q].innerText || '').trim() + '\\n\\n'; } }"
            + "if (text.length < 40 && document.body) { text = (document.body.innerText || '').trim(); }"
            + "if (text.length < 20) { window.__wtrActive = false; return; }"
            + "window.__wtrPara = []; window.__wtrParaIdx = -1;"
            + "var u0 = new window.SpeechSynthesisUtterance(text.slice(0, 200000));"
            + "try { u0.rate = window.__ttsBridge.getReadRate(); } catch (e) {}"
            + "try { var v0 = window.__ttsBridge.getReadVoice(); if (v0 && v0.length) { u0.voice = { voiceURI: v0, name: v0 }; } } catch (e) {}"
            + "u0.onend = function() { window.__wtrActive = false; window.__wtrFinish(); };"
            + "window.speechSynthesis.speak(u0);"
            + "} catch (e) { window.__wtrActive = false; } };"
            + "window.__wtrNextPara = function() { try {"
            + "if (window.__wtrActive !== true) { return; }"
            + "var list = window.__wtrPara || [];"
            + "var prev = window.__wtrParaIdx;"
            + "if (prev >= 0 && prev < list.length) { try { list[prev].style.backgroundColor = list[prev].__wtrBg || ''; } catch (e) {} }"
            + "var i = prev + 1;"
            + "if (i >= list.length) { window.__wtrActive = false; window.__wtrParaIdx = -1; window.__wtrFinish(); return; }"
            + "window.__wtrParaIdx = i;"
            + "var el = list[i];"
            + "try { if (window.__ttsBridge.isHighlight()) { el.__wtrBg = el.style.backgroundColor;"
            + "el.style.transition = 'background-color .25s'; el.style.backgroundColor = 'rgba(255,214,0,0.35)';"
            + "el.scrollIntoView({ block: 'center', behavior: 'smooth' }); } } catch (e) {}"
            + "var text = (el.innerText || '').trim();"
            + "if (!text) { window.__wtrNextPara(); return; }"
            + "var u = new window.SpeechSynthesisUtterance(text);"
            + "try { u.rate = window.__ttsBridge.getReadRate(); } catch (e) {}"
            + "try { var v = window.__ttsBridge.getReadVoice(); if (v && v.length) { u.voice = { voiceURI: v, name: v }; } } catch (e) {}"
            + "u.onend = function() { window.__wtrNextPara(); };"
            + "window.speechSynthesis.speak(u);"
            + "} catch (e) { window.__wtrActive = false; } };"
            + "window.__wtrFindNext = function() { try {"
            + "var a = document.querySelector('a[rel=next]'); if (a && a.href) { return a.href; }"
            + "var links = document.querySelectorAll('a');"
            + "var re = /^(next|next chapter|next page|continue|\u0905\u0917\u0932\u093e|\u4e0b\u4e00)/i;"
            + "for (var i = 0; i < links.length; i++) { var t = (links[i].innerText || '').trim();"
            + "if (t.length > 0 && t.length < 24 && re.test(t) && links[i].href && links[i].href.indexOf('javascript') !== 0) { return links[i].href; } }"
            + "var cn = document.querySelector('a.next, .next-chapter, .next_chapter, .next-page, .btn-next');"
            + "if (cn && cn.href) { return cn.href; }"
            + "} catch (e) {} return ''; };"
            + "window.__wtrGetPageText = function() { try {"
            + "var c = document.querySelectorAll('article, main, .chapter-content, .chapter, #chapter, .entry-content, .post-content, .reading-content, .text-content');"
            + "var box = null; var best = 0;"
            + "for (var i = 0; i < c.length; i++) { var t = (c[i].innerText || '').trim(); if (t.length > best) { best = t.length; box = c[i]; } }"
            + "var text = ''; if (box) { text = (box.innerText || '').trim(); }"
            + "if (text.length < 60) { var ps = document.querySelectorAll('p'); var buf = '';"
            + "for (var j = 0; j < ps.length; j++) { var q = (ps[j].innerText || '').trim(); if (q.length > 25) { buf += q + '\\n\\n'; } }"
            + "if (buf.length > text.length) { text = buf; } }"
            + "if (text.length < 60 && document.body) { text = (document.body.innerText || '').trim(); }"
            + "return text.slice(0, 60000);"
            + "} catch (e) { return ''; } };";

    MainActivity$onCreate$3(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(request, "request");
        String host = request.getUrl().getHost();
        if (host == null) {
            return null;
        }
        String lowerCase = host.toLowerCase(Locale.ROOT);
        // Block ONLY the listed ad/tracker hosts (the user can switch this off);
        // every other request, including the page itself, loads normally.
        boolean adBlockOn = true;
        try {
            adBlockOn = this.this$0.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                    .getBoolean("adBlock", false);
        } catch (Exception ignored) {
        }
        // domains added from the studio / config file
        try {
            String extra = this.this$0.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE)
                    .getString("customBlocked", "");
            if (adBlockOn && extra != null && extra.length() > 0) {
                for (String d : extra.split(",")) {
                    String dd = d.trim().toLowerCase(Locale.ROOT);
                    if (dd.length() > 2 && (lowerCase.equals(dd) || lowerCase.endsWith("." + dd))) {
                        return new WebResourceResponse("text/plain", "utf-8",
                                new ByteArrayInputStream(new byte[0]));
                    }
                }
            }
        } catch (Exception ignored) {
        }
        if (adBlockOn) {
            for (String blocked : MainActivity.BLOCKED_HOSTS) {
                if (lowerCase.equals(blocked) || lowerCase.endsWith("." + blocked)) {
                    return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream(new byte[0]));
                }
            }
        }
        // ---- offline audio capture: save the site's own TTS audio chunks ----
        try {
            android.content.SharedPreferences prefs = this.this$0
                    .getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
            if (prefs.getBoolean("capture", false)) {
                java.util.Map<String, String> headers = request.getRequestHeaders();
                String accept = headers == null ? null : headers.get("Accept");
                String range = headers == null ? null : headers.get("Range");
                String url = request.getUrl().toString();
                boolean seen;
                synchronized (RECENT_CAPTURED) {
                    seen = RECENT_CAPTURED.contains(url);
                    if (!seen) {
                        RECENT_CAPTURED.add(url);
                        if (RECENT_CAPTURED.size() > 60) {
                            RECENT_CAPTURED.remove(0);
                        }
                    }
                }
                if (!seen && range == null && AudioStore.looksLikeAudio(url, accept)) {
                    Object[] got = fetchAudio(url);
                    if (got != null && got[1] != null && ((byte[]) got[1]).length > 1024) {
                        TtsBridge bridge = this.this$0.ttsBridge;
                        String pageUrl = bridge == null ? null : bridge.currentPageUrl();
                        String title = bridge == null ? null : bridge.currentPageTitle();
                        String key = AudioStore.chapterKey(pageUrl, title);
                        AudioStore.saveChunk(this.this$0, key, AudioStore.extensionOf(url), (byte[]) got[1]);
                        AudioStore.enforceLimit(this.this$0, prefs.getInt("audioLimit", 500) * 1048576L);
                        return new WebResourceResponse((String) got[0], null,
                                new ByteArrayInputStream((byte[]) got[1]));
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /** Fetch an audio resource ourselves so we can keep a copy of it. */
    private static Object[] fetchAudio(String url) {
        java.net.HttpURLConnection conn = null;
        java.io.InputStream in = null;
        try {
            conn = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 15) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36");
            String cookie = android.webkit.CookieManager.getInstance().getCookie(url);
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }
            int code = conn.getResponseCode();
            if (code < 200 || code >= 400) {
                return null;
            }
            String type = conn.getContentType();
            in = conn.getInputStream();
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            byte[] buf = new byte[16384];
            int n;
            long total = 0L;
            while ((n = in.read(buf)) > 0) {
                bos.write(buf, 0, n);
                total += n;
                if (total > 8388608L) {
                    break;
                }
            }
            return new Object[]{type == null ? "audio/mpeg" : type, bos.toByteArray()};
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception ignored) {
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(request, "request");
        Uri url = request.getUrl();
        String scheme = url.getScheme();
        if (scheme == null) {
            return false;
        }
        if (!Intrinsics.areEqual(scheme, "http") && !Intrinsics.areEqual(scheme, "https")) {
            try {
                this.this$0.startActivity(new Intent("android.intent.action.VIEW", url));
            } catch (ActivityNotFoundException unused) {
            }
            return true;
        }
        String host = url.getHost();
        return (host == null || Intrinsics.areEqual(host, MainActivity.HOST) || host.endsWith(".wtr-lab.com") || !request.isForMainFrame() || isAuthHost(host)) ? false : true;
    }

    static boolean isAuthHost(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String[] strArr = {"accounts.google.com", "www.google.com", "googleapis.com", "googleusercontent.com", "gstatic.com", "recaptcha.net", "discord.com", "facebook.com", "appleid.apple.com", "github.com", "challenges.cloudflare.com", "hcaptcha.com"};
        for (int i = 0; i < 12; i++) {
            String str2 = strArr[i];
            if (lowerCase.equals(str2)) {
                return true;
            }
            if (lowerCase.endsWith("." + str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onPageStarted(view, url, favicon);
        TtsBridge ttsBridge = this.this$0.ttsBridge;
        if (ttsBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsBridge");
            ttsBridge = null;
        }
        ttsBridge.updateUrl(url);
        view.evaluateJavascript("\n(function() {\n  if (window.__nativeTtsInstalled) return;\n  window.__nativeTtsInstalled = true;\n  try { window.__wtrPerf = window.__ttsBridge.isPerfMode(); } catch (e) {}\n\n  // Capture page errors so the app can show WHY a page came up blank\n  try {\n    if (!window.__wtrErrHooked) {\n      window.__wtrErrHooked = true;\n      window.addEventListener('error', function(e) { try { window.__ttsBridge.logError('ERR ' + (e.message || '') + ' @' + String(e.filename || '').slice(-40) + ':' + (e.lineno || 0)); } catch (x) {} });\n      window.addEventListener('unhandledrejection', function(e) { try { var r = e.reason; window.__ttsBridge.logError('REJ ' + ((r && (r.message || r)) || '')); } catch (x) {} });\n    }\n  } catch (x) {}\n\n  function NativeTtsUtterance(text) {\n    this.text = (text === undefined || text === null) ? '' : String(text);\n    this.lang = 'en-US';\n    this.rate = 1;\n    this.pitch = 1;\n    this.volume = 1;\n    this.voice = null;\n    this.onstart = null;\n    this.onend = null;\n    this.onerror = null;\n    this.onboundary = null;\n    this.onmark = null;\n    this.onpause = null;\n    this.onresume = null;\n    this.__id = 'u' + (++NativeTtsUtterance.__seq);\n  }\n  NativeTtsUtterance.__seq = 0;\n  window.SpeechSynthesisUtterance = NativeTtsUtterance;\n\n  var active = {};\n  var queue = [];\n\n  // --- reliability: split long chunks, chain them, recover from stalls\n  var pieces = {};\n  var lastEvent = Date.now();\n  function splitText(s) {\n    s = String(s === undefined || s === null ? \"\" : s);\n    var segs = [];\n    var parts = s.split(/([.!?;:\\u0964\\u3002])\\s+/);\n    for (var j = 0; j < parts.length; j += 2) {\n      var seg = parts[j] + (parts[j + 1] || \"\");\n      if (seg) segs.push(seg);\n    }\n    var out = [];\n    var buf = \"\";\n    for (var i = 0; i < segs.length; i++) {\n      var p = segs[i];\n      if (buf.length && (buf.length + p.length) > 380) { out.push(buf); buf = p; }\n      else { buf = buf ? (buf + \" \" + p) : p; }\n      while (buf.length > 600) { out.push(buf.slice(0, 600)); buf = buf.slice(600); }\n    }\n    if (buf.length) out.push(buf);\n    if (!out.length) out.push(s);\n    return out;\n  }\n  function nativeSpeak(u, piece) {\n    lastEvent = Date.now();\n    window.__ttsBridge.speak(piece, u.lang || \"en-US\",\n      Number(u.rate) || 1, Number(u.pitch) || 1, u.__id,\n      (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : \"\");\n  }\n\n  // Inaudible audio that keeps Chromium from freezing this hidden page: a\n  // WebView that is actively producing audio keeps running its JavaScript\n  // in the background, so the site's TTS player keeps sending the next\n  // chunk. The volume is 0.001 — far below audible, but not digital silence.\n  var keepAliveCtx = null;\n  var keepAliveEl = null;\n  function startKeepAlive() {\n    try {\n      \n      if (!keepAliveCtx) {\n        var AC = window.AudioContext || window.webkitAudioContext;\n        if (!AC) return;\n        keepAliveCtx = new AC();\n        var osc = keepAliveCtx.createOscillator();\n        var gain = keepAliveCtx.createGain();\n        gain.gain.value = 0.001;\n        osc.connect(gain);\n        gain.connect(keepAliveCtx.destination);\n        osc.start();\n      } else if (keepAliveCtx.state === 'suspended') {\n        keepAliveCtx.resume();\n      }\n      if (false) {\n        keepAliveEl = document.createElement(\"audio\");\n        keepAliveEl.setAttribute(\"playsinline\", \"\");\n        keepAliveEl.loop = true;\n        keepAliveEl.volume = 0.03;\n        keepAliveEl.src = 'data:audio/wav;base64,UklGRsQPAABXQVZFZm10IBAAAAABAAEAQB8AAEAfAAABAAgAZGF0YaAPAACAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA';\n        try { document.documentElement.appendChild(keepAliveEl); } catch (e) {}\n      }\n      if (keepAliveEl && keepAliveEl.paused) {\n        var pr = keepAliveEl.play();\n        if (pr && pr.catch) pr.catch(function () {});\n      }\n    } catch (e) {}\n  }\n  function suspendKeepAlive() {\n    try { if (keepAliveCtx && keepAliveCtx.state === 'running') keepAliveCtx.suspend(); } catch (e) {}\n  }\n  var synth = {\n    speaking: false,\n    pending: false,\n    paused: false,\n    onvoiceschanged: null,\n    onstart: null,\n    onend: null,\n    onerror: null,\n    getVoices: function() {\n      try {\n        var raw = window.__ttsBridge.getVoicesJson();\n        var list = JSON.parse(raw);\n        if (list && list.length) return list;\n      } catch (e) {}\n      return [{\n        voiceURI: 'Android System TTS',\n        name: 'Android System TTS',\n        lang: 'en-US',\n        localService: true,\n        default: true\n      }];\n    },\n    speak: function(u) {\n      if (!u || !(u instanceof NativeTtsUtterance)) {\n        u = new NativeTtsUtterance(String(u && u.text));\n      }\n      // While paused, hold new utterances (the site keeps sending the next\n      // chunk after each onend); resume() plays the queued chunk.\n      if (synth.paused) {\n        queue.push(u);\n        synth.pending = true;\n        return;\n      }\n      startKeepAlive();\n      active[u.__id] = u;\n      synth.speaking = true;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      if (window.__ttsBridge.isSimpleTts && window.__ttsBridge.isSimpleTts()) {\n        try { window.__wtrLast = { text: u.text, lang: u.lang || 'en-US', rate: Number(u.rate) || 1, pitch: Number(u.pitch) || 1, id: u.__id, voice: (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : '' }; try { window.__ttsBridge.logEvent('speak ' + String(u.text).slice(0, 36)); } catch (e) {} window.__ttsBridge.speak(u.text, u.lang || 'en-US', Number(u.rate) || 1, Number(u.pitch) || 1, u.__id, (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : ''); return; } catch (e) {}\n      }\n      var list = splitText(u.text);\n      pieces[u.__id] = { list: list, idx: 0, u: u, retried: false };\n      try {\n        nativeSpeak(u, list[0]);\n      } catch (e) {\n        synth.speaking = false;\n        delete pieces[u.__id];\n        if (u.onerror) u.onerror({ type: 'error', error: 'native-tts-unavailable' });\n        if (synth.onerror) synth.onerror({ type: 'error', error: 'native-tts-unavailable' });\n      }\n    },\n    cancel: function() {\n      suspendKeepAlive();\n      try { window.__ttsBridge.cancel(); } catch (e) {}\n      synth.speaking = false;\n      synth.pending = false;\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"stopped\"); } catch (e) {}\n      queue = [];\n      pieces = {};\n      for (var k in active) {\n        var u = active[k];\n        window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      }\n      active = {};\n    },\n    pause: function() {\n      synth.paused = true;\n      synth.speaking = false;\n      try { window.__ttsBridge.setState(\"paused\"); } catch (e) {}\n      try { window.__ttsBridge.pause(); } catch (e) {}\n    },\n    resume: function() {\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      startKeepAlive();\n      if (queue.length) {\n        var q = queue; queue = [];\n        synth.pending = false;\n        for (var i = 0; i < q.length; i++) synth.speak(q[i]);\n      } else {\n        try { window.__ttsBridge.resume(); } catch (e) {}\n      }\n    },\n    addEventListener: function(ev, fn) {\n      if (ev === 'voiceschanged') this.onvoiceschanged = fn;\n    },\n    removeEventListener: function() {}\n  };\n  window.speechSynthesis = synth;\n\n  // Native engine events -> utterance callbacks\n  window.__ttsEvent = function(type, id, reason) {\n    lastEvent = Date.now(); try { window.__ttsBridge.logEvent('event ' + type + ' ' + (reason || '')); } catch (e) {}\n    var u = active[id];\n    var ps = pieces[id];\n    if (type === 'start') {\n      if (u && u.onstart) u.onstart({ type: 'start' });\n      if (synth.onstart) synth.onstart({ type: 'start' });\n    } else if (type === 'end') {\n      if (ps && ps.idx + 1 < ps.list.length) {\n        ps.idx++;\n        try { nativeSpeak(ps.u, ps.list[ps.idx]); return; } catch (e) {}\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      if (synth.onend) synth.onend({ type: 'end' });\n    } else if (type === 'error') {\n      if (ps && !ps.retried) {\n        ps.retried = true;\n        setTimeout(function () { try { nativeSpeak(ps.u, ps.list[ps.idx]); } catch (e) {} }, 500);\n        return;\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      var err = { type: 'error', error: reason || 'unknown' };\n      if (u && u.onerror) u.onerror(err);\n      if (synth.onerror) synth.onerror(err);\n    }\n  };\n\n  // auto-scroll: keeps the page moving while reading\n  setInterval(function() {\n    try {\n      var a = window.__wtrAutoScroll;\n      if (a && a.on && a.px > 0) { window.scrollBy(0, a.px); }\n    } catch (e) {}\n  }, 250);\n\n  // stall watchdog: if the engine goes quiet mid-chapter, re-speak the piece\n  setInterval(function() {\n    try {\n      if (!synth.speaking) return;\n      if (Date.now() - lastEvent < 20000) return; try { window.__ttsBridge.logEvent('stalled - no auto replay'); } catch (e) {}\n      for (var k in pieces) {\n        var ps = pieces[k];\n        if (ps && ps.list && ps.list.length > ps.idx) {\n          nativeSpeak(ps.u, ps.list[ps.idx]);\n          return;\n        }\n      }\n    } catch (e) {}\n  }, 5000);\n\n  setTimeout(function() {\n    if (synth.onvoiceschanged) {\n      try { synth.onvoiceschanged({ type: 'voiceschanged' }); } catch (e) {}\n    }\n  }, 50);\n})();\n", null);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        super.onPageFinished(view, url);
        try {
            TtsBridge bridgeForTitle = this.this$0.ttsBridge;
            if (bridgeForTitle != null) {
                bridgeForTitle.updateTitle(view.getTitle());
            }
        } catch (Exception ignored) {
        }
        try {
            view.evaluateJavascript(THEME_JS, null);
        } catch (Exception ignored) {
        }
        try {
            view.evaluateJavascript(READPAGE_JS, null);
        } catch (Exception ignored) {
        }
        final TtsBridge autoBridge = this.this$0.ttsBridge;
        try {
            if (autoBridge != null && autoBridge.consumePendingAutoRead()) {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            autoBridge.readPageAloud();
                        } catch (Exception ignored) {
                        }
                    }
                }, 2500L);
            }
        } catch (Exception ignored) {
        }
        // blank-page self-heal disabled: reloading mid-chapter cut playback
        TtsBridge ttsBridge = this.this$0.ttsBridge;
        if (ttsBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsBridge");
            ttsBridge = null;
        }
        ttsBridge.updateUrl(url);
        view.evaluateJavascript("\n(function() {\n  if (window.__nativeTtsInstalled) return;\n  window.__nativeTtsInstalled = true;\n  try { window.__wtrPerf = window.__ttsBridge.isPerfMode(); } catch (e) {}\n\n  // Capture page errors so the app can show WHY a page came up blank\n  try {\n    if (!window.__wtrErrHooked) {\n      window.__wtrErrHooked = true;\n      window.addEventListener('error', function(e) { try { window.__ttsBridge.logError('ERR ' + (e.message || '') + ' @' + String(e.filename || '').slice(-40) + ':' + (e.lineno || 0)); } catch (x) {} });\n      window.addEventListener('unhandledrejection', function(e) { try { var r = e.reason; window.__ttsBridge.logError('REJ ' + ((r && (r.message || r)) || '')); } catch (x) {} });\n    }\n  } catch (x) {}\n\n  function NativeTtsUtterance(text) {\n    this.text = (text === undefined || text === null) ? '' : String(text);\n    this.lang = 'en-US';\n    this.rate = 1;\n    this.pitch = 1;\n    this.volume = 1;\n    this.voice = null;\n    this.onstart = null;\n    this.onend = null;\n    this.onerror = null;\n    this.onboundary = null;\n    this.onmark = null;\n    this.onpause = null;\n    this.onresume = null;\n    this.__id = 'u' + (++NativeTtsUtterance.__seq);\n  }\n  NativeTtsUtterance.__seq = 0;\n  window.SpeechSynthesisUtterance = NativeTtsUtterance;\n\n  var active = {};\n  var queue = [];\n\n  // --- reliability: split long chunks, chain them, recover from stalls\n  var pieces = {};\n  var lastEvent = Date.now();\n  function splitText(s) {\n    s = String(s === undefined || s === null ? \"\" : s);\n    var segs = [];\n    var parts = s.split(/([.!?;:\\u0964\\u3002])\\s+/);\n    for (var j = 0; j < parts.length; j += 2) {\n      var seg = parts[j] + (parts[j + 1] || \"\");\n      if (seg) segs.push(seg);\n    }\n    var out = [];\n    var buf = \"\";\n    for (var i = 0; i < segs.length; i++) {\n      var p = segs[i];\n      if (buf.length && (buf.length + p.length) > 380) { out.push(buf); buf = p; }\n      else { buf = buf ? (buf + \" \" + p) : p; }\n      while (buf.length > 600) { out.push(buf.slice(0, 600)); buf = buf.slice(600); }\n    }\n    if (buf.length) out.push(buf);\n    if (!out.length) out.push(s);\n    return out;\n  }\n  function nativeSpeak(u, piece) {\n    lastEvent = Date.now();\n    window.__ttsBridge.speak(piece, u.lang || \"en-US\",\n      Number(u.rate) || 1, Number(u.pitch) || 1, u.__id,\n      (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : \"\");\n  }\n\n  // Inaudible audio that keeps Chromium from freezing this hidden page: a\n  // WebView that is actively producing audio keeps running its JavaScript\n  // in the background, so the site's TTS player keeps sending the next\n  // chunk. The volume is 0.001 — far below audible, but not digital silence.\n  var keepAliveCtx = null;\n  var keepAliveEl = null;\n  function startKeepAlive() {\n    try {\n      \n      if (!keepAliveCtx) {\n        var AC = window.AudioContext || window.webkitAudioContext;\n        if (!AC) return;\n        keepAliveCtx = new AC();\n        var osc = keepAliveCtx.createOscillator();\n        var gain = keepAliveCtx.createGain();\n        gain.gain.value = 0.001;\n        osc.connect(gain);\n        gain.connect(keepAliveCtx.destination);\n        osc.start();\n      } else if (keepAliveCtx.state === 'suspended') {\n        keepAliveCtx.resume();\n      }\n      if (false) {\n        keepAliveEl = document.createElement(\"audio\");\n        keepAliveEl.setAttribute(\"playsinline\", \"\");\n        keepAliveEl.loop = true;\n        keepAliveEl.volume = 0.03;\n        keepAliveEl.src = 'data:audio/wav;base64,UklGRsQPAABXQVZFZm10IBAAAAABAAEAQB8AAEAfAAABAAgAZGF0YaAPAACAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA';\n        try { document.documentElement.appendChild(keepAliveEl); } catch (e) {}\n      }\n      if (keepAliveEl && keepAliveEl.paused) {\n        var pr = keepAliveEl.play();\n        if (pr && pr.catch) pr.catch(function () {});\n      }\n    } catch (e) {}\n  }\n  function suspendKeepAlive() {\n    try { if (keepAliveCtx && keepAliveCtx.state === 'running') keepAliveCtx.suspend(); } catch (e) {}\n  }\n  var synth = {\n    speaking: false,\n    pending: false,\n    paused: false,\n    onvoiceschanged: null,\n    onstart: null,\n    onend: null,\n    onerror: null,\n    getVoices: function() {\n      try {\n        var raw = window.__ttsBridge.getVoicesJson();\n        var list = JSON.parse(raw);\n        if (list && list.length) return list;\n      } catch (e) {}\n      return [{\n        voiceURI: 'Android System TTS',\n        name: 'Android System TTS',\n        lang: 'en-US',\n        localService: true,\n        default: true\n      }];\n    },\n    speak: function(u) {\n      if (!u || !(u instanceof NativeTtsUtterance)) {\n        u = new NativeTtsUtterance(String(u && u.text));\n      }\n      // While paused, hold new utterances (the site keeps sending the next\n      // chunk after each onend); resume() plays the queued chunk.\n      if (synth.paused) {\n        queue.push(u);\n        synth.pending = true;\n        return;\n      }\n      startKeepAlive();\n      active[u.__id] = u;\n      synth.speaking = true;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      if (window.__ttsBridge.isSimpleTts && window.__ttsBridge.isSimpleTts()) {\n        try { window.__wtrLast = { text: u.text, lang: u.lang || 'en-US', rate: Number(u.rate) || 1, pitch: Number(u.pitch) || 1, id: u.__id, voice: (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : '' }; try { window.__ttsBridge.logEvent('speak ' + String(u.text).slice(0, 36)); } catch (e) {} window.__ttsBridge.speak(u.text, u.lang || 'en-US', Number(u.rate) || 1, Number(u.pitch) || 1, u.__id, (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : ''); return; } catch (e) {}\n      }\n      var list = splitText(u.text);\n      pieces[u.__id] = { list: list, idx: 0, u: u, retried: false };\n      try {\n        nativeSpeak(u, list[0]);\n      } catch (e) {\n        synth.speaking = false;\n        delete pieces[u.__id];\n        if (u.onerror) u.onerror({ type: 'error', error: 'native-tts-unavailable' });\n        if (synth.onerror) synth.onerror({ type: 'error', error: 'native-tts-unavailable' });\n      }\n    },\n    cancel: function() {\n      suspendKeepAlive();\n      try { window.__ttsBridge.cancel(); } catch (e) {}\n      synth.speaking = false;\n      synth.pending = false;\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"stopped\"); } catch (e) {}\n      queue = [];\n      pieces = {};\n      for (var k in active) {\n        var u = active[k];\n        window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      }\n      active = {};\n    },\n    pause: function() {\n      synth.paused = true;\n      synth.speaking = false;\n      try { window.__ttsBridge.setState(\"paused\"); } catch (e) {}\n      try { window.__ttsBridge.pause(); } catch (e) {}\n    },\n    resume: function() {\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      startKeepAlive();\n      if (queue.length) {\n        var q = queue; queue = [];\n        synth.pending = false;\n        for (var i = 0; i < q.length; i++) synth.speak(q[i]);\n      } else {\n        try { window.__ttsBridge.resume(); } catch (e) {}\n      }\n    },\n    addEventListener: function(ev, fn) {\n      if (ev === 'voiceschanged') this.onvoiceschanged = fn;\n    },\n    removeEventListener: function() {}\n  };\n  window.speechSynthesis = synth;\n\n  // Native engine events -> utterance callbacks\n  window.__ttsEvent = function(type, id, reason) {\n    lastEvent = Date.now(); try { window.__ttsBridge.logEvent('event ' + type + ' ' + (reason || '')); } catch (e) {}\n    var u = active[id];\n    var ps = pieces[id];\n    if (type === 'start') {\n      if (u && u.onstart) u.onstart({ type: 'start' });\n      if (synth.onstart) synth.onstart({ type: 'start' });\n    } else if (type === 'end') {\n      if (ps && ps.idx + 1 < ps.list.length) {\n        ps.idx++;\n        try { nativeSpeak(ps.u, ps.list[ps.idx]); return; } catch (e) {}\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      if (synth.onend) synth.onend({ type: 'end' });\n    } else if (type === 'error') {\n      if (ps && !ps.retried) {\n        ps.retried = true;\n        setTimeout(function () { try { nativeSpeak(ps.u, ps.list[ps.idx]); } catch (e) {} }, 500);\n        return;\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      var err = { type: 'error', error: reason || 'unknown' };\n      if (u && u.onerror) u.onerror(err);\n      if (synth.onerror) synth.onerror(err);\n    }\n  };\n\n  // auto-scroll: keeps the page moving while reading\n  setInterval(function() {\n    try {\n      var a = window.__wtrAutoScroll;\n      if (a && a.on && a.px > 0) { window.scrollBy(0, a.px); }\n    } catch (e) {}\n  }, 250);\n\n  // stall watchdog: if the engine goes quiet mid-chapter, re-speak the piece\n  setInterval(function() {\n    try {\n      if (!synth.speaking) return;\n      if (Date.now() - lastEvent < 20000) return; try { window.__ttsBridge.logEvent('stalled - no auto replay'); } catch (e) {}\n      for (var k in pieces) {\n        var ps = pieces[k];\n        if (ps && ps.list && ps.list.length > ps.idx) {\n          nativeSpeak(ps.u, ps.list[ps.idx]);\n          return;\n        }\n      }\n    } catch (e) {}\n  }, 5000);\n\n  setTimeout(function() {\n    if (synth.onvoiceschanged) {\n      try { synth.onvoiceschanged({ type: 'voiceschanged' }); } catch (e) {}\n    }\n  }, 50);\n})();\n", null);
    }

    @Override // android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(detail, "detail");
        // The renderer died. A corrupted cached bundle is the usual cause of
        // a crash loop, so drop the cache before restarting the activity.
        try {
            view.clearCache(true);
        } catch (Exception ignored) {
        }
        this.this$0.recreate();
        return true;
    }

    /** Server hiccups (nginx 503 etc.): show a friendly page that retries
     *  by itself, so a temporary outage heals without the user doing anything. */
    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        if (!request.isForMainFrame()) {
            return;
        }
        int code = 0;
        try {
            code = errorResponse.getStatusCode();
        } catch (Exception ignored) {
        }
        if (code >= 500) {
            showServerBusyPage(view, code);
        }
    }

    private void showServerBusyPage(WebView view, int code) {
        String html = "<html><head><meta name='viewport' content='width=device-width,initial-scale=1'>"
                + "<style>body{background:#0F172A;color:#E2E8F0;font-family:sans-serif;text-align:center;"
                + "padding-top:32vh;margin:0}h2{font-size:22px;margin:0 0 8px}p{color:#94A3B8;font-size:14px;margin:0 0 6px}"
                + "b{color:#FBBF24;font-size:28px}button{margin-top:20px;font-size:16px;padding:14px 36px;border-radius:28px;"
                + "border:0;background:#F59E0B;color:#1E293B;font-weight:bold}</style></head>"
                + "<body><h2>&#9203; Server Temporarily Unavailable</h2>"
                + "<p>wtr-lab is not responding right now (error " + code + ").</p>"
                + "<p>Retrying automatically in <b id='cd'>8</b> seconds</p>"
                + "<button onclick=\"location.href='https://wtr-lab.com/'\">Retry Now</button>"
                + "<script>var n=8;setInterval(function(){n--;var e=document.getElementById('cd');if(e)e.textContent=n;if(n<=0){location.href='https://wtr-lab.com/';}},1000);</script>"
                + "</body></html>";
        view.loadDataWithBaseURL("https://wtr-lab.com/", html, "text/html", "utf-8", "https://wtr-lab.com/");
    }

    /** Friendly fallback page showing the REAL error reason + recovery actions. */
    private int autoRetries;
    private long lastRetryAt;

    private static boolean isNetworkError(int code) {
        return code == android.webkit.WebViewClient.ERROR_HOST_LOOKUP
                || code == android.webkit.WebViewClient.ERROR_CONNECT
                || code == android.webkit.WebViewClient.ERROR_IO
                || code == android.webkit.WebViewClient.ERROR_TIMEOUT;
    }

    private void showLoadErrorPage(WebView view, String reason) {
        showLoadErrorPage(view, reason, false);
    }

    private void showLoadErrorPage(WebView view, String reason, boolean autoRetrying) {
        String safeReason = reason == null ? "" : reason.replace("<", "").replace(">", "");
        try {
            view.getContext().getSharedPreferences("app", android.content.Context.MODE_PRIVATE)
                    .edit().putBoolean("lastLoadFailed", true).apply();
        } catch (Exception ignored) {
        }
        String html = "<html><head><meta name='viewport' content='width=device-width,initial-scale=1'>"
                + "<style>body{background:#0F172A;color:#E2E8F0;font-family:sans-serif;text-align:center;"
                + "padding-top:26vh;margin:0}h2{font-size:22px;margin:0 0 8px}p{color:#94A3B8;font-size:14px;margin:0 0 18px}"
                + "code{display:block;color:#FBBF24;font-size:12px;word-break:break-all;padding:0 20px;margin-bottom:22px}"
                + "button{display:block;margin:10px auto;font-size:16px;padding:14px 36px;border-radius:28px;border:0;"
                + "background:#F59E0B;color:#1E293B;font-weight:bold}button.alt{background:#334155;color:#E2E8F0}</style></head>"
                + "<body><h2>&#9888; Unable to Load Page</h2>"
                + "<p>" + (autoRetrying ? "Retrying automatically..." : "Please check your internet connection and try again.") + "</p>"
                + "<code>" + safeReason + "</code>"
                + "<button onclick=\"location.href='https://wtr-lab.com/'\">Retry</button>"
                + "<button class='alt' onclick=\"location.href='https://www.wtr-lab.com/'\">Try www.wtr-lab.com</button>"
                + "<button class='alt' onclick=\"try{window.__ttsBridge.clearAndReload()}catch(e){location.reload()}\">Clear Cache &amp; Reload</button>"
                + "<button class='alt' onclick=\"try{window.__ttsBridge.openExternal('https://wtr-lab.com/')}catch(e){}\">Open in Browser</button>"
                + "</body></html>";
        view.loadDataWithBaseURL("https://wtr-lab.com/", html, "text/html", "utf-8", "https://wtr-lab.com/");
    }

    /** Some broken cached bundles finish "loading" but paint nothing at all
     *  (a black screen). After the first load, check once whether the page
     *  has any text and offer the recovery page if it is empty. */
    private void scheduleBlankCheck(final WebView view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.evaluateJavascript(
                        "(function(){try{var b=document.body;var t=b?(b.innerText||''):'';var h=document.documentElement?(document.documentElement.outerHTML||''):'';var r=document.getElementById('root')||document.getElementById('app')||document.getElementById('__next');return JSON.stringify({txt:t.replace(/\\s/g,'').length,html:h.length,title:document.title||'-',url:location.href||'-',root:r?1:0});}catch(e){return 'ERR:'+e.message;}})()",
                        new android.webkit.ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String value) {
                                if (value == null) {
                                    return;
                                }
                                String v = value.replace("\\\"", "\"");
                                if (v.contains("\"txt\":0")) {
                                    showLoadErrorPage(view, "The page loaded empty. " + (v.length() > 200 ? v.substring(0, 200) : v));
                                }
                            }
                        });
            }
        }, 5000);
    }

    /** Black-screen fix: if the main page fails to load, show a friendly
     *  error + Retry page instead of an empty dark WebView. */
    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, WebResourceRequest request, android.webkit.WebResourceError error) {
        if (request.isForMainFrame()) {
            String reason;
            int code = -1;
            try {
                code = error.getErrorCode();
                reason = error.getDescription() + " (" + code + ")";
            } catch (Exception e) {
                reason = "load error";
            }
            // A flaky network or a DNS hiccup: retry a few times by itself
            // before bothering the user (max 3 tries per minute).
            if (System.currentTimeMillis() - lastRetryAt > 60000L) {
                autoRetries = 0;
            }
            if (autoRetries < 3 && isNetworkError(code)) {
                autoRetries++;
                lastRetryAt = System.currentTimeMillis();
                final WebView v = view;
                final String url = request.getUrl() == null
                        ? "https://wtr-lab.com/" : request.getUrl().toString();
                showLoadErrorPage(view, reason, true);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            v.loadUrl(url);
                        } catch (Exception ignored) {
                        }
                    }
                }, 6000L);
                return;
            }
            showLoadErrorPage(view, reason);
        }
    }
}
