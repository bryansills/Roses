package ninja.bryansills.roses.category

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import ninja.bryansills.roses.R
import ninja.bryansills.roses.ViewModelFactory
import ninja.bryansills.roses.entry.EntryActivity
import javax.inject.Inject

class CategoryFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var subscription: CompositeDisposable

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var categoryList: RecyclerView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        categoryList = view.findViewById(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(context)
        categoryList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        categoryList.adapter = CategoryAdapter {
            val intent = Intent(context, EntryActivity::class.java)
            intent.putExtra(EntryActivity.CATEGORY_ID, it.id)
            startActivity(intent)
        }.also { this.categoryAdapter = it }

        categoryViewModel = ViewModelProviders.of(this, viewModelFactory)[CategoryViewModel::class.java]
        subscription = CompositeDisposable()

        return view
    }
}