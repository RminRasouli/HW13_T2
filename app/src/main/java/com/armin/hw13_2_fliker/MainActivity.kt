package com.armin.hw13_2_fliker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.armin.hw13_2_fliker.data.network.NetworkManager.api
import com.armin.hw13_2_fliker.data.network.model.Photo
import com.armin.hw13_2_fliker.data.network.model.PhotoJson
import com.armin.hw13_2_fliker.databinding.ActivityMainBinding
import com.armin.hw13_2_fliker.ui.adapter.PhotoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var photoAdapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getImageCurrent()
        photoAdapter = PhotoAdapter()
        binding.apply {
            recyclerViewMain.apply {
                adapter = photoAdapter
                layoutManager = LinearLayoutManager(applicationContext)
                setHasFixedSize(true)
            }
        }

    }

    private fun getImageCurrent() {
        Log.d("PHOTOIMAGE", "Start Fun")
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("PHOTOIMAGE", "Start Curatine")
            val response: Response<PhotoJson> = api
                .getPhoto(
                    "1c04e05bce6e626247758d120b372a73",
                    "flickr.photos.getRecent",
                    "url_s",
                    "json",
                    1,
                    100,
                    1
                )
                .awaitResponse()
            Log.d("PHOTOIMAGE", "A Wait for response")
            if (response.isSuccessful) {
                Log.d("PHOTOIMAGE", "OK Get DAta")
                val data = response.body()!!.photos
                Log.d("PHOTOIMAGE", data.toString())
                photoAdapter.submitList(data.photo.toMutableList())
            }
        }
    }
}
