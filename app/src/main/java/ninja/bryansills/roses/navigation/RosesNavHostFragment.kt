package ninja.bryansills.roses.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import javax.inject.Inject

class RosesNavHostFragment @Inject constructor() : NavHostFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).setupActionBarWithNavController(navController)
    }

    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return RosesFragmentNavigator(requireContext(), childFragmentManager, id)
    }
}