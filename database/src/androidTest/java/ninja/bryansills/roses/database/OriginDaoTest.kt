package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.zipWith
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

        Singles.zip(firstOutput, secondOutput) { left, right -> Pair(left, right) }
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

        Singles.zip(firstOutput, duplicateOutput) { left, right -> Pair(left, right) }
                .test()
                .assertValue {(first, duplicate) -> first == duplicate }
                .assertComplete()
                .assertNoErrors()
    }

    @Test
    fun upsertUpdateOldOrigin() {
        val firstInput = DatabaseTestUtils.createOrigin(1)
        val secondInput = DatabaseTestUtils.createOrigin(2)
        val thirdInput = DatabaseTestUtils.createOrigin(3)
        val firstDuplicateInput = DatabaseTestUtils.createOrigin(1)

        val firstSingle = db.originDao().upsertOrigin(firstInput)
        val secondSingle = db.originDao().upsertOrigin(secondInput)
        val thirdSingle = db.originDao().upsertOrigin(thirdInput)
        val firstDuplicateSingle = db.originDao().upsertOrigin(firstDuplicateInput)

        Singles.zip(firstSingle, secondSingle, thirdSingle) { first, second, third -> listOf(first, second, third) }
                .zipWith(firstDuplicateSingle) { groupResult, duplicateResult -> Pair(groupResult, duplicateResult)}
                .test()
                .assertValue { (group, duplicate) ->
                    val first = group[0]
                    first == duplicate
                }
                .assertValue { (group, _) ->
                    val first = group[0]
                    val second = group[1]
                    first < second
                }
                .assertValue { (group, _) ->
                    val second = group[1]
                    val third = group[2]
                    second < third
                }
                .assertComplete()
                .assertNoErrors()
    }
}