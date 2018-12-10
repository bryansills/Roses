package ninja.bryansills.roses.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.databinding.ItemCategoryBinding

class CategoryAdapter : ListAdapter<Category, CategoryItemViewHolder>(CategoryCallback()) {

    var clickListener: (Category) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val categoryBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryItemViewHolder(categoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}