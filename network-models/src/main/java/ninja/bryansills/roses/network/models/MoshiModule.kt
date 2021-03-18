package ninja.bryansills.roses.network.models

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module
object MoshiModule {
    @Provides
    fun moshi(): Moshi = Moshi.Builder().build()
}
