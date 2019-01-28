package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.Completable
import ninja.bryansills.database.test.DatabaseTestUtils
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
        val firstOriginId = db.originDao().upsertOrigin(firstOrigin).blockingGet()
        val firstEntry = DatabaseTestUtils.createEntry(1, 1L, firstOriginId)
        val firstNewerEntry = DatabaseTestUtils.createEntry(2, 2L, firstOriginId)
        db.entryDao().insertEntries(listOf(firstEntry, firstNewerEntry)).blockingAwait()

        val secondOrigin = DatabaseTestUtils.createOrigin(2)
        val secondOriginId = db.originDao().upsertOrigin(secondOrigin).blockingGet()
        val secondEntry = DatabaseTestUtils.createEntry(3, 1L, secondOriginId)
        db.entryDao().insertEntries(listOf(secondEntry)).blockingAwait()

        val thirdOrigin = DatabaseTestUtils.createOrigin(3)
        val thirdOriginId = db.originDao().upsertOrigin(thirdOrigin).blockingGet()

        val categories = db.categoryDao().getAllCategories().blockingFirst()

        val firstCategory = categories.first { it.id.toLong() == firstOriginId }
        val secondCategory = categories.first { it.id.toLong() == secondOriginId }
        val thirdCategory = categories.firstOrNull { it.id.toLong() == thirdOriginId }

        assertTrue(firstCategory.count == 2)
        assertTrue(secondCategory.count == 1)
        assertTrue(thirdCategory == null)
    }

//    @Test
//    fun correctWayToDoThis() {
//        var firstId: Long? = null
//        var secondId: Long? = null
//        var thirdId: Long? = null
//
//        upsertOriginAndEntries(1, 2) { first -> firstId = first}
//                .andThen {
//                    upsertOriginAndEntries(2, 1) { second -> secondId = second }
//                }.andThen {
//                    upsertOriginAndEntries(3, 0) { third -> thirdId = third }
//                }.andThen(db.categoryDao().getAllCategories())
//                .test()
//                .assertValue { categories ->
//                    val firstCategory = categories.first { it.id.toLong() == firstId }
//                    firstCategory.count == 2
//                }.assertValue { categories ->
//                    val secondCategory = categories.first { it.id.toLong() == secondId }
//                    secondCategory.count == 1
//                }.assertValue { categories ->
//                    val thirdCategory = categories.firstOrNull { it.id.toLong() == thirdId }
//                    thirdCategory == null
//                }
//                .assertNoErrors()
//    }

    fun upsertOriginAndEntries(originId: Int, count: Int, originIdCallback: (Long) -> Unit): Completable {
        val firstOrigin = DatabaseTestUtils.createOrigin(originId)
        return db.originDao().upsertOrigin(firstOrigin)
                .flatMapCompletable { firstOriginId ->
                    originIdCallback(firstOriginId)
                    val entries = (1..count).map { index ->
                        DatabaseTestUtils.createEntry(index, index.toLong(), firstOriginId)
                    }

                    db.entryDao().insertEntries(entries)
                }
    }
}