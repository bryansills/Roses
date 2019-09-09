package ninja.bryansills.roses.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider

class SingleViewModelFragmentFactory(val viewModelFactory: ViewModelProvider.Factory) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragment = loadFragmentClass(classLoader, className)
            .getDeclaredConstructor(ViewModelProvider.Factory::class.java)
            .newInstance(viewModelFactory) as Fragment
        return fragment
    }
}