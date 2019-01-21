package com.fibelatti.core.extension

import android.os.Build

fun isLollipopOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

inline fun inLollipopOrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) body()
}

fun isLollipopMr1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

inline fun inLollipopMr1OrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) body()
}

fun isMarshmallowOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

inline fun inMarshmallowOrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) body()
}

fun isNougatOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

inline fun inNougatOrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) body()
}

fun isNougatMr1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

inline fun inNougatMr1OrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) body()
}

fun isOreoOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

inline fun inOreoOrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) body()
}

fun isPieOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

inline fun inPieOrLater(body: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) body()
}
