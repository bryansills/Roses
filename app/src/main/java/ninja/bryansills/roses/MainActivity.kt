package ninja.bryansills.roses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ninja.bryansills.network.NetworkService

class MainActivity : AppCompatActivity() {

    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = NetworkService()
    }

    override fun onResume() {
        super.onResume()
        NetworkService().getProfile()
    }
}
