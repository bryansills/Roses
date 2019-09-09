package ninja.bryansills.roses.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ninja.bryansills.repo.Category
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.TestCoroutineDispatchers
import ninja.bryansills.roses.utils.ViewModelTest
import ninja.bryansills.roses.utils.observeOnce
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RealCategoryViewModelTest : ViewModelTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository
    lateinit var categoryViewModel: CategoryViewModel

    @Before
    override fun setup() {
        super.setup()
        fakeRepository = FakeRepository()
    }

    @Test
    fun defaultsToLoading() {
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestCoroutineDispatchers())

        categoryViewModel.getCategories().observeOnce { actual ->
            assertTrue(actual is CategoryUiModel.Loading)
        }
    }

    @Test
    fun basicSuccess() {
        fakeRepository.categories = FetchCategoryResult.Success(emptyList())
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestCoroutineDispatchers())

        categoryViewModel.getCategories().observeOnce { actual ->
            assertTrue(actual is CategoryUiModel.Success)
            assertEquals(actual.categories, emptyList())
        }
    }

    @Test
    fun normalSuccess() {
        val categories = listOf(
            Category("1", "FIRST", 4),
            Category("2", "SECOND", 6)
        )

        fakeRepository.categories = FetchCategoryResult.Success(categories)
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestCoroutineDispatchers())

        categoryViewModel.getCategories().observeOnce { actual ->
            assertTrue(actual is CategoryUiModel.Success)
            assertEquals(actual.categories, categories)
        }
    }

    @Test
    fun expectedError() {
        fakeRepository.categories = FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.API_KEY_INVALID)
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestCoroutineDispatchers())

        categoryViewModel.getCategories().observeOnce { actual ->
            assertTrue(actual is CategoryUiModel.Error)
            assertEquals(actual.error, R.string.api_key_invalid)
        }
    }

    @Test
    fun unexpectedError() {
        fakeRepository.throwCategoriesError = true
        categoryViewModel = RealCategoryViewModel(fakeRepository, TestCoroutineDispatchers())

        categoryViewModel.getCategories().observeOnce { actual ->
            assertTrue(actual is CategoryUiModel.Error)
            assertEquals(actual.error, R.string.unknown_category_error)
        }
    }
}
