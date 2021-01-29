package com.intutu.wap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() ,CityFragment.Callbacks , SydneyFragment.Callbacks , HobartFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CityFragment.newInstance())
                .commit()
        }


    }

    override fun nextFragment(frname : String) {

        if(frname.equals("sydney")) {
            val fragment = SydneyFragment()
            supportFragmentManager.commit {
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.slide_out,
                        R.anim.slide_in,
                        R.anim.slide_out
                )
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)

            }
        }
            if(frname.equals("hobart")) {
            val fragment = HobartFragment()
            supportFragmentManager.commit {
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.slide_out,
                        R.anim.slide_in,
                        R.anim.slide_out

                )
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)

            }
        }

        if(frname.equals("perth")) {
            val fragment = PerthFragment()
            supportFragmentManager.commit {
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.slide_out,
                        R.anim.slide_in,
                        R.anim.slide_out
                )
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)

            }
        }



    }
}