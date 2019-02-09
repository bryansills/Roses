package ninja.bryansills.roses.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import ninja.bryansills.roses.TestRosesApplication

class InstrumentedTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestRosesApplication::class.java.name, context)
    }
}