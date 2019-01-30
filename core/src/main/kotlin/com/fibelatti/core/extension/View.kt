package com.fibelatti.core.extension

import android.animation.LayoutTransition
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.method.TransformationMethod
import android.text.util.Linkify
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.annotation.StyleableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.fibelatti.core.android.recyclerview.ItemOffsetDecoration
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private const val INVALID_RESOURCE_ID = -1

// region View
/**
 * Set `this` visibility to [View.GONE].
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Set `this` visibility to [View.GONE] if the [predicate] is true.
 */
fun View.goneIf(predicate: Boolean) {
    if (predicate) visibility = View.GONE
}

/**
 * Set `this` visibility to [View.GONE] if the [predicate] is true,
 * to [otherwiseVisibility] if false.
 */
fun View.goneIf(predicate: Boolean, otherwiseVisibility: Int) {
    visibility = if (predicate) View.GONE else otherwiseVisibility
}

/**
 * @return true if `this` visibility is [View.GONE], false otherwise.
 */
fun View.isGone(): Boolean = visibility == View.GONE

/**
 * Set `this` visibility to [View.INVISIBLE].
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Set `this` visibility to [View.INVISIBLE] if the [predicate] is true.
 */
fun View.invisibleIf(predicate: Boolean) {
    if (predicate) visibility = View.INVISIBLE
}

/**
 * Set `this` visibility to [View.INVISIBLE] if the [predicate] is true,
 * to [otherwiseVisibility] if false.
 */
fun View.invisibleIf(predicate: Boolean, otherwiseVisibility: Int) {
    visibility = if (predicate) View.INVISIBLE else otherwiseVisibility
}

/**
 * @return true if `this` visibility is [View.INVISIBLE], false otherwise.
 */
fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

/**
 * Set `this` visibility to [View.VISIBLE].
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Set `this` visibility to [View.VISIBLE] if the [predicate] is true.
 */
fun View.visibleIf(predicate: Boolean) {
    if (predicate) visibility = View.VISIBLE
}

/**
 * Set `this` visibility to [View.VISIBLE] if the [predicate] is true,
 * to [otherwiseVisibility] if false.
 */
fun View.visibleIf(predicate: Boolean, otherwiseVisibility: Int) {
    visibility = if (predicate) View.VISIBLE else otherwiseVisibility
}

/**
 * @return true if `this` visibility is [View.VISIBLE], false otherwise.
 */
fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.heightCollapse() {
    val params = layoutParams
    params.height = 0
    layoutParams = params
    requestLayout()
}

fun View.heightIsCollapsed(): Boolean = layoutParams.height == 0

fun View.heightWrapContent() {
    val params = layoutParams
    params.height = when (layoutParams) {
        is LinearLayout -> LinearLayout.LayoutParams.WRAP_CONTENT
        is RelativeLayout -> RelativeLayout.LayoutParams.WRAP_CONTENT
        is FrameLayout -> FrameLayout.LayoutParams.WRAP_CONTENT
        else -> ViewGroup.LayoutParams.WRAP_CONTENT
    }

    layoutParams = params
    requestLayout()
}

fun View.setPadding(
    @DimenRes left: Int? = null,
    @DimenRes top: Int? = null,
    @DimenRes right: Int? = null,
    @DimenRes bottom: Int? = null,
    predicate: Boolean = true
) {
    if (predicate) {
        setPadding(
            left?.let(resources::getDimensionPixelSize).orZero(),
            top?.let(resources::getDimensionPixelSize).orZero(),
            right?.let(resources::getDimensionPixelSize).orZero(),
            bottom?.let(resources::getDimensionPixelSize).orZero()
        )
    }
}
// endregion

// region ViewGroup
@JvmOverloads
fun ViewGroup.inflate(@LayoutRes layoutResource: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutResource, this, attachToRoot)

val ViewGroup.children: Iterable<View> get() = (0 until childCount).map(::getChildAt)

fun ViewGroup.setMargin(
    @DimenRes left: Int? = null,
    @DimenRes top: Int? = null,
    @DimenRes right: Int? = null,
    @DimenRes bottom: Int? = null
) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        leftMargin = left?.let(resources::getDimensionPixelSize).orZero()
        topMargin = top?.let(resources::getDimensionPixelSize).orZero()
        rightMargin = right?.let(resources::getDimensionPixelSize).orZero()
        bottomMargin = bottom?.let(resources::getDimensionPixelSize).orZero()
    }
}

fun ViewGroup.animateChangingTransitions() {
    layoutTransition = LayoutTransition().apply { enableTransitionType(LayoutTransition.CHANGING) }
}
// endregion

// region TextView
/**
 * Set `this` text to [textId] and `this` visibility to [View.VISIBLE].
 */
fun TextView.visible(@StringRes textId: Int) {
    visible()
    setText(textId)
}

/**
 * Set `this` text to [text] and `this` visibility to [View.VISIBLE].
 */
fun TextView.visible(text: String) {
    visible()
    this.text = text
}

/**
 * Set `this` text to [text] and `this` visibility to [View.VISIBLE] if [predicate] is true.
 */
fun TextView.visibleIf(predicate: Boolean, text: String) {
    visibleIf(predicate)
    if (predicate) this.text = text
}

/**
 * Set `this` text to [text] if [predicate] is true and `this` visibility to [View.VISIBLE] if
 * [predicate] is true, to [otherwiseVisibility] if false.
 */
