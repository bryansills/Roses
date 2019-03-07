package ninja.bryansills.roses.entry

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import ninja.bryansills.roses.R
import ninja.bryansills.roses.category.CategoryFragmentDirections
import ninja.bryansills.roses.utils.FragmentScenarioRule
import ninja.bryansills.roses.utils.SingleViewModelFactory
import ninja.bryansills.roses.utils.SingleViewModelFragmentFactory
import ninja.bryansills.roses.utils.onDataBindingFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryFragmentTest {

    val entryViewModel = FakeEntryViewModel()
    val fragmentFactory = SingleViewModelFragmentFactory(SingleViewModelFactory(entryViewModel))
    val args = CategoryFragmentDirections.selectCategory("1337", "CATEGORY_NAME").arguments

    @get:Rule
    val fragmentScenarioRule = FragmentScenarioRule(fragmentFactory, args, EntryFragment::class.java)

    lateinit var scenario: FragmentScenario<EntryFragment>

    @Before
    fun setup() {
        scenario = fragmentScenarioRule.fragmentScenario
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun displayLoading() {
        scenario.onDataBindingFragment {
            entryViewModel.entries.value = EntryUiModel.Loading()
        }

        onView(withId(R.id.loading_bar)).check(matches(isDisplayed()))
    }
}

class FakeEntryViewModel : EntryViewModel() {
    val entries = MutableLiveData<EntryUiModel>()
    override fun getEntries(categoryId: String) = entries
}

