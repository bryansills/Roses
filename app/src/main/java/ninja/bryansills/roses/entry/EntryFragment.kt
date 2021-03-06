package ninja.bryansills.roses.entry

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import ninja.bryansills.roses.R
import ninja.bryansills.roses.binding.BindingFragment
import ninja.bryansills.roses.databinding.FragmentEntryBinding

@AndroidEntryPoint
class EntryFragment : Fragment(), BindingFragment<FragmentEntryBinding> {
    private val entryViewModel: RealEntryViewModel by viewModels()
    val args: EntryFragmentArgs by navArgs()
    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entry, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.title = args.categoryName
        val entryList = binding.entryList
        entryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        entryList.adapter = EntryAdapter {
            if (it.url != null && activity != null) {
                val uri = Uri.parse(it.url)
                val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                if (browserIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(browserIntent)
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        entryViewModel.getEntries(args.categoryId)
            .observe(viewLifecycleOwner, Observer { binding.uiModel = it })
    }

    override fun getBinding(): FragmentEntryBinding = binding
}
