package ninja.bryansills.roses.entry

import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import ninja.bryansills.repo.Entry
import ninja.bryansills.roses.R
import ninja.bryansills.roses.databinding.ItemEntryBinding

class EntryItemViewHolder(val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: Entry, clickListener: (Entry) -> Unit) {
        binding.entry = entry
        binding.root.setOnClickListener { clickListener(entry) }

        val summaryText = binding.root.findViewById<TextView>(R.id.entry_summary)
        summaryText.afterMeasured {
            if (this is TextView) {
                if (lineCount > 5) {
                    val endOfLastLine = layout.getLineEnd(4)
                    val subset = text.subSequence(0, endOfLastLine - 3)
                    val ellipsized = SpannableStringBuilder(subset).append("...").toSpannable()

                    text = ellipsized
                }
            }
        }

        binding.executePendingBindings()
    }
}

inline fun <T: View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}