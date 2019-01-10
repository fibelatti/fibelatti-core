# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

-obfuscationdictionary proguard-dictionary.txt
-packageobfuscationdictionary proguard-dictionary.txt
-classobfuscationdictionary proguard-dictionary.txt

-repackageclasses 'fibelatti'

-optimizationpasses 5

# Debugging
-keepattributes LineNumberTable, SourceFile

# Common attributes
-keepattributes Signature, Exceptions, InnerClasses, EnclosingMethod, *Annotation*

# Remove log calls
-assumenosideeffects class android.util.Log {
    public static *** d(...);
}

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

# Gson
-dontnote sun.misc.Unsafe
-dontnote com.google.gson.**
