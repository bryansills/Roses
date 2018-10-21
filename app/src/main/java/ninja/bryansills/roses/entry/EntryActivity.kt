package ninja.bryansills.roses.entry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import javax.inject.Inject

class EntryActivity : AppCompatActivity() {
    companion object {
        const val CATEGORY_ID = "CATEGORY_ID"
    }

    @Inject lateinit var repository: Repository
    lateinit var subscription: CompositeDisposable
    lateinit var categoryId: String

    lateinit var entryAdapter: EntryAdapter
    lateinit var entryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        AndroidInjection.inject(this)

        entryList = findViewById(R.id.entry_list)
        entryList.layoutManager = LinearLayoutManager(this)
        entryList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        entryList.adapter = EntryAdapter {
            if (it.url != null) {
                val uri = Uri.parse(it.url)
                val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                if (browserIntent.resolveActivity(this.packageManager) != null) {
                    startActivity(browserIntent)
                }
            }
        }.also { this.entryAdapter = it }

        categoryId = intent.getStringExtra(CATEGORY_ID)
        subscription = CompositeDisposable()
    }

    override fun onResume() {
        super.onResume()
        subscription.add(repository.getEntries(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> entryAdapter.submitList(response) },
                        { error -> Log.w("BLARG", error.toString()) }
                ))
    }
}