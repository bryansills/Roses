package ninja.bryansills.roses.entry

import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ninja.bryansills.roses.repo.Entry
import ninja.bryansills.roses.repo.FetchEntryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.TestCoroutineDispatchers
import ninja.bryansills.roses.utils.ViewModelTest
import ninja.bryansills.roses.utils.observeOnce
import org.junit.Before
import org.junit.Test

class RealEntryViewModelTest : ViewModelTest() {

    lateinit var fakeRepository: FakeRepository
    lateinit var entryViewModel: EntryViewModel

    @Before
    override fun setup() {
        super.setup()

        fakeRepository = FakeRepository()
        entryViewModel = RealEntryViewModel(fakeRepository, TestCoroutineDispatchers())
    }

    @Test
    fun defaultsToLoading() {
        entryViewModel.getEntries("LOL").observeOnce { actual ->
            assertTrue(actual is EntryUiModel.Loading)
        }
    }

    @Test
    fun basicSuccess() {
        fakeRepository.entries = FetchEntryResult.Success(emptyList())
        entryViewModel.getEntries("LOL").observeOnce { actual ->
            assertTrue(actual is EntryUiModel.Success)
            assertEquals(actual.results, emptyList())
        }
    }

    @Test
    fun normalSuccess() {
        val entries = listOf(
            Entry("1", "FIRST_TITLE", "FIRST_URL", Date(), "FIRST_AUTHOR", "FIRST_SUMMARY"),
            Entry("2", "SECOND_TITLE", "SECOND_URL", Date(), "SECOND_AUTHOR", "SECOND_SUMMARY")
        )
        fakeRepository.entries = FetchEntryResult.Success(entries)
        entryViewModel.getEntries("LOL").observeOnce { actual ->
            assertTrue(actual is EntryUiModel.Success)
            assertEquals(actual.results, entries)
        }
    }

    @Test
    fun expectedError() {
        fakeRepository.entries = FetchEntryResult.Error(FetchEntryResult.FetchEntryError.UNKNOWN)
        entryViewModel.getEntries("LOL").observeOnce { actual ->
            assertTrue(actual is EntryUiModel.Error)
            assertEquals(actual.error, R.string.unknown_entry_error)
        }
    }

    @Test
    fun unexpectedError() {
        fakeRepository.throwEntriesError = true
        entryViewModel.getEntries("LOL").observeOnce { actual ->
            assertTrue(actual is EntryUiModel.Error)
            assertEquals(actual.error, R.string.unknown_entry_error)
        }
    }
}
