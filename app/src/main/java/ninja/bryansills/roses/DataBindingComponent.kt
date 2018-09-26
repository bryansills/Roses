package ninja.bryansills.roses

import androidx.databinding.DataBindingComponent
import dagger.Component
import dagger.Module

@Component(modules = [BindingModule::class])
interface DataBindingComponent : DataBindingComponent

@Module
class BindingModule
