package ninja.bryansills.repo

import ninja.bryansills.roses.repo.Entry

sealed class FetchEntryResult {
    object InFlight : FetchEntryResult()
    data class Success(val entries: List<Entry>) : FetchEntryResult()
    data class Error(val error: FetchEntryError) : FetchEntryResult()

    enum class FetchEntryError {
        UNKNOWN
    }
}
