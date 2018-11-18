package com.fibelatti.core.platform.customview

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import com.fibelatti.core.R
import com.google.android.material.textfield.TextInputEditText

class BackPressReactiveTextInputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {

    var onBackPressed: BackPressReactiveTextInputEditText.() -> Unit = {}

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            onBackPressed()
        }

        return super.onKeyPreIme(keyCode, event)
    }
}
