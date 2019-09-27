package com.tregz.mvvm.main

import androidx.lifecycle.ViewModel
import com.tregz.mvvm.data.DataApple
import com.tregz.mvvm.list.ListApple
import java.util.*

class MainBackend(private val view: MainView) : ViewModel() {

    fun insertApple(ripe: Date?, color: Int): DataApple {
        view.toast("Pomme ajoutée de couleur: $color")
        return ListApple.add(DataApple(ripe).apply { this.color = color })
    }

    companion object {
        //private val TAG: String = MainBackend::class.java.simpleName
    }
}
