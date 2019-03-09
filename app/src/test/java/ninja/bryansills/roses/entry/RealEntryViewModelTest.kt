package ninja.bryansills.roses.entry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.TestSchedulerProvider
import ninja.bryansills.roses.utils.getLatestValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RealEntryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository
    lateinit var entryViewModel: EntryViewModel

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        entryViewModel = RealEntryViewModel(fakeRepository, TestSchedulerProvider())
    }

    @Test
    fun defaultsToLoading() {
        val initialValue = entryViewModel.getEntries("LOL").getLatestValue()
        assertTrue(initialValue is EntryUiModel.Loading)
    }

    @Test
    fun basicSuccess() {
        fakeRepository.entriesSubject.onNext(FetchEntryResult.Success(emptyList()))
        val realResult = entryViewModel.getEntries("LOL").getLatestValue()

        assertTrue(realResult is EntryUiModel.Success)
        assertEquals(realResult.results, emptyList())
    }

    @Test
    fun normalSuccess() {
        val entries = listOf(
                Entry("1", "FIRST_TITLE", "FIRST_URL", Date(), "FIRST_AUTHOR", "FIRST_SUMMARY"),
                Entry("2", "SECOND_TITLE", "SECOND_URL", Date(), "SECOND_AUTHOR", "SECOND_SUMMARY")
        )
        fakeRepository.entriesSubject.onNext(FetchEntryResult.Success(entries))
        val realResult = entryViewModel.getEntries("LOL").getLatestValue()

        assertTrue(realResult is EntryUiModel.Success)
        assertEquals(realResult.results, entries)
    }

    @Test
    fun expectedError() {
        fakeRepository.entriesSubject.onNext(FetchEntryResult.Error(FetchEntryResult.FetchEntryError.UNKNOWN))
        val realResult = entryViewModel.getEntries("LOL").getLatestValue()

        assertTrue(realResult is EntryUiModel.Error)
        assertEquals(realResult.error, R.string.unknown_entry_error)
    }

    @Test
    fun unexpectedError() {
        fakeRepository.entriesSubject.onError(Throwable("LOL ERROR"))
        val realResult = entryViewModel.getEntries("LOL").getLatestValue()

        assertTrue(realResult is EntryUiModel.Error)
        assertEquals(realResult.error, R.string.unknown_entry_error)
    }
}