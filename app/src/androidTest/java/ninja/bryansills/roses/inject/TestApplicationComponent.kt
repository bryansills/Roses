package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ninja.bryansills.roses.TestRosesApplication

@Component(
        dependencies = [FakeRepoComponent::class],
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityModule::class
        ]
)
interface TestApplicationComponent : AndroidInjector<TestRosesApplication> {
        @Component.Builder
        abstract class Builder : AndroidInjector.Builder<TestRosesApplication>() {
            abstract fun fakeRepoComponent(fakeRepoComponent: FakeRepoComponent): Builder
        }
}