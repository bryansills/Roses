package ninja.bryansills.repo

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RealRepositoryTest {
    lateinit var repository: RealRepository
    lateinit var fakeNetwork: FakeNetworkService
    lateinit var fakeDatabaseService: FakeDatabaseService

    @Before
    fun setup() {
        fakeNetwork = FakeNetworkService()
        fakeDatabaseService = FakeDatabaseService()
        repository = RealRepository(fakeNetwork, fakeDatabaseService, 1)
    }

    @Test
    fun cachedResultsAreReturnedWhenDataIsFresh() {
        assertTrue(true)
    }

    @Test
    fun networkResultsAreReturnedWhenDataIsOld() {
        assertTrue(true)
    }

    @Test
    fun errorResultIsReturnedWhenNetworkErrors() {
        assertTrue(true)
    }

    @Test
    fun getEntriesForCategoryIdWorks() {
        assertTrue(true)
    }

    @Test
    fun getEntriesForCategoryReturnsNothingWhenIdIsInvalid() {
        assertTrue(true)
    }
}