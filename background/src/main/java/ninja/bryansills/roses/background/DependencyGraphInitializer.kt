package ninja.bryansills.roses.background

import android.content.Context
import androidx.startup.Initializer

class DependencyGraphInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
