package ninja.bryansills.roses.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class CategoryViewModel : ViewModel() {
    abstract fun getCategories(): LiveData<CategoryUiModel>
}