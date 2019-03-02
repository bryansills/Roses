package ninja.bryansills.roses.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SingleViewModelFactory(val viewModel: ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}