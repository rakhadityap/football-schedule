package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.match.MatchPresenter
import com.example.footbalschedule.match.MatchView
import com.example.footbalschedule.model.MatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenterTest {

    private lateinit var presenter: MatchPresenter

    @Mock private lateinit var view: MatchView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<MatchResponse>

    @Mock private lateinit var res: Response<MatchResponse>

    @Captor private lateinit var argumentCaptor: ArgumentCaptor<Callback<MatchResponse>>

    val id = "0000"

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = MatchPresenter(view, apiService)
    }

    @Test
    fun testGetFutureMatches(){
        Mockito.`when`(apiService.getSchedule(id)).thenReturn(call)

        presenter.getFutureMatches(id)

        Mockito.verify(call).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call, res)

        Mockito.verify(view).showFutureMatches(res.body()?.events)
    }

    @Test
    fun testGetPastMatches(){
        Mockito.`when`(apiService.getPastEvents(id)).thenReturn(call)

        presenter.getPastMatches(id)

        Mockito.verify(call).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call, res)

        Mockito.verify(view).showPastMatches(res.body()?.events)
    }
}