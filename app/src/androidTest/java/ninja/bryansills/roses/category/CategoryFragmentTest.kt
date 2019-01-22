package ninja.bryansills.roses.category

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import ninja.bryansills.roses.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {
    @Test
    fun something() {
        launchFragmentInContainer<CategoryFragment>()

        onView(withId(R.id.category_list)).check(matches(isDisplayed()))
    }
}