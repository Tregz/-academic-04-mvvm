package com.tregz.mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tregz.mvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        //private val TAG: String = MainActivity::class.java.simpleName
    }
}
