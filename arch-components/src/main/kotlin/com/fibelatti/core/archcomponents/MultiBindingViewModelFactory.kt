package com.fibelatti.core.archcomponents

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class MultiBindingViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

inline fun <reified T : ViewModel> ViewModelProvider.Factory.get(activity: FragmentActivity): T =
    ViewModelProvider(activity, this).get(T::class.java)

inline fun <reified T : ViewModel> ViewModelProvider.Factory.get(fragment: Fragment): T =
    ViewModelProvider(fragment, this).get(T::class.java)
