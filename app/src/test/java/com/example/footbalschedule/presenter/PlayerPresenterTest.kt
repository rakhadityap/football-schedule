package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.PlayerListResponse
import com.example.footbalschedule.player.PlayerPresenter
import com.example.footbalschedule.player.PlayerView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerPresenterTest {
    private lateinit var presenter: PlayerPresenter

    @Mock private lateinit var view: PlayerView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<PlayerListResponse>

    @Mock private lateinit var res: Response<PlayerListResponse>

    @Captor private lateinit var captor: ArgumentCaptor<Callback<PlayerListResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter= PlayerPresenter(view, apiService)
    }

    @Test
    fun TestGetPlayers(){
        val id = "0000"
        Mockito.`when`(apiService.getPlayerList(id)).thenReturn(call)

        presenter.getPlayers(id)

        Mockito.verify(call).enqueue(captor.capture())
        captor.value.onResponse(call, res)

        Mockito.verify(view).showPlayers(res.body()?.player)
    }
}