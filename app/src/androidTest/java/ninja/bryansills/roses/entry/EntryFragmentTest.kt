package ninja.bryansills.roses.entry

// @RunWith(AndroidJUnit4::class)
// class EntryFragmentTest {
//
//     val entryViewModel = FakeEntryViewModel()
//     val fragmentFactory = SingleViewModelFragmentFactory(SingleViewModelFactory(entryViewModel))
//     val args = CategoryFragmentDirections.selectCategory("1337", "CATEGORY_NAME").arguments
//
//     @get:Rule
//     val fragmentScenarioRule = FragmentScenarioRule(
//         fragmentFactory,
//         args,
//         EntryFragment::class.java
//     )
//
//     lateinit var scenario: FragmentScenario<EntryFragment>
//
//     @Before
//     fun setup() {
//         scenario = fragmentScenarioRule.fragmentScenario
//         scenario.moveToState(Lifecycle.State.RESUMED)
//     }
//
//     @Test
//     fun handlesBundle() {
//         scenario.onDataBindingFragment {
//             assertTrue(entryViewModel.categoryId == it.args.categoryId)
//         }
//     }
//
//     @Test
//     fun displayLoading() {
//         scenario.onDataBindingFragment {
//             entryViewModel.entries.value = EntryUiModel.Loading()
//         }
//
//         onView(withId(R.id.entry_error)).check(matches(not(isDisplayed())))
//         onView(withId(R.id.loading_bar)).check(matches(isDisplayed()))
//         onView(withId(R.id.entry_list)).check(matches(not(isDisplayed())))
//     }
//
//     @Test
//     fun displayError() {
//         scenario.onDataBindingFragment {
//             entryViewModel.entries.value = EntryUiModel.Error(R.string.app_name)
//         }
//
//         onView(withId(R.id.entry_error)).check(matches(withText(R.string.app_name)))
//         onView(withId(R.id.loading_bar)).check(matches(not(isDisplayed())))
//         onView(withId(R.id.entry_list)).check(matches(not(isDisplayed())))
//     }
//
//     @Test
//     fun displayList() {
//         scenario.onDataBindingFragment {
//             val entries = listOf(
//                 Entry("1", "FIRST_TITLE", "FIRST_URL", Date(), "FIRST_AUTHOR", "FIRST_SUMMARY"),
//                 Entry("2", "SECOND_TITLE", "SECOND_URL", Date(), "SECOND_AUTHOR", "SECOND_SUMMARY")
//             )
//             entryViewModel.entries.value = EntryUiModel.Success(entries)
//         }
//
//         onView(withId(R.id.entry_list)).check(
//             matches(
//                 CustomMatchers.atPosition(
//                     0,
//                     ViewMatchers.hasDescendant(ViewMatchers.withText("FIRST_TITLE"))
//                 )
//             )
//         )
//         onView(withId(R.id.entry_list)).check(
//             matches(
//                 CustomMatchers.atPosition(
//                     0,
//                     ViewMatchers.hasDescendant(ViewMatchers.withText("FIRST_AUTHOR"))
//                 )
//             )
//         )
//
//         onView(withId(R.id.entry_list)).check(
//             matches(
//                 CustomMatchers.atPosition(
//                     1,
//                     ViewMatchers.hasDescendant(ViewMatchers.withText("SECOND_TITLE"))
//                 )
//             )
//         )
//         onView(withId(R.id.entry_list)).check(
//             matches(
//                 CustomMatchers.atPosition(
//                     1,
//                     ViewMatchers.hasDescendant(ViewMatchers.withText("SECOND_AUTHOR"))
//                 )
//             )
//         )
//     }
// }
//
// class FakeEntryViewModel : EntryViewModel() {
//     val entries = MutableLiveData<EntryUiModel>()
//     var categoryId: String? = null
//
//     override fun getEntries(categoryId: String): LiveData<EntryUiModel> {
//         this.categoryId = categoryId
//         return entries
//     }
// }
