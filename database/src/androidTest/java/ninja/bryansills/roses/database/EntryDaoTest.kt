package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxkotlin.Singles
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntryDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun simpleLastUpdated() {
        Utils.upsertOriginAndInsertEntries(db, 1, 1)
                .flatMap { db.entryDao().getLastUpdatedAt() }
                .test()
                .assertValue { 1L == it }
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun lastUpdated() {
        val firstOutput = Utils.upsertOriginAndInsertEntries(db, 1, 1)
                .flatMap { db.entryDao().getLastUpdatedAt() }
        val secondOutput = Utils.upsertOriginAndInsertEntries(db, 2, 2)
                .flatMap { db.entryDao().getLastUpdatedAt() }

        Singles.zip(firstOutput, secondOutput) { first, second -> Pair(first, second) }
                .test()
                .assertValue { (first, _) -> 1L == first }
                .assertValue { (_, second) -> 2L == second }
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun emptyLastUpdatedFails() {
        db.entryDao().getLastUpdatedAt()
                .test()
                .assertError { it is EmptyResultSetException }
    }
}