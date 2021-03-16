package ninja.bryansills.roses.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import ninja.bryansills.roses.R
import ninja.bryansills.roses.binding.BindingFragment
import ninja.bryansills.roses.databinding.FragmentCategoryBinding

@AndroidEntryPoint
class CategoryFragment : Fragment(), BindingFragment<FragmentCategoryBinding> {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        val categoryList = binding.categoryList
        categoryList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        categoryList.adapter = CategoryAdapter {
            binding.root.findNavController()
                .navigate(CategoryFragmentDirections.selectCategory(it.id, it.title))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.getCategories()
            .observe(viewLifecycleOwner, Observer { binding.uiModel = it })
    }

    override fun getBinding(): FragmentCategoryBinding = binding
}
