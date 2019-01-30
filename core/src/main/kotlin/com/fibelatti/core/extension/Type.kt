package com.fibelatti.core.extension

// region Any
/**
 * Handy extension to enforce when expressions to always be exhaustive, even if the return type
 * is not assigned.
 */
val Unit?.exhaustive get() = this

/**
 * @return `this` is not null, throws [exception] otherwise.
 */
fun <T> T?.orThrow(exception: Throwable) = this ?: throw exception
// endregion
// region Boolean
/**
 * @return `this` is not null, false otherwise.
 */
fun Boolean?.orFalse(): Boolean = this ?: false

/**
 * @return `this` is not null, true otherwise.
 */
fun Boolean?.orTrue(): Boolean = this ?: true
// endregion
// region Int
/**
 * @return `this` is not null, 0 otherwise.
 */
fun Int?.orZero(): Int = this ?: 0

// endregion
// region Long
/**
 * @return `this` is not null, 0 otherwise.
 */
fun Long?.orZero(): Long = this ?: 0

// endregion
// region String
/**
 * Calls [block] with `this` as its parameter if `this` is not null or empty.
 */
inline fun String?.ifNotNullOrEmpty(block: (String) -> Unit) {
    if (this != null && isNotEmpty()) block(this)
}

/**
 * @return an empty string.
 */
fun String.Companion.empty(): String = ""

/**
 * Replaces [value] with an empty string.
 *
 * @return the new string.
 */
fun String.remove(value: String): String = replace(value, "")

/**
 * Replaces [regex] with an empty string.
 *
 * @return the new string.
 */
fun String.remove(regex: Regex): String = replace(regex, "")

/**
 * Replaces the first instance [value] with an empty string.
 *
 * @return the new string.
 */
fun String.removeFirst(value: String): String = replaceFirst(value, "")

/**
 * Replaces the first instance [regex] with an empty string.
 *
 * @return the new string.
 */
fun String.removeFirst(regex: Regex): String = replaceFirst(regex, "")

/**
 * @return true if the strings are equal ignoring the casing, false otherwise.
 */
fun String.equalsIgnoreCase(otherString: String) = equals(otherString, true)

/**
 * Attempts to cast `this` to an [Int].
 *
 * @return true if the cast was successful, false otherwise.
 */
fun String.isInt(): Boolean = try {
    toInt()
    true
} catch (e: Exception) {
    false
}

// endregion
// region CharSequence
/**
 * @return `this` as a string if not null, empty string otherwise.
 */
fun CharSequence?.orEmpty(): String = this?.toString() ?: ""

/**
 * Replaces [regex] with an empty string.
 *
 * @return the new string.
 */
fun CharSequence.remove(regex: Regex): String = replace(regex, "")

/**
 * Replaces the first instance of [regex] with an empty string.
 *
 * @return the new string.
 */
fun CharSequence.removeFirst(regex: Regex): String = replaceFirst(regex, "")
// endregion
// region Map
/***
 * [Map.getOrDefault] was added in Java 8. This ExFun mimics its implementation.
 */
fun <K, V> Map<K, V>.getOrDefaultValue(key: K, defaultValue: V): V =
    get(key)?.let { it } ?: defaultValue

// endregion
// region List
/**
 * @return a random element of `this`.
 */
fun <T> List<T>.random(): T = shuffled().first()
// endregion
