package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ninja.bryansills.database.test.DatabaseTestUtils
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

        db.originDao().upsertOrigin(input)
                .test()
                .assertValue { it >= 0L }
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun upsertInsertAutoIncrements() {
        val first = DatabaseTestUtils.createOrigin(1)
        val second = DatabaseTestUtils.createOrigin(2)

        val firstOutput = db.originDao().upsertOrigin(first)
        val secondOutput = db.originDao().upsertOrigin(second)

        Single.zip(firstOutput, secondOutput, BiFunction<Long, Long, Pair<Long, Long>> { left, right -> Pair(left, right) })
                .test()
                .assertValue {(first, second) -> first < second }
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun upsertUpdate() {
        val first = DatabaseTestUtils.createOrigin(1)
        val duplicate = DatabaseTestUtils.createOrigin(1)

        val firstOutput = db.originDao().upsertOrigin(first)
        val duplicateOutput = db.originDao().upsertOrigin(duplicate)

        Single.zip(firstOutput, duplicateOutput, BiFunction<Long, Long, Pair<Long, Long>> { left, right -> Pair(left, right) })
                .test()
                .assertValue {(first, duplicate) -> first == duplicate }
                .assertComplete()
                .assertNoErrors()
    }

//    @Test
//    fun upsertUpdateOldOrigin() {
//        val first = DatabaseTestUtils.createOrigin(1)
//        val second = DatabaseTestUtils.createOrigin(2)
//        val third = DatabaseTestUtils.createOrigin(3)
//        val firstDuplicate = DatabaseTestUtils.createOrigin(1)
//
//        val firstOutput = db.originDao().upsertOrigin(first)
//        val secondOutput = db.originDao().upsertOrigin(second)
//        val thirdOutput = db.originDao().upsertOrigin(third)
//        val firstDuplicateOutput = db.originDao().upsertOrigin(firstDuplicate)
//
////        Single.zip(firstOutput, secondOutput, thirdOutput, { first: Long, second: Long, third: Long -> listOf(first, second, third) } )
////        assertTrue(firstOutput == firstDuplicateOutput)
////        assertTrue(firstOutput <= secondOutput)
////        assertTrue(secondOutput <= thirdOutput)
//    }
}