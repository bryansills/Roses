package ninja.bryansills.roses.category

import androidx.test.ext.junit.runners.AndroidJUnit4
import ninja.bryansills.roses.factory.ViewModelFactory
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Before
    fun setup() {
    }

    @Test
    fun something() {
//        launchFragmentInContainer<CategoryFragment>()
//        onView(withId(R.id.category_list)).check(matches(isDisplayed()))
        assertTrue(true)
    }
}