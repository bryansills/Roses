package ninja.bryansills.roses.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ninja.bryansills.repo.Entry
import ninja.bryansills.repo.FetchCategoryResult
import ninja.bryansills.repo.Repository
import ninja.bryansills.roses.R
import ninja.bryansills.roses.factory.ViewModelFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Provider

@RunWith(AndroidJUnit4::class)
class CategoryFragmentTest {

    lateinit var repository: Repository
    lateinit var compositeDisposable: CompositeDisposable

    lateinit var scenario: FragmentScenario<CategoryFragment>

    @Before
    fun setup() {
        repository = FakeRepository()
        compositeDisposable = CompositeDisposable()
        val map = mapOf<Class<*>, Provider<ViewModel>>(Pair(CategoryViewModel::class.java, CategoryViewModelProvider(repository, compositeDisposable)))
        val viewModelFactory = ViewModelFactory(map)
        val fragmentFactory = TestFragmentFactory(viewModelFactory)

        scenario = launchFragmentInContainer(factory = fragmentFactory)
    }

    @Test
    fun something() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.category_error)).check(matches(isDisplayed()))
    }
}

class CategoryViewModelProvider(val repository: Repository, val compositeDisposable: CompositeDisposable) : Provider<ViewModel> {
    override fun get(): CategoryViewModel {
        return CategoryViewModel(repository, compositeDisposable)
    }
}

class FakeRepository : Repository {
    override fun categories(): Observable<FetchCategoryResult> {
        return Observable.just(FetchCategoryResult.Error(FetchCategoryResult.FetchCategoryError.UNKNOWN))
    }

    override fun getEntries(categoryId: String): Flowable<List<Entry>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateDatabase(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class TestFragmentFactory(val viewModelFactory: ViewModelFactory) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        return CategoryFragment(viewModelFactory)
    }
}