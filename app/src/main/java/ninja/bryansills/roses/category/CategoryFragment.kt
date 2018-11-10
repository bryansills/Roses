package ninja.bryansills.roses.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.R
import ninja.bryansills.roses.ViewModelFactory
import ninja.bryansills.roses.databinding.FragmentCategoryBinding
import javax.inject.Inject

class CategoryFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var subscription: CompositeDisposable

    lateinit var binding: FragmentCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: RecyclerView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        categoryList = binding.root.findViewById(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(context)
        categoryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        categoryList.adapter = CategoryAdapter {
            binding.root.findNavController().navigate(CategoryFragmentDirections.selectCategory(it.id, it.title))
        }.also { this.categoryAdapter = it }

        categoryViewModel = ViewModelProviders.of(this, viewModelFactory)[CategoryViewModel::class.java]
        subscription = CompositeDisposable()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        subscription.add(categoryViewModel.categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { when (it) {
                            is CategoryUiModel.Success -> onSuccess(it.categories)
                            CategoryUiModel.Loading -> onLoading()
                            is CategoryUiModel.Error -> Log.d("BLARG", it.error.message)
                        } }, { Log.d("BLARG", "throwing ${it.message}")}
                ))
    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }

    fun onSuccess(categories: List<Category>) {
        binding.loading = false
        categoryAdapter.submitList(categories)
    }

    fun onLoading() {
        binding.loading = true
    }
}