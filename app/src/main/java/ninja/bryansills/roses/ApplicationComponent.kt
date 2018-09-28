package ninja.bryansills.roses

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            ActivityModule::class
])
interface ApplicationComponent: AndroidInjector<RosesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RosesApplication>()
}
