package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityListBinding
import com.example.myapplication.network.MenuResult
import com.example.myapplication.network.NetworkConstants
import com.google.gson.GsonBuilder
import org.json.JSONObject


enum class Category{ENTREE, PLAT, DESSERT}



class List : AppCompatActivity() {
    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityListBinding
    lateinit var currentCategory: Category
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getSerializableExtra(extraKey) as? Category
        currentCategory = category ?: Category.ENTREE
        supportActionBar?.title = categoryName()
        makeRequest()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "List onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "List onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "List onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "List onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "List onDestroy")
    }
    private fun makeRequest() {
        val params = JSONObject()
        val queue = Volley.newRequestQueue(this)
        params.put(NetworkConstants.idShopKey, 1)
        val request = JsonObjectRequest(
            Method.POST,
            NetworkConstants.url,
            params,
            { result ->
                Log.d("request", result.toString(2))
                //SUCCESS OF OBJECT
                parseData(result.toString())
            },
            { error ->//ERROR WHEN REQUEST
                Log.e("request", error.toString())
            }
        )
        queue.add(request)
    }
    private fun parseData(data: String) {

        val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
        val category = result.data.first{ it.name == categoryFilterKey() }

        showDatas(category)
    }

    private fun showDatas(category: com.example.myapplication.network.Category) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomAdapter(category.items) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.PLATE_EXTRA, it)
            startActivity(intent)
        }
    }

    private fun categoryName(): String {
        return when (currentCategory) {
            Category.ENTREE -> getString(R.string.entree)
            Category.PLAT -> getString(R.string.plat)
            Category.DESSERT -> getString(R.string.dessert)
        }
    }


    private fun categoryFilterKey(): String {
        return when (currentCategory) {
            Category.ENTREE -> "Entrées"
            Category.PLAT -> "Plats"
            Category.DESSERT -> "Desserts"
        }
    }
}