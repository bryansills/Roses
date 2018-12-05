package ninja.bryansills.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun basicCategoryCount() {
        val firstOrigin = DatabaseTestUtils.createOrigin(1)
        val firstOriginId = db.originDao().upsertOrigin(firstOrigin)
        val firstEntry = DatabaseTestUtils.createEntry(1, 1L, firstOriginId)
        val firstNewerEntry = DatabaseTestUtils.createEntry(2, 2L, firstOriginId)
        db.entryDao().insertEntries(listOf(firstEntry, firstNewerEntry)).blockingAwait()

        val secondOrigin = DatabaseTestUtils.createOrigin(2)
        val secondOriginId = db.originDao().upsertOrigin(secondOrigin)
        val secondEntry = DatabaseTestUtils.createEntry(3, 1L, secondOriginId)
        db.entryDao().insertEntries(listOf(secondEntry)).blockingAwait()

        val thirdOrigin = DatabaseTestUtils.createOrigin(3)
        val thirdOriginId = db.originDao().upsertOrigin(thirdOrigin)

        val categories = db.categoryDao().getAllCategories().blockingFirst()

        val firstCategory = categories.first { it.id.toLong() == firstOriginId }
        val secondCategory = categories.first { it.id.toLong() == secondOriginId }
        val thirdCategory = categories.firstOrNull { it.id.toLong() == thirdOriginId }

        assertTrue(firstCategory.count == 2)
        assertTrue(secondCategory.count == 1)
        assertTrue(thirdCategory == null)
    }
}