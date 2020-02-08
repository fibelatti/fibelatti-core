package com.fibelatti.core.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * A [FragmentFactory] that can be used in conjunction with dependency injection frameworks that
 * support multi-binding.
 */
class MultiBindingFragmentFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = creators[fragmentClass]
            ?: throw IllegalArgumentException("Unknown fragment class $fragmentClass")

        try {
            return creator.get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
