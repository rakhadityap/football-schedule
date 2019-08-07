package com.example.footbalschedule.search

import com.example.footbalschedule.app.BaseView

interface SearchView : BaseView
{
    fun showSearchResult(datas: List<Any>?)
}