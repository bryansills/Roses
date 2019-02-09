package ninja.bryansills.roses

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ninja.bryansills.roses.inject.DaggerFakeRepoComponent
import ninja.bryansills.roses.inject.DaggerFakeWorkerComponent
import ninja.bryansills.roses.inject.DaggerTestApplicationComponent
import ninja.bryansills.roses.inject.FakeRepoModule
import ninja.bryansills.roses.inject.FakeWorkerModule

class TestRosesApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val fakeRepoModule = FakeRepoModule()
        val fakeRepoComponent = DaggerFakeRepoComponent.builder()
                .fakeRepoModule(fakeRepoModule)
                .build()

        val fakeWorkerModule = FakeWorkerModule()
        val fakeWorkerComponent = DaggerFakeWorkerComponent.builder()
                .fakeWorkerModule(fakeWorkerModule)
                .build()

        return DaggerTestApplicationComponent.builder()
                .fakeRepoComponent(fakeRepoComponent)
                .fakeWorkerComponent(fakeWorkerComponent)
                .build()
    }
}