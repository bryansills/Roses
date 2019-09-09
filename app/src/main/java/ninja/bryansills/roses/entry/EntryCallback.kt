package ninja.bryansills.roses.entry

import androidx.recyclerview.widget.DiffUtil
import ninja.bryansills.repo.Entry

class EntryCallback : DiffUtil.ItemCallback<Entry>() {
    override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
        return oldItem == newItem
    }
}
