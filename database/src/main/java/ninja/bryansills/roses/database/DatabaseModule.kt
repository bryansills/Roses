package ninja.bryansills.roses.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    @Provides
    @Singleton
    fun context(): Context = context

    @Provides
    @Singleton
    fun database(appDatabase: AppDatabase): DatabaseService = RealDatabaseService(appDatabase)

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "roses.db")
            .fallbackToDestructiveMigration()
            .build()
}