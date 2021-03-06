package ninja.bryansills.roses.repo

interface Repository {
    suspend fun categories(): FetchCategoryResult

    suspend fun getEntries(categoryId: String): FetchEntryResult

    suspend fun updateDatabase()
}
