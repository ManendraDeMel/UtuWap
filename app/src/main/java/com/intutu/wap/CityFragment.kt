package com.intutu.wap

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "RetroCall"
private lateinit var wapviewmodel: WapViewModel
private lateinit var wapviewModelFactory: WapViewModelFactory

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
    //private lateinit var xx: String
    private var adapter: wapAdapter? = null
    private lateinit var citynametxt : TextView
    private lateinit var curennttemptxt : TextView
    private lateinit var mainweathertxt : TextView
    private lateinit var parentlayout : ConstraintLayout
    private lateinit var minmaxtxt : TextView
    private lateinit var dailywapitems : List<DailyWeather>
    private lateinit var location : LatLon
    private var utility : Utility = Utility()
    lateinit var paramsize: ViewGroup.LayoutParams
    lateinit var paramsoriginal: ViewGroup.LayoutParams
    var recyclerclick : Boolean = false

    interface Callbacks {
        fun nextFragment(frname: String)
    }
    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }


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

        var xxx : DoubleArray = getLatLon()

        var latt : String = xxx[0].toString()
        var long : String = xxx[1].toString()

        wapviewModelFactory = WapViewModelFactory(latt, long)

        citynametxt = view.findViewById(R.id.hellotext)
        curennttemptxt = view.findViewById(R.id.tempcurrent)
        mainweathertxt = view.findViewById(R.id.weathermain)
        minmaxtxt = view.findViewById(R.id.tempminmax)
        parentlayout = view.findViewById(R.id.parentlayout)





        wapRecyclerView =
                view.findViewById(R.id.wap_recycler_view) as RecyclerView
        wapRecyclerView.layoutManager = LinearLayoutManager(context)


        wapviewmodel = ViewModelProviders.of(requireActivity(), wapviewModelFactory).get(WapViewModel::class.java)
        wapviewmodel.dailywapsLiveData.observe(
                viewLifecycleOwner,
                Observer { dailywapItems ->
                    Log.d(TAG, "Response received OpenWeatherMaps $dailywapItems")
                    //xx = dailywapItems.first().dt.toString()
                    dailywapitems = wapviewmodel.dailywaps

                    wapviewmodel.dd("current")

                    dailywapitems = wapviewmodel.dailywaps

                    //var xx : String = wapviewmodel.cityobject.cityname


                    //citynametxt.setText(wapviewmodel.cityobject.getCitylocationname());
                    //Log.d("TAG2", "CITY NAME : $xx")

                    // Eventually, update data backing the recycler view
                    updateUI()
                    // citynametxt.setText(wapviewmodel.cityobject.cityname);
                    curennttemptxt.setText(dailywapItems.first().temp.day.toString().substringBefore(".") + "\u00B0")
                    mainweathertxt.setText(dailywapItems.first().weather[0].main)
                    minmaxtxt.setText(dailywapItems.first().temp?.min.toString().substringBefore(".") + "\u00B0" + "/" + dailywapItems.first().temp?.max.toString().substringBefore(".") + "\u00B0")


                    citynamepass()
                })

        if(wapviewmodel.initcheck) {
            wapviewmodel.ddd("initiate")

            curennttemptxt.setText(wapviewmodel.currentTemp)
            mainweathertxt.setText(wapviewmodel.currentMain)
            minmaxtxt.setText(wapviewmodel.currentminmax)

        }

        wapviewmodel.initcheck = true


        view.setOnTouchListener(object : SwipeChecker(requireActivity()) {
            override fun onSwipeLeft() {
                callbacks?.nextFragment("sydney")
            }

            override fun onSwipeRight() {

            }
        })

        paramsize= parentlayout.getLayoutParams()
         val params2 = wapRecyclerView.getLayoutParams()
        paramsoriginal = params2



        //citynametxt.setText(wapviewmodel.cityobject.cityname);
        return view
    }



    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class CrimeHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        val dateTextView: TextView = itemView.findViewById(R.id.date_info)
        val tempTextView: TextView = itemView.findViewById(R.id.temp_info)
        val weatherImageView: ImageView = itemView.findViewById(R.id.weather_image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            var params: ViewGroup.LayoutParams = wapRecyclerView.getLayoutParams()
            if (!recyclerclick)
            {

                params.height = paramsize.height
            wapRecyclerView.setLayoutParams(params)
                val aniFade = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in)
                wapRecyclerView.startAnimation(aniFade)
            recyclerclick = true
        }
            else{
                    params.height = 715
                    wapRecyclerView.setLayoutParams(params)
                val aniFade = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out)
                wapRecyclerView.startAnimation(aniFade)
                    recyclerclick = false
                }



        }
    }

    private inner class wapAdapter(var dailywaps: List<DailyWeather>)
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
                tempTextView.text = dailywap.temp //dailywap.temp.max.toString() + "/" + dailywap.temp.min.toString()//dailywap.weather[0].main
                if(position == 0)
                {
                    dateTextView.text = "Tomorrow"
                }
                else
                {
                dateTextView.text = utility.getDateTime(dailywap.date.toString())
                }

                if(dailywap.weathermain.equals("Clear"))
                {
                    weatherImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.clear_weather))
                }
                else if(dailywap.weathermain.equals("Clear"))
                {
                    weatherImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.clear_weather))
                }




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

    private fun citynamepass() {

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                var  xx2 : String? = wapviewmodel.currentcityobject.getCitylocationname()
                citynametxt.setText(xx2)

                wapviewmodel.currentTemp = (wapviewmodel.cdailywapsLiveData.value!!.first().temp.day.toString().substringBefore(".") + "\u00B0" )
                var xx : String = wapviewmodel.currentTemp
                Log.d("Testcall", "$xx")
                curennttemptxt.setText(xx)
                wapviewmodel.currentMain = (wapviewmodel.cdailywapsLiveData.value!!.first().weather[0].main)
                var x : String = wapviewmodel.currentMain
                Log.d("Testcall", "$x")
                mainweathertxt.setText(x)
                wapviewmodel.currentminmax = (wapviewmodel.cdailywapsLiveData.value!!.first().temp?.min.toString().substringBefore(".") + "\u00B0" + "/" + wapviewmodel.cdailywapsLiveData.value!!.first().temp?.max.toString().substringBefore(".") + "\u00B0")
                var x2: String = wapviewmodel.currentminmax
                minmaxtxt.setText(x2)
            }
        }

    }

    private fun updateUI() {

        adapter = wapAdapter(wapviewmodel.dailywaps)
        wapRecyclerView.adapter = adapter
    }

    private fun getLatLon () : DoubleArray {
        location = LatLon(context)
        val str = DoubleArray(2)
        if (location.canGetLocation()) {
            str[0] = location.getLatitude()
            str[1] = location.getLongitude()
        } else {
            location.showSettingsAlert()
        }
        return str
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