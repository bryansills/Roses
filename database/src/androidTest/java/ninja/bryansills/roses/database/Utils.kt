package ninja.bryansills.roses.database

import io.reactivex.Single
import ninja.bryansills.database.test.DatabaseTestUtils

object Utils {
    fun upsertOriginAndInsertEntries(db: AppDatabase, originId: Int, count: Int): Single<Long> {
        val firstOrigin = DatabaseTestUtils.createOrigin(originId)
        return Single.fromCallable { db.originDao().upsertOrigin(firstOrigin) }
                .flatMap { firstOriginId ->
                    val entries = (1..count).map { index ->
                        DatabaseTestUtils.createEntry(hashIndex(originId, index), index.toLong(), firstOriginId)
                    }

                    db.entryDao().insertEntries(entries).andThen(Single.just(firstOriginId))
                }
    }

    private fun hashIndex(originId: Int, index: Int): Int = (originId * 31 + 37) * (index * 41 + 43) * 53 + 59
}