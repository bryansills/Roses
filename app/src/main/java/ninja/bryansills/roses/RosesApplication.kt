package ninja.bryansills.roses

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class RosesApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder().create(this)
}
