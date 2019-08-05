package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.TeamResponse
import com.example.footbalschedule.team.TeamPresenter
import com.example.footbalschedule.team.TeamView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPresenterTest {
    private lateinit var presenter: TeamPresenter

    @Mock private lateinit var view: TeamView

    @Mock private lateinit var apiService: ApiService

    @Mock private lateinit var call: Call<TeamResponse>

    @Mock private lateinit var res: Response<TeamResponse>

    @Captor private lateinit var captor: ArgumentCaptor<Callback<TeamResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = TeamPresenter(view, apiService)
    }

    @Test
    fun testGetTeam(){
        val id = "0000"
        Mockito.`when`(apiService.getTeamList(id)).thenReturn(call)

        presenter.getTeams(id)

        Mockito.verify(call).enqueue(captor.capture())

        captor.value.onResponse(call, res)

        Mockito.verify(view).showTeams(res.body()?.teams)
    }
}