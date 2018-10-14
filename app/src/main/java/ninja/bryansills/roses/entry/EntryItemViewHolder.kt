package ninja.bryansills.roses.entry

import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Entry
import ninja.bryansills.roses.databinding.ItemEntryBinding

class EntryItemViewHolder(val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: Entry, clickListener: (Entry) -> Unit) {
        binding.entry = entry
        binding.root.setOnClickListener { clickListener(entry) }
        binding.executePendingBindings()
    }
}
