object Versions {
    val kotlin = "1.3.21"
    val junit = "4.12"
    val kotlin_reflect = "1.3.31"
    val dagger = "2.16"
    const val retrofit = "2.5.0"
}

object Config {
    val minSdk = 21
    val compileSdk = 28
    val targetSdk = 28
    val versionName = "0.0.1"
    val versionCode = 1
}

object Modules {
    val glue = ":glue"
    val models = ":base:models"
    val main = ":base:main"
    val ui = ":ui"
    val network = ":network"
}

object Dependencies {
    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_reflect}"
    val junit = "junit:junit:${Versions.junit}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:3.12.0"
    const val coroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    const val jodaTime = "net.danlew:android.joda:2.10.1"

}