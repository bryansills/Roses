package ninja.bryansills.roses.network.models

import com.squareup.moshi.Moshi
import dagger.Component

@Component(modules = [MoshiModule::class])
interface MoshiComponent {
    fun moshi(): Moshi
}