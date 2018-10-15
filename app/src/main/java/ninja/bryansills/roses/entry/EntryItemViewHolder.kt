package ninja.bryansills.roses.entry

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.toSpannable
import androidx.core.view.doOnLayout
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Entry
import ninja.bryansills.roses.R
import ninja.bryansills.roses.databinding.ItemEntryBinding

class EntryItemViewHolder(val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: Entry, clickListener: (Entry) -> Unit) {
        binding.entry = entry
        binding.root.setOnClickListener { clickListener(entry) }

        val summaryText = binding.root.findViewById<TextView>(R.id.entry_summary)
        summaryText.doOnLayout {
            if (it is TextView) {
                if (it.lineCount > 5) {
                    val endOfLastLine = it.layout.getLineEnd(4)
                    val subset = it.text.subSequence(0, endOfLastLine - 3)
                    val ellipsized = SpannableStringBuilder(subset).append("...").toSpannable()

                    it.text = ellipsized

                    it.postDelayed(20) {
                        it.invalidate()
                        it.parent.requestLayout()
                    }
                }
            }
        }

        binding.executePendingBindings()
    }
}