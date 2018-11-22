package ninja.bryansills.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OriginDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertOrigin() {
        val input = Origin(
                networkId = "TEST_NETWORK_ID",
                title = "TEST_TITLE",
                htmlUrl = "TEST_URL"
        )

        val outputId = db.originDao().upsertOrigin(input)

        assertThat(outputId, notNullValue())
    }
}