# Build notes

No Gradle, no AndroidX, no third-party runtime dependencies – just the Android
framework. Everything below is a plain command-line build.

## 1. Toolchain

- JDK 17
- Android build-tools 35 (`aapt2`, `d8`, `zipalign`, `apksigner`)
- Android platform 35 (`android.jar`)
- Kotlin stdlib jar (only because the decompiled sources carry Kotlin
  metadata annotations; nothing is written in Kotlin)

## 2. Resources

```
aapt2 compile --dir res -o res.zip
aapt2 link -o base.apk -I android.jar --manifest AndroidManifest.xml \
    --min-sdk-version 24 --target-sdk-version 34 \
    --version-code <N> --version-name 1.0 res.zip
```

## 3. Java

```
javac -encoding UTF-8 -source 1.8 -target 1.8 \
    -bootclasspath "android.jar:core-lambda-stubs.jar" \
    -classpath kotlin-stdlib.jar \
    -d classes com/vasudev/novelreader/*.java com/vasudev/settingsui/*.java
```

## 4. Dex + package + sign

```
jar cf app-classes.jar -C classes .
d8 --release --min-api 24 --lib android.jar --output dex app-classes.jar kotlin-stdlib.jar
cp base.apk unsigned.apk && (cd dex && zip -q ../unsigned.apk classes.dex)
zipalign -f 4 unsigned.apk aligned.apk
apksigner sign --ks <your-own.keystore> --out app.apk aligned.apk
```

**Create your own keystore** – this repository ships none:

```
keytool -genkeypair -v -keystore my-release.keystore -alias mykey \
    -keyalg RSA -keysize 2048 -validity 10000
```

Keep the keystore and its passwords out of version control (`.gitignore`
already excludes them).
