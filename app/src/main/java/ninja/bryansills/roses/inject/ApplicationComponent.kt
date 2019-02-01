package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ninja.bryansills.repo.RepoComponent
import ninja.bryansills.roses.RosesApplication
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [RepoComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityModule::class
])
interface ApplicationComponent: AndroidInjector<RosesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RosesApplication>() {
        abstract fun repoComponent(repoComponent: RepoComponent): Builder
    }
}
