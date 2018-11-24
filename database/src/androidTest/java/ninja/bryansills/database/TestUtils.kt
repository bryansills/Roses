package ninja.bryansills.database

object TestUtils {
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
}