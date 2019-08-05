package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.league.LeaguePresenter
import com.example.footbalschedule.league.LeagueView
import com.example.footbalschedule.model.LeagueResponse
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenterTest {

    private lateinit var presenterTest: LeaguePresenter

    @Mock private lateinit var view: LeagueView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<LeagueResponse>

    @Mock private lateinit var res: Response<LeagueResponse>

    @Captor private lateinit var argumentCaptor: ArgumentCaptor<Callback<LeagueResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenterTest = LeaguePresenter(view, apiService)
    }

    @Test
    fun testGetLeagues(){
        Mockito.`when`(apiService.getLeagueList()).thenReturn(call)

        presenterTest.getLeagues()

        Mockito.verify(call).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call, res)

        Mockito.verify(view).showLeague(res.body()?.countrys)
    }
}