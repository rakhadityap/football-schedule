package com.example.footbalschedule.favorite

import com.example.footbalschedule.app.BaseView

interface FavoriteView: BaseView{
    fun showFavorite(datas: List<Any>?)
}
