package ninja.bryansills.roses.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ninja.bryansills.roses.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    val categoryViewModel = FakeCategoryViewModel()
    lateinit var scenario: FragmentScenario<CategoryFragment>

    @Before
    fun setup() {
        val viewModelFactory = CategoryViewModelFactory(categoryViewModel) as ViewModelProvider.Factory
        val fragmentFactory = CategoryFragmentFactory(viewModelFactory)

        scenario = launchFragmentInContainer(factory = fragmentFactory)
    }

    @Test
    fun displayError() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment {
            categoryViewModel.categories.value = CategoryUiModel.Error("BIG OL\' ERROR")
        }
        onView(withId(R.id.category_error)).check(matches(withText("BIG OL\' ERROR")))
    }
}

class FakeCategoryViewModel : CategoryViewModel() {
    val categories = MutableLiveData<CategoryUiModel>()
    override fun getCategories(): LiveData<CategoryUiModel> = categories
}

class CategoryViewModelFactory(val viewModel: ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}

class CategoryFragmentFactory(val viewModelFactory: ViewModelProvider.Factory) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        return CategoryFragment(viewModelFactory)
    }
}