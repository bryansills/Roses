package ninja.bryansills.roses.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import ninja.bryansills.roses.binding.BindingFragment

inline fun <T: Fragment> FragmentScenario<T>.onDataBindingFragment(crossinline lambda: (T) -> Unit) {
    this.onFragment {
        lambda(it)

        if (it is BindingFragment) {
            it.getBinding().executePendingBindings()
        }
    }
}