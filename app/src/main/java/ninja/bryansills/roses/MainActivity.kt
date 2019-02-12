package ninja.bryansills.roses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.navigation.findNavController
import dagger.android.AndroidInjection
import ninja.bryansills.roses.navigation.RosesNavHostFragment
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val bundle = Bundle()
            bundle.putInt("android-support-nav:fragment:graphId", R.navigation.nav_graph)
            val navFragment = fragmentFactory.instantiate(RosesNavHostFragment::class.java.classLoader!!, RosesNavHostFragment::class.java.name, bundle) as RosesNavHostFragment
            supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, navFragment)
                    .setPrimaryNavigationFragment(navFragment) // this is the equivalent to app:defaultNavHost="true"
                    .commit()
        }
    }

    override fun onSupportNavigateUp() =
            findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
}
