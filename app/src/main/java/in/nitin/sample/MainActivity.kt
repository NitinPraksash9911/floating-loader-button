package `in`.nitin.sample

import `in`.nitin.library.FloatingLoaderButton
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var fabSmall: FloatingLoaderButton
    private lateinit var fabMedium: FloatingLoaderButton
    private lateinit var fabLarge: FloatingLoaderButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabSmall = findViewById(R.id.fabSmall)
        fabMedium = findViewById(R.id.fabMedium)
        fabLarge = findViewById(R.id.fabLarge)

        clickHandler()
    }

    private fun clickHandler() {
        fabSmall.setOnClickListener {

            fabSmall.setLoadingStatus(FloatingLoaderButton.LoaderStatus.LOADING)

            Handler().postDelayed({

                fabSmall.setLoadingStatus(FloatingLoaderButton.LoaderStatus.FINISH)

            }, 3000)

        }

        fabMedium.setOnClickListener {

            fabMedium.setLoadingStatus(FloatingLoaderButton.LoaderStatus.LOADING)

            Handler().postDelayed({

                fabMedium.setLoadingStatus(FloatingLoaderButton.LoaderStatus.FINISH)

            }, 3000)

        }
        fabLarge.setOnClickListener {

            fabLarge.setLoadingStatus(FloatingLoaderButton.LoaderStatus.LOADING)

            Handler().postDelayed({

                fabLarge.setLoadingStatus(FloatingLoaderButton.LoaderStatus.FINISH)

            }, 3000)

        }
    }
}
