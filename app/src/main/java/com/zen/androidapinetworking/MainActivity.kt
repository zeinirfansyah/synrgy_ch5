package com.zen.androidapinetworking

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.zen.androidapinetworking.databinding.ActivityMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.Permission
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginFragment(LoginFragment())

        playground()
//        loadImage()
//        initEvent()
        fetchAllData()
    }

    private fun loginFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun fetchAllData() {
        ApiClient.apiClient.getMoviePopular(ApiKey.apiKeyTMDB)
            .enqueue(object : Callback<MoviePopular> {
                override fun onResponse(
                    call: Call<MoviePopular>,
                    response: Response<MoviePopular>
                ) {
                    if (response.isSuccessful) {
                        val moviePopular = response.body()
                        val listPopular = moviePopular?.results

                        Log.d("MANTAP", listPopular?.get(0).toString())
                        Log.e("SIP", Gson().toJson(listPopular?.get(0)))
                    }
                }

                override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                    Log.d("MainActivity", "onFailure: ${t.message}")
                }
            })
    }

//    private fun initEvent(){
//        findViewById<MaterialButton>(R.id.btn_load).setOnClickListener {
//            if (!PermissionUtils.isPermissionGranted(this, android.Manifest.permission.CAMERA)) {
//                PermissionUtils.requestPermission(this, android.Manifest.permission.CAMERA, PermissionUtils.REQUEST_CODE_CAMERA)
//            } else {
//                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Contact Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contact Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun loadImage() {
//        Glide.with(this)
//            .load("https://image.tmdb.org/t/p/w500/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg")
//            .into(findViewById(R.id.img))
//    }



    fun playground() {
        val jsonObject = JSONObject()
        jsonObject.put("name", "Zen")
        jsonObject.put("age", 30)

        val json = "{\"phonetype\":\"N95\",\"cat\":WP}"
        val jsonObject2 = JSONObject(json)

        Log.e("jsonObject", jsonObject2.getString("phonetype"))
    }
}