package ninja.bryansills.roses.database

import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun databaseService(): DatabaseService

    @Component.Builder
    interface Builder {
        fun build(): DatabaseComponent
        fun databaseModule(databaseModule: DatabaseModule): Builder
    }
}