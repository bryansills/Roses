package ninja.bryansills.roses.repo

import ninja.bryansills.roses.database.models.Origin
import ninja.bryansills.roses.network.models.streams.EntryResponse
import java.util.Date

fun List<EntryResponse>.toOrigin(): Origin {
    val origin = this[0].origin
    return Origin(null, origin.streamId, origin.title, origin.htmlUrl)
}

fun List<EntryResponse>.toDbEntries(): List<ninja.bryansills.roses.database.models.Entry> {
    val timestamp = Date().time
    return this.map { netEntry ->
        ninja.bryansills.roses.database.models.Entry(
            netEntry.id,
            netEntry.title,
            netEntry.url,
            netEntry.published,
            netEntry.author,
            netEntry.summary?.content,
            timestamp,
            null
        )
    }
}
