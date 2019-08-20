package ninja.bryansills.roses.network

import ninja.bryansills.roses.network.models.streams.StreamContentsResponse

interface NetworkService {
    suspend fun streamContents(): StreamContentsResponse
}
