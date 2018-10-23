package ninja.bryansills.roses.entry

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.roses.R
import ninja.bryansills.roses.ViewModelFactory
import javax.inject.Inject

class EntryFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var entryViewModel: EntryViewModel
    lateinit var subscription: CompositeDisposable

    lateinit var categoryId: String
    lateinit var entryAdapter: EntryAdapter
    lateinit var entryList: RecyclerView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = EntryFragmentArgs.fromBundle(arguments).categoryId
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        entryList = view.findViewById(R.id.entry_list)
        entryList.layoutManager = LinearLayoutManager(context)
        entryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        entryList.adapter = EntryAdapter {
            if (it.url != null && activity != null) {
                val uri = Uri.parse(it.url)
                val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                if (browserIntent.resolveActivity(activity!!.packageManager) != null) {
                    startActivity(browserIntent)
                }
            }
        }.also { this.entryAdapter = it }

        entryViewModel = ViewModelProviders.of(this, viewModelFactory)[EntryViewModel::class.java]
        subscription = CompositeDisposable()

        return view
    }

    override fun onStart() {
        super.onStart()
        subscription.add(entryViewModel.getEntries(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> entryAdapter.submitList(response) },
                        { error -> Log.w("BLARG", error.toString()) }
                ))
    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }
}