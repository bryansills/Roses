package ninja.bryansills.roses.inject

// @Singleton
// @Component(
//     dependencies = [RepoComponent::class, WorkerComponent::class],
//     modules = [
//         ApplicationModule::class
//     ]
// )
// interface ApplicationComponent : AndroidInjector<RosesApplication> {
//     @Component.Builder
//     abstract class Builder : AndroidInjector.Builder<RosesApplication>() {
//         abstract fun repoComponent(repoComponent: RepoComponent): Builder
//         abstract fun workerComponent(workerComponent: WorkerComponent): Builder
//     }
// }

// @InstallIn(SingletonComponent::class)
// @Module(
//     includes = [
//         ApplicationModule::class
//     ]
// )
// interface AggregatorModule
