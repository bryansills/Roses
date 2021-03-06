package ninja.bryansills.roses.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun database(appDatabase: AppDatabase): DatabaseService = RealDatabaseService(appDatabase)

    @Provides
    fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "roses.db"
    )
        .fallbackToDestructiveMigration()
        .build()
}
