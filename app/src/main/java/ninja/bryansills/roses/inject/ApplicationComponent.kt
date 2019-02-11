package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import ninja.bryansills.background.WorkerComponent
import ninja.bryansills.repo.RepoComponent
import ninja.bryansills.roses.RosesApplication
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [RepoComponent::class, WorkerComponent::class],
        modules = [
            ApplicationModule::class,
            ActivityModule::class,
            FragmentModule::class
])
interface ApplicationComponent: AndroidInjector<RosesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RosesApplication>() {
        abstract fun repoComponent(repoComponent: RepoComponent): Builder
        abstract fun workerComponent(workerComponent: WorkerComponent): Builder
    }
}
