package ninja.bryansills.roses.category

import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Category
import ninja.bryansills.roses.databinding.ItemCategoryBinding

class CategoryItemViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    inline fun bind(category: Category, crossinline clickListener: (Category) -> Unit) {
        binding.category = category
        binding.root.setOnClickListener { clickListener(category) }
        binding.executePendingBindings()
    }
}
