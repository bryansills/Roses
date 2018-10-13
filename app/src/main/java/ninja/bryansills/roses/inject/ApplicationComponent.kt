package ninja.bryansills.roses.inject

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ninja.bryansills.roses.RosesApplication

@Component(modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityModule::class,
            ViewModelModule::class
])
interface ApplicationComponent: AndroidInjector<RosesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RosesApplication>()
}
