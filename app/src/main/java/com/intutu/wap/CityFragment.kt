package com.intutu.wap

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "RetroCall"
private lateinit var wapviewmodel: WapViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var wapRecyclerView: RecyclerView
    private lateinit var xx: String
    private var adapter: wapAdapter? = null
    private lateinit var hellotxt : TextView
    private lateinit var dailywapitems : List<jk>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)

        hellotxt = view.findViewById(R.id.hellotext)


        wapRecyclerView =
                view.findViewById(R.id.wap_recycler_view) as RecyclerView
        wapRecyclerView.layoutManager = LinearLayoutManager(context)


        wapviewmodel = ViewModelProviders.of(this).get(WapViewModel::class.java)
        wapviewmodel.dailywapsLiveData.observe(
            viewLifecycleOwner,
            Observer { dailywapItems ->
                Log.d(TAG, "Response received OpenWeatherMaps $dailywapItems")
                xx = dailywapItems.first().dt.toString()
                dailywapitems = dailywapItems

                hellotxt.setText(xx);
                // Eventually, update data backing the recycler view
                updateUI()
            })




        return view
    }

    private inner class CrimeHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = itemView.findViewById(R.id.date_info)
        val tempTextView: TextView = itemView.findViewById(R.id.temp_info)
        val weatherImageView: ImageView = itemView.findViewById(R.id.weather_image)
    }

    private inner class wapAdapter(var dailywaps: List<jk>)
        : RecyclerView.Adapter<CrimeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_daywap, parent, false)
            return CrimeHolder(view)
        }
        override fun getItemCount() = dailywapitems.size
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val dailywap = dailywapitems[position]
            holder.apply {
                tempTextView.text = dailywap.weather[0].main
                dateTextView.text = dailywap.dt.toString()

               /* if(dailywap.isRaining)
                {
                    weatherImageView.setImageResource(R.drawable.rain_weather)
                }
                else{
                    weatherImageView.setImageResource(R.drawable.clear_weather)
                } */
            }
        }
    }

    private fun updateUI() {

        adapter = wapAdapter(dailywapitems)
        wapRecyclerView.adapter = adapter
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CityFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance() = CityFragment()
    }
}