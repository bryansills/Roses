package ninja.bryansills.roses.category

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.R
import ninja.bryansills.roses.binding.BindingFragment
import ninja.bryansills.roses.databinding.FragmentCategoryBinding
import javax.inject.Inject

@SuppressLint("ValidFragment")
class CategoryFragment @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) : Fragment(), BindingFragment {

    private val categoryViewModel: CategoryViewModel by viewModels { viewModelFactory }
    lateinit var binding: FragmentCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        categoryList = binding.root.findViewById(R.id.category_list)
        categoryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        categoryList.adapter = CategoryAdapter {
            binding.root.findNavController().navigate(CategoryFragmentDirections.selectCategory(it.id, it.title))
        }.also { this.categoryAdapter = it }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.getCategories().observe(viewLifecycleOwner, Observer {
            when (it) {
                is CategoryUiModel.Success -> onSuccess(it.categories)
                CategoryUiModel.Loading -> onLoading()
                is CategoryUiModel.Error -> onError(it.error)
            }
        })
    }

    fun onSuccess(categories: List<Category>) {
        binding.loading = false
        categoryAdapter.submitList(categories)
    }

    fun onLoading() {
        binding.loading = true
    }

    fun onError(error: String) {
        binding.loading = false
        binding.error = error
    }

    override fun getBinding(): ViewDataBinding = binding
}