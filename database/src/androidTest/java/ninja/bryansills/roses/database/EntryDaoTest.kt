package ninja.bryansills.roses.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryDaoTest : DbTest() {

    @Test
    fun simpleLastUpdated() = runBlocking {
        upsertOriginAndInsertEntries(db, 1, 1)
        val lastUpdated = db.entryDao().getLastUpdatedAt()

        assertThat(lastUpdated, `is`(1L))
    }

//    @Test
//    fun lastUpdated() {
//        val firstOutput = Utils.upsertOriginAndInsertEntries(db, 1, 1)
//                .flatMap { db.entryDao().getLastUpdatedAt() }
//        val secondOutput = Utils.upsertOriginAndInsertEntries(db, 2, 2)
//                .flatMap { db.entryDao().getLastUpdatedAt() }
//
//        Singles.zip(firstOutput, secondOutput) { first, second -> Pair(first, second) }
//                .test()
//                .assertValue { (first, _) -> 1L == first }
//                .assertValue { (_, second) -> 2L == second }
//                .assertComplete()
//                .assertNoErrors()
//    }
//
//    @Test
//    fun emptyLastUpdatedFails() {
//        db.entryDao().getLastUpdatedAt()
//                .test()
//                .assertError { it is EmptyResultSetException }
//    }
}