package ninja.bryansills.roses.network

import dagger.Component
import ninja.bryansills.roses.network.models.MoshiComponent

@Component(
    dependencies = [MoshiComponent::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent {
    fun networkService(): NetworkService

    @Component.Builder
    interface Builder {
        fun build(): NetworkComponent
        fun networkModule(networkModule: NetworkModule): Builder
        fun moshiComponent(moshiComponent: MoshiComponent): Builder
    }
}