package ninja.bryansills.roses.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.roses.R
import ninja.bryansills.roses.ViewModelFactory
import ninja.bryansills.roses.entry.EntryActivity
import ninja.bryansills.roses.entry.EntryActivity.Companion.CATEGORY_ID
import javax.inject.Inject


class CategoryActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var categoryViewModel: CategoryViewModel
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
            val intent = Intent(this, EntryActivity::class.java)
            intent.putExtra(CATEGORY_ID, it.id)
            startActivity(intent)
        }.also { this.categoryAdapter = it }

        categoryViewModel = ViewModelProviders.of(this, viewModelFactory)[CategoryViewModel::class.java]
        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(categoryViewModel.categories()
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
