package ninja.bryansills.roses.entry

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import ninja.bryansills.roses.R

class EntryActivity : AppCompatActivity() {
    companion object {
        const val CATEGORY_ID = "CATEGORY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        AndroidInjection.inject(this)

        val categoryId = intent.getStringExtra(CATEGORY_ID)
        Log.d("BLARG", categoryId)
    }
}