package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ninja.bryansills.roses.RosesApplication

@Component(
        dependencies = [FakeWorkerComponent::class, FakeRepoComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityModule::class
        ]
)
interface TestApplicationComponent : AndroidInjector<RosesApplication> {
        @Component.Builder
        abstract class Builder : AndroidInjector.Builder<RosesApplication>() { // AndroidInjector<RosesApplication>???
            abstract fun fakeRepoComponent(fakeRepoComponent: FakeRepoComponent): Builder
            abstract fun fakeWorkerComponent(fakeWorkerComponent: FakeWorkerComponent): Builder
        }
}