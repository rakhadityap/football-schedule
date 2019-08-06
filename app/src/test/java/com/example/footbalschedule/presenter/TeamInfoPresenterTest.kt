package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.TeamResponse
import com.example.footbalschedule.teaminfo.TeamInfoPresenter
import com.example.footbalschedule.teaminfo.TeamInfoView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamInfoPresenterTest {
    private lateinit var presenter: TeamInfoPresenter

    @Mock private lateinit var view:TeamInfoView
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var call: Call<TeamResponse>
    @Mock private lateinit var res: Response<TeamResponse>
    @Captor private lateinit var captor: ArgumentCaptor<Callback<TeamResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = TeamInfoPresenter(view, apiService)
    }

    @Test
    fun testGetTeamInfo(){
        val id = "0000"
        Mockito.`when`(apiService.getTeam(id)).thenReturn(call)

        presenter.getTeamInfo(id)

        Mockito.verify(call).enqueue(captor.capture())
        captor.value.onResponse(call, res)

        Mockito.verify(view).showTeamInfo(res.body()?.teams?.get(0))
    }
}