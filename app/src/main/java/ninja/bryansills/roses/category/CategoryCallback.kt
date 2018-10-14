package ninja.bryansills.roses.category

import androidx.recyclerview.widget.DiffUtil
import ninja.bryansills.repo.Category


class CategoryCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}