package ninja.bryansills.roses.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider

class SingleViewModelFragmentFactory(val viewModelFactory: ViewModelProvider.Factory) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        val fragment = classLoader.loadClass(className)
                .getDeclaredConstructor(ViewModelProvider.Factory::class.java)
                .newInstance(viewModelFactory) as Fragment
        fragment.arguments = args
        return fragment
    }
}