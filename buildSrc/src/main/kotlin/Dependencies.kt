object Versions {
    internal const val gradlePluginVersion = "3.2.1"
    internal const val kotlinVersion = "1.3.10"

    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val compileSdkVersion = 28

    const val buildToolsVersion = "28.0.3"

    internal const val appCompatVersion = "1.0.2"
    internal const val materialDesignVersion = "1.0.0"
    internal const val supportAnnotationVersion = "1.0.0"

    internal const val archComponentsVersion = "2.0.0"

    internal const val coroutinesCoreVersion = "1.0.0"
    internal const val coroutinesAndroidVersion = "1.0.0"

    internal const val rxJavaVersion = "2.2.3"
    internal const val rxAndroidVersion = "2.1.0"

    // region Distribution
    internal const val bintrayPluginVersion = "1.8.0"
    // endregion

    // region Testing
    internal const val jUnitVersion = "4.12"
    internal const val testRunnerVersion = "1.0.1"
    internal const val mockitoVersion = "2.23.0"
    internal const val mockitoAndroidVersion = "2.18.3"
    internal const val junit5pluginVersion = "1.3.1.1"
    internal const val junit5Version = "5.3.1"
    // endregion
}

object Classpaths {
    val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePluginVersion}"
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val junit5Plugin = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junit5pluginVersion}"
    val bintrayPlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Versions.bintrayPluginVersion}"
}

object KotlinDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
}

object SupportLibraryDependencies {
    val supportLibrary = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"
}

object ArchitectureComponentDependencies {
    val archComponents = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponentsVersion}"
    val archComponentsCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archComponentsVersion}"
}

object CoroutineDependencies {
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}

object RxDependencies {
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
}

object TestDependencies {
    val junit = "junit:junit:${Versions.jUnitVersion}"
    val junit5 = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}"
    val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}"
    val junit5Params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5Version}"
    val junitVintage = "org.junit.vintage:junit-vintage-engine:${Versions.junit5Version}"
    val testRunner = "com.android.support.test:runner:${Versions.testRunnerVersion}"
    val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
    val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroidVersion}"
    val supportAnnotations = "androidx.annotation:annotation:${Versions.supportAnnotationVersion}"
    val archComponentsTest = "android.arch.core:core-testing:${Versions.archComponentsVersion}"
}
