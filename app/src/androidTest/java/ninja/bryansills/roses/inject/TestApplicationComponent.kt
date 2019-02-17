package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import ninja.bryansills.roses.TestRosesApplication

@Component(
        dependencies = [FakeWorkerComponent::class, FakeRepoComponent::class],
        modules = [
            ApplicationModule::class
        ]
)
interface TestApplicationComponent : AndroidInjector<TestRosesApplication> {
        @Component.Builder
        abstract class Builder : AndroidInjector.Builder<TestRosesApplication>() {
            abstract fun fakeRepoComponent(fakeRepoComponent: FakeRepoComponent): Builder
            abstract fun fakeWorkerComponent(fakeWorkerComponent: FakeWorkerComponent): Builder
        }
}