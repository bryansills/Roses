package ninja.bryansills.roses.category

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ninja.bryansills.roses.R
import ninja.bryansills.roses.RosesApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    @Before
    fun setup() {
    }

    @Test
    fun something() {
        launchFragmentInContainer<CategoryFragment>()
        onView(withId(R.id.category_list)).check(matches(isDisplayed()))
    }
}