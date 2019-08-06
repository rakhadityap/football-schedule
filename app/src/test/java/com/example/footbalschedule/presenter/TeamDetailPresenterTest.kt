package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.TeamResponse
import com.example.footbalschedule.teamdetail.TeamDetailPresenter
import com.example.footbalschedule.teamdetail.TeamDetailView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailPresenterTest {
    private lateinit var presenter: TeamDetailPresenter
    @Mock private lateinit var view: TeamDetailView
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var call: Call<TeamResponse>
    @Mock private lateinit var res: Response<TeamResponse>
    @Captor private lateinit var captor: ArgumentCaptor<Callback<TeamResponse>>
    val id = "0000"
    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = TeamDetailPresenter(view, apiService)
    }

    @Test
    fun testGetTeamDetail(){
        Mockito.`when`(apiService.getTeam(id)).thenReturn(call)
        presenter.getTeamDetail(id)
        Mockito.verify(call).enqueue(captor.capture())
        captor.value.onResponse(call, res)
        Mockito.verify(view).showTeamDetail(res.body()?.teams?.get(0))
    }
}