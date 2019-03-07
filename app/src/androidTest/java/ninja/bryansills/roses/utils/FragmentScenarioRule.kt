package ninja.bryansills.roses.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.testing.R
import org.junit.rules.ExternalResource

class FragmentScenarioRule<F : Fragment>(val fragmentFactory: FragmentFactory, val args: Bundle?, private val clazz: Class<F>) : ExternalResource() {

    lateinit var fragmentScenario: FragmentScenario<F>

    override fun before() {
        fragmentScenario = FragmentScenario.launchInContainer(clazz, args, R.style.FragmentScenarioEmptyFragmentActivityTheme, fragmentFactory)
    }

    override fun after() {
        fragmentScenario.recreate()
    }
}