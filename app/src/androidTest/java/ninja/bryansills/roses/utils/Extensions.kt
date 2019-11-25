package ninja.bryansills.roses.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import ninja.bryansills.roses.binding.BindingFragment

inline fun <Frag : Fragment> FragmentScenario<Frag>.onDataBindingFragment(
    crossinline lambda: (Frag) -> Unit
) {
    this.onFragment {
        lambda(it)

        if (it is BindingFragment) {
            it.getBinding().executePendingBindings()
        } else {
            throw RuntimeException("Don\'t run onDataBindingFragment on non-databinding Fragments")
        }
    }
}
