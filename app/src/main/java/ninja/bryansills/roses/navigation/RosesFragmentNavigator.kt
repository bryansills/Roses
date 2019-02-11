package ninja.bryansills.roses.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("root")
class RosesFragmentNavigator(context: Context, manager: FragmentManager, containerId: Int) : FragmentNavigator(context, manager, containerId) {
    override fun instantiateFragment(context: Context, fragmentManager: FragmentManager, className: String, args: Bundle?): Fragment {
        return fragmentManager.fragmentFactory.instantiate(Class.forName(className).classLoader!!, className, args)
    }
}