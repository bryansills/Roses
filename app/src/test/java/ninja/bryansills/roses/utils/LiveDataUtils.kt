package ninja.bryansills.roses.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <U> LiveData<U>.getLatestValue(): U? {
    var result: U? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<U> {
        override fun onChanged(latest: U?) {
            result = latest
            latch.countDown()
            this@getLatestValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return result
}