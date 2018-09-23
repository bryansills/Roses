package ninja.bryansills.roses

import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.roses.databinding.ItemCategoryBinding

class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(uiCategory: UiCategory, clickListener: (UiCategory) -> Unit) {
        binding.category = uiCategory
        binding.root.setOnClickListener { clickListener(uiCategory) }
        binding.executePendingBindings()
    }
}
