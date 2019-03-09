package ninja.bryansills.roses.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.TestSchedulerProvider
import ninja.bryansills.roses.utils.getLatestValue
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
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestSchedulerProvider())
    }

    @Test
    fun defaultsToLoading() {
        val initialValue = categoryViewModel.getCategories().getLatestValue()
        assertTrue(initialValue is CategoryUiModel.Loading)
    }

    @Test
    fun basicSuccess() {
        fakeRepository.categorySubject.onNext(FetchCategoryResult.Success(emptyList()))
        val result = categoryViewModel.getCategories().getLatestValue()

        assertTrue(result is CategoryUiModel.Success)
        assertEquals(result.categories, emptyList())
    }

    @Test
    fun normalSuccess() {
        val categories = listOf(
                Category("1", "FIRST", 4),
                Category("2", "SECOND", 6)
        )

        fakeRepository.categorySubject.onNext(FetchCategoryResult.Success(categories))
        val result = categoryViewModel.getCategories().getLatestValue()

        assertTrue(result is CategoryUiModel.Success)
        assertEquals(result.categories, categories)
    }

    @Test
    fun expectedError() {
        fakeRepository.categorySubject.onNext(FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.API_KEY_INVALID))
        val result = categoryViewModel.getCategories().getLatestValue()

        assertTrue(result is CategoryUiModel.Error)
        assertEquals(result.error, R.string.api_key_invalid)
    }

    @Test
    fun unexpectedError() {
        fakeRepository.categorySubject.onError(Throwable("LOL WTF"))
        val result = categoryViewModel.getCategories().getLatestValue()

        assertTrue(result is CategoryUiModel.Error)
        assertEquals(result.error, R.string.unknown_category_error)
    }
}
