package ninja.bryansills.roses.entry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.schedulers.Schedulers
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.roses.R
import ninja.bryansills.roses.utils.FakeRepository
import ninja.bryansills.roses.utils.LiveDataUtils.getValue
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
        entryViewModel = RealEntryViewModel(fakeRepository, Schedulers.trampoline())
    }

    @Test
    fun defaultsToLoading() {
        val initialValue = getValue(entryViewModel.getEntries("LOL"))
        assertTrue(initialValue is EntryUiModel.Loading)
    }

    @Test
    fun basicSuccess() {
        val loadingResult = getValue(entryViewModel.getEntries("LOL"))
        fakeRepository.entriesSubject.onNext(FetchEntryResult.Success(emptyList()))
        val realResult = getValue(entryViewModel.getEntries("LOL"))

        assertTrue(loadingResult is EntryUiModel.Loading)
        assertTrue(realResult is EntryUiModel.Success)
        assertEquals(realResult.results, emptyList())
    }

    @Test
    fun normalSuccess() {
        getValue(entryViewModel.getEntries("LOL"))

        val entries = listOf(
                Entry("1", "FIRST_TITLE", "FIRST_URL", Date(), "FIRST_AUTHOR", "FIRST_SUMMARY"),
                Entry("2", "SECOND_TITLE", "SECOND_URL", Date(), "SECOND_AUTHOR", "SECOND_SUMMARY")
        )
        fakeRepository.entriesSubject.onNext(FetchEntryResult.Success(entries))
        val realResult = getValue(entryViewModel.getEntries("LOL"))

        assertTrue(realResult is EntryUiModel.Success)
        assertEquals(realResult.results, entries)
    }

    @Test
    fun expectedError() {
        getValue(entryViewModel.getEntries("LOL"))

        fakeRepository.entriesSubject.onNext(FetchEntryResult.Error(FetchEntryResult.FetchEntryError.UNKNOWN))
        val realResult = getValue(entryViewModel.getEntries("LOL"))

        assertTrue(realResult is EntryUiModel.Error)
        assertEquals(realResult.error, R.string.unknown_entry_error)
    }

    @Test
    fun unexpectedError() {
        getValue(entryViewModel.getEntries("LOL"))

        fakeRepository.entriesSubject.onError(Throwable("LOL ERROR"))
        val realResult = getValue(entryViewModel.getEntries("LOL"))

        assertTrue(realResult is EntryUiModel.Error)
        assertEquals(realResult.error, R.string.unknown_entry_error)
    }
}