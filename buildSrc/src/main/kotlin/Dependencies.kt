object Versions {
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29

    internal const val kotlinVersion = "1.3.72"
    internal const val coroutinesVersion = "1.3.7"
    internal const val lifecycleVersion = "2.2.0"
}

object Classpaths {
    const val gradlePlugin = "com.android.tools.build:gradle:4.0.0"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val mavenPlugin = "com.github.dcendents:android-maven-gradle-plugin:2.1"
    const val bintrayPlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.0"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    const val supportLibrary = "androidx.appcompat:appcompat:1.1.0"
    const val activity = "androidx.activity:activity-ktx:1.1.0"
    const val fragments = "androidx.fragment:fragment-ktx:1.2.4"
    const val materialDesign = "com.google.android.material:material:1.1.0"
    const val supportAnnotations = "androidx.annotation:annotation:1.1.0"

    const val archComponents = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val archComponentsCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.4"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

    const val javaxInject = "javax.inject:javax.inject:1"
}

object TestDependencies {
    private const val junit5Version = "5.6.0"

    const val junit = "junit:junit:4.13"
    const val junit5 = "org.junit.jupiter:junit-jupiter-api:$junit5Version"
    const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:$junit5Version"
    const val junit5Params = "org.junit.jupiter:junit-jupiter-params:$junit5Version"
    const val junitVintage = "org.junit.vintage:junit-vintage-engine:$junit5Version"
    const val testRunner = "com.android.support.test:runner:1.1.0"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"
    const val mockitoCore = "org.mockito:mockito-inline:3.2.4"
    const val archComponentsTest = "android.arch.core:core-testing:${Versions.lifecycleVersion}"
}
