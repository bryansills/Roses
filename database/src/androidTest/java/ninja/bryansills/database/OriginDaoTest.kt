package ninja.bryansills.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
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
        val input = createOrigin(1)

        val outputId = db.originDao().upsertOrigin(input)

        assertTrue(outputId >= 0L)
    }

    @Test
    fun upsertInsertAutoIncrements() {
        val first = createOrigin(1)
        val second = createOrigin(2)

        val firstOutput = db.originDao().upsertOrigin(first)
        val secondOutput = db.originDao().upsertOrigin(second)

        assertTrue(firstOutput < secondOutput)
    }

    @Test
    fun upsertUpdate() {
        val first = createOrigin(1)
        val firstDuplicate = createOrigin(1)

        val firstOutput = db.originDao().upsertOrigin(first)
        val firstDuplicateOutput = db.originDao().upsertOrigin(firstDuplicate)

        assertTrue(firstOutput == firstDuplicateOutput)
    }

    @Test
    fun upsertUpdateOldOrigin() {
        val first = createOrigin(1)
        val second = createOrigin(2)
        val third = createOrigin(3)
        val firstDuplicate = createOrigin(1)

        val firstOutput = db.originDao().upsertOrigin(first)
        val secondOutput = db.originDao().upsertOrigin(second)
        val thirdOutput = db.originDao().upsertOrigin(third)
        val firstDuplicateOutput = db.originDao().upsertOrigin(firstDuplicate)

        assertTrue(firstOutput == firstDuplicateOutput)
        assertTrue(firstOutput <= secondOutput)
        assertTrue(secondOutput <= thirdOutput)
    }

    private fun createOrigin(id: Int): Origin {
        return Origin(
                networkId = "TEST_NETWORK_ID_$id",
                title = "TEST_TITLE_$id",
                htmlUrl = "TEST_URL_$id"
        )
    }
}