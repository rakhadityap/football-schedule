package com.example.footbalschedule.app

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun View.visible()
{
    visibility = View.VISIBLE
}

fun View.invisible()
{
    visibility = View.INVISIBLE
}

fun View.hide()
{
    visibility = View.GONE
}

fun SwipeRefreshLayout.refresh()
{
    isRefreshing = true
}

fun SwipeRefreshLayout.done()
{
    isRefreshing = false
}

fun getDate(dateString: String): String? {
    try
    {
        val f = SimpleDateFormat("dd/MM/yy HH:mm:ssZ", Locale("id", "ID"))
        val datetime: Date = f.parse(dateString)
        val cal: Calendar = Calendar.getInstance()
        cal.time = datetime
        val df = DecimalFormat("00")

        val dayOfWeek = getDay(cal.get(Calendar.DAY_OF_WEEK))
        val date = cal.get(Calendar.DAY_OF_MONTH)
        val month = getMonth(cal.get(Calendar.MONTH))
        val year = cal.get(Calendar.YEAR)

        val hours = df.format(cal.get(Calendar.HOUR))
        val minutes = df.format(cal.get(Calendar.MINUTE))
        val tanggal = "$dayOfWeek, $date $month $year $hours:$minutes"
        return tanggal
    } catch (e: Exception){
        return null
    }
}

fun getDay(dayOfWeek: Int) = when (dayOfWeek) {
    1 -> "Min"
    2 -> "Sen"
    3 -> "Sel"
    4 -> "Rab"
    5 -> "Kam"
    6 -> "Jum"
    7 -> "Sab"
    else -> "-"
}

fun getMonth(month: Int) = when (month){
    0 -> "Jan"
    1 -> "Feb"
    2 -> "Mar"
    3->"Apr"
    4->"Mei"
    5->"Jun"
    6->"Jul"
    7->"Agu"
    8->"Sep"
    9->"Okt"
    10->"Nov"
    11->"Des"
    else -> "-"
}

fun showToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun snapRecyclerView(r: RecyclerView){
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(r)
}
