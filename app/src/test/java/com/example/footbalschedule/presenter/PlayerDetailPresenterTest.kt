package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.PlayerResponse
import com.example.footbalschedule.playerdetail.PlayerDetailPresenter
import com.example.footbalschedule.playerdetail.PlayerView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerDetailPresenterTest {
    private lateinit var presenter: PlayerDetailPresenter
    @Mock private lateinit var view: PlayerView
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var call: Call<PlayerResponse>
    @Mock private lateinit var res: Response<PlayerResponse>
    @Captor private lateinit var captor: ArgumentCaptor<Callback<PlayerResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = PlayerDetailPresenter(view, apiService)
    }

    @Test
    fun testGetPlayer(){
        val id = "0000"
        Mockito.`when`(apiService.getPlayer(id)).thenReturn(call)

        presenter.getPlayerDetail(id)

        Mockito.verify(call).enqueue(captor.capture())
        captor.value.onResponse(call, res)

        Mockito.verify(view).showPlayerDetail(res.body()?.players?.get(0))
    }
}