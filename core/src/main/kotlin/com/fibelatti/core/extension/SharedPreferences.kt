package com.fibelatti.core.extension

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

// region getSharedPreferences
fun Context.getSharedPreferences(name: String): SharedPreferences =
    getSharedPreferences(name, MODE_PRIVATE)
// endregion

// region Operations on SharedPreferences
private inline fun SharedPreferences.edit(operation: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
}

fun SharedPreferences.put(key: String, value: Any?) {
    when (value) {
        is Boolean -> edit { putBoolean(key, value) }
        is Int -> edit { putInt(key, value) }
        is Float -> edit { putFloat(key, value) }
        is Long -> edit { putLong(key, value) }
        is String? -> edit { putString(key, value) }
    }
}

fun SharedPreferences.clear() {
    edit { clear() }
}

inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T): T =
    when (defaultValue) {
        is Boolean -> getBoolean(key, defaultValue) as? T ?: defaultValue
        is Int -> getInt(key, defaultValue) as? T ?: defaultValue
        is Float -> getFloat(key, defaultValue) as? T? ?: defaultValue
        is Long -> getLong(key, defaultValue) as? T? ?: defaultValue
        is String -> getString(key, defaultValue) as? T? ?: defaultValue
        else -> throw UnsupportedOperationException("Class not supported by SharedPreferences.put()")
    }
// endregion
