package ninja.bryansills.repo

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RealRepositoryTest {
    lateinit var repository: RealRepository

    @Before
    fun setup() {
        repository = RealRepository(FakeNetworkService(), FakeDatabaseService(), 1)
    }

    @Test
    fun testing() {
        assertTrue(true)
    }
}