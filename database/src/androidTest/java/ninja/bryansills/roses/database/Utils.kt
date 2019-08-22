package ninja.bryansills.roses.database

import ninja.bryansills.database.test.DatabaseTestUtils

suspend fun upsertOriginAndInsertEntries(db: AppDatabase, originId: Int, count: Int): Long {
    val firstOrigin = DatabaseTestUtils.createOrigin(originId)
    val firstOriginId = db.originDao().upsertOrigin(firstOrigin)

    val entries = (1..count).map { index ->
        DatabaseTestUtils.createEntry(hashIndex(originId, index), index.toLong(), firstOriginId)
    }
    db.entryDao().insertEntries(entries)

    return firstOriginId
}

private fun hashIndex(originId: Int, index: Int): Int = (originId * 31 + 37) * (index * 41 + 43) * 53 + 59