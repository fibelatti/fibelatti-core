package com.fibelatti.core.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(value: String): T =
    fromJson(value, TypeToken.get(T::class.java).type)
