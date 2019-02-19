package ninja.bryansills.roses

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ninja.bryansills.background.DaggerWorkerComponent
import ninja.bryansills.background.WorkerManager
import ninja.bryansills.background.WorkerModule
import ninja.bryansills.repo.DaggerRepoComponent
import ninja.bryansills.repo.RepoModule
import ninja.bryansills.roses.database.DaggerDatabaseComponent
import ninja.bryansills.roses.database.DatabaseModule
import ninja.bryansills.roses.inject.DaggerApplicationComponent
import ninja.bryansills.roses.network.DaggerNetworkComponent
import ninja.bryansills.roses.network.NetworkModule
import ninja.bryansills.roses.network.models.DaggerMoshiComponent
import ninja.bryansills.roses.network.models.MoshiModule
import javax.inject.Inject


class RosesApplication : DaggerApplication() {

    @Inject lateinit var workerManager: WorkerManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val databaseModule = DatabaseModule(applicationContext)
        val databaseComponent = DaggerDatabaseComponent.builder()
                .databaseModule(databaseModule)
                .build()

        val moshiComponent = DaggerMoshiComponent.builder()
                .moshiModule(MoshiModule())
                .build()
        val networkModule = NetworkModule(BuildConfig.FEEDLY_ACCESS_TOKEN)
        val networkComponent = DaggerNetworkComponent.builder()
                .moshiComponent(moshiComponent)
                .networkModule(networkModule)
                .build()

        val repoModule = RepoModule(3)
        val repoComponent = DaggerRepoComponent.builder()
                .databaseComponent(databaseComponent)
                .networkComponent(networkComponent)
                .repoModule(repoModule)
                .build()

        val workerModule = WorkerModule(applicationContext, 3)
        val workerComponent = DaggerWorkerComponent.builder()
                .repoComponent(repoComponent)
                .workerModule(workerModule)
                .build()

        return DaggerApplicationComponent.builder()
                .repoComponent(repoComponent)
                .workerComponent(workerComponent)
                .create(this)
    }
}
