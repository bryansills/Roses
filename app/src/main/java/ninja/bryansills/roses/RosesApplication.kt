package ninja.bryansills.roses

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ninja.bryansills.roses.inject.DaggerApplicationComponent


class RosesApplication : DaggerApplication() {

//    @Inject lateinit var workerManager: WorkerManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder().create(this)
}
