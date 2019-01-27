package ninja.bryansills.roses.network.models

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoshiModule {
    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder().build()
}