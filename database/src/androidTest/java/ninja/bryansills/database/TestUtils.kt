package ninja.bryansills.database

object TestUtils {
    fun createOrigin(id: Int): Origin {
        return Origin(
                networkId = "TEST_NETWORK_ID_$id",
                title = "TEST_TITLE_$id",
                htmlUrl = "TEST_URL_$id"
        )
    }

    fun createEntry(updatedAt: Long, originId: Long): Entry {
        return Entry("TEST_ID",
                "TEST_TITLE",
                "TEST_URL",
                1L,
                "TEST_AUTHOR",
                "TEST_SUMMARY",
                updatedAt,
                originId)
    }
}