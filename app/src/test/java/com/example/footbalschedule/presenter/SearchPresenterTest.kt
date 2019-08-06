package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.model.MatchSearchResponse
import com.example.footbalschedule.model.TeamResponse
import com.example.footbalschedule.search.SearchPresenter
import com.example.footbalschedule.search.SearchView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenterTest {

    private lateinit var presenter: SearchPresenter
    @Mock private lateinit var view: SearchView
    @Mock private lateinit var callTeam: Call<TeamResponse>
    @Mock private lateinit var callMatch: Call<MatchSearchResponse>
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var resTeam: Response<TeamResponse>
    @Mock private lateinit var resMatch: Response<MatchSearchResponse>
    @Captor private lateinit var captorTeam: ArgumentCaptor<Callback<TeamResponse>>
    @Captor private lateinit var captorMatch: ArgumentCaptor<Callback<MatchSearchResponse>>

    var query = "Arsernal"

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = SearchPresenter(view, apiService)
    }

    @Test
    fun testSearchTeam(){
        Mockito.`when`(apiService.searchTeams(query)).thenReturn(callTeam)

        presenter.searchTeam(query)

        Mockito.verify(callTeam).enqueue(captorTeam.capture())
        captorTeam.value.onResponse(callTeam, resTeam)

        Mockito.verify(view).showSearchResult(resTeam.body()?.teams)
    }

    @Test
    fun testSearchMatch(){
        Mockito.`when`(apiService.searchMatch(query)).thenReturn(callMatch)

        presenter.searchMatch(query)

        Mockito.verify(callMatch).enqueue(captorMatch.capture())
        captorMatch.value.onResponse(callMatch, resMatch)

        Mockito.verify(view).showSearchResult(resMatch.body()?.event)
    }
}