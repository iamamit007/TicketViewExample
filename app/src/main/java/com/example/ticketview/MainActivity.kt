package com.example.ticketview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.*
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import jdk.nashorn.internal.objects.NativeDate.getTime
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR)
        getSupportActionBar()!!.hide()
        setContentView(R.layout.activity_main)
                actionBar!!.hide()
        //test here to view
        setUpView()
        getDaysAgo(7)
    }

    private fun setUpView(){
        val array  = resources.getStringArray(R.array.slots)
        list_con.removeAllViews()


        for ((index,i) in array.withIndex()){

            val view = LayoutInflater.from(this).inflate(R.layout.row_timings, list_con, false)
            val title_text_hd = view.findViewById(R.id.title_text_hd) as TextView
            val title_text = view.findViewById(R.id.title_text) as TextView
            val ttle_img_hd = view.findViewById(R.id.ttle_img_hd) as ImageView
            val ttle_img = view.findViewById(R.id.ttle_img) as ImageView
            val add_btn = view.findViewById(R.id.ttle_img) as ImageView
            val details_con = view.findViewById(R.id.details_con) as RelativeLayout
            val card_view = view.findViewById(R.id.card_view) as CardView

            title_text_hd.text = i
            title_text.text = i
            if(i=="Morning"){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttle_img_hd.setImageDrawable(this.getDrawable(R.drawable.ic_sun))
                    ttle_img.setImageDrawable(this.getDrawable(R.drawable.ic_sun))
                }
            }
            card_view.setOnClickListener {
               if(details_con.visibility == View.GONE){
                   details_con.visibility = View.VISIBLE
               }else{
                   details_con.visibility = View.GONE

               }
            }


            list_con.addView(view)
        }
    }


    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, +daysAgo)
        Log.d("cccc",calendar.time.toString())

        return calendar.time
    }

    fun main(args: Array<String>) {
        val now = Calendar.getInstance()

        val format = SimpleDateFormat("MM/dd/yyyy  EEE")

        val days = arrayOfNulls<String>(7)
        val delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2 //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta)
        for (i in 0..6) {
            days[i] = format.format(now.time)
            now.add(Calendar.DAY_OF_MONTH, 1)
        }
        System.out.println(Arrays.toString(days))

    }
}
