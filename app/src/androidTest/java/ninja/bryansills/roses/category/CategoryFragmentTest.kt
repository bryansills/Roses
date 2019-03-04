package ninja.bryansills.roses.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.CustomMatchers.atPosition
import ninja.bryansills.roses.utils.FragmentScenarioRule
import ninja.bryansills.roses.utils.SingleViewModelFactory
import ninja.bryansills.roses.utils.onDataBindingFragment
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    val categoryViewModel = FakeCategoryViewModel()
    val fragmentFactory = CategoryFragmentFactory(SingleViewModelFactory(categoryViewModel))

    @get:Rule
    val fragmentScenarioRule = FragmentScenarioRule(fragmentFactory, CategoryFragment::class.java)

    lateinit var scenario: FragmentScenario<CategoryFragment>

    @Before
    fun setup() {
        scenario = fragmentScenarioRule.fragmentScenario
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun displayError() {
        scenario.onDataBindingFragment {
            categoryViewModel.categories.value = CategoryUiModel.Error(ninja.bryansills.roses.test.R.string.test_error)
        }

        onView(withId(R.id.category_error)).check(matches(withText(ninja.bryansills.roses.test.R.string.test_error)))
        onView(withId(R.id.loading_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun displayLoading() {
        scenario.onDataBindingFragment {
            categoryViewModel.categories.value = CategoryUiModel.Loading
        }

        onView(withId(R.id.loading_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun displayEmptyList() {
        scenario.onDataBindingFragment {
            categoryViewModel.categories.value = CategoryUiModel.Success(emptyList())
        }

        onView(withId(R.id.category_list)).check(matches(not(isDisplayed())))
        onView(withId(R.id.loading_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.category_empty)).check(matches(withText(R.string.categories_empty)))
    }

    @Test
    fun displayList() {
        scenario.onDataBindingFragment {
            val categories = listOf(
                    Category("1", "FIRST", 5),
                    Category("2", "SECOND", 7)
            )
            categoryViewModel.categories.value = CategoryUiModel.Success(categories)
        }

        onView(withId(R.id.category_list)).check(matches(atPosition(0, hasDescendant(withText("FIRST")))))
        onView(withId(R.id.category_list)).check(matches(atPosition(0, hasDescendant(withText("5")))))

        onView(withId(R.id.category_list)).check(matches(atPosition(1, hasDescendant(withText("SECOND")))))
        onView(withId(R.id.category_list)).check(matches(atPosition(1, hasDescendant(withText("7")))))
    }
}

class FakeCategoryViewModel : CategoryViewModel() {
    val categories = MutableLiveData<CategoryUiModel>()
    override fun getCategories(): LiveData<CategoryUiModel> = categories
}

class CategoryFragmentFactory(val viewModelFactory: ViewModelProvider.Factory) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        return CategoryFragment(viewModelFactory)
    }
}