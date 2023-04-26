package com.example.flashfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


//a01ab1d365b7417bbf7e06dc7fe82ee7


class MainActivity  : AppCompatActivity(),CatergoryRVAdapter.CategoryClickInterface  {

    private lateinit var newsRV:RecyclerView
    private lateinit var categoryRV:RecyclerView
    private lateinit var loadingPB:ProgressBar
    private lateinit var articlesArrayList:ArrayList<Articles>
    private lateinit var categoryRVModelArrayList: ArrayList<CategoryRVModel>
    private lateinit var categoryRVAdapter:CatergoryRVAdapter
    private lateinit var newsRVAdapter:NewsRVAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRV = findViewById(R.id.idRVNews)
        categoryRV = findViewById(R.id.idRvCatergories)
        loadingPB = findViewById(R.id.idPbloading)

        articlesArrayList = ArrayList()
        categoryRVModelArrayList = ArrayList()
        newsRVAdapter = NewsRVAdapter(articlesArrayList,this)
        categoryRVAdapter = CatergoryRVAdapter(categoryRVModelArrayList,this,this)

        newsRV.adapter = newsRVAdapter
        categoryRV.adapter = categoryRVAdapter
        getCatergories()
        getNews("All")
        newsRVAdapter.notifyDataSetChanged()

    }
    @SuppressLint("NotifyDataSetChanged")
    fun getCatergories(){
        categoryRVModelArrayList.add(CategoryRVModel("All","https://plus.unsplash.com/premium_photo-1670226145696-56d31c4d4416?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8bWFwc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("Technology","https://images.unsplash.com/photo-1518770660439-4636190af475?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("science","https://images.unsplash.com/photo-1628595351029-c2bf17511435?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fFNjaWVuY2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("sports","https://images.unsplash.com/photo-1583454110551-21f2fa2afe61?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGZpdG5lc3N8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("General","https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8R2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60\n"))
        categoryRVModelArrayList.add(CategoryRVModel(" business","https://images.unsplash.com/photo-1661956602944-249bcd04b63f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxzZWFyY2h8MjJ8fEJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("Entertainment","https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"))
        categoryRVModelArrayList.add(CategoryRVModel("Health","https://plus.unsplash.com/premium_photo-1675033558464-a9d0859b6230?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"))
        categoryRVAdapter.notifyDataSetChanged()
    }

    private fun getNews(category:String){
        loadingPB.visibility = View.VISIBLE
        articlesArrayList.clear()
        val categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category=$category&apiKey=a01ab1d365b7417bbf7e06dc7fe82ee7"
        val URL = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=a01ab1d365b7417bbf7e06dc7fe82ee7"
        val BASE_URL = "https://newsapi.org/"
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val retrofitApi : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        var call: Call<NewsModal>? = null
        call = if (category == "All"){
            retrofitApi.getAllNews(URL)
        } else{
            retrofitApi.getNewsByCatergory(categoryURL)
        }

        call.enqueue(object : Callback<NewsModal> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<NewsModal>, response: Response<NewsModal>) {
                    val newsModal = response.body()
                    loadingPB.visibility = View.INVISIBLE
                    val articles = newsModal!!.articles
                    for (i in 0 until articles.size){
                        articlesArrayList.add(Articles(articles[i].title, articles[i].description, articles[i].urlToImage, articles[i].url, articles[i].content))
                        newsRVAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<NewsModal>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fail to get news", Toast.LENGTH_SHORT).show()
                }
        })
    }

    override fun onCategoryClick(position: Int) {
        val category:String = categoryRVModelArrayList[position].category
        getNews(category)
    }
}