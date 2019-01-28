package ninja.bryansills.roses.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import io.reactivex.rxkotlin.Singles
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun basicCategoryCount() {
        val firstInput = Utils.upsertOriginAndInsertEntries(db, 1, 2)
        val secondInput = Utils.upsertOriginAndInsertEntries(db, 2, 1)
        val thirdInput = Utils.upsertOriginAndInsertEntries(db, 3, 0)

        Singles.zip(firstInput, secondInput, thirdInput) { one, two, three -> listOf(one, two, three) }
                .flatMap { originIds ->
                    db.categoryDao().getAllCategories()
                            .map { categories -> Pair(originIds, categories) }
                            .firstOrError()
                }
                .test()
                .assertValue { (originIds, categories) ->
                    val firstId = originIds[0]
                    val firstCategory = categories.first { cat -> cat.id.toLong() == firstId }
                    firstCategory.count == 2
                }
                .assertValue { (originIds, categories) ->
                    val secondId = originIds[1]
                    val secondCategory = categories.first { it.id.toLong() == secondId }
                    secondCategory.count == 1
                }
                .assertValue { (originIds, categories) ->
                    val thirdId = originIds[2]
                    val thirdCategory = categories.firstOrNull { it.id.toLong() == thirdId }
                    thirdCategory == null
                }
                .assertNoErrors()
    }
}