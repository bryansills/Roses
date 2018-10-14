package ninja.bryansills.roses.binding

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("published")
    fun formatPublished(textView: TextView, published: Date) {
        textView.text = DateUtils.getRelativeTimeSpanString(textView.context, published.time, true)
    }
}