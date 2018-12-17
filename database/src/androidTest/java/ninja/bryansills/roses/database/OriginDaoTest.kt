package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import ninja.bryansills.database.test.DatabaseTestUtils
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OriginDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun upsertInsert() {
        val input = DatabaseTestUtils.createOrigin(1)

        val outputId = db.originDao().upsertOrigin(input)

        assertTrue(outputId >= 0L)
    }

    @Test
    fun upsertInsertAutoIncrements() {
        val first = DatabaseTestUtils.createOrigin(1)
        val second = DatabaseTestUtils.createOrigin(2)

        val firstOutput = db.originDao().upsertOrigin(first)
        val secondOutput = db.originDao().upsertOrigin(second)

        assertTrue(firstOutput < secondOutput)
    }

    @Test
    fun upsertUpdate() {
        val first = DatabaseTestUtils.createOrigin(1)
        val firstDuplicate = DatabaseTestUtils.createOrigin(1)

        val firstOutput = db.originDao().upsertOrigin(first)
        val firstDuplicateOutput = db.originDao().upsertOrigin(firstDuplicate)

        assertTrue(firstOutput == firstDuplicateOutput)
    }

    @Test
    fun upsertUpdateOldOrigin() {
        val first = DatabaseTestUtils.createOrigin(1)
        val second = DatabaseTestUtils.createOrigin(2)
        val third = DatabaseTestUtils.createOrigin(3)
        val firstDuplicate = DatabaseTestUtils.createOrigin(1)

        val firstOutput = db.originDao().upsertOrigin(first)
        val secondOutput = db.originDao().upsertOrigin(second)
        val thirdOutput = db.originDao().upsertOrigin(third)
        val firstDuplicateOutput = db.originDao().upsertOrigin(firstDuplicate)

        assertTrue(firstOutput == firstDuplicateOutput)
        assertTrue(firstOutput <= secondOutput)
        assertTrue(secondOutput <= thirdOutput)
    }
}