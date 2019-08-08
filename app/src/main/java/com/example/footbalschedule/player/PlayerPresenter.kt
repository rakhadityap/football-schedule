package com.example.footbalschedule.player

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.PlayerListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerPresenter(val view: PlayerView, private val apiService: ApiService)
{
    var call: Call<PlayerListResponse>? = null

    fun getPlayers(id: String)
    {
        call = apiService.getPlayerList(id)
        call?.enqueue(object : Callback<PlayerListResponse>
        {
            override fun onFailure(call: Call<PlayerListResponse>, t: Throwable)
            {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<PlayerListResponse>, response: Response<PlayerListResponse>)
            {
                view.showPlayers(response.body()?.player)
            }

        })
    }

    fun stopRequest(){
        call?.cancel()
    }
}