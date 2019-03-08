package ninja.bryansills.roses.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.LiveDataUtils.getValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
        getValue(categoryViewModel.getCategories())

        fakeRepository.categorySubject.onNext(FetchCategoryResult.Success(emptyList()))
        val result = getValue(categoryViewModel.getCategories())

        assertTrue(result is CategoryUiModel.Success)
        assertEquals(result.categories, emptyList())
    }

    @Test
    fun normalSuccess() {
        getValue(categoryViewModel.getCategories())

        val categories = listOf(
                Category("1", "FIRST", 4),
                Category("2", "SECOND", 6)
        )

        fakeRepository.categorySubject.onNext(FetchCategoryResult.Success(categories))
        val result = getValue(categoryViewModel.getCategories())

        assertTrue(result is CategoryUiModel.Success)
        assertEquals(result.categories, categories)
    }

    @Test
    fun expectedError() {
        getValue(categoryViewModel.getCategories())

        fakeRepository.categorySubject.onNext(FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.API_KEY_INVALID))
        val result = getValue(categoryViewModel.getCategories())

        assertTrue(result is CategoryUiModel.Error)
        assertEquals(result.error, R.string.api_key_invalid)
    }

    @Test
    fun unexpectedError() {
        getValue(categoryViewModel.getCategories())

        fakeRepository.categorySubject.onError(Throwable("LOL WTF"))
        val result = getValue(categoryViewModel.getCategories())

        assertTrue(result is CategoryUiModel.Error)
        assertEquals(result.error, R.string.unknown_category_error)
    }
}
