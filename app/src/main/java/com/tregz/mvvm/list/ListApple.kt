package com.tregz.mvvm.list

import com.tregz.mvvm.data.DataApple

object ListApple {

    private val list = mutableListOf<DataApple>()
    private val set = mutableSetOf<DataApple>()
    private val map = mutableMapOf<Int, DataApple>()

    val listCount: Int
        get() = list.size

    val setCount: Int
        get() = set.size

    fun add(apple: DataApple): DataApple {
        list.add(apple)
        set.add(apple)
        map[map.size] = apple
        return apple
    }

    fun clear() {
        list.clear()
        set.clear()
        map.clear()
    }
}