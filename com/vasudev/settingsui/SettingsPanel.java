package com.vasudev.settingsui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class SettingsPanel {

    private final android.app.Activity act;
    private android.widget.FrameLayout container;
    private android.app.Dialog dialog;

    // ================= Developer constants =================
    static final String UPI_ID = "Vasudevupi@upi";
    static final String PAYEE_NAME = "Vasudev";
    static final String DEV_NAME = "Vasudev Dangaya";
    static final String DEV_EMAIL = "Vasudev.work@rediffmail.com";
    static final String PRIVACY_URL = "https://example.com/privacy";
    static final String GITHUB_URL = "https://github.com/vasudev-work";
    static final String APP_VERSION = "1.0";

    // style / theme ids
    static final int STYLE_GLASS = 0, STYLE_CLAY = 1, STYLE_SKEUO = 2, STYLE_NEU = 3;
    static final int THEME_SYSTEM = 0, THEME_LIGHT = 1, THEME_DARK = 2;
    static final int PRESET_LOVE = 0, PRESET_MATERIAL = 1, PRESET_MONO = 2;

    static final int[] ACCENTS = {0xFFE53935, 0xFF1E88E5, 0xFF43A047, 0xFFFDD835,
            0xFF8E24AA, 0xFFFB8C00, 0xFFEC407A, 0xFF00897B};

    // ================= state =================
    SharedPreferences prefs;
    int tab = 3;                 // 0=Read 1=Display 2=Speech 3=Settings 4=More
    int uiStyle = STYLE_GLASS;
    int themeMode = THEME_SYSTEM;
    int colorPreset = PRESET_LOVE;
    int accent = ACCENTS[4];
    int scalePct = 100;
    int langIdx = 0;
    int volumePct = 100;
    boolean volKeys = false;
    boolean keepScreenOn = false;
    int brightPct = 100;
    int sleepMinutes = 0;
    boolean siteTheme = true;
    boolean autoDark = false;
    int darkFrom = 20;
    int darkTo = 7;
    boolean ambient = false;
    int volMode = 0;
    boolean dblTap = false;
    boolean twoFingerBright = false;
    boolean bionic = false;
    boolean autoAdvance = false;
    boolean highlightPara = true;
    boolean perfMode = false;
    int focusMin = 0;
    String customCss = "";
    boolean capture = false;
    boolean adBlock = true;
    boolean typoOn = false;
    int fontIdx = 0;
    int textSizePct = 100;
    int lineHeightPct = 160;
    int textAlignIdx = 0;
    boolean simpleTts = false;
    boolean autoScroll = false;
    int autoScrollSpeed = 3;
    int pageWidthPct = 100;
    boolean amoled = false;
    int audioLimitMb = 500;
    android.media.MediaPlayer offlinePlayer;
    java.io.File[] offlineQueue;
    int offlineIndex = 0;
    boolean dynamicCards = true;
    boolean bgAnim = true;
    boolean isDark;

    // palette (computed)
    int cBg, cCard, cText, cSub, cBorder;

    float density;
    Random rnd = new Random();

    public SettingsPanel(android.app.Activity host) {
        act = host;
    }

    /** Open the settings panel as a full-screen overlay inside the app. */
    public void show() {
        density = act.getResources().getDisplayMetrics().density;
        prefs = act.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
        uiStyle = prefs.getInt("style", STYLE_GLASS);
        themeMode = prefs.getInt("theme", THEME_SYSTEM);
        colorPreset = prefs.getInt("preset", PRESET_LOVE);
        accent = prefs.getInt("accent", ACCENTS[4]);
        scalePct = prefs.getInt("scale", 100);
        langIdx = prefs.getInt("lang", 0);
        dynamicCards = prefs.getBoolean("dyncard", true);
        bgAnim = prefs.getBoolean("bganim", true);
        volumePct = Math.round(prefs.getFloat("volume", 1.0f) * 100f);
        volKeys = prefs.getBoolean("volKeys", false);
        keepScreenOn = prefs.getBoolean("keepScreenOn", false);
        brightPct = prefs.getInt("bright", 100);
        sleepMinutes = prefs.getInt("sleep", 0);
        siteTheme = prefs.getBoolean("siteTheme", true);
        autoDark = prefs.getBoolean("autoDark", false);
        darkFrom = prefs.getInt("darkFrom", 20);
        darkTo = prefs.getInt("darkTo", 7);
        ambient = prefs.getBoolean("ambient", false);
        volMode = prefs.getInt("volMode", prefs.getBoolean("volKeys", false) ? 1 : 0);
        dblTap = prefs.getBoolean("dblTap", false);
        twoFingerBright = prefs.getBoolean("twoFingerBright", false);
        bionic = prefs.getBoolean("bionic", false);
        autoAdvance = prefs.getBoolean("autoAdvance", false);
        highlightPara = prefs.getBoolean("highlightPara", true);
        perfMode = prefs.getBoolean("perfMode", false);
        focusMin = prefs.getInt("focusMin", 0);
        customCss = prefs.getString("customCss", "");
        capture = prefs.getBoolean("capture", false);
        adBlock = prefs.getBoolean("adBlock", false);
        typoOn = prefs.getBoolean("typoOn", false);
        fontIdx = prefs.getInt("fontIdx", 0);
        textSizePct = prefs.getInt("textSize", 100);
        lineHeightPct = prefs.getInt("lineHeight", 160);
        textAlignIdx = prefs.getInt("textAlign", 0);
        simpleTts = prefs.getBoolean("simpleTts", true);
        autoScroll = prefs.getBoolean("autoScroll", false);
        autoScrollSpeed = prefs.getInt("autoScrollSpeed", 3);
        pageWidthPct = prefs.getInt("pageWidth", 100);
        amoled = prefs.getBoolean("amoled", false);
        audioLimitMb = prefs.getInt("audioLimit", 500);

        dialog = new android.app.Dialog(act, android.R.style.Theme_Material_NoActionBar);
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(d -> stopOfflinePlayback());
        container = new android.widget.FrameLayout(act);
        dialog.setContentView(container, new android.view.ViewGroup.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();
        android.view.Window w = dialog.getWindow();
        if (w != null) {
            w.setLayout(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        }
        showSplash();
    }

    // ================= helpers =================
    int px(float dp) { return Math.round(dp * density); }

    /** localized string lookup (i18n provider) */
    String t(String key) { return Strings.get(langIdx, key); }

    static int lighten(int c, float f) {
        int r = Math.min(255, Math.round(Color.red(c) + (255 - Color.red(c)) * f));
        int g = Math.min(255, Math.round(Color.green(c) + (255 - Color.green(c)) * f));
        int bl = Math.min(255, Math.round(Color.blue(c) + (255 - Color.blue(c)) * f));
        return Color.rgb(r, g, bl);
    }
    static int darken(int c, float f) {
        return Color.rgb(Math.round(Color.red(c) * f), Math.round(Color.green(c) * f), Math.round(Color.blue(c) * f));
    }

    GradientDrawable rounded(int color, float radiusDp, int strokeColor, float strokeDp) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(px(radiusDp));
        if (strokeColor != 0) { g.setStroke(Math.max(1, px(strokeDp)), strokeColor); }
        return g;
    }

    void computePalette() {
        boolean sysDark = (act.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES;
        isDark = themeMode == THEME_DARK || (themeMode == THEME_SYSTEM && sysDark);

        switch (colorPreset) {
            case PRESET_LOVE:
                cBg = isDark ? 0xFF1A1218 : 0xFFFDF1F5; break;
            case PRESET_MATERIAL:
                cBg = isDark ? 0xFF101418 : 0xFFF2F6FB; break;
            default:
                cBg = isDark ? 0xFF151515 : 0xFFF5F5F5;
        }
        cText = isDark ? 0xFFECEDF2 : 0xFF191B22;
        cSub = isDark ? 0xB3ECEDF2 : 0xA6191B22;
        if (colorPreset == PRESET_MONO) {
            cCard = isDark ? 0xFF202020 : 0xFFFFFFFF;
            cBorder = isDark ? 0x33FFFFFF : 0x22000000;
        } else {
            cCard = isDark ? lighten(cBg, 0.07f) : 0xFFFFFFFF;
            cBorder = isDark ? 0x2EFFFFFF : 0x14000000;
        }
    }

    // ================= style engine =================
    GradientDrawable cardBase() {
        switch (uiStyle) {
            case STYLE_GLASS:
                return rounded(isDark ? 0x22FFFFFF : 0x96FFFFFF, 26, isDark ? 0x45FFFFFF : 0xD9FFFFFF, 1.4f);
            case STYLE_CLAY:
                return rounded(lighten(cCard, 0.09f), 36, lighten(cCard, 0.45f), 2.5f);
            case STYLE_SKEUO: {
                GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{lighten(cCard, 0.18f), darken(cCard, 0.85f)});
                g.setCornerRadius(px(14));
                g.setStroke(Math.max(1, px(1)), darken(cCard, 0.68f));
                return g;
            }
            default: { // NEU
                return rounded(cBg, 24, isDark ? lighten(cBg, 0.22f) : 0xFFFFFFFF, 2f);
            }
        }
    }

    LayerDrawable styledCard() {
        GradientDrawable base = cardBase();
        if (uiStyle == STYLE_NEU) {
            GradientDrawable shadow = rounded(darken(cBg, isDark ? 0.55f : 0.88f), 24, 0, 0);
            LayerDrawable ld = new LayerDrawable(new android.graphics.drawable.Drawable[]{shadow, base});
            int s = px(6);
            ld.setLayerInset(1, 0, 0, s, s);
            return ld;
        }
        if (uiStyle == STYLE_CLAY) {
            GradientDrawable shadow = rounded(darken(cCard, 0.82f), 34, 0, 0);
            LayerDrawable ld = new LayerDrawable(new android.graphics.drawable.Drawable[]{shadow, base});
            int s = px(8);
            ld.setLayerInset(1, 0, 0, s, s);
            return ld;
        }
        return new LayerDrawable(new android.graphics.drawable.Drawable[]{base});
    }

    GradientDrawable pill(int color, boolean selected, boolean accentStyle) {
        if (uiStyle == STYLE_SKEUO && accentStyle) {
            GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{lighten(color, 0.15f), darken(color, 0.88f)});
            g.setCornerRadius(px(999));
            return g;
        }
        if (selected) return rounded(color, 999, darken(color, 0.8f), 1);
        return rounded(isDark ? 0x22FFFFFF : 0x14000000, 999, cBorder, 1);
    }

    // ================= UI builders =================
    /**
     * Which cards each bottom-bar tab shows.
     * Change this one method to move a setting to another tab.
     */
    void addTabCards(LinearLayout content) {
        if (tab == 1) {                 // Display
            content.addView(cardStyle());
            content.addView(cardTheme());
            content.addView(cardTypography());
            content.addView(cardDisplay());
            content.addView(cardGestures());
            content.addView(cardCustom());
            content.addView(cardReading());
        } else if (tab == 2) {          // Speech
            content.addView(cardPlayback());
            content.addView(cardSpeech());
            content.addView(cardTranslate());
            content.addView(cardVolume());
            content.addView(cardSleep());
            content.addView(cardFocus());
            content.addView(cardOffline());
        } else if (tab == 4) {          // More
            content.addView(cardSystem());
            content.addView(cardTipJar());
            content.addView(cardConfig());
            content.addView(cardAbout());
        } else {                        // Settings
            content.addView(cardAdBlock());
            content.addView(cardBattery());
            content.addView(cardPerformance());
            content.addView(cardAutomation());
            content.addView(cardShortcuts());
            content.addView(cardBackup());
            content.addView(cardInsights());
        }
    }

    /** Bottom navigation bar: Read / Display / Speech / Settings / More. */
    View bottomBar() {
        LinearLayout bar = new LinearLayout(act);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setGravity(Gravity.CENTER);
        bar.setBackground(rounded(isDark ? 0xE6141B2E : 0xF2FFFFFF, 0, cBorder, 1));
        bar.setPadding(px(4), px(10), px(4), px(10));
        String[] names = {"Read", "Display", "Speech", "Settings", "More"};
        for (int i = 0; i < names.length; i++) {
            final int id = i;
            TextView item = new TextView(act);
            item.setText(names[i]);
            item.setGravity(Gravity.CENTER);
            item.setTextSize(TypedSp(12));
            boolean active = (i == tab);
            item.setTextColor(active ? accent : cSub);
            item.setTypeface(Typeface.create(active ? "sans-serif-medium" : "sans-serif", Typeface.NORMAL));
            item.setPadding(px(4), px(8), px(4), px(8));
            item.setOnClickListener(v -> {
                if (id == 0) {
                    try {
                        dialog.dismiss();
                    } catch (Exception ignored) {
                    }
                    return;
                }
                tab = id;
                buildUI();
            });
            bar.addView(item, new LinearLayout.LayoutParams(0, -2, 1f));
        }
        return bar;
    }

    void buildUI() {
        computePalette();
        FrameLayout root = new FrameLayout(act);
        root.setBackgroundColor(cBg);
        container.setBackgroundColor(cBg);

        BgView bgv = new BgView(act);
        root.addView(bgv, new FrameLayout.LayoutParams(-1, -1));

        ScrollView sv = new ScrollView(act);
        sv.setFillViewport(true);
        LinearLayout content = new LinearLayout(act);
        content.setOrientation(LinearLayout.VERTICAL);
        int pad = px(18);
        content.setPadding(pad, px(28), pad, px(104));
        sv.addView(content, new ScrollView.LayoutParams(-1, -2));
        root.addView(sv, new FrameLayout.LayoutParams(-1, -1));

        TextView title = new TextView(act);
        title.setText(t("title"));
        title.setTextColor(cText);
        title.setTextSize(TypedSp(30));
        title.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        content.addView(title);

        TextView sub = new TextView(act);
        sub.setText(t("subtitle"));
        sub.setTextColor(cSub);
        sub.setTextSize(TypedSp(14));
        sub.setPadding(0, px(2), 0, px(16));
        content.addView(sub);

        // header developer branding
        LinearLayout headerBrand = new LinearLayout(act);
        headerBrand.setOrientation(LinearLayout.HORIZONTAL);
        headerBrand.setGravity(Gravity.CENTER_VERTICAL);
        headerBrand.addView(devLogo(), new LinearLayout.LayoutParams(px(36), px(36)));
        headerBrand.addView(spacerW(10));
        headerBrand.addView(sublabel(t("designed_dev")));
        LinearLayout.LayoutParams hlp = new LinearLayout.LayoutParams(-2, -2);
        hlp.bottomMargin = px(16);
        headerBrand.setLayoutParams(hlp);
        content.addView(headerBrand);

        addTabCards(content);

        // footer developer branding — clean & centered
        LinearLayout footer = new LinearLayout(act);
        footer.setOrientation(LinearLayout.VERTICAL);
        footer.setGravity(Gravity.CENTER_HORIZONTAL);
        footer.setPadding(0, px(10), 0, 0);
        TextView f1 = sublabel(t("designed_dev"));
        f1.setGravity(Gravity.CENTER);
        f1.setPadding(0, 0, 0, px(8));
        footer.addView(f1);
        TextView mk = new TextView(act);
        mk.setText("MAKE IN INDIA 🇮🇳");
        mk.setTextColor(0xFFFFFFFF);
        mk.setTextSize(TypedSp(11));
        mk.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        mk.setGravity(Gravity.CENTER);
        mk.setBackground(rounded(0xFFFF8F00, 999, 0xFFFF6D00, 1));
        mk.setPadding(px(14), px(5), px(14), px(5));
        footer.addView(mk);
        content.addView(footer);

        // close button overlay (panel is a dialog, give it a visible exit)
        TextView close = new TextView(act);
        close.setText("\u2715");
        close.setTextColor(cText);
        close.setTextSize(TypedSp(18));
        close.setGravity(Gravity.CENTER);
        close.setBackground(rounded(isDark ? 0x33FFFFFF : 0x22000000, 999, cBorder, 1));
        close.setPadding(px(10), px(6), px(10), px(6));
        close.setOnClickListener(v -> dialog.dismiss());
        root.addView(close, new android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.END | Gravity.TOP));

        root.addView(bottomBar(), new android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM));

        container.removeAllViews();
        applyThemeToSite();
        container.addView(root, new android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
    }

    float TypedSp(float sp) { return sp * scalePct / 100f; }

    LinearLayout cardShell() {
        LinearLayout card = new LinearLayout(act);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackground(styledCard());
        int p = px(18);
        card.setPadding(p, p, p, p);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.setMargins(0, 0, 0, px(18));
        card.setLayoutParams(lp);
        if (dynamicCards) {
            card.getBackground().setAlpha(colorPreset == PRESET_MONO ? 255 : (isDark ? 235 : 250));
        }
        return card;
    }

    TextView cardTitle(String s) {
        TextView t2 = new TextView(act);
        t2.setText(s);
        t2.setTextColor(isDark ? lighten(accent, 0.30f) : accent);
        t2.setTextSize(TypedSp(12));
        t2.setLetterSpacing(0.12f);
        t2.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        t2.setAllCaps(true);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.bottomMargin = px(12);
        t2.setLayoutParams(lp);
        return t2;
    }

    TextView label(String s) {
        TextView t2 = new TextView(act);
        t2.setText(s);
        t2.setTextColor(cText);
        t2.setTextSize(TypedSp(15));
        return t2;
    }

    TextView sublabel(String s) {
        TextView t2 = new TextView(act);
        t2.setText(s);
        t2.setTextColor(cSub);
        t2.setTextSize(TypedSp(12));
        return t2;
    }

    LinearLayout chipRow() {
        LinearLayout row = new LinearLayout(act);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.bottomMargin = px(8);
        row.setLayoutParams(lp);
        return row;
    }

    TextView chip(String text, boolean selected, boolean accentStyle, View.OnClickListener l) {
        TextView c = new TextView(act);
        c.setText(text);
        c.setTextSize(TypedSp(13));
        c.setGravity(Gravity.CENTER);
        c.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        if (selected) c.setTextColor(0xFFFFFFFF);
        else c.setTextColor(cSub);
        c.setBackground(pill(accent, selected, accentStyle));
        c.setPadding(px(14), px(8), px(14), px(8));
        c.setOnClickListener(l);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        lp.rightMargin = px(8);
        c.setLayoutParams(lp);
        return c;
    }

    // ---------- 1. UI style ----------
    View cardStyle() {
        LinearLayout card = cardShell();
        card.addView(cardTitle(t("sec_style")));
        TextView hint = sublabel("Glassmorphism · Claymorphism · Skeuomorphism · Neumorphism");
        hint.setPadding(0, 0, 0, px(12));
        card.addView(hint);
        LinearLayout row = chipRow();
        String[] names = {"Glass", "Clay", "Skeuo", "Neu"};
        for (int i = 0; i < 4; i++) {
            final int id = i;
            row.addView(chip(names[i], uiStyle == i, true, v -> {
                uiStyle = id; prefs.edit().putInt("style", id).apply(); buildUI();
            }));
        }
        card.addView(wrapRow(row));
        return card;
    }

    // ---------- 2. theme & colors ----------
    View cardTheme() {
        LinearLayout card = cardShell();
        card.addView(cardTitle(t("sec_theme")));

        // Language
        LinearLayout ls = new LinearLayout(act);
        ls.setOrientation(LinearLayout.VERTICAL);
        ls.setPadding(0, 0, 0, px(12));
        LinearLayout langRow = new LinearLayout(act);
        langRow.setOrientation(LinearLayout.HORIZONTAL);
        langRow.setGravity(Gravity.CENTER_VERTICAL);
        langRow.addView(label(t("current_language")));
        TextView spacer = new TextView(act);
        spacer.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1f));
        langRow.addView(spacer);
        TextView langVal = new TextView(act);
        langVal.setText(Strings.LANG_NAMES[langIdx] + " ›");
        langVal.setTextColor(accent);
        langVal.setTextSize(TypedSp(13));
        langRow.addView(langVal);
        langRow.setOnClickListener(v -> showLanguageDialog());
        ls.addView(langRow);
        card.addView(ls);

        // interface scale
        LinearLayout scaleRow = new LinearLayout(act);
        scaleRow.setOrientation(LinearLayout.VERTICAL);
        scaleRow.setPadding(0, 0, 0, px(14));
        LinearLayout sr = new LinearLayout(act);
        sr.setOrientation(LinearLayout.HORIZONTAL);
        sr.setGravity(Gravity.CENTER_VERTICAL);
        sr.addView(label(t("interface_scale")));
        TextView sp2 = new TextView(act);
        sp2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1f));
        sr.addView(sp2);
        TextView pct = new TextView(act);
        pct.setText(scalePct + "%");
        pct.setTextColor(accent);
        pct.setTextSize(TypedSp(13));
        sr.addView(pct);
        scaleRow.addView(sr);
        SeekBar seek = new SeekBar(act);
        seek.setMax(60); // 80% .. 140%
        seek.setProgress(scalePct - 80);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) { }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                scalePct = s.getProgress() + 80;
                prefs.edit().putInt("scale", scalePct).apply();
                buildUI();
            }
        });
        scaleRow.addView(seek, new LinearLayout.LayoutParams(-1, -2));
        card.addView(scaleRow);

        // theme mode
        card.addView(label(t("theme_mode")));
        LinearLayout modeRow = chipRow();
        String[] modes = {t("sys"), t("light"), t("dark")};
        for (int i = 0; i < 3; i++) {
            final int id = i;
            modeRow.addView(chip(modes[i], themeMode == i, false, v -> {
                themeMode = id; prefs.edit().putInt("theme", id).apply(); buildUI();
            }));
        }
        card.addView(wrapRow(modeRow));
        card.addView(spacer(6));

        // color preset
        card.addView(label(t("color_preset")));
        LinearLayout presetRow = chipRow();
        String[] presets = {"Love", "Material You", "Monochrome"};
        for (int i = 0; i < 3; i++) {
            final int id = i;
            presetRow.addView(chip(presets[i], colorPreset == i, false, v -> {
                colorPreset = id; prefs.edit().putInt("preset", id).apply(); buildUI();
            }));
        }
        card.addView(wrapRow(presetRow));
        card.addView(spacer(6));

        // accent swatches
        card.addView(label(t("accent_color")));
        LinearLayout sw = new LinearLayout(act);
        sw.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams swlp = new LinearLayout.LayoutParams(-1, -2);
        swlp.bottomMargin = px(10);
        sw.setLayoutParams(swlp);
        for (int i = 0; i < ACCENTS.length; i++) {
            final int color = ACCENTS[i];
            View dot = new View(act);
            GradientDrawable g = rounded(color, 999, color == accent ? cText : 0x33000000, color == accent ? 2.5f : 1.2f);
            dot.setBackground(g);
            LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(px(34), px(34));
            dlp.rightMargin = px(10);
            dot.setLayoutParams(dlp);
            dot.setOnClickListener(v -> { accent = color; prefs.edit().putInt("accent", color).apply(); buildUI(); });
            sw.addView(dot);
        }
        sw.addView(chip(t("custom"), false, true, v -> showColorPicker()));
        card.addView(sw);

        // toggles
        card.addView(toggleRow(t("dyn_cards"), t("dyn_cards_sub"), dynamicCards, checked -> {
            dynamicCards = checked;
            prefs.edit().putBoolean("dyncard", dynamicCards).apply();
            buildUI();
        }));
        card.addView(toggleRow("Apply theme to website",
                "Match the novel page to the theme, colours and accent chosen here",
                siteTheme, checked -> {
                    siteTheme = checked;
                    prefs.edit().putBoolean("siteTheme", checked).apply();
                    applyThemeToSite();
                }));
        card.addView(toggleRow(t("bg_anim"), t("bg_anim_sub"), bgAnim, checked -> {
            bgAnim = checked;
            prefs.edit().putBoolean("bganim", bgAnim).apply();
            buildUI();
        }));
        return card;
    }

    android.view.View wrapRow(LinearLayout row) {
        android.widget.HorizontalScrollView sc = new android.widget.HorizontalScrollView(act);
        sc.setHorizontalScrollBarEnabled(false);
        sc.addView(row, new android.widget.FrameLayout.LayoutParams(-2, -2));
        sc.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        return sc;
    }

    View spacer(float dp) {
        View v = new View(act);
        v.setLayoutParams(new LinearLayout.LayoutParams(1, px(dp)));
        return v;
    }

    View toggleRow(String t2, String s, boolean checked, CompoundListener l) {
        LinearLayout row = new LinearLayout(act);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, px(10), 0, px(2));
        LinearLayout txt = new LinearLayout(act);
        txt.setOrientation(LinearLayout.VERTICAL);
        txt.addView(label(t2));
        txt.addView(sublabel(s));
        row.addView(txt, new LinearLayout.LayoutParams(0, -2, 1f));
        Switch sw = new Switch(act);
        sw.setChecked(checked);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> l.onChange(isChecked));
        row.addView(sw);
        return row;
    }

    interface CompoundListener { void onChange(boolean v); }

    // ---------- 3. system ----------
    // ---------- playback controls (mirrors the notification actions) ----------
    View cardPlayback() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("PLAYBACK"));
        TextView hint = sublabel("Control the reader's speech from here, same as the notification buttons.");
        hint.setPadding(0, 0, 0, px(12));
        card.addView(hint);
        LinearLayout row = chipRow();
        row.addView(chip("Play", false, true, v -> playback("play")));
        row.addView(chip("Pause", false, true, v -> playback("pause")));
        row.addView(chip("Stop", false, true, v -> playback("stop")));
        card.addView(wrapRow(row));
        card.addView(toggleRow("Stable playback (recommended)",
                "Speaks each part in one go - the original, never-stops behaviour. Turn OFF only if a very long chapter gets cut off.",
                simpleTts, checked -> {
                    simpleTts = checked;
                    prefs.edit().putBoolean("simpleTts", checked).apply();
                    toast(checked ? "Stable playback ON" : "Text splitting ON");
                }));
        LinearLayout trow = chipRow();
        trow.addView(chip("Reading log", false, true, v -> showReadingLog()));
        trow.addView(chip("Test voice", false, true, v -> {
            try {
                com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                if (bridge != null) {
                    bridge.speak("This is a test of the reader voice.", "en-US", 1.0, 1.0, "test-voice", "");
                } else {
                    toast("Open a chapter first");
                }
            } catch (Exception ignored) {
            }
        }));
        card.addView(wrapRow(trow));
        return card;
    }

    void showReadingLog() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            String log = bridge == null ? "(not available)" : bridge.getEventLog();
            if (log == null || log.length() == 0) {
                log = "(no events yet - start reading, then open this again)";
            }
            android.widget.ScrollView sv = new android.widget.ScrollView(act);
            TextView tv = new TextView(act);
            tv.setText(log);
            tv.setTextSize(TypedSp(11));
            tv.setTextColor(cText);
            tv.setPadding(px(14), px(14), px(14), px(14));
            tv.setTextIsSelectable(true);
            sv.addView(tv);
            new android.app.AlertDialog.Builder(act)
                    .setTitle("Reading log")
                    .setView(sv)
                    .setPositiveButton("Close", null)
                    .show();
        } catch (Exception ignored) {
        }
    }

        /** Settings file support - the studio app writes these, the reader reads them. */
    public static String pendingConfigJson;

    private static final String CFG_BOOLS =
            " adBlock ambient amoled autoAdvance autoDark autoScroll bganim bionic capture dblTap "
            + "dyncard highlightPara keepScreenOn perfMode simpleTts siteTheme twoFingerBright typoOn volKeys ";
    private static final String CFG_INTS =
            " accent autoScrollSpeed bright darkFrom darkTo focusMin fontIdx lang lineHeight pageWidth "
            + "preset scale sleep style textAlign textSize theme volMode audioLimit ";
    private static final String CFG_FLOATS = " readRate volume ";

    private static boolean isBoolKey(String k) { return CFG_BOOLS.contains(" " + k + " "); }
    private static boolean isIntKey(String k) { return CFG_INTS.contains(" " + k + " "); }
    private static boolean isFloatKey(String k) { return CFG_FLOATS.contains(" " + k + " "); }

    /** Current settings as a JSON document. */
    String buildConfigJson() {
        try {
            org.json.JSONObject prefsOut = new org.json.JSONObject();
            for (String k : CFG_BOOLS.trim().split("\\s+")) {
                prefsOut.put(k, prefs.getBoolean(k, false));
            }
            for (String k : CFG_INTS.trim().split("\\s+")) {
                prefsOut.put(k, prefs.getInt(k, 0));
            }
            for (String k : CFG_FLOATS.trim().split("\\s+")) {
                prefsOut.put(k, (double) prefs.getFloat(k, 1.0f));
            }
            prefsOut.put("customCss", prefs.getString("customCss", ""));
            prefsOut.put("homeUrl", prefs.getString("homeUrl", "https://wtr-lab.com/"));
            prefsOut.put("readVoice", prefs.getString("readVoice", ""));
            prefsOut.put("ttsEngine", prefs.getString("ttsEngine", ""));
            prefsOut.put("customBlocked", prefs.getString("customBlocked", ""));
            org.json.JSONObject root = new org.json.JSONObject();
            root.put("app", "vasu-settings");
            root.put("version", 1);
            root.put("prefs", prefsOut);
            return root.toString();
        } catch (Exception e) {
            return "{}";
        }
    }

    /** Applies a settings document written by the studio app or the config file. */
    public static int applyConfigJson(android.content.Context ctx, String json) {
        int n = 0;
        try {
            org.json.JSONObject root = new org.json.JSONObject(json);
            org.json.JSONObject p = root.optJSONObject("prefs");
            if (p == null) {
                p = root;
            }
            android.content.SharedPreferences prefs =
                    ctx.getSharedPreferences("ui", android.content.Context.MODE_PRIVATE);
            android.content.SharedPreferences.Editor e = prefs.edit();
            java.util.Iterator<String> it = p.keys();
            while (it.hasNext()) {
                String k = it.next();
                if (isBoolKey(k)) {
                    e.putBoolean(k, p.optBoolean(k, false));
                    n++;
                } else if (isIntKey(k)) {
                    e.putInt(k, p.optInt(k, 0));
                    n++;
                } else if (isFloatKey(k)) {
                    e.putFloat(k, (float) p.optDouble(k, 1.0));
                    n++;
                } else if (k.equals("customCss") || k.equals("homeUrl") || k.equals("readVoice")
                        || k.equals("ttsEngine") || k.equals("customBlocked") || k.equals("transLang")) {
                    e.putString(k, p.optString(k, ""));
                    n++;
                }
            }
            e.apply();
            try {
                com.vasudev.wtrlab.TtsBridge b =
                        com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                if (b != null) {
                    b.refreshAutoTheme();
                    b.logEvent("config applied: " + n + " settings");
                }
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
        }
        return n;
    }

    void exportConfigFile() {
        try {
            pendingConfigJson = buildConfigJson();
            android.content.Intent i = new android.content.Intent(
                    android.content.Intent.ACTION_CREATE_DOCUMENT);
            i.addCategory(android.content.Intent.CATEGORY_OPENABLE);
            i.setType("application/json");
            i.putExtra(android.content.Intent.EXTRA_TITLE, "vasu-config.json");
            act.startActivityForResult(i, 301);
        } catch (Exception e) {
            toast("File picker nahi khula");
        }
    }

    void importConfigFile() {
        try {
            android.content.Intent i = new android.content.Intent(
                    android.content.Intent.ACTION_OPEN_DOCUMENT);
            i.addCategory(android.content.Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            act.startActivityForResult(i, 302);
        } catch (Exception e) {
            toast("File picker nahi khula");
        }
    }

    void saveLogFile() {
        try {
            com.vasudev.wtrlab.TtsBridge b =
                    com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            pendingConfigJson = b == null ? "(no log)" : b.getEventLog();
            android.content.Intent i = new android.content.Intent(
                    android.content.Intent.ACTION_CREATE_DOCUMENT);
            i.addCategory(android.content.Intent.CATEGORY_OPENABLE);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_TITLE, "vasu-reading-log.txt");
            act.startActivityForResult(i, 301);
        } catch (Exception e) {
            toast("File picker nahi khula");
        }
    }

    View cardConfig() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("SETTINGS FILE"));
        card.addView(sublabel("Vasu Settings Studio se settings push karo, ya yahan se file export/import karo."));
        LinearLayout row = chipRow();
        row.addView(chip("Export settings file", false, true, v -> exportConfigFile()));
        row.addView(chip("Import settings file", false, true, v -> importConfigFile()));
        card.addView(wrapRow(row));
        LinearLayout row2 = chipRow();
        row2.addView(chip("Save log to file", false, true, v -> saveLogFile()));
        card.addView(wrapRow(row2));
        return card;
    }

    View cardSpeech() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("SPEECH"));
        card.addView(sublabel("Engine, playback, voice and speed for the reader's voice. Works on any site."));
        card.addView(label("TTS ENGINE"));
        String curEngine = "com.google.android.tts";
        try {
            curEngine = prefs.getString("ttsEngine", "com.google.android.tts");
        } catch (Exception ignored) {
        }
        final java.util.List<String> pkgs = new java.util.ArrayList<>();
        final java.util.List<String> labels = new java.util.ArrayList<>();
        try {
            android.content.pm.PackageManager pm = act.getPackageManager();
            java.util.List<android.content.pm.ResolveInfo> ris = pm.queryIntentServices(
                    new android.content.Intent("android.intent.action.TTS_SERVICE"), 0);
            if (ris != null) {
                for (android.content.pm.ResolveInfo ri : ris) {
                    if (ri.serviceInfo == null || ri.serviceInfo.packageName == null) {
                        continue;
                    }
                    String pname = ri.serviceInfo.packageName;
                    if (pkgs.contains(pname)) {
                        continue;
                    }
                    pkgs.add(pname);
                    CharSequence lbl = ri.loadLabel(pm);
                    labels.add(lbl == null ? pname : lbl.toString());
                }
            }
        } catch (Exception ignored) {
        }
        if (pkgs.isEmpty()) {
            pkgs.add("com.google.android.tts");
            labels.add("Google");
        }
        LinearLayout erow = chipRow();
        for (int i = 0; i < pkgs.size(); i++) {
            final String p = pkgs.get(i);
            erow.addView(chip(labels.get(i), p.equals(curEngine), true, v -> {
                prefs.edit().putString("ttsEngine", p).apply();
                try {
                    com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                    if (bridge != null) {
                        bridge.setEnginePackage(p);
                    }
                } catch (Exception ignored) {
                }
                toast("Engine: " + p);
                buildUI();
            }));
        }
        card.addView(wrapRow(erow));

        card.addView(label("PLAYBACK"));
        LinearLayout prow = chipRow();
        prow.addView(chip("Play", false, true, v -> playback("play")));
        prow.addView(chip("Pause", false, true, v -> playback("pause")));
        prow.addView(chip("Stop", false, true, v -> playback("stop")));
        prow.addView(chip("Read this page", false, true, v -> readPage()));
        card.addView(wrapRow(prow));

        card.addView(label("VOICE"));
        String curVoice = "";
        try {
            curVoice = prefs.getString("readVoice", "");
        } catch (Exception ignored) {
        }
        LinearLayout vrow = chipRow();
        vrow.addView(chip(curVoice.length() == 0 ? "Choose voice" : curVoice, false, true, v -> showReadVoiceDialog()));
        vrow.addView(chip("Refresh voices", false, true, v -> {
            try {
                com.vasudev.wtrlab.TtsBridge b =
                        com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                if (b != null) {
                    b.restartEngine(b.getEnginePackage());
                }
            } catch (Exception ignored) {
            }
            toast("TTS engine restart - 2 second baad voice chuno");
        }));
        card.addView(wrapRow(vrow));

        card.addView(label("SPEED"));
        float curRate = 1.0f;
        try {
            curRate = prefs.getFloat("readRate", 1.0f);
        } catch (Exception ignored) {
        }
        final float[] rates = {0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
        LinearLayout srow = chipRow();
        for (int i = 0; i < rates.length; i++) {
            final float fr = rates[i];
            String lab = fr == 1.0f ? "1x" : (fr == 2.0f ? "2x" : fr + "x");
            srow.addView(chip(lab, Math.abs(fr - curRate) < 0.01f, true, v -> {
                prefs.edit().putFloat("readRate", fr).apply();
                toast("Speed: " + fr + "x");
                buildUI();
            }));
        }
        card.addView(wrapRow(srow));

        LinearLayout trow = chipRow();
        trow.addView(chip("Test voice", false, true, v -> testVoice()));
        card.addView(wrapRow(trow));

        card.addView(toggleRow("Auto-advance to next chapter",
                "When a page finishes, open the next chapter and keep reading",
                autoAdvance, checked -> {
                    autoAdvance = checked;
                    prefs.edit().putBoolean("autoAdvance", checked).apply();
                }));
        card.addView(toggleRow("Highlight paragraphs while reading",
                "Tint the paragraph being read and scroll to it",
                highlightPara, checked -> {
                    highlightPara = checked;
                    prefs.edit().putBoolean("highlightPara", checked).apply();
                }));
        return card;
    }
    View cardTranslate() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("TRANSLATE PAGE"));
        card.addView(sublabel("Translate this chapter into your language, then read it or listen to it."));
        final String[] names = {"Hindi", "English", "Bengali", "Tamil", "Telugu", "Marathi"};
        final String[] codes = {"hi", "en", "bn", "ta", "te", "mr"};
        String cur = "hi";
        try {
            cur = prefs.getString("transLang", "hi");
        } catch (Exception ignored) {
        }
        card.addView(label("TRANSLATE TO"));
        LinearLayout row = chipRow();
        for (int i = 0; i < names.length; i++) {
            final String c = codes[i];
            row.addView(chip(names[i], c.equals(cur), true, v -> {
                prefs.edit().putString("transLang", c).apply();
                buildUI();
            }));
        }
        card.addView(wrapRow(row));
        LinearLayout brow = chipRow();
        brow.addView(chip("Translate this page", false, true, v -> doTranslate(currentLang(), false)));
        brow.addView(chip("Translate & read aloud", false, true, v -> doTranslate(currentLang(), true)));
        card.addView(wrapRow(brow));
        card.addView(sublabel("Uses Google Translate, with a second service as backup. Needs internet."));
        return card;
    }
    void readPage() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.readPageAloud();
                toast("Reading this page aloud");
            }
        } catch (Exception ignored) {
        }
    }
    void testVoice() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.speak("This is a test of the reader voice.", "en-US",
                        bridge.getReadRate(), 1.0, "test-voice", bridge.getReadVoice());
            } else {
                toast("Open a chapter first");
            }
        } catch (Exception ignored) {
        }
    }
    void showReadVoiceDialog() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            org.json.JSONArray arr = new org.json.JSONArray(bridge == null ? "[]" : bridge.getVoicesJson());
            final java.util.List<String> ids = new java.util.ArrayList<>();
            final java.util.List<String> names = new java.util.ArrayList<>();
            ids.add("");
            names.add("System default");
            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONObject o = arr.getJSONObject(i);
                String uri = o.optString("voiceURI", "");
                if (uri.length() == 0) {
                    continue;
                }
                String nm = o.optString("name", uri);
                String lg = o.optString("lang", "");
                ids.add(uri);
                names.add(nm + (lg.length() > 0 ? "  (" + lg + ")" : ""));
            }
            final String[] items = names.toArray(new String[0]);
            new android.app.AlertDialog.Builder(act)
                    .setTitle("Reader voice")
                    .setItems(items, (d, which) -> {
                        prefs.edit().putString("readVoice", ids.get(which)).apply();
                        toast("Voice: " + items[which]);
                        buildUI();
                    })
                    .show();
        } catch (Exception e) {
            toast("Voice list is not ready yet");
        }
    }
    String currentLang() {
        try {
            return prefs.getString("transLang", "hi");
        } catch (Exception e) {
            return "hi";
        }
    }
    String ttsLocale(String code) {
        if ("hi".equals(code)) return "hi-IN";
        if ("en".equals(code)) return "en-US";
        if ("bn".equals(code)) return "bn-IN";
        if ("ta".equals(code)) return "ta-IN";
        if ("te".equals(code)) return "te-IN";
        if ("mr".equals(code)) return "mr-IN";
        return code;
    }
    void doTranslate(final String tl, final boolean speak) {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge == null) {
                toast("Open a chapter first");
                return;
            }
            toast("Reading the page...");
            bridge.fetchPageText(text -> {
                if (text == null || text.trim().length() < 40) {
                    toast("Could not find text on this page");
                    return;
                }
                toast("Translating, please wait...");
                new Thread(() -> {
                    final String out = translateAll(text, tl);
                    act.runOnUiThread(() -> {
                        if (out == null || out.trim().length() == 0) {
                            toast("Translation failed - check your internet");
                        } else {
                            showTranslated(out, tl, speak);
                        }
                    });
                }).start();
            });
        } catch (Exception ignored) {
        }
    }
    String translateAll(String text, String tl) {
        try {
            String t = text.replace("\r", " ").replace("\n", "\n");
            java.util.List<String> chunks = new java.util.ArrayList<>();
            int max = 1500;
            while (t.length() > max) {
                int cut = t.lastIndexOf('.', max);
                if (cut < 300) cut = t.lastIndexOf(' ', max);
                if (cut < 300) cut = max;
                chunks.add(t.substring(0, cut + 1));
                t = t.substring(cut + 1);
            }
            if (t.trim().length() > 0) chunks.add(t);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chunks.size() && i < 40; i++) {
                String part = translateChunk(chunks.get(i), tl);
                if (part == null) return null;
                sb.append(part).append('\n');
            }
            return sb.toString().trim();
        } catch (Exception e) {
            return null;
        }
    }
    String translateChunk(String q, String tl) {
        try {
            String enc = java.net.URLEncoder.encode(q, "UTF-8");
            // 1. Chrome dictionary endpoint - simple JSON, works widely
            String g1 = httpGet("https://clients5.google.com/translate_a/t?client=dict-chrome-ex&sl=auto&tl="
                    + tl + "&q=" + enc);
            if (g1 != null) {
                String r = parseFlat(g1);
                if (r != null && r.trim().length() > 0) return r;
            }
            // 2. Google gtx endpoint
            String g2 = httpGet("https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl="
                    + tl + "&dt=t&q=" + enc);
            if (g2 != null) {
                String r = parseSingle(g2);
                if (r != null && r.trim().length() > 0) return r;
            }
            // 3. MyMemory - independent service, last resort
            String shortQ = q.length() > 480 ? q.substring(0, 480) : q;
            String m = httpGet("https://api.mymemory.translated.net/get?q="
                    + java.net.URLEncoder.encode(shortQ, "UTF-8") + "&langpair=auto|" + tl);
            if (m != null) {
                org.json.JSONObject o = new org.json.JSONObject(m);
                org.json.JSONObject rd = o.optJSONObject("responseData");
                if (rd != null) {
                    String r = rd.optString("translatedText", "");
                    if (r.length() > 0 && !r.toLowerCase().contains("invalid")) return r;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }
    String parseFlat(String g) {
        try {
            org.json.JSONArray arr = new org.json.JSONArray(g);
            StringBuilder sb = new StringBuilder();
            if (arr.length() > 0 && arr.optJSONArray(0) != null) {
                for (int i = 0; i < arr.length(); i++) {
                    org.json.JSONArray seg = arr.optJSONArray(i);
                    if (seg != null && seg.length() > 0) sb.append(seg.optString(0, ""));
                }
            } else if (arr.length() > 0) {
                sb.append(arr.optString(0, ""));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    String parseSingle(String g) {
        try {
            org.json.JSONArray arr = new org.json.JSONArray(g);
            org.json.JSONArray segs = arr.getJSONArray(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < segs.length(); i++) {
                org.json.JSONArray seg = segs.optJSONArray(i);
                if (seg != null && seg.length() > 0) sb.append(seg.optString(0, ""));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    String httpGet(String url) {
        java.net.HttpURLConnection c = null;
        try {
            c = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
            c.setConnectTimeout(12000);
            c.setReadTimeout(15000);
            c.setRequestProperty("User-Agent", "Mozilla/5.0");
            if (c.getResponseCode() != 200) return null;
            java.io.InputStream in = c.getInputStream();
            java.io.ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) {
                bo.write(buf, 0, n);
            }
            in.close();
            return new String(bo.toByteArray(), "UTF-8");
        } catch (Exception e) {
            return null;
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ignored) {
                }
            }
        }
    }
    void showTranslated(String text, String tl, boolean speak) {
        try {
            android.widget.ScrollView sv = new android.widget.ScrollView(act);
            TextView tv = new TextView(act);
            tv.setText(text);
            tv.setTextSize(TypedSp(15));
            tv.setTextColor(cText);
            tv.setPadding(px(16), px(16), px(16), px(16));
            tv.setTextIsSelectable(true);
            sv.addView(tv);
            new android.app.AlertDialog.Builder(act)
                    .setTitle("Translated")
                    .setView(sv)
                    .setPositiveButton("Read aloud", (d, w) -> speakTranslated(text, tl))
                    .setNegativeButton("Close", null)
                    .show();
            if (speak) {
                speakTranslated(text, tl);
            }
        } catch (Exception ignored) {
        }
    }
    void speakTranslated(String text, String tl) {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.speak(text, ttsLocale(tl), bridge.getReadRate(), 1.0, "translated", bridge.getReadVoice());
            }
        } catch (Exception ignored) {
        }
    }

    void playback(String action) {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge == null) {
                toast("Open a chapter first, then use these controls.");
                return;
            }
            if ("pause".equals(action)) {
                bridge.jsPause();
            } else if ("stop".equals(action)) {
                bridge.jsCancel();
            } else {
                bridge.jsResume();
            }
        } catch (Exception e) {
            toast("Playback is not available right now.");
        }
    }

    // ---------- playback volume ----------
    View cardVolume() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("PLAYBACK VOLUME"));
        LinearLayout sr = new LinearLayout(act);
        sr.setOrientation(LinearLayout.HORIZONTAL);
        sr.setGravity(Gravity.CENTER_VERTICAL);
        sr.addView(label("Speech volume"));
        TextView sp = new TextView(act);
        sp.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1f));
        sr.addView(sp);
        final TextView pct = new TextView(act);
        pct.setText(volumePct + "%");
        pct.setTextColor(accent);
        pct.setTextSize(TypedSp(13));
        sr.addView(pct);
        card.addView(sr);
        SeekBar seek = new SeekBar(act);
        seek.setMax(100);
        seek.setProgress(volumePct);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                pct.setText(v + "%");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                volumePct = s.getProgress();
                prefs.edit().putFloat("volume", volumePct / 100f).apply();
                applyVolume();
            }
        });
        card.addView(seek, new LinearLayout.LayoutParams(-1, -2));
        TextView note = sublabel("Applies to the reader's speech and in-page audio.");
        note.setPadding(0, 0, 0, px(4));
        card.addView(note);
        card.addView(toggleRow("Volume keys", "Adjust speech volume with the hardware volume buttons",
                volKeys, checked -> {
                    volKeys = checked;
                    prefs.edit().putBoolean("volKeys", checked).apply();
                }));
        return card;
    }

    String buildSiteCss(boolean dark) {
        int bg = dark ? (amoled ? 0xFF000000 : 0xFF101418) : 0xFFF2F6FB;
        int fg = dark ? 0xFFECEDF2 : 0xFF191B22;
        return ":root{color-scheme:" + (dark ? "dark" : "light") + ";}"
                + "html,body{background:" + hex(bg) + " !important;color:" + hex(fg) + " !important;}"
                + "a,a:visited{color:" + hex(accent) + " !important;}"
                + "::selection{background:" + hex(accent) + " !important;color:#FFFFFF !important;}"
                + "input,textarea,select{background-color:" + hex(bg) + " !important;color:" + hex(fg) + " !important;}";
    }

    /** Push the app theme (light + dark variants) and custom CSS into the site. */
    void applyThemeToSite() {
        try {
            String darkCss = buildSiteCss(true);
            String lightCss = buildSiteCss(false);
            prefs.edit().putString("siteCssDark", darkCss).putString("siteCssLight", lightCss).apply();
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge == null) {
                return;
            }
            if (!siteTheme) {
                bridge.setSiteTheme("", isDark);
                return;
            }
            String base = isDark ? darkCss : lightCss;
            String typo = buildTypoCss();
            if (typo.length() > 0) {
                base = base + "\n" + typo;
            }
            if (customCss != null && customCss.length() > 0) {
                base = base + "\n" + customCss;
            }
            bridge.setSiteTheme(base, isDark);
        } catch (Exception ignored) {
        }
    }

    void pushAutoScroll() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.setAutoScroll(autoScroll, autoScrollSpeed * 2);
            }
        } catch (Exception ignored) {
        }
    }

    void exportStats() {
        try {
            java.io.File dir = act.getExternalFilesDir(null);
            if (dir == null) {
                dir = act.getFilesDir();
            }
            java.io.File f = new java.io.File(dir, "reading_stats.csv");
            java.io.FileWriter w = new java.io.FileWriter(f);
            w.write("date,minutes\n");
            java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US);
            java.util.Calendar c = java.util.Calendar.getInstance();
            for (int i = 29; i >= 0; i--) {
                String key = "rd" + new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.US).format(c.getTime());
                w.write(fmt.format(c.getTime()) + "," + prefs.getInt(key, 0) + "\n");
                c.add(java.util.Calendar.DAY_OF_YEAR, -1);
            }
            w.close();
            toast("Saved: " + f.getAbsolutePath());
        } catch (Exception e) {
            toast("Export failed");
        }
    }

    void exportSettings() {
        try {
            java.io.File dir = act.getExternalFilesDir(null);
            if (dir == null) {
                dir = act.getFilesDir();
            }
            java.io.File f = new java.io.File(dir, "settings_backup.txt");
            java.io.FileWriter w = new java.io.FileWriter(f);
            for (java.util.Map.Entry<String, ?> e : prefs.getAll().entrySet()) {
                w.write(e.getKey() + "=" + String.valueOf(e.getValue()) + "\n");
            }
            w.close();
            toast("Saved: " + f.getAbsolutePath());
        } catch (Exception e) {
            toast("Backup failed");
        }
    }

    void restoreSettings() {
        try {
            java.io.File dir = act.getExternalFilesDir(null);
            if (dir == null) {
                dir = act.getFilesDir();
            }
            java.io.File f = new java.io.File(dir, "settings_backup.txt");
            if (!f.exists()) {
                toast("No backup file found");
                return;
            }
            java.io.BufferedReader r = new java.io.BufferedReader(new java.io.FileReader(f));
            android.content.SharedPreferences.Editor ed = prefs.edit();
            String line;
            while ((line = r.readLine()) != null) {
                int i = line.indexOf('=');
                if (i <= 0) {
                    continue;
                }
                String k = line.substring(0, i);
                String v = line.substring(i + 1);
                if (v.equals("true") || v.equals("false")) {
                    ed.putBoolean(k, v.equals("true"));
                } else {
                    try {
                        ed.putInt(k, Integer.parseInt(v));
                    } catch (Exception ex) {
                        try {
                            ed.putFloat(k, Float.parseFloat(v));
                        } catch (Exception ex2) {
                            ed.putString(k, v);
                        }
                    }
                }
            }
            r.close();
            ed.apply();
            toast("Settings restored - close and reopen Settings");
        } catch (Exception e) {
            toast("Restore failed");
        }
    }

    String buildTypoCss() {
        if (!typoOn) {
            return "";
        }
        String fam = "";
        if (fontIdx == 1) {
            fam = "Georgia, 'Times New Roman', serif";
        } else if (fontIdx == 2) {
            fam = "system-ui, Roboto, 'Noto Sans', sans-serif";
        } else if (fontIdx == 3) {
            fam = "ui-monospace, 'Roboto Mono', monospace";
        } else if (fontIdx == 4) {
            fam = "'Comic Sans MS', 'Segoe UI', sans-serif";
        }
        int px = Math.round(17f * textSizePct / 100f);
        String align = textAlignIdx == 1 ? "left" : (textAlignIdx == 2 ? "justify" : "start");
        String css = "";
        if (fam.length() > 0) {
            css = css + "body, p, span, div, li {font-family:" + fam + " !important;}";
        }
        css = css + "p {font-size:" + px + "px !important;line-height:"
                + String.format(java.util.Locale.US, "%.2f", lineHeightPct / 100f)
                + " !important;text-align:" + align + " !important;}";
        if (pageWidthPct < 100) {
            int pad = Math.round((100 - pageWidthPct) * 0.45f);
            css = css + "body{padding-left:" + pad + "% !important;padding-right:" + pad + "% !important;}";
        }
        return css;
    }

    void applyTypo() {
        try {
            String css = buildTypoCss();
            prefs.edit().putString("typoCss", css).apply();
            applyThemeToSite();
        } catch (Exception ignored) {
        }
    }

    void refreshTheme() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.refreshAutoTheme();
            }
        } catch (Exception ignored) {
        }
    }

    static String pad2(int h) {
        return (h < 10 ? "0" : "") + h;
    }

    static String hex(int c) {
        return String.format("#%06X", 0xFFFFFF & c);
    }

    void applyVolume() {
        try {
            com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
            if (bridge != null) {
                bridge.setVolume(volumePct / 100f);
            }
        } catch (Exception ignored) {
        }
    }

    // ---------- display ----------
    View cardDisplay() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("DISPLAY"));
        card.addView(toggleRow("Keep screen on", "Prevent the screen from switching off while reading",
                keepScreenOn, checked -> {
                    keepScreenOn = checked;
                    prefs.edit().putBoolean("keepScreenOn", checked).apply();
                    try {
                        if (checked) {
                            act.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        } else {
                            act.getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        }
                    } catch (Exception ignored) {
                    }
                }));
        LinearLayout sr = new LinearLayout(act);
        sr.setOrientation(LinearLayout.HORIZONTAL);
        sr.setGravity(Gravity.CENTER_VERTICAL);
        sr.setPadding(0, px(12), 0, 0);
        sr.addView(label("Screen brightness"));
        TextView sp = new TextView(act);
        sp.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1f));
        sr.addView(sp);
        final TextView bpct = new TextView(act);
        bpct.setText(brightPct + "%");
        bpct.setTextColor(accent);
        bpct.setTextSize(TypedSp(13));
        sr.addView(bpct);
        card.addView(sr);
        SeekBar seek = new SeekBar(act);
        seek.setMax(90);
        seek.setProgress(brightPct - 10);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                bpct.setText((v + 10) + "%");
                applyBrightness(v + 10);
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                brightPct = s.getProgress() + 10;
                prefs.edit().putInt("bright", brightPct).apply();
            }
        });
        card.addView(seek, new LinearLayout.LayoutParams(-1, -2));
        card.addView(toggleRow("AMOLED black",
                "Pure black background in dark mode (saves battery on OLED screens)",
                amoled, checked -> {
                    amoled = checked;
                    prefs.edit().putBoolean("amoled", checked).apply();
                    applyThemeToSite();
                }));
        card.addView(toggleRow("Bionic reading",
                "Bold the first letters of words to help you read faster",
                bionic, checked -> {
                    bionic = checked;
                    prefs.edit().putBoolean("bionic", checked).apply();
                    try {
                        com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                        if (bridge != null) {
                            if ("speaking".equals(bridge.getState())) {
                                toast("Bionic reading will apply after playback stops");
                            } else {
                                bridge.reloadPage();
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }));
        return card;
    }

    void applyBrightness(int pct) {
        try {
            android.view.WindowManager.LayoutParams lp = act.getWindow().getAttributes();
            lp.screenBrightness = pct / 100f;
            act.getWindow().setAttributes(lp);
        } catch (Exception ignored) {
        }
    }

    // ---------- sleep timer ----------
    View cardSleep() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("SLEEP TIMER"));
        TextView hint = sublabel("Stops the reader automatically after the selected time.");
        hint.setPadding(0, 0, 0, px(12));
        card.addView(hint);
        LinearLayout row = chipRow();
        String[] labels = {"Off", "15 min", "30 min", "60 min"};
        int[] mins = {0, 15, 30, 60};
        for (int i = 0; i < mins.length; i++) {
            final int m = mins[i];
            row.addView(chip(labels[i], sleepMinutes == m, true, v -> {
                sleepMinutes = m;
                prefs.edit().putInt("sleep", m).apply();
                try {
                    com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                    if (bridge != null) {
                        bridge.setSleepTimer(m);
                    }
                } catch (Exception ignored) {
                }
                toast(m == 0 ? "Sleep timer is off" : "Reader will stop in " + m + " minutes");
                buildUI();
            }));
        }
        card.addView(wrapRow(row));
        return card;
    }

    // ---------- automation ----------
    View cardAutomation() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("AUTOMATION"));
        card.addView(toggleRow("Auto dark at night",
                "Switch the theme automatically during the chosen hours",
                autoDark, checked -> {
                    autoDark = checked;
                    prefs.edit().putBoolean("autoDark", checked).apply();
                    refreshTheme();
                }));
        final TextView range = new TextView(act);
        range.setText("Night hours: " + pad2(darkFrom) + ":00 to " + pad2(darkTo) + ":00");
        range.setTextColor(cSub);
        range.setTextSize(TypedSp(12));
        range.setPadding(0, px(8), 0, 0);
        card.addView(range);
        SeekBar fromBar = new SeekBar(act);
        fromBar.setMax(23);
        fromBar.setProgress(darkFrom);
        fromBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                range.setText("Night hours: " + pad2(v) + ":00 to " + pad2(darkTo) + ":00");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                darkFrom = s.getProgress();
                prefs.edit().putInt("darkFrom", darkFrom).apply();
                refreshTheme();
            }
        });
        card.addView(fromBar, new LinearLayout.LayoutParams(-1, -2));
        SeekBar toBar = new SeekBar(act);
        toBar.setMax(23);
        toBar.setProgress(darkTo);
        toBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                range.setText("Night hours: " + pad2(darkFrom) + ":00 to " + pad2(v) + ":00");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                darkTo = s.getProgress();
                prefs.edit().putInt("darkTo", darkTo).apply();
                refreshTheme();
            }
        });
        card.addView(toBar, new LinearLayout.LayoutParams(-1, -2));
        card.addView(toggleRow("Follow ambient light",
                "Use the light sensor to switch between light and dark automatically",
                ambient, checked -> {
                    ambient = checked;
                    prefs.edit().putBoolean("ambient", checked).apply();
                    refreshTheme();
                }));
        return card;
    }

    // ---------- gestures ----------
    View cardGestures() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("GESTURES"));
        card.addView(label("Volume keys"));
        LinearLayout row = chipRow();
        String[] names = {"Off", "Speech volume", "Page scroll"};
        for (int i = 0; i < 3; i++) {
            final int mode = i;
            row.addView(chip(names[i], volMode == mode, true, v -> {
                volMode = mode;
                prefs.edit().putInt("volMode", mode).putBoolean("volKeys", mode == 1).apply();
                buildUI();
            }));
        }
        card.addView(wrapRow(row));
        card.addView(toggleRow("Double-tap to play/pause",
                "Tap twice anywhere to start or pause the reader",
                dblTap, checked -> {
                    dblTap = checked;
                    prefs.edit().putBoolean("dblTap", checked).apply();
                }));
        card.addView(toggleRow("Two-finger swipe for brightness",
                "Drag two fingers up or down to change the screen brightness",
                twoFingerBright, checked -> {
                    twoFingerBright = checked;
                    prefs.edit().putBoolean("twoFingerBright", checked).apply();
                }));
        return card;
    }

    // ---------- focus / pomodoro ----------
    View cardFocus() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("FOCUS SESSION"));
        card.addView(sublabel("The reader pauses automatically when the session ends, so you take a break."));
        LinearLayout row = chipRow();
        String[] labels = {"Off", "15 min", "25 min", "45 min"};
        int[] mins = {0, 15, 25, 45};
        for (int i = 0; i < mins.length; i++) {
            final int m = mins[i];
            row.addView(chip(labels[i], focusMin == m, true, v -> {
                focusMin = m;
                prefs.edit().putInt("focusMin", m).apply();
                try {
                    com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                    if (bridge != null) {
                        bridge.startFocusSession(m);
                    }
                } catch (Exception ignored) {
                }
                toast(m == 0 ? "Focus session is off" : "Focus session started: " + m + " minutes");
                buildUI();
            }));
        }
        card.addView(wrapRow(row));
        card.addView(chip("Enable Do Not Disturb access", false, false, v -> {
            try {
                act.startActivity(new android.content.Intent(
                        android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
            } catch (Exception e) {
                toast("Could not open the Do Not Disturb settings");
            }
        }));
        return card;
    }

    // ---------- reading insights ----------
    View cardInsights() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("READING INSIGHTS"));
        int[] days = last7Days();
        int week = 0;
        for (int d : days) {
            week += d;
        }
        card.addView(label("Today: " + days[6] + " min   |   Last 7 days: " + week + " min"));
        BarsView bars = new BarsView(act, days);
        LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(-1, px(90));
        blp.topMargin = px(10);
        card.addView(bars, blp);
        TextView note = sublabel("Minutes the app was open on each of the last 7 days.");
        note.setPadding(0, px(6), 0, px(4));
        card.addView(note);
        LinearLayout er = chipRow();
        er.addView(chip("Export CSV (30 days)", false, true, v -> exportStats()));
        card.addView(wrapRow(er));
        return card;
    }

    // ---------- backup ----------
    View cardBackup() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("BACKUP"));
        card.addView(sublabel("Save or restore all your settings (themes, fonts, TTS, automation)."));
        LinearLayout row = chipRow();
        row.addView(chip("Export settings", true, true, v -> exportSettings()));
        row.addView(chip("Restore settings", false, true, v -> restoreSettings()));
        card.addView(wrapRow(row));
        card.addView(sublabel("Files are saved in the app's own folder on this device."));
        return card;
    }

    int[] last7Days() {
        int[] out = new int[7];
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.US);
        java.util.Calendar c = java.util.Calendar.getInstance();
        for (int i = 6; i >= 0; i--) {
            out[6 - i] = prefs.getInt("rd" + f.format(c.getTime()), 0);
            c.add(java.util.Calendar.DAY_OF_YEAR, -1);
        }
        return out;
    }

    class BarsView extends android.view.View {
        int[] vals;

        BarsView(android.content.Context c, int[] v) {
            super(c);
            vals = v;
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            int w = getWidth();
            int h = getHeight();
            int n = vals.length;
            if (n == 0 || w == 0) {
                return;
            }
            int max = 1;
            for (int v : vals) {
                if (v > max) {
                    max = v;
                }
            }
            android.graphics.Paint p = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
            int gap = px(6);
            int bw = (w - gap * (n - 1)) / n;
            for (int i = 0; i < n; i++) {
                int bh = (int) ((h - px(6)) * (vals[i] / (float) max));
                if (bh < px(3)) {
                    bh = px(3);
                }
                p.setColor(i == n - 1 ? accent : (isDark ? 0x55FFFFFF : 0x22000000));
                int left = i * (bw + gap);
                canvas.drawRoundRect(new android.graphics.RectF(left, h - bh, left + bw, h), px(4), px(4), p);
            }
        }
    }

    // ---------- custom css ----------
    View cardCustom() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("CUSTOM CSS"));
        card.addView(sublabel("Advanced: paste your own CSS to personalise the novel page."));
        EditText box = new EditText(act);
        box.setText(customCss);
        box.setHint("p { line-height: 1.9; }");
        box.setTextColor(cText);
        box.setHintTextColor(cSub);
        box.setTextSize(TypedSp(13));
        box.setBackground(rounded(isDark ? 0x22FFFFFF : 0x11000000, 14, cBorder, 1));
        box.setPadding(px(12), px(10), px(12), px(10));
        box.setMinLines(3);
        box.setGravity(Gravity.TOP | Gravity.START);
        LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(-1, -2);
        blp.topMargin = px(10);
        card.addView(box, blp);
        LinearLayout row = chipRow();
        row.addView(chip("Apply CSS", true, true, v -> {
            customCss = box.getText().toString();
            prefs.edit().putString("customCss", customCss).apply();
            applyThemeToSite();
            toast("Custom CSS applied");
        }));
        row.addView(chip("Clear", false, true, v -> {
            customCss = "";
            prefs.edit().putString("customCss", "").apply();
            box.setText("");
            applyThemeToSite();
            toast("Custom CSS cleared");
        }));
        card.addView(wrapRow(row));
        return card;
    }

    // ---------- performance ----------
    View cardPerformance() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("PERFORMANCE"));
        card.addView(toggleRow("Performance mode",
                "Reduces animations (for low-end phones). Background reading is not affected.",
                perfMode, checked -> {
                    perfMode = checked;
                    prefs.edit().putBoolean("perfMode", checked).apply();
                    if (checked && bgAnim) {
                        bgAnim = false;
                        prefs.edit().putBoolean("bganim", false).apply();
                    }
                    toast(checked ? "Performance mode on" : "Performance mode off");
                    buildUI();
                }));
        return card;
    }

    // ---------- automation shortcuts ----------
    View cardShortcuts() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("AUTOMATION SHORTCUTS"));
        card.addView(sublabel("Use these with Tasker, Bixby Routines or Google Assistant to control the reader."));
        card.addView(label("com.vasudev.wtrlab.action.PAUSE"));
        card.addView(label("com.vasudev.wtrlab.action.RESUME"));
        card.addView(label("com.vasudev.wtrlab.action.STOP"));
        TextView note = sublabel("Example: a Tasker task can send the RESUME action to the WTR Lab playback service to start reading by voice command.");
        note.setPadding(0, px(6), 0, px(4));
        card.addView(note);
        return card;
    }

    // ---------- offline audio ----------
    View cardOffline() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("OFFLINE AUDIO"));
        card.addView(toggleRow("Capture audio while playing",
                "While you listen with the site's own voice, the audio is saved for offline use",
                capture, checked -> {
                    capture = checked;
                    prefs.edit().putBoolean("capture", checked).apply();
                }));
        LinearLayout row = chipRow();
        row.addView(chip("Save this chapter (device voice)", false, true, v -> {
            try {
                com.vasudev.wtrlab.TtsBridge bridge = com.vasudev.wtrlab.TtsBridge.INSTANCE.getInstance();
                if (bridge != null) {
                    bridge.downloadChapter(null);
                } else {
                    toast("Open a chapter first");
                }
            } catch (Exception ignored) {
            }
        }));
        card.addView(wrapRow(row));
        final TextView lim = new TextView(act);
        lim.setText("Storage limit: " + audioLimitMb + " MB");
        lim.setTextColor(cSub);
        lim.setTextSize(TypedSp(12));
        lim.setPadding(0, px(10), 0, 0);
        card.addView(lim);
        SeekBar seek = new SeekBar(act);
        seek.setMax(19);
        seek.setProgress(Math.max(0, audioLimitMb / 100 - 1));
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                lim.setText("Storage limit: " + ((v + 1) * 100) + " MB");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                audioLimitMb = (s.getProgress() + 1) * 100;
                prefs.edit().putInt("audioLimit", audioLimitMb).apply();
                com.vasudev.wtrlab.AudioStore.enforceLimit(act, audioLimitMb * 1048576L);
            }
        });
        card.addView(seek, new LinearLayout.LayoutParams(-1, -2));
        TextView total = label("Saved: " + com.vasudev.wtrlab.AudioStore.humanSize(
                com.vasudev.wtrlab.AudioStore.totalSize(act)));
        total.setPadding(0, px(10), 0, 0);
        card.addView(total);
        java.util.List<String> chapters = com.vasudev.wtrlab.AudioStore.chapters(act);
        java.io.File[] ttsFiles = com.vasudev.wtrlab.AudioStore.ttsDir(act).listFiles();
        boolean any = !chapters.isEmpty() || (ttsFiles != null && ttsFiles.length > 0);
        if (!any) {
            card.addView(sublabel("Nothing saved yet. Play a chapter with the site's voice (capture on) or use the button above."));
        }
        for (final String ch : chapters) {
            card.addView(audioRow(ch, com.vasudev.wtrlab.AudioStore.humanSize(
                    com.vasudev.wtrlab.AudioStore.chapterSize(act, ch)), new Runnable() {
                @Override
                public void run() {
                    playParts(com.vasudev.wtrlab.AudioStore.parts(act, ch));
                }
            }, new Runnable() {
                @Override
                public void run() {
                    com.vasudev.wtrlab.AudioStore.delete(act, ch);
                    buildUI();
                }
            }));
        }
        if (ttsFiles != null) {
            for (final java.io.File f : ttsFiles) {
                card.addView(audioRow(f.getName(), com.vasudev.wtrlab.AudioStore.humanSize(f.length()),
                        new Runnable() {
                            @Override
                            public void run() {
                                playParts(new java.io.File[]{f});
                            }
                        }, new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    f.delete();
                                } catch (Exception ignored) {
                                }
                                buildUI();
                            }
                        }));
            }
        }
        LinearLayout row2 = chipRow();
        row2.addView(chip("Stop playback", false, true, v -> stopOfflinePlayback()));
        row2.addView(chip("Delete all", false, false, v -> {
            com.vasudev.wtrlab.AudioStore.deleteAll(act);
            try {
                java.io.File[] tf = com.vasudev.wtrlab.AudioStore.ttsDir(act).listFiles();
                if (tf != null) {
                    for (java.io.File f : tf) {
                        f.delete();
                    }
                }
            } catch (Exception ignored) {
            }
            buildUI();
        }));
        card.addView(wrapRow(row2));
        return card;
    }

    View audioRow(String name, String size, final Runnable onPlay, final Runnable onDelete) {
        LinearLayout row = new LinearLayout(act);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, px(8), 0, px(2));
        TextView nm = new TextView(act);
        nm.setText(name + "\n" + size);
        nm.setTextColor(cText);
        nm.setTextSize(TypedSp(13));
        row.addView(nm, new LinearLayout.LayoutParams(0, -2, 1f));
        row.addView(chip("Play", false, true, v -> onPlay.run()));
        row.addView(chip("Delete", false, false, v -> onDelete.run()));
        return row;
    }

    void playParts(java.io.File[] files) {
        stopOfflinePlayback();
        if (files == null || files.length == 0) {
            toast("No audio files here");
            return;
        }
        offlineQueue = files;
        offlineIndex = 0;
        playNextPart();
    }

    void playNextPart() {
        if (offlineQueue == null || offlineIndex >= offlineQueue.length) {
            stopOfflinePlayback();
            return;
        }
        try {
            offlinePlayer = new android.media.MediaPlayer();
            offlinePlayer.setDataSource(offlineQueue[offlineIndex].getAbsolutePath());
            offlinePlayer.setOnCompletionListener(mp -> {
                offlineIndex++;
                playNextPart();
            });
            offlinePlayer.setOnPreparedListener(mp -> {
                try {
                    mp.start();
                } catch (Exception ignored) {
                }
            });
            offlinePlayer.prepareAsync();
            toast("Playing " + (offlineIndex + 1) + "/" + offlineQueue.length);
        } catch (Exception e) {
            stopOfflinePlayback();
            toast("Could not play this audio file");
        }
    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }

    void stopOfflinePlayback() {
        try {
            if (offlinePlayer != null) {
                offlinePlayer.stop();
                offlinePlayer.release();
            }
        } catch (Exception ignored) {
        }
        offlinePlayer = null;
        offlineQueue = null;
        offlineIndex = 0;
    }

    // ---------- ad blocking ----------
    View cardAdBlock() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("AD BLOCKING"));
        card.addView(toggleRow("Block ads and trackers",
                "Blocks ad networks, trackers and popup ads. Switch off if a page misbehaves.",
                adBlock, checked -> {
                    adBlock = checked;
                    prefs.edit().putBoolean("adBlock", checked).apply();
                    toast(checked ? "Ad blocking is ON" : "Ad blocking is OFF");
                }));
        return card;
    }

    // ---------- battery & background ----------
    View cardBattery() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("BATTERY & BACKGROUND"));
        boolean exempt = false;
        try {
            android.os.PowerManager pm = (android.os.PowerManager)
                    act.getSystemService(android.content.Context.POWER_SERVICE);
            exempt = pm != null && pm.isIgnoringBatteryOptimizations(act.getPackageName());
        } catch (Exception ignored) {
        }
        card.addView(label(exempt ? "Background reading: Allowed" : "Background reading: Restricted"));
        card.addView(sublabel("Allow the app to keep reading with the screen off or in battery saver mode."));
        card.addView(chip("Disable battery optimisation", true, true, v -> {
            try {
                android.content.Intent i = new android.content.Intent(
                        android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                i.setData(android.net.Uri.parse("package:" + act.getPackageName()));
                act.startActivity(i);
            } catch (Exception e) {
                try {
                    act.startActivity(new android.content.Intent(
                            android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS));
                } catch (Exception ignored) {
                }
            }
            toast("Please choose Allow on the next screen");
            buildUI();
        }));
        return card;
    }

    // ---------- typography ----------
    View cardTypography() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("FONT & TYPOGRAPHY"));
        card.addView(toggleRow("Custom typography",
                "Change the font, text size, line spacing and alignment of the novel text",
                typoOn, checked -> {
                    typoOn = checked;
                    prefs.edit().putBoolean("typoOn", checked).apply();
                    applyTypo();
                    buildUI();
                }));

        card.addView(label("Font"));
        LinearLayout r1 = chipRow();
        String[] fonts1 = {"Default", "Serif", "Sans"};
        for (int i = 0; i < fonts1.length; i++) {
            final int idx = i;
            r1.addView(chip(fonts1[i], fontIdx == idx, true, v -> {
                fontIdx = idx;
                prefs.edit().putInt("fontIdx", idx).apply();
                applyTypo();
                buildUI();
            }));
        }
        card.addView(wrapRow(r1));
        LinearLayout r2 = chipRow();
        String[] fonts2 = {"Monospace", "Rounded"};
        for (int i = 0; i < fonts2.length; i++) {
            final int idx = i + 3;
            r2.addView(chip(fonts2[i], fontIdx == idx, true, v -> {
                fontIdx = idx;
                prefs.edit().putInt("fontIdx", idx).apply();
                applyTypo();
                buildUI();
            }));
        }
        card.addView(wrapRow(r2));

        final TextView sizeVal = new TextView(act);
        sizeVal.setText("Text size: " + textSizePct + "%");
        sizeVal.setTextColor(cSub);
        sizeVal.setTextSize(TypedSp(12));
        sizeVal.setPadding(0, px(10), 0, 0);
        card.addView(sizeVal);
        SeekBar sizeBar = new SeekBar(act);
        sizeBar.setMax(80);
        sizeBar.setProgress(Math.max(0, textSizePct - 80));
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                sizeVal.setText("Text size: " + (v + 80) + "%");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                textSizePct = s.getProgress() + 80;
                prefs.edit().putInt("textSize", textSizePct).apply();
                applyTypo();
            }
        });
        card.addView(sizeBar, new LinearLayout.LayoutParams(-1, -2));

        final TextView lhVal = new TextView(act);
        lhVal.setText(String.format(java.util.Locale.US, "Line spacing: %.1fx", lineHeightPct / 100f));
        lhVal.setTextColor(cSub);
        lhVal.setTextSize(TypedSp(12));
        card.addView(lhVal);
        SeekBar lhBar = new SeekBar(act);
        lhBar.setMax(100);
        lhBar.setProgress(Math.max(0, lineHeightPct - 120));
        lhBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                lhVal.setText(String.format(java.util.Locale.US, "Line spacing: %.1fx", (v + 120) / 100f));
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                lineHeightPct = s.getProgress() + 120;
                prefs.edit().putInt("lineHeight", lineHeightPct).apply();
                applyTypo();
            }
        });
        card.addView(lhBar, new LinearLayout.LayoutParams(-1, -2));

        card.addView(label("Alignment"));
        LinearLayout r3 = chipRow();
        String[] aligns = {"Default", "Left", "Justify"};
        for (int i = 0; i < aligns.length; i++) {
            final int idx = i;
            r3.addView(chip(aligns[i], textAlignIdx == idx, true, v -> {
                textAlignIdx = idx;
                prefs.edit().putInt("textAlign", idx).apply();
                applyTypo();
                buildUI();
            }));
        }
        card.addView(wrapRow(r3));

        final TextView wVal = new TextView(act);
        wVal.setText("Page width: " + pageWidthPct + "%");
        wVal.setTextColor(cSub);
        wVal.setTextSize(TypedSp(12));
        wVal.setPadding(0, px(10), 0, 0);
        card.addView(wVal);
        SeekBar wBar = new SeekBar(act);
        wBar.setMax(40);
        wBar.setProgress(Math.max(0, pageWidthPct - 60));
        wBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                wVal.setText("Page width: " + (v + 60) + "%");
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                pageWidthPct = s.getProgress() + 60;
                prefs.edit().putInt("pageWidth", pageWidthPct).apply();
                applyTypo();
            }
        });
        card.addView(wBar, new LinearLayout.LayoutParams(-1, -2));

        TextView prev = new TextView(act);
        prev.setText("Preview: The night wind carried the scent of rain across the silent courtyard.");
        prev.setTextColor(cText);
        Typeface famTf = fontIdx == 1 ? Typeface.SERIF : (fontIdx == 3 ? Typeface.MONOSPACE : Typeface.SANS_SERIF);
        prev.setTypeface(Typeface.create(famTf, Typeface.NORMAL));
        prev.setTextSize(Math.max(10f, 17f * textSizePct / 100f));
        prev.setLineSpacing(0f, lineHeightPct / 100f);
        prev.setPadding(px(12), px(12), px(12), px(12));
        prev.setBackground(rounded(isDark ? 0x22FFFFFF : 0x11000000, 12, cBorder, 1));
        LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(-1, -2);
        plp.topMargin = px(10);
        card.addView(prev, plp);
        return card;
    }

    // ---------- reading ----------
    View cardReading() {
        LinearLayout card = cardShell();
        card.addView(cardTitle("READING"));
        card.addView(toggleRow("Auto-scroll",
                "The page moves down slowly on its own while you read",
                autoScroll, checked -> {
                    autoScroll = checked;
                    prefs.edit().putBoolean("autoScroll", checked).apply();
                    pushAutoScroll();
                }));
        final TextView spd = new TextView(act);
        spd.setText("Auto-scroll speed: " + autoScrollSpeed);
        spd.setTextColor(cSub);
        spd.setTextSize(TypedSp(12));
        card.addView(spd);
        SeekBar sb = new SeekBar(act);
        sb.setMax(9);
        sb.setProgress(Math.max(0, autoScrollSpeed - 1));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s, int v, boolean u) {
                spd.setText("Auto-scroll speed: " + (v + 1));
            }
            public void onStartTrackingTouch(SeekBar s) { }
            public void onStopTrackingTouch(SeekBar s) {
                autoScrollSpeed = s.getProgress() + 1;
                prefs.edit().putInt("autoScrollSpeed", autoScrollSpeed).apply();
                pushAutoScroll();
            }
        });
        card.addView(sb, new LinearLayout.LayoutParams(-1, -2));
        card.addView(sublabel("Works together with text to speech as well."));
        return card;
    }

    View cardSystem() {
        LinearLayout card = cardShell();
        card.addView(cardTitle(t("sec_system")));

        PowerManager pm = (PowerManager) act.getSystemService(android.content.Context.POWER_SERVICE);
        boolean batteryOk = pm.isIgnoringBatteryOptimizations(act.getPackageName());

        card.addView(tile(t("battery"), batteryOk ? t("battery_ok") : t("battery_bad"),
                "bat", batteryOk, v -> {
                    try {
                        act.startActivity(new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS));
                    } catch (Exception e) { toast(t("settings_na")); }
                }));
        card.addView(tile(t("notifications"), t("notif_sub"), "not", false, v -> {
            try {
                Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                i.putExtra(Settings.EXTRA_APP_PACKAGE, act.getPackageName());
                act.startActivity(i);
            } catch (Exception e) { toast(t("settings_na")); }
        }));

        // storage
        StatFs fs = new StatFs(act.getFilesDir().getAbsolutePath());
        float total = fs.getTotalBytes() / 1073741824f;
        float free = fs.getAvailableBytes() / 1073741824f;
        LinearLayout st = new LinearLayout(act);
        st.setOrientation(LinearLayout.VERTICAL);
        st.setPadding(0, px(12), 0, px(4));
        LinearLayout r = new LinearLayout(act);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setGravity(Gravity.CENTER_VERTICAL);
        r.addView(label(t("storage")));
        TextView sp3 = new TextView(act);
        sp3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1f));
        r.addView(sp3);
        TextView badge = new TextView(act);
        badge.setText(String.format(Locale.US, t("storage_free"), free));
        badge.setTextColor(0xFF43A047);
        badge.setTextSize(TypedSp(12));
        badge.setBackground(rounded(isDark ? 0x22FFFFFF : 0x11E53935, 999, 0x3343A047, 1));
        badge.setPadding(px(10), px(3), px(10), px(3));
        r.addView(badge);
        st.addView(r);
        ProgressBar pb = new ProgressBar(act, null, android.R.attr.progressBarStyleHorizontal);
        pb.setMax((int) (total * 10));
        pb.setProgress((int) ((total - free) * 10));
        pb.getProgressDrawable().setColorFilter(accent, android.graphics.PorterDuff.Mode.SRC_IN);
        st.addView(pb, new LinearLayout.LayoutParams(-1, px(6)));
        st.addView(sublabel(String.format(Locale.US, t("storage_used"), total - free, total)));
        st.setOnClickListener(v -> {
            try { act.startActivity(new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS)); }
            catch (Exception e) { toast(t("settings_na")); }
        });
        card.addView(st);
        card.addView(spacer(6));

        card.addView(toggleRow(t("file_picker"), t("file_picker_sub"), true,
                checked -> toast(t("pref_saved"))));
        return card;
    }

    View tile(String t2, String s, String tag, boolean ok, View.OnClickListener l) {
        LinearLayout row = new LinearLayout(act);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, px(12), 0, px(12));
        LinearLayout txt = new LinearLayout(act);
        txt.setOrientation(LinearLayout.VERTICAL);
        txt.addView(label(t2));
        txt.addView(sublabel(s));
        row.addView(txt, new LinearLayout.LayoutParams(0, -2, 1f));
        if (tag.equals("bat")) {
            TextView badge = new TextView(act);
            badge.setText(ok ? "ON" : "OFF");
            badge.setTextColor(ok ? 0xFF43A047 : 0xFFE53935);
            badge.setTextSize(TypedSp(11));
            badge.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            badge.setBackground(rounded(isDark ? 0x22FFFFFF : 0x14000000, 999, ok ? 0x3343A047 : 0x33E53935, 1));
            badge.setPadding(px(10), px(3), px(10), px(3));
            row.addView(badge);
            row.addView(spacerW(8));
        }
        TextView arrow = new TextView(act);
        arrow.setText("›");
        arrow.setTextSize(TypedSp(22));
        arrow.setTextColor(cSub);
        row.addView(arrow);
        row.setOnClickListener(l);
        return row;
    }

    View spacerW(float dp) {
        View v = new View(act);
        v.setLayoutParams(new LinearLayout.LayoutParams(px(dp), 1));
        return v;
    }

    // ---------- 4. tip jar ----------
    View cardTipJar() {
        LinearLayout card = cardShell();
        card.addView(cardTitle(t("sec_support")));
        card.addView(label("Tip via UPI"));
        TextView desc = sublabel(t("tip_desc"));
        desc.setPadding(0, px(2), 0, px(14));
        card.addView(desc);

        LinearLayout pills = chipRow();
        pills.addView(chip("₹10", false, true, v -> payUpi(10)));
        pills.addView(chip("₹30", false, true, v -> payUpi(30)));
        card.addView(wrapRow(pills));

        LinearLayout custom = new LinearLayout(act);
        custom.setOrientation(LinearLayout.HORIZONTAL);
        custom.setGravity(Gravity.CENTER_VERTICAL);
        EditText amt = new EditText(act);
        amt.setHint(t("custom_amount"));
        amt.setInputType(InputType.TYPE_CLASS_NUMBER);
        amt.setTextColor(cText);
        amt.setHintTextColor(cSub);
        amt.setBackground(rounded(isDark ? 0x22FFFFFF : 0x11000000, 14, cBorder, 1));
        amt.setPadding(px(12), px(10), px(12), px(10));
        amt.setTextSize(TypedSp(14));
        custom.addView(amt, new LinearLayout.LayoutParams(0, -2, 1f));
        custom.addView(spacerW(10));
        custom.addView(chip("Tip ›", true, true, v -> {
            String s = amt.getText().toString().trim();
            if (s.isEmpty()) { toast(t("enter_amount")); return; }
            try {
                double d = Double.parseDouble(s);
                if (d < 1) { toast(t("min_one")); return; }
                payUpi(d);
            } catch (NumberFormatException e) { toast(t("invalid_amount")); }
        }));
        card.addView(custom);
        return card;
    }

    void payUpi(double amount) {
        String amt = String.format(Locale.US, "%.2f", amount);
        Uri uri = Uri.parse("upi://pay?pa=" + UPI_ID + "&pn=" + PAYEE_NAME + "&am=" + amt + "&cu=INR");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        try {
            act.startActivity(i);
        } catch (Exception e) {
            toast("No UPI app found (GPay / PhonePe / Paytm)");
        }
    }

    // ---------- 5. about ----------
    View cardAbout() {
        LinearLayout card = cardShell();
        card.addView(cardTitle(t("sec_about")));
        LinearLayout row = new LinearLayout(act);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, 0, 0, px(4));
        LinearLayout txt = new LinearLayout(act);
        txt.setOrientation(LinearLayout.VERTICAL);
        txt.addView(label("WTR Lab"));
        txt.addView(sublabel(t("version") + " " + APP_VERSION));
        row.addView(txt, new LinearLayout.LayoutParams(0, -2, 1f));
        TextView badge = new TextView(act);
        badge.setText("MAKE IN INDIA 🇮🇳");
        badge.setTextColor(0xFFFFFFFF);
        badge.setTextSize(TypedSp(10));
        badge.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        badge.setBackground(rounded(0xFFFF8F00, 999, 0xFFFF6D00, 1));
        badge.setPadding(px(10), px(4), px(10), px(4));
        row.addView(badge);
        card.addView(wrapRow(row));
        card.addView(spacer(8));

        card.addView(tile(t("export_log"), t("export_log_sub"), "dbg", false, v -> exportDebugLog()));
        card.addView(tile("GitHub Profile", "github.com/vasudev-work", "gh", false, v -> openUrl(GITHUB_URL)));
        card.addView(tile(t("about_dev"), DEV_NAME, "abt", false, v -> showAboutModal()));
        card.addView(tile(t("privacy"), t("privacy_sub"), "priv", false, v -> openUrl(PRIVACY_URL)));
        card.addView(tile(t("rate"), t("rate_sub"), "rate", false, v -> rateApp()));
        return card;
    }

    void openUrl(String url) {
        try { act.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch (Exception e) { toast(t("no_browser")); }
    }

    void rateApp() {
        Uri market = Uri.parse("market://details?id=" + act.getPackageName());
        try { act.startActivity(new Intent(Intent.ACTION_VIEW, market)); }
        catch (Exception e) { openUrl("https://play.google.com/store/apps/details?id=" + act.getPackageName()); }
    }

    void exportDebugLog() {
        try {
            File dir = act.getExternalFilesDir(null);
            if (dir == null) dir = act.getFilesDir();
            File f = new File(dir, "debug_log.txt");
            FileWriter w = new FileWriter(f);
            String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
            w.write("WTR Lab debug log\nGenerated: " + ts + "\nVersion: " + APP_VERSION
                    + "\nUI style: " + uiStyle + "\nTheme: " + themeMode + "\nAccent: #" + Integer.toHexString(accent)
                    + "\nLanguage: " + Strings.LANG_CODES[langIdx]
                    + "\nScale: " + scalePct + "%\nAndroid: " + android.os.Build.VERSION.RELEASE
                    + "\nDevice: " + android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL + "\n");
            w.close();
            toast(t("saved_to") + " " + f.getAbsolutePath());
        } catch (Exception e) { toast(t("export_fail")); }
    }

    void showLanguageDialog() {
        String[] items = new String[Strings.LANG_NAMES.length];
        for (int i = 0; i < items.length; i++)
            items[i] = Strings.LANG_NAMES[i] + " (" + Strings.LANG_EN[i] + ")";
        showDialog(dialogBuilder()
                .setTitle(t("app_language"))
                .setSingleChoiceItems(items, langIdx, (d, w) -> {
                    langIdx = w;
                    prefs.edit().putInt("lang", langIdx).apply();
                    d.dismiss();
                    buildUI();
                })
                .setNegativeButton(t("cancel"), null));
    }

    void showColorPicker() {
        LinearLayout box = new LinearLayout(act);
        box.setOrientation(LinearLayout.VERTICAL);
        int p = px(24);
        box.setPadding(p, p, p, 0);
        final int[] rgb = {Color.red(accent), Color.green(accent), Color.blue(accent)};
        final TextView preview = new TextView(act);
        preview.setText("  " + t("accent_preview") + "  ");
        preview.setGravity(Gravity.CENTER);
        preview.setTextColor(0xFFFFFFFF);
        preview.setTextSize(TypedSp(14));
        LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(-1, px(52));
        plp.bottomMargin = px(16);
        preview.setLayoutParams(plp);
        preview.setBackground(rounded(accent, 14, 0, 0));
        box.addView(preview);
        String[] names = {t("red"), t("green"), t("blue")};
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            TextView lbl = new TextView(act);
            lbl.setText(names[idx]);
            lbl.setTextColor(cText);
            box.addView(lbl);
            SeekBar sb = new SeekBar(act);
            sb.setMax(255);
            sb.setProgress(rgb[i]);
            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar s, int v, boolean u) {
                    rgb[idx] = v;
                    preview.setBackground(rounded(Color.rgb(rgb[0], rgb[1], rgb[2]), 14, 0, 0));
                }
                public void onStartTrackingTouch(SeekBar s) { }
                public void onStopTrackingTouch(SeekBar s) { }
            });
            box.addView(sb, new LinearLayout.LayoutParams(-1, -2));
        }
        showDialog(dialogBuilder()
                .setTitle(t("custom_accent"))
                .setView(box)
                .setPositiveButton(t("apply"), (d, w) -> {
                    accent = Color.rgb(rgb[0], rgb[1], rgb[2]);
                    prefs.edit().putInt("accent", accent).apply();
                    buildUI();
                })
                .setNegativeButton(t("cancel"), null));
    }

    void showAboutModal() {
        LinearLayout box = new LinearLayout(act);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER_HORIZONTAL);
        int p = px(28);
        box.setPadding(p, px(28), p, px(12));

        TextView mlogo = devLogo();
        mlogo.setTextSize(TypedSp(20));
        LinearLayout.LayoutParams mlp2 = new LinearLayout.LayoutParams(px(56), px(56));
        mlp2.bottomMargin = px(10);
        mlogo.setLayoutParams(mlp2);
        box.addView(mlogo);

        TextView name = new TextView(act);
        name.setText("WTR Lab");
        name.setTextSize(TypedSp(24));
        name.setTextColor(cText);
        name.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        name.setGravity(Gravity.CENTER);
        box.addView(name);

        TextView ver = new TextView(act);
        ver.setText(t("version") + " " + APP_VERSION);
        ver.setTextColor(cSub);
        ver.setTextSize(TypedSp(13));
        ver.setGravity(Gravity.CENTER);
        ver.setPadding(0, px(4), 0, px(14));
        box.addView(ver);

        TextView badge = new TextView(act);
        badge.setText("MAKE IN INDIA 🇮🇳");
        badge.setTextColor(0xFFFFFFFF);
        badge.setTextSize(TypedSp(12));
        badge.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        badge.setGravity(Gravity.CENTER);
        badge.setBackground(rounded(0xFFFF8F00, 999, 0xFFFF6D00, 1));
        badge.setPadding(px(16), px(6), px(16), px(6));
        box.addView(badge);

        TextView dev = new TextView(act);
        dev.setText(t("developed_by") + " " + DEV_NAME);
        dev.setTextColor(cText);
        dev.setTextSize(TypedSp(15));
        dev.setGravity(Gravity.CENTER);
        dev.setPadding(0, px(16), 0, px(4));
        box.addView(dev);

        TextView mail = new TextView(act);
        mail.setText("✉  " + DEV_EMAIL);
        mail.setTextColor(isDark ? 0xFF82B1FF : 0xFF1558D6);
        mail.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        mail.setTextSize(TypedSp(14));
        mail.setGravity(Gravity.CENTER);
        mail.setPadding(0, px(2), 0, px(4));
        mail.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + DEV_EMAIL));
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback — WTR Lab");
            try { act.startActivity(i); } catch (Exception e) { toast(t("no_email")); }
        });
        box.addView(mail);

        TextView gh = new TextView(act);
        gh.setText("⌥  github.com/vasudev-work");
        gh.setTextColor(isDark ? 0xFF82B1FF : 0xFF1558D6);
        gh.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        gh.setTextSize(TypedSp(14));
        gh.setGravity(Gravity.CENTER);
        gh.setPadding(0, 0, 0, px(12));
        gh.setOnClickListener(v -> openUrl(GITHUB_URL));
        box.addView(gh);

        showDialog(dialogBuilder()
                .setTitle(t("sec_about"))
                .setView(box)
                .setPositiveButton(t("close"), null)
                .setNeutralButton(t("privacy"), (d, w) -> openUrl(PRIVACY_URL)));
    }

    // ================= splash & branding =================
    void showSplash() {
        FrameLayout splash = new FrameLayout(act);
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{lighten(accent, 0.25f), darken(accent, 0.65f)});
        splash.setBackground(gd);

        LinearLayout box = new LinearLayout(act);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, Gravity.CENTER));

        TextView logo = new TextView(act);
        logo.setText("VD");
        logo.setTextColor(0xFFFFFFFF);
        logo.setTextSize(40);
        logo.setGravity(Gravity.CENTER);
        logo.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        logo.setBackground(rounded(0x33FFFFFF, 30, 0x66FFFFFF, 1.5f));
        logo.setPadding(px(18), px(12), px(18), px(12));
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-2, -2);
        llp.bottomMargin = px(18);
        logo.setLayoutParams(llp);
        box.addView(logo);

        TextView an = new TextView(act);
        an.setText("WTR Lab");
        an.setTextColor(0xFFFFFFFF);
        an.setTextSize(32);
        an.setGravity(Gravity.CENTER);
        an.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        box.addView(an);

        TextView pw = new TextView(act);
        pw.setText("Powered by Vasudev");
        pw.setTextColor(0xE6FFFFFF);
        pw.setTextSize(14);
        pw.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams plp2 = new LinearLayout.LayoutParams(-2, -2);
        plp2.topMargin = px(6);
        pw.setLayoutParams(plp2);
        box.addView(pw);

        TextView mk = new TextView(act);
        mk.setText("MAKE IN INDIA 🇮🇳");
        mk.setTextColor(0xFFFFFFFF);
        mk.setTextSize(11);
        mk.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        mk.setGravity(Gravity.CENTER);
        mk.setBackground(rounded(0xFFFF8F00, 999, 0xFFFF6D00, 1));
        mk.setPadding(px(14), px(5), px(14), px(5));
        LinearLayout.LayoutParams mlp = new LinearLayout.LayoutParams(-2, -2);
        mlp.topMargin = px(22);
        mk.setLayoutParams(mlp);
        box.addView(mk);

        splash.addView(box);
        container.removeAllViews();
        container.addView(splash, new android.widget.FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        splash.postDelayed(this::buildUI, 1400);
    }

    TextView devLogo() {
        TextView logo = new TextView(act);
        logo.setText("VD");
        logo.setTextColor(0xFFFFFFFF);
        logo.setTextSize(TypedSp(13));
        logo.setGravity(Gravity.CENTER);
        logo.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{accent, darken(accent, 0.7f)});
        g.setCornerRadius(px(12));
        logo.setBackground(g);
        return logo;
    }

    AlertDialog showDialog(AlertDialog.Builder b) {
        AlertDialog d = b.create();
        d.show();
        d.getWindow().setBackgroundDrawable(rounded(isDark ? 0xFF1E1E2E : 0xFFFFFFFF, 22, cBorder, 1.2f));
        int[] btnIds = {AlertDialog.BUTTON_POSITIVE, AlertDialog.BUTTON_NEGATIVE, AlertDialog.BUTTON_NEUTRAL};
        for (int id : btnIds) {
            android.widget.Button btn = d.getButton(id);
            if (btn != null) {
                btn.setTextColor(isDark ? lighten(accent, 0.30f) : accent);
                btn.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            }
        }
        return d;
    }

    AlertDialog.Builder dialogBuilder() {
        return new AlertDialog.Builder(act, isDark
                ? android.R.style.Theme_Material_Dialog_Alert
                : android.R.style.Theme_Material_Light_Dialog_Alert);
    }

    void toast(String s) { Toast.makeText(act, s, Toast.LENGTH_SHORT).show(); }

    // ================= animated background =================
    class BgView extends View {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float[] cx = new float[9], cy = new float[9], rad = new float[9], spd = new float[9], ph = new float[9];
        long start = SystemClock.elapsedRealtime();

        BgView(Context c) {
            super(c);
            for (int i = 0; i < 9; i++) {
                rad[i] = 40 + rnd.nextInt(140);
                ph[i] = rnd.nextFloat() * 1000f;
                spd[i] = 0.02f + rnd.nextFloat() * 0.05f;
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (!bgAnim) return;
            int w = getWidth(), h = getHeight();
            long t = SystemClock.elapsedRealtime() - start;
            for (int i = 0; i < 9; i++) {
                cx[i] = (float) (w * (0.5 + 0.45 * Math.sin(t * spd[i] * 0.001 + ph[i])));
                cy[i] = (float) (h * (0.5 + 0.45 * Math.cos(t * spd[i] * 0.0013 + ph[i] * 1.7)));
                paint.setColor(accent);
                paint.setAlpha(i % 3 == 0 ? 14 : 9);
                canvas.drawCircle(cx[i], cy[i], px(rad[i] / 2f) + px(20), paint);
            }
            postInvalidateOnAnimation();
        }
    }
}
