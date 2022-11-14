package com.zen.androidapinetworking

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    const val REQUEST_CODE_CAMERA = 100

    fun checkPermission(context: Context, permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), requestCode)
        } else {
            actionDenied()
        }
    }

    private fun actionDenied() {
        TODO("Not yet implemented")
    }

    fun isPermissionGranted(context: Context, permissionRequestName: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permissionRequestName) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: Activity, permissionRequestName: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permissionRequestName), requestCode)
    }

}