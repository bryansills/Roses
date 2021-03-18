package ninja.bryansills.roses.repo

sealed class FetchEntryResult {
    object InFlight : FetchEntryResult()
    data class Success(val entries: List<Entry>) : FetchEntryResult()
    data class Error(val error: FetchEntryError) : FetchEntryResult()

    enum class FetchEntryError {
        UNKNOWN
    }
}
