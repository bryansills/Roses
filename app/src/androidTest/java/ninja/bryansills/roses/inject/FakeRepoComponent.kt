package ninja.bryansills.roses.inject

import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository

@Component(modules = [FakeRepoModule::class])
interface FakeRepoComponent {
    fun fakeRepo(): Repository

    @Component.Builder
    interface Builder {
        fun build(): FakeRepoComponent
        fun fakeRepoModule(fakeRepoModule: FakeRepoModule): Builder
    }
}

@Module
class FakeRepoModule {
    @Provides
    fun fakeRepository(): Repository = FakeRepository()
}

class FakeRepository : Repository {
    override fun categories(): Observable<FetchCategoryResult> {
        return Observable.just(FetchCategoryResult.Success(emptyList()))
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        return Flowable.just(emptyList())
    }

    override fun updateDatabase(): Completable {
        return Completable.complete()
    }

}