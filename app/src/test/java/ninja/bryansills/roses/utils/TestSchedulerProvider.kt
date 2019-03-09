package ninja.bryansills.roses.utils

import io.reactivex.schedulers.Schedulers
import ninja.bryansills.roses.rx.SchedulerProvider

class TestSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}