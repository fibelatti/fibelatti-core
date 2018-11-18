package com.fibelatti.core.extension

import android.content.Context
import androidx.core.content.ContextCompat

inline fun <reified T> Context.getSystemService(): T? =
    ContextCompat.getSystemService(this, T::class.java)
