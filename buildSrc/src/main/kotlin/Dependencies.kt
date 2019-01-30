object Versions {
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
    const val compileSdkVersion = 28
    const val buildToolsVersion = "28.0.3"

    internal const val kotlinVersion = "1.3.20"
    internal const val coroutinesVersion = "1.1.1"
    internal const val lifeCycleVersion = "2.0.0"
}

object Classpaths {
    const val gradlePlugin = "com.android.tools.build:gradle:3.2.1"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val mavenPlugin = "com.github.dcendents:android-maven-gradle-plugin:2.1"
    const val bintrayPlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.0"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    const val supportLibrary = "androidx.appcompat:appcompat:1.1.0-alpha01"
    const val materialDesign = "com.google.android.material:material:1.1.0-alpha02"
    const val supportAnnotations = "androidx.annotation:annotation:1.0.0"

    const val archComponents = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleVersion}"
    const val archComponentsCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycleVersion}"

    val rxJava = "io.reactivex.rxjava2:rxjava:2.2.4"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

    val javaxInject = "javax.inject:javax.inject:1"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.8.0"
}

object TestDependencies {
    private const val junit5Version = "5.3.1"

    const val junit = "junit:junit:4.12"
    const val junit5 = "org.junit.jupiter:junit-jupiter-api:$junit5Version"
    const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:$junit5Version"
    const val junit5Params = "org.junit.jupiter:junit-jupiter-params:$junit5Version"
    const val junitVintage = "org.junit.vintage:junit-vintage-engine:$junit5Version"
    const val testRunner = "com.android.support.test:runner:1.1.0"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val mockitoCore = "org.mockito:mockito-core:2.23.0"
    const val mockitoAndroid = "org.mockito:mockito-android:2.18.3"
    const val archComponentsTest = "android.arch.core:core-testing:${Versions.lifeCycleVersion}"
}
