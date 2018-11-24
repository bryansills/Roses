package ninja.bryansills.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun simpleLastUpdated() {
        val origin = TestUtils.createOrigin(1)
        val originId = db.originDao().upsertOrigin(origin)
        val entry = TestUtils.createEntry(1, 1L, originId)

        db.entryDao().insertEntries(listOf(entry)).blockingAwait()
        val outputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()

        assertTrue(1L == outputUpdatedAt)
    }

    @Test
    fun lastUpdated() {
        val origin = TestUtils.createOrigin(1)
        val originId = db.originDao().upsertOrigin(origin)
        val entry = TestUtils.createEntry(1, 1L, originId)
        val newerEntry = TestUtils.createEntry(2, 2L, originId)

        db.entryDao().insertEntries(listOf(entry)).blockingAwait()
        val firstOutputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()

        db.entryDao().insertEntries(listOf(newerEntry)).blockingAwait()
        val secondOutputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()

        assertTrue(1L == firstOutputUpdatedAt)
        assertTrue(2L == secondOutputUpdatedAt)
    }

    @Test(expected = EmptyResultSetException::class)
    fun emptyLastUpdatedFails() {
        db.entryDao().getLastUpdatedAt().blockingGet()
    }
}