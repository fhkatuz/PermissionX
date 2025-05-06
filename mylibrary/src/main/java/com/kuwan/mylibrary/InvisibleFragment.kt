package com.kuwan.mylibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean, List<String>) -> Unit   //这里是用于顶层给任意类型设置别名的

/**
 *  create by lzq on 2025-05-06
 */

class InvisibleFragment :  Fragment(){
    private val REQUESTCODE = 1
//    private var callback : ((Boolean, List<String>) -> Unit)? = null
    private var callback : PermissionCallback? = null

    fun requestNow(cb : PermissionCallback, vararg permissions: String){
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUESTCODE){
            val  deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()){
                if(result != PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index].toString())
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}