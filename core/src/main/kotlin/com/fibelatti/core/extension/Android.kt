package com.fibelatti.core.extension

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

inline fun <reified T> Context.getSystemService(): T? =
    ContextCompat.getSystemService(this, T::class.java)

fun Context.getActivityPendingIntent(requestCode: Int = 0, intent: Intent, flags: Int = PendingIntent.FLAG_ONE_SHOT): PendingIntent =
    PendingIntent.getActivity(this, requestCode, intent, flags)

fun Context.getBroadcastPendingIntent(requestCode: Int = 0, intent: Intent, flags: Int = PendingIntent.FLAG_ONE_SHOT): PendingIntent =
    PendingIntent.getBroadcast(this, requestCode, intent, flags)
