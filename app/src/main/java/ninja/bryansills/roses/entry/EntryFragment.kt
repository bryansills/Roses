package ninja.bryansills.roses.entry

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import ninja.bryansills.roses.R
import ninja.bryansills.roses.ViewModelFactory
import javax.inject.Inject

class EntryFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val entryViewModel: EntryViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory)[EntryViewModel::class.java]
    }

    val args: EntryFragmentArgs by navArgs()

    lateinit var entryAdapter: EntryAdapter
    lateinit var entryList: RecyclerView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.title = args.categoryName
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        entryViewModel.entries.observe(viewLifecycleOwner, Observer {
            entryAdapter.submitList(it)
        })
        entryViewModel.initEntries(args.categoryId)
    }
}