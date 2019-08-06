package com.example.footbalschedule.presenter

import com.example.footbalschedule.app.network.ApiService
import com.example.footbalschedule.eventdetail.EventDetailPresenter
import com.example.footbalschedule.eventdetail.EventDetailView
import com.example.footbalschedule.model.EventDetail
import com.example.footbalschedule.model.EventDetailResponse
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailPresenterTest {
    private lateinit var presenter: EventDetailPresenter

    @Mock private lateinit var view: EventDetailView
    @Mock private lateinit var apiService: ApiService
    @Mock private lateinit var call: Call<EventDetailResponse>
    @Mock private lateinit var res: Response<EventDetailResponse>
    @Mock private lateinit var event: EventDetail
    @Captor private lateinit var captor: ArgumentCaptor<Callback<EventDetailResponse>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

        presenter = EventDetailPresenter(view, apiService)
    }

    @Test
    fun testGetEventDetail(){
        val id = "0000"
        Mockito.`when`(apiService.getEventDetails(id)).thenReturn(call)
        presenter.getEventDetail(id)

        Mockito.verify(call).enqueue(captor.capture())
        captor.value.onResponse(call, res)

        Mockito.verify(view).showDetail(event)
    }
}