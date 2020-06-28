import com.android.build.gradle.LibraryExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(from = "detekt.gradle")

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(Classpaths.gradlePlugin)
        classpath(Classpaths.kotlinPlugin)
        classpath(Classpaths.mavenPlugin)
        classpath(Classpaths.bintrayPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

subprojects {
    configureLibrary()
}

subprojects {
    afterEvaluate {
        tasks.withType<KotlinCompile>().all {
            kotlinOptions.jvmTarget = "1.8"
        }

        tasks.findByName("preBuild")?.dependsOn(":detekt")
    }
}

fun Project.configureLibrary() {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")

    configure<LibraryExtension> {
        compileSdkVersion(Versions.compileSdkVersion)

        defaultConfig {
            versionCode = LibInfo.versionCode
            versionName = LibInfo.versionName
            minSdkVersion(Versions.minSdkVersion)
            targetSdkVersion(Versions.targetSdkVersion)
            consumerProguardFiles("${rootProject.rootDir.absolutePath}/proguard-rules.pro")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        sourceSets {
            names.map { name -> getByName(name).java.srcDirs("src/$name/kotlin") }
        }
    }
}
