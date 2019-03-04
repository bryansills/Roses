package ninja.bryansills.roses.inject

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {
    @Provides
    fun compositeDisposable() = CompositeDisposable()

    @Provides
    fun observeOnScheduler() = AndroidSchedulers.mainThread()
}
