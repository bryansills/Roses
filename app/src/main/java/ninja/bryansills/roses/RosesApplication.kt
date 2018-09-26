package ninja.bryansills.roses

import android.app.Activity
import android.app.Application
import androidx.databinding.DataBindingUtil
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class RosesApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this).build().inject(this)
        DataBindingUtil.setDefaultComponent(DataBindingComponent())
    }

    override fun activityInjector() = dispatchingActivityInjector
}