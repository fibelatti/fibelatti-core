# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Kotlin
-keep class kotlin.** { *; }
-dontnote kotlin.coroutines.jvm.internal.DebugMetadataKt**
-dontnote kotlin.internal.PlatformImplementationsKt
-dontnote kotlin.jvm.internal.Reflection

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# Material
-keepnames class androidx.** { *; }
-keepnames interface androidx.** { *; }
-keepnames class android.support.** { *; }
-keepnames interface android.support.** { *; }
-keepnames class com.google.android.material.** { *; }

# Rx
-dontnote io.reactivex.**
