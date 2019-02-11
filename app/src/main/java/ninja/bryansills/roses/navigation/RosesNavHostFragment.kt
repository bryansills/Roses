package ninja.bryansills.roses.navigation

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import javax.inject.Inject

class RosesNavHostFragment @Inject constructor() : NavHostFragment() {
    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
        return RosesFragmentNavigator(requireContext(), childFragmentManager, id)
    }
}