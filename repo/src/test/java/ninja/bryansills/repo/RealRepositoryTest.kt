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
                .assertValue {
                    (it as FetchCategoryResult.Success).categories == repositoryResults
                }
                .assertValueCount(1)
                .assertNoErrors()
        assertTrue(!fakeNetworkService.hasBeenCalled)
    }

    @Test
    fun networkResultsAreReturnedWhenDataIsOld() {
        repository.categories().test()
                .assertValue {
                    it != null
                }
                .assertValueCount(1)
                .assertNoErrors()
        assertTrue(fakeNetworkService.hasBeenCalled)
        assertTrue(fakeDatabaseService.hasCategoriesBeenCalled)
    }

    @Test
    fun errorResultIsReturnedWhenNetworkErrors() {
        // is database results
        // only 1 emission
        // 1 error thrown
        // database is called
        // network is called
        assertTrue(true)
    }

    @Test
    fun getEntriesForCategoryIdWorks() {
        // only 1 emission
        // for same category
        // only 1 category
        assertTrue(true)
    }

    @Test
    fun getEntriesForCategoryReturnsNothingWhenIdIsInvalid() {
        // only 1 emission
        // no results
        assertTrue(true)
    }
}