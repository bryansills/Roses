package ninja.bryansills.roses.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerFragmentFactory @Inject constructor(
        private val fragmentMap: @JvmSuppressWildcards Map<Class<*>, Provider<Fragment>>
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = fragmentMap[fragmentClass]
                ?: throw IllegalArgumentException("Unknown fragment class $fragmentClass")

        try {
            return creator.get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
