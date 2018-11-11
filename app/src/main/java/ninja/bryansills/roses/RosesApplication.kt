package ninja.bryansills.roses

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ninja.bryansills.background.WorkerManager
import ninja.bryansills.roses.inject.DaggerApplicationComponent
import javax.inject.Inject


class RosesApplication : DaggerApplication() {

    @Inject lateinit var workerManager: WorkerManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder().create(this)
}
