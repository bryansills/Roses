package ninja.bryansills.roses.binding

import androidx.databinding.ViewDataBinding

interface BindingFragment<Binding : ViewDataBinding> {
    fun getBinding(): Binding
}
