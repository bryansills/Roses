package ninja.bryansills.roses.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.testing.R
import org.junit.rules.ExternalResource

class FragmentScenarioRule<F : Fragment>(val fragmentFactory: FragmentFactory, private val clazz: Class<F>) : ExternalResource() {

    lateinit var fragmentScenario: FragmentScenario<F>

    override fun before() {
        fragmentScenario = FragmentScenario.launchInContainer(clazz, null, R.style.FragmentScenarioEmptyFragmentActivityTheme, fragmentFactory)
    }

    override fun after() {
        fragmentScenario.recreate()
    }
}