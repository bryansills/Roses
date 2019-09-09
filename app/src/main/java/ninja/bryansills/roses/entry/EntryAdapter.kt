package ninja.bryansills.roses.entry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ninja.bryansills.repo.Entry
import ninja.bryansills.roses.databinding.ItemEntryBinding

class EntryAdapter(val clickListener: (Entry) -> Unit) : ListAdapter<Entry, EntryItemViewHolder>(EntryCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val entryBinding = ItemEntryBinding.inflate(inflater, parent, false)
        return EntryItemViewHolder(entryBinding)
    }

    override fun onBindViewHolder(holder: EntryItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}