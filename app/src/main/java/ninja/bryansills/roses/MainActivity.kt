package ninja.bryansills.roses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.network.NetworkService

class MainActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService
    lateinit var subscription: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = NetworkService(BuildConfig.FEEDLY_ACCESS_TOKEN)
        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(networkService.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { profile -> Log.d("BLARG", profile.toString()) },
                        { error -> Log.e("BLARG", error.toString()) }
                )
        )
    }

    override fun onPause() {
        subscription.clear()
        super.onPause()
    }
}
