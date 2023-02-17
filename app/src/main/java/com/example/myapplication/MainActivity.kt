package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.List
import com.example.myapplication.databinding.ActivityListBinding
import com.example.myapplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonsListener()

    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle","MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle","MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle","MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle","MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle","MainActivity onDestroy")
    }



    private fun  buttonsListener() {
        binding.entree.setOnClickListener {
            Log.d("entree", "Click sur button entrées")

            showCategory(Category.ENTREE)
        }
        binding.plat.setOnClickListener {
            Log.d("plat", "Click sur button plats")

            showCategory(Category.PLAT)
        }
        binding.dessert.setOnClickListener {
            Log.d("dessert", "Click sur button Desserts")

            showCategory(Category.DESSERT)

        }
    }
    private fun showCategory(category: Category){
        val intent = Intent(this,List::class.java)
        intent.putExtra(List.extraKey, category)
        startActivity(intent)
    }

}