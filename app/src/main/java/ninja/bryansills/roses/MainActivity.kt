package ninja.bryansills.roses

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import ninja.bryansills.repo.DefaultRepository
import ninja.bryansills.repo.Repository

class MainActivity : AppCompatActivity() {

    lateinit var repo: Repository
    lateinit var subscription: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var networkService = NetworkService(BuildConfig.FEEDLY_ACCESS_TOKEN)
        var databaseService = DatabaseService(applicationContext)
        repo = DefaultRepository(networkService, databaseService)

        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(repo.entries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> Log.d("BLARG", "response: " + response.toString()) },
                        { error -> Log.e("BLARG", "error: " + error.toString()) }
                )
        )
    }

    override fun onPause() {
        subscription.clear()
        super.onPause()
    }
}
