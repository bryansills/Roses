package ninja.bryansills.roses

import androidx.annotation.StringRes

sealed class CategoryUIEvent {
    object SetupNavigationController: CategoryUIEvent()
    data class ShowSnackbar(@StringRes val messageId: Int): CategoryUIEvent()
    data class StartDetailView(val id: String, val title: String): CategoryUIEvent()
}