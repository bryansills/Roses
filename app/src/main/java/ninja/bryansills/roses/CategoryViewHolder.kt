package ninja.bryansills.roses

import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.databinding.ItemCategoryBinding

class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category, clickListener: (Category) -> Unit) {
        binding.category = category
        binding.root.setOnClickListener { clickListener(category) }
        binding.executePendingBindings()
    }
}
