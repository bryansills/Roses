package ninja.bryansills.database.test

import ninja.bryansills.roses.database.models.Category
import ninja.bryansills.roses.database.models.Entry
import ninja.bryansills.roses.database.models.Origin

object DatabaseTestUtils {
    fun createOrigin(id: Int): Origin {
        return Origin(
                networkId = "TEST_NETWORK_ID_$id",
                title = "TEST_TITLE_$id",
                htmlUrl = "TEST_URL_$id"
        )
    }

    fun createEntry(id: Int, updatedAt: Long, originId: Long): Entry {
        return Entry("TEST_ID_$id",
                "TEST_TITLE_$id",
                "TEST_URL_$id",
                1L,
                "TEST_AUTHOR_$id",
                "TEST_SUMMARY_$id",
                updatedAt,
                originId)
    }

    fun createCategory(id: Int): Category {
        return Category("TEST_ID_$id", "TEST_TITLE_$id", id)
    }
}