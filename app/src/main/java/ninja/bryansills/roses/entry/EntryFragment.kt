package ninja.bryansills.roses.entry

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.roses.R
import ninja.bryansills.roses.databinding.FragmentEntryBinding
import javax.inject.Inject

@SuppressLint("ValidFragment")
class EntryFragment @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private val entryViewModel: EntryViewModel by viewModels { viewModelFactory }
    private val args: EntryFragmentArgs by navArgs()
    private lateinit var binding: FragmentEntryBinding
    lateinit var entryAdapter: EntryAdapter
    lateinit var entryList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.title = args.categoryName
        entryList = binding.entryList
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        entryViewModel.getEntries(args.categoryId).observe(viewLifecycleOwner, Observer {
            if (it is EntryUiModel.Success) {
                entryAdapter.submitList(it.entries)
            }
        })
    }
}