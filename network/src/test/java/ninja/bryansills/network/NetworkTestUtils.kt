package ninja.bryansills.network

import ninja.bryansills.network.streams.EntryResponse
import ninja.bryansills.network.streams.Origin
import ninja.bryansills.network.streams.StreamContentsResponse

object NetworkTestUtils {
    fun createStreamContentsResponse(): StreamContentsResponse =
            StreamContentsResponse(
                    "TEST_ID",
                    1L,
                    "TEST_CONTINUATION",
                    arrayOf(createEntryResponse()))

    fun createEntryResponse(): EntryResponse {
        val origin = Origin(
                "TEST_STREAM_ID",
                "TEST_TITLE",
                "TEST_URL"
        )

        return EntryResponse(
                "TEST_ID",
                null,
                1L,
                null,
                null,
                origin.streamId,
                origin,
                null, null
                )
    }
}