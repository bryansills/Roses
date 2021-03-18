package ninja.bryansills.repo

import ninja.bryansills.roses.repo.Category

sealed class FetchCategoryResult {
    object InFlight : FetchCategoryResult()
    data class Success(val categories: List<Category>) : FetchCategoryResult()
    data class Error(val error: FetchCategoryError) : FetchCategoryResult()

    enum class FetchCategoryError {
        API_KEY_INVALID,
        RATE_LIMIT_REACHED,
        UNKNOWN
    }
}