fun TextView.visibleIf(predicate: Boolean, text: String, otherwiseVisibility: Int = View.GONE) {
    visibleIf(predicate, otherwiseVisibility = otherwiseVisibility)
    if (predicate) this.text = text
}

fun TextView.setOnClickListener(@StringRes textId: Int, clickListener: () -> Unit) {
    setOnClickListener(context.getString(textId), clickListener)
}

fun TextView.setOnClickListener(text: String, clickListener: () -> Unit) {
    isClickable = true
    isFocusable = true
    this.text = text
    setOnClickListener { clickListener() }
}

fun TextView.clearDrawables() {
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
}

fun TextView.setDrawables(
    @DrawableRes drawableLeftRes: Int? = null,
    @DrawableRes drawableTopRes: Int? = null,
    @DrawableRes drawableRightRes: Int? = null,
    @DrawableRes drawableBottomRes: Int? = null
) {
    setCompoundDrawablesWithIntrinsicBounds(
        drawableLeftRes?.let { AppCompatResources.getDrawable(context, it) },
        drawableTopRes?.let { AppCompatResources.getDrawable(context, it) },
        drawableRightRes?.let { AppCompatResources.getDrawable(context, it) },
        drawableBottomRes?.let { AppCompatResources.getDrawable(context, it) })
}

fun TextView.setupLinks(transformationMethod: TransformationMethod? = null) {
    Linkify.addLinks(this, Linkify.ALL)
    movementMethod = LinkMovementMethod.getInstance()
    transformationMethod?.let(::setTransformationMethod)
}
// endregion

// region EditText
fun EditText.clearText() {
    setText("")
}

inline fun EditText.addTextChangedListener(
    crossinline beforeTextChanged: (charSequence: CharSequence, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    crossinline onTextChanged: (charSequence: CharSequence, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> },
    crossinline afterTextChanged: (text: String) -> Unit = {}
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged(charSequence, start, count, after)
        }

        override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged(charSequence, start, before, count)
        }

        override fun afterTextChanged(editable: Editable) {
            afterTextChanged(editable.toString())
        }
    })
}
// endregion

// region TextInputLayout
fun TextInputLayout.showError(errorMessage: String) {
    error = errorMessage
    if (childCount == 1 && (getChildAt(0) is TextInputEditText || getChildAt(0) is EditText)) {
        getChildAt(0).requestFocus()
    }
}

fun TextInputLayout.clearError() {
    error = null
}
// endregion

// region EditText
fun EditText.textAsString(): String = this.text?.toString().orEmpty()

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}
// endregion

// region ImageView
fun ImageView.visible(@DrawableRes imageRes: Int) {
    visible()
    setImageResource(imageRes)
}

fun ImageView.visible(imageUri: Uri) {
    visible()
    setImageURI(imageUri)
}
// endregion

// region RecyclerView
fun RecyclerView.withItemOffsetDecoration(@DimenRes dimenRes: Int): RecyclerView = apply {
    addItemDecoration(ItemOffsetDecoration(context, dimenRes))
}

fun RecyclerView.withGridLayoutManager(spanCount: Int): RecyclerView = apply {
    layoutManager = GridLayoutManager(context, spanCount)
}

fun RecyclerView.withLinearLayoutManager(
    vertical: Boolean = true,
    reversed: Boolean = false
): RecyclerView = apply {
    layoutManager =
        LinearLayoutManager(context, if (vertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, reversed)
}

fun RecyclerView.withPagerSnapHelper(): RecyclerView = apply {
    PagerSnapHelper().attachToRecyclerView(this)
}
// endregion

// region Toolbar
fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar,
    displayHomeAsUpEnabled: Boolean = true,
    displayShowHomeEnabled: Boolean = true,
    displayShowTitleEnabled: Boolean = false,
    showUpArrowAsCloseIcon: Boolean = false,
    @DrawableRes closeIconDrawableRes: Int? = null
) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        setDisplayShowHomeEnabled(displayShowHomeEnabled)
        setDisplayShowTitleEnabled(displayShowTitleEnabled)

        if (showUpArrowAsCloseIcon && closeIconDrawableRes != null) {
            setHomeAsUpIndicator(AppCompatResources.getDrawable(this@setupToolbar, closeIconDrawableRes))
        }
    }
}

fun AppCompatActivity.onSupportNavigateUpGoBack(): Boolean {
    onBackPressed()
    return true
}
// endregion

// region Keyboard
fun isKeyboardSubmit(actionId: Int, event: KeyEvent?): Boolean =
    actionId == EditorInfo.IME_ACTION_GO ||
        actionId == EditorInfo.IME_ACTION_DONE ||
        (event != null && event.action == KeyEvent.ACTION_UP && event.keyCode == KeyEvent.KEYCODE_ENTER)

fun View.showKeyboard() {
    requestFocus()
    context.getSystemService<InputMethodManager>()?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    context.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(windowToken, 0)
}
// endregion

// region Resources
fun TypedArray.isResourceIdInvalid(resourceId: Int): Boolean =
    getResourceId(resourceId, INVALID_RESOURCE_ID) == INVALID_RESOURCE_ID

fun TypedArray.resolveResource(context: Context, @StyleableRes id: Int): Drawable? =
    try {
        getResourceId(id, INVALID_RESOURCE_ID).takeIf { it != INVALID_RESOURCE_ID }
            ?.let { AppCompatResources.getDrawable(context, it) }
    } catch (exception: Exception) {
        null
    }
// endregion
