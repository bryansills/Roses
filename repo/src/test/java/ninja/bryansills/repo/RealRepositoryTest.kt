package ninja.bryansills.repo

import kotlinx.coroutines.runBlocking
import ninja.bryansills.database.test.DatabaseTestUtils
import ninja.bryansills.roses.repo.Category
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class RealRepositoryTest {
    lateinit var repository: RealRepository
    lateinit var fakeNetworkService: FakeNetworkService
    lateinit var fakeDatabaseService: FakeDatabaseService

    @Before
    fun setup() {
        fakeNetworkService = FakeNetworkService()
        fakeDatabaseService = FakeDatabaseService()
        repository = RealRepository(fakeNetworkService, fakeDatabaseService, 3)
    }

    @Test
    fun cachedResultsAreReturnedWhenDataIsFresh() = runBlocking {
        val databaseResults = listOf(
            DatabaseTestUtils.createCategory(1),
            DatabaseTestUtils.createCategory(2),
            DatabaseTestUtils.createCategory(3)
        )
        val repositoryResults = databaseResults.map { Category(it.id, it.title, it.count) }
        val timestamp = Calendar.getInstance()
        timestamp.add(Calendar.HOUR, -2)

        fakeDatabaseService.categories = databaseResults
        fakeDatabaseService.lastUpdated = timestamp.timeInMillis

        val result = repository.categories()

        assertThat(
            (result as FetchCategoryResult.Success).categories,
            `is`(equalTo(repositoryResults))
        )
        assertTrue(!fakeNetworkService.hasBeenCalled)
    }

    @Test
    fun networkResultsAreReturnedWhenDataIsOld() = runBlocking {
        val timestamp = Calendar.getInstance()
        timestamp.add(Calendar.HOUR, -4)

        fakeDatabaseService.lastUpdated = timestamp.timeInMillis

        val result = repository.categories()

        assertTrue(result is FetchCategoryResult.Success)
        assertTrue(fakeNetworkService.hasBeenCalled)
        assertTrue(fakeDatabaseService.hasCategoriesBeenCalled)
    }

    @Test
    fun errorResultIsReturnedWhenNetworkErrors() = runBlocking {
        val timestamp = Calendar.getInstance()
        timestamp.add(Calendar.HOUR, -4)

        fakeNetworkService.emitError = true
        fakeDatabaseService.lastUpdated = timestamp.timeInMillis

        val result = repository.categories()

        assertTrue(result is FetchCategoryResult.Error)
        assertTrue(fakeNetworkService.hasBeenCalled)
        assertFalse(fakeDatabaseService.hasCategoriesBeenCalled)
    }
}
