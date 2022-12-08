package com.example.clean_architecture_mvvm

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoDestroyView<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {

    private var view: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                destroyView(fragment)
            }
        })
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        view = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return view ?: throw IllegalStateException("View not be available")
    }

    private fun destroyView(fragment: Fragment) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { vlo ->
            vlo?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
//                    Logger.d("Destroy view: $view")
                    view = null
                }
            })
        }
    }
}

fun <T : Any> Fragment.autoDestroy() = AutoDestroyView<T>(this)