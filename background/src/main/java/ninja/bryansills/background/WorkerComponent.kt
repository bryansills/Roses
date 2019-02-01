package ninja.bryansills.background

import dagger.Component
import ninja.bryansills.repo.RepoComponent

@Component(
        dependencies = [RepoComponent::class],
        modules = [WorkerModule::class]
)
interface WorkerComponent {
    fun workerManager(): WorkerManager

    @Component.Builder
    interface Builder {
        fun build(): WorkerComponent
        fun repoComponent(repoComponent: RepoComponent): Builder
        fun workerModule(workerModule: WorkerModule): Builder
    }
}