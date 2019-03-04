package ninja.bryansills.repo

import ninja.bryansills.database.test.DatabaseTestUtils
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
    fun startsWithInFlight() {
        repository.categories().test()
                .assertValueAt(0) {
                    it is FetchCategoryResult.InFlight
                }
    }

    @Test
    fun cachedResultsAreReturnedWhenDataIsFresh() {
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

        repository.categories().test()
                .assertValueAt(1) {
                    (it as FetchCategoryResult.Success).categories == repositoryResults
                }
                .assertValueCount(2)
                .assertNoErrors()
        assertTrue(!fakeNetworkService.hasBeenCalled)
    }

    @Test
    fun networkResultsAreReturnedWhenDataIsOld() {
        val timestamp = Calendar.getInstance()
        timestamp.add(Calendar.HOUR, -4)

        fakeDatabaseService.lastUpdated = timestamp.timeInMillis

        repository.categories().test()
                .assertValueAt(1) {
                    it is FetchCategoryResult.Success
                }
                .assertValueCount(2)
                .assertNoErrors()
        assertTrue(fakeNetworkService.hasBeenCalled)
        assertTrue(fakeDatabaseService.hasCategoriesBeenCalled)
    }

    @Test
    fun errorResultIsReturnedWhenNetworkErrors() {
        val timestamp = Calendar.getInstance()
        timestamp.add(Calendar.HOUR, -4)

        fakeNetworkService.emitError = true
        fakeDatabaseService.lastUpdated = timestamp.timeInMillis

        repository.categories().test()
                .assertValueAt(1) {
                    it is FetchCategoryResult.Error
                }
                .assertValueCount(2)
                .assertNoErrors()
        assertTrue(fakeNetworkService.hasBeenCalled)
        assertTrue(fakeDatabaseService.hasCategoriesBeenCalled)
    }
}