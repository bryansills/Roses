package ninja.bryansills.roses.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import ninja.bryansills.database.test.DatabaseTestUtils
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OriginDaoTest : DbTest() {

    @Test
    fun upsertInsert() = runBlocking {
        val input = DatabaseTestUtils.createOrigin(1)

        val originId = db.originDao().upsertOrigin(input)

        assertThat(originId, `is`(1L))
    }

    @Test
    fun upsertInsertAutoIncrements() = runBlocking {
        val first = DatabaseTestUtils.createOrigin(1)
        val second = DatabaseTestUtils.createOrigin(2)

        val firstOriginId = db.originDao().upsertOrigin(first)
        val secondOriginId = db.originDao().upsertOrigin(second)

        assertTrue(firstOriginId < secondOriginId)
    }

    @Test
    fun upsertUpdate() = runBlocking {
        val first = DatabaseTestUtils.createOrigin(1)
        val duplicate = DatabaseTestUtils.createOrigin(1)

        val firstOriginId = db.originDao().upsertOrigin(first)
        val duplicateOriginId = db.originDao().upsertOrigin(duplicate)

        assertThat(firstOriginId, `is`(duplicateOriginId))
    }

    @Test
    fun upsertUpdateOldOrigin() = runBlocking {
        val firstInput = DatabaseTestUtils.createOrigin(1)
        val secondInput = DatabaseTestUtils.createOrigin(2)
        val thirdInput = DatabaseTestUtils.createOrigin(3)
        val firstDuplicateInput = DatabaseTestUtils.createOrigin(1)

        val firstOriginId = db.originDao().upsertOrigin(firstInput)
        val secondOriginId = db.originDao().upsertOrigin(secondInput)
        val thirdOriginId = db.originDao().upsertOrigin(thirdInput)
        val firstDuplicateOriginId = db.originDao().upsertOrigin(firstDuplicateInput)

        assertThat(firstOriginId, `is`(firstDuplicateOriginId))
        assertTrue(firstOriginId < secondOriginId)
        assertTrue(secondOriginId < thirdOriginId)
    }
}