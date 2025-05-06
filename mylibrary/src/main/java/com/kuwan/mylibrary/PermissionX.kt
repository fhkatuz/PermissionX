package com.kuwan.mylibrary

import androidx.fragment.app.FragmentActivity

/**
 *  create by lzq on 2025-05-06
 */

object PermissionX {
    private const val Tag = "InvisibleFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback : PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(Tag)
        val fragment = if (existedFragment != null){
            existedFragment as InvisibleFragment
        }else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, Tag).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions) //这个*不是指针，而是表示将一个数组转变为可变长度参数传递
    }
}