# WTR Lab

A focused Android WebView reader for **wtr-lab.com** with native text-to-speech,
translation, chapter navigation and a fully themeable reading panel.

Package: `com.vasudev.wtrlab` · versionName `1.0`

## Features

- Opens **wtr-lab.com** only (by design – it is a single-site reader).
- **Speech panel** – pick a TTS engine, voice and speed, test the voice, and
  read the page aloud paragraph by paragraph with highlighting.
- **Stable playback** – text is sent to the engine in one piece (no chunking)
  and an inaudible keep-alive keeps the hidden page from freezing, so audio
  keeps playing with the screen off.
- **Translate page** – six languages, with a read-aloud option.
- **Chapter navigation** – previous/next chapter, a chapter-number or URL jump
  box, a draggable chapter bar and a local history of visited pages.
- **Appearance** – theme mode, reader themes, six font families, font size,
  line height, paragraph spacing, margins, bionic reading, custom CSS.
- **Settings panel** with five tabs: Read · Display · Translation · Speech ·
  Settings, plus an Advanced screen (custom CSS, shortcuts, maintenance,
  app info).
- **Settings file** export/import and push-from-a-companion-settings-app.

## Project layout

```
AndroidManifest.xml
com/vasudev/wtrlab/          app + WebView + TTS bridge
com/vasudev/settingsui/      the settings panel
res/                         icons, strings, theme
```

## Building

Plain Java, no Gradle, no AndroidX – build with the standard Android command
line tools. See [BUILD.md](BUILD.md).

> **Signing:** this repository deliberately contains **no keystore and no
> passwords**. Create your own keystore with `keytool` and keep it private.

## License

MIT – see [LICENSE](LICENSE).
