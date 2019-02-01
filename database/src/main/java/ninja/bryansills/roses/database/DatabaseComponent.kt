package ninja.bryansills.roses.database

import android.content.Context
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun databaseService(): DatabaseService

    @Component.Builder
    interface Builder {
        fun build(): DatabaseComponent
        fun context(context: Context): Builder
    }
}