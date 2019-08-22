package ninja.bryansills.roses.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
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

    @Test
    fun lastUpdated() = runBlocking {
        upsertOriginAndInsertEntries(db, 1, 100, 4L)
        val firstLastUpdated = db.entryDao().getLastUpdatedAt()

        upsertOriginAndInsertEntries(db, 2, 300, 7L)
        val secondLastUpdated = db.entryDao().getLastUpdatedAt()

        assertThat(firstLastUpdated, `is`(4L))
        assertThat(secondLastUpdated, `is`(7L))
    }

    @Test
    fun lastUpdatedOutOfOrder() = runBlocking {
        upsertOriginAndInsertEntries(db, 1, 100, 40L)
        val firstLastUpdated = db.entryDao().getLastUpdatedAt()

        upsertOriginAndInsertEntries(db, 2, 300, 7L)
        val secondLastUpdated = db.entryDao().getLastUpdatedAt()

        assertThat(firstLastUpdated, `is`(40L))
        assertThat(secondLastUpdated, `is`(40L))
    }

    @Test
    fun emptyLastUpdated() = runBlocking {
        val lastUpdated = db.entryDao().getLastUpdatedAt()

        assertThat(lastUpdated, `is`(nullValue()))
    }
}