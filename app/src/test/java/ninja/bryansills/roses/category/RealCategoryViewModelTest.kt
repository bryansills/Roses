package ninja.bryansills.roses.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.utils.getValue
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RealCategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository
    lateinit var categoryViewModel: CategoryViewModel

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        categoryViewModel = RealCategoryViewModel(fakeRepository, Schedulers.trampoline())
    }

    @Test
    fun defaultsToLoading() {
        val initialValue = getValue(categoryViewModel.getCategories())
        assertTrue(initialValue is CategoryUiModel.Loading)
    }

    @Test
    fun basicSuccess() {
        fakeRepository.categorySubject.onNext(FetchCategoryResult.Success(emptyList()))
        val result = getValue(categoryViewModel.getCategories())
        assertTrue(result is CategoryUiModel.Success)
    }
}

class FakeRepository : Repository {
    val categorySubject = PublishSubject.create<FetchCategoryResult>()

    override fun categories(): Observable<FetchCategoryResult> = categorySubject
    override fun getEntries(categoryId: String): Flowable<List<Entry>> = Flowable.just(emptyList())
    override fun updateDatabase(): Completable = Completable.complete()
}