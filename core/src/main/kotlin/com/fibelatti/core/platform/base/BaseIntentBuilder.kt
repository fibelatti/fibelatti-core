package com.fibelatti.core.platform.base

import android.content.Context
import android.content.Intent

abstract class BaseIntentBuilder(context: Context?, clazz: Class<*>) {
    protected val intent: Intent = Intent(context, clazz)

    fun clearTop(): BaseIntentBuilder = apply { intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }

    fun build(): Intent = intent
}
