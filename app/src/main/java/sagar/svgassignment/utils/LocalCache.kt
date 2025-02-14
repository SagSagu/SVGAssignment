package sagar.svgassignment.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.LruCache
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

object LocalCache {

    private lateinit var sharedPreferences: SharedPreferences

    var localList = MutableLiveData<ArrayList<String>>()

    private val gson = Gson()

    private val maxItemCount = 20

    private val lruCache = object : LruCache<String, String>(maxItemCount) {
        override fun sizeOf(key: String, value: String): Int {
            return 1
        }
    }

    fun addToCache(key: String, value: String) {
        lruCache.put(key, value)
        getListFromCache()
        saveCacheToPreferences()
    }

    private fun getListFromCache() {
        val valuesList = ArrayList<String>()
        for (item in lruCache.snapshot()){
            valuesList.add(item.value)
        }
        localList.value = ArrayList()
        val reversedList = valuesList.reversed()
        if (valuesList.size > 1)
            localList.value?.addAll(reversedList as ArrayList<String>)
        else
            localList.value?.addAll(valuesList)
    }

    fun clearCache() {
        lruCache.evictAll()
        localList.value = ArrayList()
        saveCacheToPreferences()
    }

    fun setupPreferences(context: Context){
        sharedPreferences = context.getSharedPreferences("LocalCache", Context.MODE_PRIVATE)
        loadCacheFromPreferences()
    }

    private fun saveCacheToPreferences() {
        val cacheMap = lruCache.snapshot()
        val json = gson.toJson(cacheMap)
        sharedPreferences.edit().putString("cache_data", json).apply()
    }

    private fun loadCacheFromPreferences() {
        val json = sharedPreferences.getString("cache_data", null)
        if (json != null) {
            val cacheMap = gson.fromJson(json, Map::class.java) as Map<String, String>
            for ((key, value) in cacheMap) {
                lruCache.put(key, value)
            }
        }
        getListFromCache()
    }

}