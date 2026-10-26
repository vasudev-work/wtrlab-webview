package com.vasudev.wtrlab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Insets;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0015¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0014¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0004H\u0014¢\u0006\u0004\b\u0014\u0010\bJ\u000f\u0010\u0015\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0015\u0010\u0003J\u000f\u0010\u0016\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0016\u0010\u0003J\u000f\u0010\u0017\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0017\u0010\u0003J\u000f\u0010\u0018\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0018\u0010\u0003R$\u0010\u001c\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#"}, d2 = {"Lcom/example/wtrlab/MainActivity;", "Landroid/app/Activity;", "<init>", "()V", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "(Landroid/os/Bundle;)V", "Landroid/content/Intent;", "intent", "onNewIntent", "(Landroid/content/Intent;)V", "", "requestCode", "resultCode", "data", "onActivityResult", "(IILandroid/content/Intent;)V", "outState", "onSaveInstanceState", "onBackPressed", "onResume", "onPause", "onDestroy", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "filePathCallback", "Landroid/webkit/ValueCallback;", "Lcom/example/wtrlab/TtsBridge;", "ttsBridge", "Lcom/example/wtrlab/TtsBridge;", "Landroid/webkit/WebView;", "webView", "Landroid/webkit/WebView;", "Companion"}, k = 1, mv = {2, 0, 0})
/* loaded from: classes.dex */
public final class MainActivity extends Activity {
    static final List<String> BLOCKED_HOSTS = Arrays.asList("doubleclick.net", "googlesyndication.com", "googleadservices.com", "adservice.google.com", "adnxs.com", "amazon-adsystem.com", "taboola.com", "outbrain.com", "popads.net", "moatads.com", "scorecardresearch.com", "quantserve.com", "criteo.com", "casalemedia.com", "pubmatic.com", "rubiconproject.com", "openx.net", "smartadserver.com", "teads.tv", "media.net", "mgid.com", "propellerads.com", "adsterra.com", "adroll.com", "smaato.com", "sharethrough.com", "google-analytics.com", "googletagmanager.com",
                "monetag.com", "pubfuture.com", "pubrev.io", "adspyglass.com",
                "adcash.com", "clickadu.com", "hilltopads.net", "popcash.net",
                "exoclick.com", "juicyads.com", "bidvertiser.com", "adpushup.com",
                "infolinks.com", "revcontent.com", "zergnet.com", "yieldmo.com",
                "vidoomy.com", "onclickalgo.com", "onclickmax.com", "adnium.com",
                "pemsrv.com", "profitableratecpm.com", "highperformancecpm.com",
                "ouo.io", "shrinkme.io");
    public static final String HOME_URL = "https://wtr-lab.com/";
    public static final String HOST = "wtr-lab.com";
    private static final int REQ_FILE = 101;
    private static final int REQ_NOTIF = 100;
    private static final String TTS_SHIM = "\n(function() {\n  if (window.__nativeTtsInstalled) return;\n  window.__nativeTtsInstalled = true;\n  try { window.__wtrPerf = window.__ttsBridge.isPerfMode(); } catch (e) {}\n\n  // Capture page errors so the app can show WHY a page came up blank\n  try {\n    if (!window.__wtrErrHooked) {\n      window.__wtrErrHooked = true;\n      window.addEventListener('error', function(e) { try { window.__ttsBridge.logError('ERR ' + (e.message || '') + ' @' + String(e.filename || '').slice(-40) + ':' + (e.lineno || 0)); } catch (x) {} });\n      window.addEventListener('unhandledrejection', function(e) { try { var r = e.reason; window.__ttsBridge.logError('REJ ' + ((r && (r.message || r)) || '')); } catch (x) {} });\n    }\n  } catch (x) {}\n\n  function NativeTtsUtterance(text) {\n    this.text = (text === undefined || text === null) ? '' : String(text);\n    this.lang = 'en-US';\n    this.rate = 1;\n    this.pitch = 1;\n    this.volume = 1;\n    this.voice = null;\n    this.onstart = null;\n    this.onend = null;\n    this.onerror = null;\n    this.onboundary = null;\n    this.onmark = null;\n    this.onpause = null;\n    this.onresume = null;\n    this.__id = 'u' + (++NativeTtsUtterance.__seq);\n  }\n  NativeTtsUtterance.__seq = 0;\n  window.SpeechSynthesisUtterance = NativeTtsUtterance;\n\n  var active = {};\n  var queue = [];\n\n  // --- reliability: split long chunks, chain them, recover from stalls\n  var pieces = {};\n  var lastEvent = Date.now();\n  function splitText(s) {\n    s = String(s === undefined || s === null ? \"\" : s);\n    var segs = [];\n    var parts = s.split(/([.!?;:\\u0964\\u3002])\\s+/);\n    for (var j = 0; j < parts.length; j += 2) {\n      var seg = parts[j] + (parts[j + 1] || \"\");\n      if (seg) segs.push(seg);\n    }\n    var out = [];\n    var buf = \"\";\n    for (var i = 0; i < segs.length; i++) {\n      var p = segs[i];\n      if (buf.length && (buf.length + p.length) > 380) { out.push(buf); buf = p; }\n      else { buf = buf ? (buf + \" \" + p) : p; }\n      while (buf.length > 600) { out.push(buf.slice(0, 600)); buf = buf.slice(600); }\n    }\n    if (buf.length) out.push(buf);\n    if (!out.length) out.push(s);\n    return out;\n  }\n  function nativeSpeak(u, piece) {\n    lastEvent = Date.now();\n    window.__ttsBridge.speak(piece, u.lang || \"en-US\",\n      Number(u.rate) || 1, Number(u.pitch) || 1, u.__id,\n      (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : \"\");\n  }\n\n  // Inaudible audio that keeps Chromium from freezing this hidden page: a\n  // WebView that is actively producing audio keeps running its JavaScript\n  // in the background, so the site's TTS player keeps sending the next\n  // chunk. The volume is 0.001 — far below audible, but not digital silence.\n  var keepAliveCtx = null;\n  var keepAliveEl = null;\n  function startKeepAlive() {\n    try {\n      \n      if (!keepAliveCtx) {\n        var AC = window.AudioContext || window.webkitAudioContext;\n        if (!AC) return;\n        keepAliveCtx = new AC();\n        var osc = keepAliveCtx.createOscillator();\n        var gain = keepAliveCtx.createGain();\n        gain.gain.value = 0.001;\n        osc.connect(gain);\n        gain.connect(keepAliveCtx.destination);\n        osc.start();\n      } else if (keepAliveCtx.state === 'suspended') {\n        keepAliveCtx.resume();\n      }\n      if (false) {\n        keepAliveEl = document.createElement(\"audio\");\n        keepAliveEl.setAttribute(\"playsinline\", \"\");\n        keepAliveEl.loop = true;\n        keepAliveEl.volume = 0.03;\n        keepAliveEl.src = 'data:audio/wav;base64,UklGRsQPAABXQVZFZm10IBAAAAABAAEAQB8AAEAfAAABAAgAZGF0YaAPAACAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA';\n        try { document.documentElement.appendChild(keepAliveEl); } catch (e) {}\n      }\n      if (keepAliveEl && keepAliveEl.paused) {\n        var pr = keepAliveEl.play();\n        if (pr && pr.catch) pr.catch(function () {});\n      }\n    } catch (e) {}\n  }\n  function suspendKeepAlive() {\n    try { if (keepAliveCtx && keepAliveCtx.state === 'running') keepAliveCtx.suspend(); } catch (e) {}\n  }\n  var synth = {\n    speaking: false,\n    pending: false,\n    paused: false,\n    onvoiceschanged: null,\n    onstart: null,\n    onend: null,\n    onerror: null,\n    getVoices: function() {\n      try {\n        var raw = window.__ttsBridge.getVoicesJson();\n        var list = JSON.parse(raw);\n        if (list && list.length) return list;\n      } catch (e) {}\n      return [{\n        voiceURI: 'Android System TTS',\n        name: 'Android System TTS',\n        lang: 'en-US',\n        localService: true,\n        default: true\n      }];\n    },\n    speak: function(u) {\n      if (!u || !(u instanceof NativeTtsUtterance)) {\n        u = new NativeTtsUtterance(String(u && u.text));\n      }\n      // While paused, hold new utterances (the site keeps sending the next\n      // chunk after each onend); resume() plays the queued chunk.\n      if (synth.paused) {\n        queue.push(u);\n        synth.pending = true;\n        return;\n      }\n      startKeepAlive();\n      active[u.__id] = u;\n      synth.speaking = true;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      if (window.__ttsBridge.isSimpleTts && window.__ttsBridge.isSimpleTts()) {\n        try { window.__wtrLast = { text: u.text, lang: u.lang || 'en-US', rate: Number(u.rate) || 1, pitch: Number(u.pitch) || 1, id: u.__id, voice: (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : '' }; try { window.__ttsBridge.logEvent('speak ' + String(u.text).slice(0, 36)); } catch (e) {} window.__ttsBridge.speak(u.text, u.lang || 'en-US', Number(u.rate) || 1, Number(u.pitch) || 1, u.__id, (u.voice && u.voice.voiceURI) ? String(u.voice.voiceURI) : ''); return; } catch (e) {}\n      }\n      var list = splitText(u.text);\n      pieces[u.__id] = { list: list, idx: 0, u: u, retried: false };\n      try {\n        nativeSpeak(u, list[0]);\n      } catch (e) {\n        synth.speaking = false;\n        delete pieces[u.__id];\n        if (u.onerror) u.onerror({ type: 'error', error: 'native-tts-unavailable' });\n        if (synth.onerror) synth.onerror({ type: 'error', error: 'native-tts-unavailable' });\n      }\n    },\n    cancel: function() {\n      suspendKeepAlive();\n      try { window.__ttsBridge.cancel(); } catch (e) {}\n      synth.speaking = false;\n      synth.pending = false;\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"stopped\"); } catch (e) {}\n      queue = [];\n      pieces = {};\n      for (var k in active) {\n        var u = active[k];\n        window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      }\n      active = {};\n    },\n    pause: function() {\n      synth.paused = true;\n      synth.speaking = false;\n      try { window.__ttsBridge.setState(\"paused\"); } catch (e) {}\n      try { window.__ttsBridge.pause(); } catch (e) {}\n    },\n    resume: function() {\n      synth.paused = false;\n      try { window.__ttsBridge.setState(\"speaking\"); } catch (e) {}\n      startKeepAlive();\n      if (queue.length) {\n        var q = queue; queue = [];\n        synth.pending = false;\n        for (var i = 0; i < q.length; i++) synth.speak(q[i]);\n      } else {\n        try { window.__ttsBridge.resume(); } catch (e) {}\n      }\n    },\n    addEventListener: function(ev, fn) {\n      if (ev === 'voiceschanged') this.onvoiceschanged = fn;\n    },\n    removeEventListener: function() {}\n  };\n  window.speechSynthesis = synth;\n\n  // Native engine events -> utterance callbacks\n  window.__ttsEvent = function(type, id, reason) {\n    lastEvent = Date.now(); try { window.__ttsBridge.logEvent('event ' + type + ' ' + (reason || '')); } catch (e) {}\n    var u = active[id];\n    var ps = pieces[id];\n    if (type === 'start') {\n      if (u && u.onstart) u.onstart({ type: 'start' });\n      if (synth.onstart) synth.onstart({ type: 'start' });\n    } else if (type === 'end') {\n      if (ps && ps.idx + 1 < ps.list.length) {\n        ps.idx++;\n        try { nativeSpeak(ps.u, ps.list[ps.idx]); return; } catch (e) {}\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      window.__wtrLast = null; if (u && u.onend) u.onend({ type: 'end' });\n      if (synth.onend) synth.onend({ type: 'end' });\n    } else if (type === 'error') {\n      if (ps && !ps.retried) {\n        ps.retried = true;\n        setTimeout(function () { try { nativeSpeak(ps.u, ps.list[ps.idx]); } catch (e) {} }, 500);\n        return;\n      }\n      delete pieces[id];\n      synth.speaking = false;\n      delete active[id];\n      var err = { type: 'error', error: reason || 'unknown' };\n      if (u && u.onerror) u.onerror(err);\n      if (synth.onerror) synth.onerror(err);\n    }\n  };\n\n  // auto-scroll: keeps the page moving while reading\n  setInterval(function() {\n    try {\n      var a = window.__wtrAutoScroll;\n      if (a && a.on && a.px > 0) { window.scrollBy(0, a.px); }\n    } catch (e) {}\n  }, 250);\n\n  // stall watchdog: if the engine goes quiet mid-chapter, re-speak the piece\n  setInterval(function() {\n    try {\n      if (!synth.speaking) return;\n      if (Date.now() - lastEvent < 20000) return; try { window.__ttsBridge.logEvent('stalled - no auto replay'); } catch (e) {}\n      for (var k in pieces) {\n        var ps = pieces[k];\n        if (ps && ps.list && ps.list.length > ps.idx) {\n          nativeSpeak(ps.u, ps.list[ps.idx]);\n          return;\n        }\n      }\n    } catch (e) {}\n  }, 5000);\n\n  setTimeout(function() {\n    if (synth.onvoiceschanged) {\n      try { synth.onvoiceschanged({ type: 'voiceschanged' }); } catch (e) {}\n    }\n  }, 50);\n})();\n";
    ValueCallback<Uri[]> filePathCallback;
    TtsBridge ttsBridge;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        this.webView = webView;
        FrameLayout rootLayout = new FrameLayout(this);
        rootLayout.addView(webView, new FrameLayout.LayoutParams(-1, -1));
        setContentView(rootLayout);
        // One-time: switch existing installs back to the stable, non-split
        // playback that the original app used (never stops mid-chapter).
        try {
            android.content.SharedPreferences up = getSharedPreferences("ui", 0);
            if (!up.getBoolean("stableMigrated", false)) {
                up.edit().putBoolean("simpleTts", true).putBoolean("stableMigrated", true).apply();
            }
        } catch (Exception ignored) {
        }
        // One-time: ad blocking defaults to OFF from here on - it was the only
        // change that could make the site's own requests fail.
        try {
            android.content.SharedPreferences sd = getSharedPreferences("ui", 0);
            if (!sd.getBoolean("safeDefaults", false)) {
                sd.edit().putBoolean("adBlock", false).putBoolean("safeDefaults", true).apply();
            }
        } catch (Exception ignored) {
        }
        addSettingsButton(rootLayout);
        handlePushConfig(getIntent());
        // "Start reading" home-screen shortcut
        try {
            if (Build.VERSION.SDK_INT >= 25) {
                android.content.pm.ShortcutManager sm =
                        (android.content.pm.ShortcutManager) getSystemService(SHORTCUT_SERVICE);
                if (sm != null && sm.getDynamicShortcuts().isEmpty()) {
                    android.content.Intent si = new android.content.Intent(this, MainActivity.class)
                            .setAction("com.vasudev.wtrlab.action.START_READING");
                    int icon = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
                    android.content.pm.ShortcutInfo shortcut = new android.content.pm.ShortcutInfo.Builder(this, "start_reading")
                            .setShortLabel("Start reading")
                            .setLongLabel("Open and continue reading")
                            .setIcon(android.graphics.drawable.Icon.createWithResource(this, icon))
                            .setIntent(si)
                            .build();
                    sm.setDynamicShortcuts(java.util.Arrays.asList(shortcut));
                }
            }
        } catch (Exception ignored) {
        }
        // Self-heal: if the last run ended on a blank page, the WebView cache
        // is probably poisoned - drop it before this load.
        android.content.SharedPreferences bootPrefs = getSharedPreferences("app", MODE_PRIVATE);
        if (bootPrefs.getBoolean("lastLoadFailed", false)) {
            bootPrefs.edit().putBoolean("lastLoadFailed", false).apply();
            try {
                webView.clearCache(true);
            } catch (Exception ignored) {
            }
            try {
                webView.clearHistory();
            } catch (Exception ignored) {
            }
        }
        getWindow().setFlags(2048, 2048);
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(true);
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.show(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
            }
        }
        getWindow().setStatusBarColor(-16777216);
        WebView webView2 = this.webView;
        WebView webView3 = null;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView2 = null;
        }
        webView2.setOnApplyWindowInsetsListener(new MainActivity$$ExternalSyntheticLambda0());
        if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, REQ_NOTIF);
        }
        WebView webView4 = this.webView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView4 = null;
        }
        WebSettings settings = webView4.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setCacheMode(-1);
        settings.setMixedContentMode(2);
        settings.setMediaPlaybackRequiresUserGesture(false);
        // Sign-up / login flows often open a popup window; without this the
        // popup request is silently dropped and nothing appears to happen.
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        try {
            android.webkit.CookieManager cm = android.webkit.CookieManager.getInstance();
            cm.setAcceptCookie(true);
            cm.setAcceptThirdPartyCookies(webView, true);
        } catch (Exception ignored) {
        }
        WebView webView5 = this.webView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView5 = null;
        }
        webView5.setBackgroundColor(-15658735);
        WebView webView6 = this.webView;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView6 = null;
        }
        webView6.setOverScrollMode(2);
        if (Build.VERSION.SDK_INT >= 26) {
            WebView webView7 = this.webView;
            if (webView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView7 = null;
            }
            webView7.setRendererPriorityPolicy(2, false);
        }
        TtsBridge ttsBridge = new TtsBridge(this);
        this.ttsBridge = ttsBridge;
        WebView webView8 = this.webView;
        if (webView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView8 = null;
        }
        ttsBridge.attach(webView8);
        WebView webView9 = this.webView;
        if (webView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView9 = null;
        }
        TtsBridge ttsBridge2 = this.ttsBridge;
        if (ttsBridge2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsBridge");
            ttsBridge2 = null;
        }
        webView9.addJavascriptInterface(ttsBridge2, "__ttsBridge");
        // restore the volume chosen in Settings
        // restore the display preferences chosen in Settings
        android.content.SharedPreferences uiPrefs = getSharedPreferences("ui", MODE_PRIVATE);
        if (uiPrefs.getBoolean("keepScreenOn", false)) {
            getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        int savedBright = uiPrefs.getInt("bright", 100);
        if (savedBright != 100) {
            try {
                android.view.WindowManager.LayoutParams wlp = getWindow().getAttributes();
                wlp.screenBrightness = savedBright / 100f;
                getWindow().setAttributes(wlp);
            } catch (Exception ignored) {
            }
        }
        float savedVolume = getSharedPreferences("ui", MODE_PRIVATE).getFloat("volume", 1.0f);
        ttsBridge2.setVolume(savedVolume);
        // apply the theme chosen in Settings to the website as well
        ttsBridge2.refreshAutoTheme();
        setupGestures(webView);
        WebView webView10 = this.webView;
        if (webView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView10 = null;
        }
        webView10.setWebViewClient(new MainActivity$onCreate$3(this));
        WebView webView11 = this.webView;
        if (webView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView11 = null;
        }
        webView11.setWebChromeClient(new MainActivity$onCreate$4(this));
        if (savedInstanceState != null) {
            WebView webView12 = this.webView;
            if (webView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView3 = webView12;
            }
            webView3.restoreState(savedInstanceState);
            return;
        }
        Intent intent = getIntent();
        String dataString = intent != null ? intent.getDataString() : null;
        if (dataString != null && dataString.startsWith("http")) {
            WebView webView13 = this.webView;
            if (webView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView3 = webView13;
            }
            webView3.loadUrl(dataString);
            return;
        }
        WebView webView14 = this.webView;
        if (webView14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView3 = webView14;
        }
        webView3.loadUrl(HOME_URL);
    }

    public static final WindowInsets onCreate$lambda$0(View v, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets systemWindowInsets = insets.getSystemWindowInsets();
        Intrinsics.checkNotNullExpressionValue(systemWindowInsets, "getSystemWindowInsets(...)");
        v.setPadding(0, systemWindowInsets.top, 0, systemWindowInsets.bottom);
        return insets;
    }

    /** Small draggable floating gear button that opens the Settings panel. */
    private void addReadControls(FrameLayout root) {
        final android.content.SharedPreferences up = getSharedPreferences("ui", 0);
        final android.widget.LinearLayout bar = new android.widget.LinearLayout(this);
        bar.setOrientation(android.widget.LinearLayout.VERTICAL);
        bar.setGravity(17);
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.RECTANGLE);
        bg.setCornerRadius(dp(18));
        bg.setColor(0xCC1E293B);
        bg.setStroke(dp(1), 0x66F59E0B);
        bar.setBackground(bg);
        bar.setPadding(dp(6), dp(6), dp(6), dp(6));

        // row 1 - transport
        final android.widget.LinearLayout row1 = new android.widget.LinearLayout(this);
        row1.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        row1.setGravity(17);
        final TextView play = new TextView(this);
        play.setText("\u25B6");
        play.setTextColor(-1);
        play.setTextSize(16.0f);
        play.setGravity(17);
        play.setMinWidth(dp(36));
        final TextView stop = new TextView(this);
        stop.setText("\u25A0");
        stop.setTextColor(-1);
        stop.setTextSize(15.0f);
        stop.setGravity(17);
        stop.setMinWidth(dp(36));
        row1.addView(play, new android.widget.LinearLayout.LayoutParams(-2, dp(32)));
        row1.addView(stop, new android.widget.LinearLayout.LayoutParams(-2, dp(32)));

        // row 2 - the two switches from the Speech panel
        final android.widget.LinearLayout row2 = new android.widget.LinearLayout(this);
        row2.setOrientation(android.widget.LinearLayout.HORIZONTAL);
        row2.setGravity(17);
        final TextView hlBtn = new TextView(this);
        hlBtn.setText("Highlight");
        hlBtn.setTextSize(10.0f);
        hlBtn.setGravity(17);
        hlBtn.setSingleLine(true);
        hlBtn.setMinWidth(dp(62));
        hlBtn.setPadding(dp(8), dp(4), dp(8), dp(4));
        final TextView adBtn = new TextView(this);
        adBtn.setText("Auto-next");
        adBtn.setTextSize(10.0f);
        adBtn.setGravity(17);
        adBtn.setSingleLine(true);
        adBtn.setMinWidth(dp(62));
        adBtn.setPadding(dp(8), dp(4), dp(8), dp(4));
        styleQuickToggle(hlBtn, up.getBoolean("highlightPara", true));
        styleQuickToggle(adBtn, up.getBoolean("autoAdvance", false));
        row2.addView(hlBtn, new android.widget.LinearLayout.LayoutParams(-2, dp(28)));
        row2.addView(adBtn, new android.widget.LinearLayout.LayoutParams(-2, dp(28)));

        bar.addView(row1, new android.widget.LinearLayout.LayoutParams(-2, -2));
        bar.addView(row2, new android.widget.LinearLayout.LayoutParams(-2, -2));

        // wrap-content: the bar measures itself, so labels can never be cut
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        final android.util.DisplayMetrics dm = getResources().getDisplayMetrics();
        lp.leftMargin = dp(10);
        lp.topMargin = Math.max(dp(12), dm.heightPixels / 2);

        play.setOnClickListener(v -> toggleReading());
        stop.setOnClickListener(v -> {
            try {
                TtsBridge b = this.ttsBridge;
                if (b != null) {
                    b.jsCancel();
                }
            } catch (Exception ignored) {
            }
        });
        hlBtn.setOnClickListener(v -> {
            boolean on = up.getBoolean("highlightPara", true);
            up.edit().putBoolean("highlightPara", !on).apply();
            styleQuickToggle(hlBtn, !on);
            Toast.makeText(this, !on ? "Highlight paragraphs ON" : "Highlight paragraphs OFF", Toast.LENGTH_SHORT).show();
        });
        adBtn.setOnClickListener(v -> {
            boolean on = up.getBoolean("autoAdvance", false);
            up.edit().putBoolean("autoAdvance", !on).apply();
            styleQuickToggle(adBtn, !on);
            Toast.makeText(this, !on ? "Auto-advance ON" : "Auto-advance OFF", Toast.LENGTH_SHORT).show();
        });

        attachDrag(bar, bar, lp, dm);
        attachDrag(row1, bar, lp, dm);
        attachDrag(row2, bar, lp, dm);
        root.addView(bar, lp);

        this.floatHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    TtsBridge b = MainActivity.this.ttsBridge;
                    String st = b == null ? "stopped" : b.getState();
                    play.setText("speaking".equals(st) ? "\u23F8" : "\u25B6");
                    styleQuickToggle(hlBtn, up.getBoolean("highlightPara", true));
                    styleQuickToggle(adBtn, up.getBoolean("autoAdvance", false));
                } catch (Exception ignored) {
                }
                MainActivity.this.floatHandler.postDelayed(this, 1500L);
            }
        }, 1500L);
    }
    private void styleQuickToggle(TextView v, boolean on) {
        v.setTextColor(on ? 0xFF0B1220 : 0xFFE5E7EB);
        GradientDrawable g = new GradientDrawable();
        g.setCornerRadius(dp(999));
        g.setColor(on ? 0xFFF59E0B : 0x33FFFFFF);
        v.setBackground(g);
    }
    private void attachDrag(View v, final View container, final FrameLayout.LayoutParams lp,
                            final android.util.DisplayMetrics dm) {
        final float[] down = new float[2];
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        down[0] = event.getRawX() - lp.leftMargin;
                        down[1] = event.getRawY() - lp.topMargin;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        int bw = container.getWidth();
                        int bh = container.getHeight();
                        if (bw <= 0) {
                            bw = dp(130);
                        }
                        if (bh <= 0) {
                            bh = dp(78);
                        }
                        int nx = (int) (event.getRawX() - down[0]);
                        int ny = (int) (event.getRawY() - down[1]);
                        if (nx < 0) nx = 0;
                        if (ny < 0) ny = 0;
                        if (nx > dm.widthPixels - bw) nx = dm.widthPixels - bw;
                        if (ny > dm.heightPixels - bh) ny = dm.heightPixels - bh;
                        lp.leftMargin = nx;
                        lp.topMargin = ny;
                        container.setLayoutParams(lp);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void toggleReading() {
        try {
            TtsBridge b = this.ttsBridge;
            if (b == null) {
                return;
            }
            String st = b.getState();
            if ("speaking".equals(st)) {
                b.jsPause();
            } else if ("paused".equals(st)) {
                b.jsResume();
            } else {
                b.readPageAloud();
            }
        } catch (Exception ignored) {
        }
    }

    private void addSettingsButton(FrameLayout root) {
        final TextView btn = new TextView(this);
        btn.setText("\u2699");
        btn.setTextColor(-1);
        btn.setTextSize(17.0f);
        btn.setGravity(17);
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(0xCC1E293B);
        bg.setStroke(dp(1), 0x66F59E0B);
        btn.setBackground(bg);
        final int size = dp(40);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(size, size);
        final android.util.DisplayMetrics dm = getResources().getDisplayMetrics();
        lp.leftMargin = dm.widthPixels - size - dp(12);
        lp.topMargin = dp(12);
        final float[] down = new float[2];
        final long[] downTime = {0L};
        final boolean[] moved = {false};
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        down[0] = event.getRawX() - lp.leftMargin;
                        down[1] = event.getRawY() - lp.topMargin;
                        downTime[0] = android.os.SystemClock.elapsedRealtime();
                        moved[0] = false;
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        int nx = (int) (event.getRawX() - down[0]);
                        int ny = (int) (event.getRawY() - down[1]);
                        if (Math.abs(nx - lp.leftMargin) > 10 || Math.abs(ny - lp.topMargin) > 10) {
                            moved[0] = true;
                        }
                        if (nx < 0) nx = 0;
                        if (ny < 0) ny = 0;
                        if (nx > dm.widthPixels - size) nx = dm.widthPixels - size;
                        if (ny > dm.heightPixels - size) ny = dm.heightPixels - size;
                        lp.leftMargin = nx;
                        lp.topMargin = ny;
                        v.setLayoutParams(lp);
                        return true;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        if (!moved[0]) {
                            if (android.os.SystemClock.elapsedRealtime() - downTime[0] > 650) {
                                // long-press: emergency cache-clear + reload
                                clearCacheAndReload();
                            } else {
                                if (settingsPanel == null || !settingsPanel.isShowing()) {
                                    settingsPanel = new com.vasudev.settingsui.SettingsPanel(MainActivity.this);
                                    settingsPanel.show();
                                }
                            }
                        }
                        return true;
                }
                return false;
            }
        });
        root.addView(btn, lp);
        root.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                try {
                    int w = root.getWidth();
                    int h = root.getHeight();
                    int nx = lp.leftMargin;
                    int ny = lp.topMargin;
                    if (nx > w - size) {
                        nx = Math.max(0, w - size);
                    }
                    if (ny > h - size) {
                        ny = Math.max(0, h - size);
                    }
                    if (nx != lp.leftMargin || ny != lp.topMargin) {
                        lp.leftMargin = nx;
                        lp.topMargin = ny;
                        btn.setLayoutParams(lp);
                    }
                } catch (Exception ignored) {
                }
            }
        });
    }

    private int dp(int v) {
        return (int) (v * getResources().getDisplayMetrics().density + 0.5f);
    }

    /** Recovery: drop the WebView cache and reload the site fresh.
     *  Fixes blank / black pages caused by a corrupted cached bundle. */
    void clearCacheAndReload() {
        WebView w = this.webView;
        if (w == null) {
            return;
        }
        try {
            w.clearCache(true);
        } catch (Exception ignored) {
        }
        try {
            w.clearHistory();
        } catch (Exception ignored) {
        }
        Toast.makeText(this, "Cache cleared - reloading", Toast.LENGTH_SHORT).show();
        w.loadUrl(HOME_URL);
    }

    /** Optional: hardware volume keys adjust the reader's speech volume. */
    @Override // android.app.Activity
    public boolean dispatchKeyEvent(android.view.KeyEvent event) {
        try {
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                int code = event.getKeyCode();
                boolean isVol = code == android.view.KeyEvent.KEYCODE_VOLUME_UP
                        || code == android.view.KeyEvent.KEYCODE_VOLUME_DOWN;
                android.content.SharedPreferences prefs = getSharedPreferences("ui", MODE_PRIVATE);
                int volMode = prefs.getInt("volMode", prefs.getBoolean("volKeys", false) ? 1 : 0);
                if (isVol && volMode == 1) {
                    float v = prefs.getFloat("volume", 1.0f);
                    v = code == android.view.KeyEvent.KEYCODE_VOLUME_UP ? v + 0.1f : v - 0.1f;
                    if (v > 1f) {
                        v = 1f;
                    }
                    if (v < 0f) {
                        v = 0f;
                    }
                    prefs.edit().putFloat("volume", v).apply();
                    TtsBridge bridge = this.ttsBridge;
                    if (bridge != null) {
                        bridge.setVolume(v);
                    }
                    Toast.makeText(this, "Speech volume: " + Math.round(v * 100) + "%", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (isVol && volMode == 2) {
                    WebView wv = this.webView;
                    if (wv != null) {
                        int step = Math.max(200, wv.getHeight() * 8 / 10);
                        if (code == android.view.KeyEvent.KEYCODE_VOLUME_DOWN) {
                            step = -step;
                        }
                        final int dy = step;
                        wv.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    WebView w2 = MainActivity.this.webView;
                                    if (w2 != null) {
                                        w2.evaluateJavascript("window.scrollBy(0," + dy + ");", null);
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                        });
                    }
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return super.dispatchKeyEvent(event);
    }

    // ---------- automation / gestures / insights state ----------
    private long readingStart = 0L;
    private final android.os.Handler uiHandler = new android.os.Handler(android.os.Looper.getMainLooper());
    private Runnable themeTick;
    private android.hardware.SensorManager sensorManager;
    private android.hardware.SensorEventListener lightListener;
    private float lastTapTime = 0f;
    private com.vasudev.settingsui.SettingsPanel settingsPanel;
    private final android.os.Handler floatHandler = new android.os.Handler(android.os.Looper.getMainLooper());
    private boolean notifToastShown;
    private float twoFingerStartY = -1f;
    private int twoFingerStartBright = -1;

    /** Reading insights: add foreground minutes to today's bucket. */
    private void addReadingMinutes(int minutes) {
        try {
            if (minutes <= 0) {
                return;
            }
            String key = "rd" + new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.US)
                    .format(new java.util.Date());
            android.content.SharedPreferences p = getSharedPreferences("ui", MODE_PRIVATE);
            p.edit().putInt(key, p.getInt(key, 0) + minutes).apply();
        } catch (Exception ignored) {
        }
    }

    private void applyBrightnessNow(int pct) {
        try {
            if (pct < 10) {
                pct = 10;
            }
            if (pct > 100) {
                pct = 100;
            }
            android.view.WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.screenBrightness = pct / 100f;
            getWindow().setAttributes(lp);
            getSharedPreferences("ui", MODE_PRIVATE).edit().putInt("bright", pct).apply();
        } catch (Exception ignored) {
        }
    }

    /** Double-tap = play/pause. Two-finger vertical swipe = brightness. */
    private void setupGestures(WebView webView) {
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, android.view.MotionEvent e) {
                try {
                    android.content.SharedPreferences p = getSharedPreferences("ui", MODE_PRIVATE);
                    if (e.getPointerCount() >= 2) {
                        if (!p.getBoolean("twoFingerBright", false)) {
                            return false;
                        }
                        if (e.getActionMasked() == android.view.MotionEvent.ACTION_POINTER_DOWN
                                || e.getActionMasked() == android.view.MotionEvent.ACTION_MOVE) {
                            float cy = (e.getY(0) + e.getY(1)) / 2f;
                            if (twoFingerStartY < 0f) {
                                twoFingerStartY = cy;
                                twoFingerStartBright = p.getInt("bright", 100);
                            } else {
                                int delta = (int) ((twoFingerStartY - cy) * 0.35f);
                                applyBrightnessNow(twoFingerStartBright + delta);
                            }
                        }
                        return true;
                    }
                    if (e.getActionMasked() == android.view.MotionEvent.ACTION_UP) {
                        twoFingerStartY = -1f;
                        twoFingerStartBright = -1;
                        return false;
                    }
                    if (e.getActionMasked() == android.view.MotionEvent.ACTION_DOWN) {
                        if (p.getBoolean("dblTap", false)) {
                            long now = android.os.SystemClock.elapsedRealtime();
                            if (now - lastTapTime < 320f) {
                                lastTapTime = 0f;
                                TtsBridge bridge = ttsBridge;
                                if (bridge != null) {
                                    bridge.jsToggle();
                                }
                                return true;
                            }
                            lastTapTime = now;
                        }
                    }
                } catch (Exception ignored) {
                }
                return false;
            }
        });
    }

    /** Ambient-light auto theme (opt-in). */
    private void startAmbientSensor() {
        try {
            if (!getSharedPreferences("ui", MODE_PRIVATE).getBoolean("ambient", false)) {
                return;
            }
            if (sensorManager == null) {
                sensorManager = (android.hardware.SensorManager) getSystemService(SENSOR_SERVICE);
            }
            if (sensorManager == null || lightListener != null) {
                return;
            }
            lightListener = new android.hardware.SensorEventListener() {
                @Override
                public void onSensorChanged(android.hardware.SensorEvent event) {
                    try {
                        TtsBridge bridge = ttsBridge;
                        if (bridge != null && event.values.length > 0) {
                            bridge.setAmbientDark(event.values[0] < 15f);
                        }
                    } catch (Exception ignored) {
                    }
                }

                @Override
                public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
                }
            };
            sensorManager.registerListener(lightListener,
                    sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_LIGHT),
                    android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
        } catch (Exception ignored) {
        }
    }

    private void stopAmbientSensor() {
        try {
            if (sensorManager != null && lightListener != null) {
                sensorManager.unregisterListener(lightListener);
            }
            lightListener = null;
        } catch (Exception ignored) {
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        String dataString = intent.getDataString();
        if (dataString != null && dataString.startsWith("http")) {
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.loadUrl(dataString);
        }
    }

    /** Settings pushed from the Vasu Settings Studio app. */
    private boolean handlePushConfig(Intent intent) {
        try {
            if (intent == null || !"com.vasudev.settingsstudio.PUSH_CONFIG".equals(intent.getAction())) {
                return false;
            }
            String cfg = intent.getStringExtra("config");
            if (cfg == null || cfg.length() == 0) {
                return false;
            }
            int n = com.vasudev.settingsui.SettingsPanel.applyConfigJson(this, cfg);
            Toast.makeText(this, "Studio se " + n + " settings lag gayi", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Writes a pending settings/log file the user chose a place for. */
    private void writePendingFile(Intent data) {
        try {
            if (data == null || data.getData() == null) {
                return;
            }
            java.io.OutputStream os = getContentResolver().openOutputStream(data.getData());
            String txt = com.vasudev.settingsui.SettingsPanel.pendingConfigJson;
            os.write((txt == null ? "{}" : txt).getBytes("UTF-8"));
            os.close();
            com.vasudev.settingsui.SettingsPanel.pendingConfigJson = null;
            Toast.makeText(this, "File save ho gayi", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Save nahi ho payi", Toast.LENGTH_SHORT).show();
        }
    }

    private void readConfigFile(Intent data) {
        try {
            if (data == null || data.getData() == null) {
                return;
            }
            java.io.InputStream in = getContentResolver().openInputStream(data.getData());
            java.io.ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) {
                bo.write(buf, 0, n);
            }
            in.close();
            String json = new String(bo.toByteArray(), "UTF-8");
            int c = com.vasudev.settingsui.SettingsPanel.applyConfigJson(this, json);
            Toast.makeText(this, c + " settings import ho gayin", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Config file padhi nahi ja saki", Toast.LENGTH_SHORT).show();
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 301) {
            writePendingFile(data);
            return;
        }
        if (requestCode == 302) {
            readConfigFile(data);
            return;
        }
        if (requestCode == REQ_FILE) {
            ValueCallback<Uri[]> valueCallback = this.filePathCallback;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            }
            this.filePathCallback = null;
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.saveState(outState);
    }

    @Override // android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void onBackPressed() {
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        if (!webView.canGoBack()) {
            finish();
            return;
        }
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView3;
        }
        webView2.goBack();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.onResume();
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView3;
        }
        webView2.resumeTimers();
        stopService(new Intent(this, (Class<?>) MediaPlaybackService.class));
        // reading insights + automation
        readingStart = android.os.SystemClock.elapsedRealtime();
        try {
            if (getIntent() != null && "com.vasudev.wtrlab.action.START_READING".equals(getIntent().getAction())) {
                TtsBridge b0 = this.ttsBridge;
                if (b0 != null) {
                    b0.jsResume();
                }
            }
        } catch (Exception ignored) {
        }
        // NOTE: deliberately NOT resuming here. Native resume() re-speaks the
        // last utterance from its start, so doing it automatically on every
        // return to the app replayed a line and broke the reader's chain.
        startAmbientSensor();
        final TtsBridge bridge = this.ttsBridge;
        if (bridge != null) {
            bridge.refreshAutoTheme();
        }
        if (themeTick == null) {
            themeTick = new Runnable() {
                @Override
                public void run() {
                    try {
                        TtsBridge b = MainActivity.this.ttsBridge;
                        if (b != null) {
                            b.refreshAutoTheme();
                        }
                    } catch (Exception ignored) {
                    }
                    uiHandler.postDelayed(this, 300000L);
                }
            };
        }
        uiHandler.removeCallbacks(themeTick);
        uiHandler.postDelayed(themeTick, 300000L);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (!notifToastShown && Build.VERSION.SDK_INT >= 33
                && checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
            notifToastShown = true;
            Toast.makeText(this, "Notifications are off — enable them for WTR Lab to see the playback controls", 1).show();
        }
        // Keep the media service alive whenever the app goes to the background.
        // The original app did this unconditionally; it is what stops Android
        // from freezing the page and cutting playback short.
        Intent intent = new Intent(this, (Class<?>) MediaPlaybackService.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        } catch (Exception unused) {
        }
        // reading insights + automation teardown
        if (readingStart > 0L) {
            addReadingMinutes((int) ((android.os.SystemClock.elapsedRealtime() - readingStart) / 60000L));
            readingStart = 0L;
        }
        stopAmbientSensor();
        if (themeTick != null) {
            uiHandler.removeCallbacks(themeTick);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        stopService(new Intent(this, (Class<?>) MediaPlaybackService.class));
        TtsBridge ttsBridge = this.ttsBridge;
        WebView webView = null;
        if (ttsBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsBridge");
            ttsBridge = null;
        }
        ttsBridge.shutdown();
        WebView webView2 = this.webView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView = webView2;
        }
        webView.destroy();
        super.onDestroy();
    }
}
