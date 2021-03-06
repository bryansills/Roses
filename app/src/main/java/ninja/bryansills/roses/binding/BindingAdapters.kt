package ninja.bryansills.roses.binding

import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("published")
    fun formatPublished(textView: TextView, published: Date) {
        textView.text = DateUtils.getRelativeTimeSpanString(textView.context, published.time)
    }

    @JvmStatic
    @BindingAdapter("html")
    fun formatHtml(textView: TextView, html: String?) {
        if (html != null) {
            textView.text = HtmlCompat.fromHtml(html, FROM_HTML_MODE_COMPACT, null, null)
        }
    }

    @JvmStatic
    @BindingAdapter("data")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        (recyclerView.adapter as? ListAdapter<T, *>)?.submitList(items)
    }
}