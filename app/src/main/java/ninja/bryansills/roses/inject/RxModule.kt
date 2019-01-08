package ninja.bryansills.roses.inject

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {
    @Provides
    fun compositeDisposable() = CompositeDisposable()
}
