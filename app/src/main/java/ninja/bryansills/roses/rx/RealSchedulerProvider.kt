package ninja.bryansills.roses.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RealSchedulerProvider : SchedulerProvider {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}