package com.fibelatti.core.extension

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

/**
 * Shorthand function to retrieve a System Service from `this` [Context] with type inference.
 *
 * @param T the [Class] of the System Service
 *
 * @return the System Service if found, null otherwise
 */
inline fun <reified T> Context.getSystemService(): T? = ContextCompat.getSystemService(this, T::class.java)

fun Context.getActivityPendingIntent(
    requestCode: Int = 0,
    intent: Intent,
    flags: Int = PendingIntent.FLAG_ONE_SHOT
): PendingIntent = PendingIntent.getActivity(this, requestCode, intent, flags)

fun Context.getBroadcastPendingIntent(
    requestCode: Int = 0,
    intent: Intent,
    flags: Int = PendingIntent.FLAG_ONE_SHOT
): PendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, flags)
