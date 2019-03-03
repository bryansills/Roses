package ninja.bryansills.roses.category

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.R
import ninja.bryansills.roses.binding.BindingFragment
import ninja.bryansills.roses.databinding.FragmentCategoryBinding
import javax.inject.Inject

@SuppressLint("ValidFragment")
class CategoryFragment @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) : Fragment(), BindingFragment {

    private val categoryViewModel: CategoryViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        val categoryList = binding.categoryList
        categoryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        categoryList.adapter = CategoryAdapter {
            binding.root.findNavController().navigate(CategoryFragmentDirections.selectCategory(it.id, it.title))
        }

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

    private fun onSuccess(categories: List<Category>) {
        binding.loading = false
        binding.categories = categories
    }

    private fun onLoading() {
        binding.loading = true
    }

    private fun onError(@StringRes error: Int) {
        binding.loading = false
        binding.error = resources.getString(error)
    }

    override fun getBinding(): ViewDataBinding = binding
}