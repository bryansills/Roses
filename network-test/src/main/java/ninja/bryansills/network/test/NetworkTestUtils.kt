package ninja.bryansills.network.test

import ninja.bryansills.network.models.streams.EntryResponse
import ninja.bryansills.network.models.streams.Origin
import ninja.bryansills.network.models.streams.StreamContentsResponse

object NetworkTestUtils {
    fun createStreamContentsResponse(id: Int = 1, count: Int = 0): StreamContentsResponse =
            StreamContentsResponse(
                    "TEST_ID_$id",
                    id.toLong(),
                    "TEST_CONTINUATION_$id",
                    IntRange(0, count).map { createEntryResponse(it) }.toTypedArray())

    fun createEntryResponse(id: Int): EntryResponse {
        val origin = Origin(
                "TEST_STREAM_ID_$id",
                "TEST_ORIGIN_TITLE_$id",
                "TEST_ORIGIN_URL_$id"
        )

        return EntryResponse(
                "TEST_ID_$id",
                "TEST_TITLE_$id",
                id.toLong(),
                "TEST_AUTHOR_$id",
                "TEST_CANONICAL_URL_$id",
                origin.streamId,
                origin,
                null, null
                )
    }
}