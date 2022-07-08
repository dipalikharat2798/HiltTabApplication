package com.geico.hilttabapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geico.hilttabapplication.databinding.ActivityMainBinding
import com.geico.hilttabapplication.model.PostDataItem
import com.geico.hilttabapplication.network.ApiInterface
import com.geico.hilttabapplication.viewmodel.MainViewModel
import com.geico.hilttabapplication.viewmodel.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NetworkViewModel
    val TAG = "MAinActivity"
    val baseUrl = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel= ViewModelProvider(this).get(NetworkViewModel::class.java)

        viewModel.responsePosts.observe(this, Observer {
           // Log.d("TAG", "onCreate: "+it.get(0).title)
            val responseBody = it!!
            val myStringBuilder = StringBuilder()
            for (mydata in responseBody) {
                myStringBuilder.append(mydata.id)
                myStringBuilder.append("\t")
                myStringBuilder.append(mydata.title)
                myStringBuilder.append("\n")
                Log.d("api", "onResponse: " + myStringBuilder + "\n")
            }
        })
        //   getPostData()
    }

    private fun getPostData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getApiData()

        retrofitData.enqueue(object : Callback<List<PostDataItem>?> {
            override fun onResponse(
                call: Call<List<PostDataItem>?>,
                response: Response<List<PostDataItem>?>
            ) {
                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()
                for (mydata in responseBody) {
                    myStringBuilder.append(mydata.id)
                    myStringBuilder.append("\n")
                    Log.d(TAG, "onResponse: " + myStringBuilder + "\n")
                }
            }

            override fun onFailure(call: Call<List<PostDataItem>?>, t: Throwable) {
                Log.d(TAG, "onFailure: Request Failure")
            }
        })
    }

}


