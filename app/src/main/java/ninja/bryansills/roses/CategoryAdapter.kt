package ninja.bryansills.roses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ninja.bryansills.roses.databinding.ItemCategoryBinding

class CategoryAdapter(val clickListener: (UiCategory) -> Unit) : ListAdapter<UiCategory, CategoryViewHolder>(UiCategoryCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val categoryBinding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(categoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class UiCategoryCallback : DiffUtil.ItemCallback<UiCategory>() {
    override fun areItemsTheSame(oldItem: UiCategory, newItem: UiCategory): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiCategory, newItem: UiCategory): Boolean {
        return oldItem == newItem
    }
}