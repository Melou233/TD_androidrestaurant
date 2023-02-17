package com.example.myapplication.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredients (
    @SerializedName("name_fr")val name : String,
): Serializable