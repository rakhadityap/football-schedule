package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.StandingsResponse
import com.example.footbalschedule.standings.StandingsPresenter
import com.example.footbalschedule.standings.StandingsView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsPresenterTest {
    private lateinit var presenter: StandingsPresenter

    @Mock private lateinit var view: StandingsView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<StandingsResponse>

    @Mock private lateinit var res: Response<StandingsResponse>

    @Captor private lateinit var argCaptor: ArgumentCaptor<Callback<StandingsResponse>>

    val id = "0000"

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = StandingsPresenter(view, apiService)
    }

    @Test
    fun testGetStandings(){
        Mockito.`when`(apiService.getStandings(id)).thenReturn(call)

        presenter.getStandings(id)

        Mockito.verify(call).enqueue(argCaptor.capture())
        argCaptor.value.onResponse(call, res)

        Mockito.verify(view).showStandings(res.body()?.table)
    }
}