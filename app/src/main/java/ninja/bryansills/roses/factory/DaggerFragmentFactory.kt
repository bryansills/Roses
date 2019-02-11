package ninja.bryansills.roses.factory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerFragmentFactory @Inject constructor(
        private val fragmentMap: @JvmSuppressWildcards Map<Class<*>, Provider<Fragment>>
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = fragmentMap[fragmentClass]
                ?: throw IllegalArgumentException("Unknown fragment class $fragmentClass")

        try {
            val fragment = creator.get()
            fragment.arguments = args
            return fragment
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
