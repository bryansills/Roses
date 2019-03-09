package ninja.bryansills.roses.inject

import dagger.Module
import dagger.Provides
import ninja.bryansills.roses.rx.RealSchedulerProvider
import ninja.bryansills.roses.rx.SchedulerProvider

@Module
class RxModule {
    @Provides
    fun schedulerProvider(): SchedulerProvider = RealSchedulerProvider()
}
