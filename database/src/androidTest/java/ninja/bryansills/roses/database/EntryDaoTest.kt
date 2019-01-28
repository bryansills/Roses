package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Test
//    fun simpleLastUpdated() {
//        val origin = DatabaseTestUtils.createOrigin(1)
//        val originId = db.originDao().upsertOrigin(origin)
//        val entry = DatabaseTestUtils.createEntry(1, 1L, originId)
//
//        db.entryDao().insertEntries(listOf(entry)).blockingAwait()
//        val outputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()
//
//        assertTrue(1L == outputUpdatedAt)
//    }
//
//    @Test
//    fun lastUpdated() {
//        val origin = DatabaseTestUtils.createOrigin(1)
//        val originId = db.originDao().upsertOrigin(origin)
//        val entry = DatabaseTestUtils.createEntry(1, 1L, originId)
//        val newerEntry = DatabaseTestUtils.createEntry(2, 2L, originId)
//
//        db.entryDao().insertEntries(listOf(entry)).blockingAwait()
//        val firstOutputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()
//
//        db.entryDao().insertEntries(listOf(newerEntry)).blockingAwait()
//        val secondOutputUpdatedAt = db.entryDao().getLastUpdatedAt().blockingGet()
//
//        assertTrue(1L == firstOutputUpdatedAt)
//        assertTrue(2L == secondOutputUpdatedAt)
//    }
//
//    @Test(expected = EmptyResultSetException::class)
//    fun emptyLastUpdatedFails() {
//        db.entryDao().getLastUpdatedAt().blockingGet()
//    }
}