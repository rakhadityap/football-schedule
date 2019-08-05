package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.leaguedetail.LeagueDetailPresenter
import com.example.footbalschedule.leaguedetail.LeagueDetailView
import com.example.footbalschedule.model.LeagueDetailResponse
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenterTest {

    private lateinit var presenter: LeagueDetailPresenter

    @Mock private lateinit var view: LeagueDetailView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<LeagueDetailResponse>

    @Mock private lateinit var res: Response<LeagueDetailResponse>

    @Captor private lateinit var argumentCaptor: ArgumentCaptor<Callback<LeagueDetailResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = LeagueDetailPresenter(view, apiService)
    }

    @Test
    fun testGetLeagueDetail(){
        val id = "0000"
        Mockito.`when`(apiService.getLeagueDetail(id)).thenReturn(call)

        presenter.getLeagueDetail(id)

        Mockito.verify(call).enqueue(argumentCaptor.capture())
        argumentCaptor.value.onResponse(call, res)

        Mockito.verify(view).showLeagueDetail(res.body()?.leagues?.get(0))
    }
}