package ninja.bryansills.repo

import dagger.Component
import ninja.bryansills.roses.database.DatabaseComponent
import ninja.bryansills.roses.network.NetworkComponent

@Component(
    dependencies = [DatabaseComponent::class, NetworkComponent::class],
    modules = [RepoModule::class]
)
interface RepoComponent {
    fun repository(): Repository

    // @Component.Builder
    // interface Builder {
    //     fun build(): RepoComponent
    //     fun repoModule(repoModule: RepoModule): Builder
    //     fun databaseComponent(databaseComponent: DatabaseComponent): Builder
    //     fun networkComponent(networkComponent: NetworkComponent): Builder
    // }
}
