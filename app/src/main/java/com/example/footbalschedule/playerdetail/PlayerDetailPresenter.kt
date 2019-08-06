package com.example.footbalschedule.playerdetail

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.PlayerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerDetailPresenter(val view: PlayerView, private val apiService: ApiService) {
    private lateinit var call: Call<PlayerResponse>
    fun getPlayerDetail(id: String){
        call = apiService.getPlayer(id)
        call.enqueue(object: Callback<PlayerResponse>{
            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                view.showError(t.localizedMessage)
            }

            override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                view.showPlayerDetail(response.body()?.players?.get(0))
            }

        })

    }
}