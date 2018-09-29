package ninja.bryansills.roses

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Repository
import javax.inject.Inject


class CategoryActivity : AppCompatActivity() {

    @Inject lateinit var repository: Repository
    lateinit var subscription: CompositeDisposable

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        AndroidInjection.inject(this)

        categoryList = findViewById(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(this)
        categoryList.addItemDecoration(DividerItemDecoration(this, VERTICAL))
        categoryList.adapter = CategoryAdapter {
            Log.d("BLARG", it.toString())
        }.also { this.categoryAdapter = it }

        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(repository.categories()
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
