package ninja.bryansills.roses

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.database.DatabaseService
import ninja.bryansills.network.NetworkService
import ninja.bryansills.repo.RealRepository
import ninja.bryansills.repo.Repository


class MainActivity : AppCompatActivity() {

    lateinit var repo: Repository
    lateinit var subscription: CompositeDisposable

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryList = findViewById(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(this)
        categoryList.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        categoryList.adapter = CategoryAdapter {
            Log.d("BLARG", it.toString())
        }.also { this.categoryAdapter = it }

        var networkService = NetworkService(BuildConfig.FEEDLY_ACCESS_TOKEN)
        var databaseService = DatabaseService(applicationContext)
        repo = RealRepository(networkService, databaseService)

        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(repo.categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> categoryAdapter.submitList(response) },
                        { error -> Log.e("BLARG", "error: " + error.toString()) }
                )
        )
    }

    override fun onPause() {
        subscription.clear()
        super.onPause()
    }
}
