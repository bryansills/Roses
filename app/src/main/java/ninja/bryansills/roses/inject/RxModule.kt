package ninja.bryansills.roses.inject

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class RxModule {
    @Provides
    fun observeOnScheduler() = AndroidSchedulers.mainThread()!!
}
