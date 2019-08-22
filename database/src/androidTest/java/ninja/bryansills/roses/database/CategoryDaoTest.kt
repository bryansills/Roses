package ninja.bryansills.roses.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest : DbTest() {

    @Test
    fun basicCategoryCount() = runBlocking {
        val firstCategoryId = upsertOriginAndInsertEntries(db, 1, 2)
        val secondCategoryId = upsertOriginAndInsertEntries(db, 2, 1)
        val thirdCategoryId = upsertOriginAndInsertEntries(db, 3, 0)

        val allCategories = db.categoryDao().getAllCategories()

        val firstCategory = allCategories.first { category -> category.id.toLong() == firstCategoryId }
        assertThat(firstCategory.count, `is`(2))

        val secondCategory = allCategories.first { category -> category.id.toLong() == secondCategoryId }
        assertThat(secondCategory.count, `is`(1))

        val thirdCategory = allCategories.firstOrNull { category -> category.id.toLong() == thirdCategoryId }
        assertThat(thirdCategory, `is`(nullValue()))
    }
}