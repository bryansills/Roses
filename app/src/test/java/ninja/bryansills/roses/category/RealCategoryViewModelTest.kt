package ninja.bryansills.roses.category

import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.repo.Category
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.TestCoroutineDispatchers
import ninja.bryansills.roses.utils.ViewModelTest
import ninja.bryansills.roses.utils.observeOnce
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RealCategoryViewModelTest : ViewModelTest() {

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
        fakeRepository.categories = FetchCategoryResult.Error(
            FetchCategoryResult.FetchCategoryError.API_KEY_INVALID
        )
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
