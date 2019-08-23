package ninja.bryansills.roses.utils

import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.FetchEntryResult
import ninja.bryansills.repo.Repository

class FakeRepository : Repository {
    var categories: FetchCategoryResult = FetchCategoryResult.InFlight
    var entries: FetchEntryResult = FetchEntryResult.InFlight

    var throwCategoriesError: Boolean = false
    var throwEntriesError: Boolean = false

    override suspend fun categories(): FetchCategoryResult =
            if (throwCategoriesError) throw RuntimeException("Categories Error") else categories

    override suspend fun getEntries(categoryId: String): FetchEntryResult =
            if (throwEntriesError) throw RuntimeException("Entries Error") else entries

    override suspend fun updateDatabase() {}
}