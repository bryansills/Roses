package ninja.bryansills.roses.category

// @RunWith(AndroidJUnit4::class)
// class CategoryFragmentTest {
//
//     val categoryViewModel = FakeCategoryViewModel()
//     val fragmentFactory = SingleViewModelFragmentFactory(SingleViewModelFactory(categoryViewModel))
//
//     @get:Rule
//     val fragmentScenarioRule = FragmentScenarioRule(
//         fragmentFactory,
//         null,
//         CategoryFragment::class.java
//     )
//
//     lateinit var scenario: FragmentScenario<CategoryFragment>
//
//     @Before
//     fun setup() {
//         scenario = fragmentScenarioRule.fragmentScenario
//         scenario.moveToState(Lifecycle.State.RESUMED)
//     }
//
//     @Test
//     fun displayError() {
//         scenario.onDataBindingFragment {
//             categoryViewModel.categories.value = CategoryUiModel.Error(R.string.app_name)
//         }
//
//         onView(withId(R.id.category_error)).check(matches(withText(R.string.app_name)))
//         onView(withId(R.id.loading_bar)).check(matches(not(isDisplayed())))
//     }
//
//     @Test
//     fun displayLoading() {
//         scenario.onDataBindingFragment {
//             categoryViewModel.categories.value = CategoryUiModel.Loading
//         }
//
//         onView(withId(R.id.loading_bar)).check(matches(isDisplayed()))
//     }
//
//     @Test
//     fun displayEmptyList() {
//         scenario.onDataBindingFragment {
//             categoryViewModel.categories.value = CategoryUiModel.Success(emptyList())
//         }
//
//         onView(withId(R.id.category_list)).check(matches(not(isDisplayed())))
//         onView(withId(R.id.loading_bar)).check(matches(not(isDisplayed())))
//         onView(withId(R.id.category_empty)).check(matches(withText(R.string.categories_empty)))
//     }
//
//     @Test
//     fun displayList() {
//         scenario.onDataBindingFragment {
//             val categories = listOf(
//                 Category("1", "FIRST", 5),
//                 Category("2", "SECOND", 7)
//             )
//             categoryViewModel.categories.value = CategoryUiModel.Success(categories)
//         }
//
//         onView(withId(R.id.category_list)).check(
//             matches(atPosition(0, hasDescendant(withText("FIRST"))))
//         )
//         onView(withId(R.id.category_list)).check(
//             matches(atPosition(0, hasDescendant(withText("5"))))
//         )
//
//         onView(withId(R.id.category_list)).check(
//             matches(atPosition(1, hasDescendant(withText("SECOND"))))
//         )
//         onView(withId(R.id.category_list)).check(
//             matches(atPosition(1, hasDescendant(withText("7"))))
//         )
//     }
// }
//
// class FakeCategoryViewModel : CategoryViewModel() {
//     val categories = MutableLiveData<CategoryUiModel>()
//     override fun getCategories(): LiveData<CategoryUiModel> = categories
// }
