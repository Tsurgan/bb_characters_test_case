package com.example.test_bb

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.android.synthetic.main.fragment_blank.view.*

import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_bb.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CHAR_ID = "1"


/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    private var char_id_data: String? = null
    private var char_name:String? = null
    private var char_birthday:String? = null
    private var char_occupation:List<String>?=null
    private var char_img:String?=null
    private var char_status:String?=null
    private var char_nickname:String?=null
    private var char_appearance:List<Int>?=null
    private var char_portrayed:String?=null
    private var char_category:String?=null
    private var char_better_call_saul_appearance:List<Int>?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity)?.supportActionBar?.hide()
        arguments?.let {




        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_blank, container, false)

        char_id_data = arguments?.getString("char_id_data")
        char_id_data?.let {

        }
        char_name=arguments?.getString("char_name")
        char_name?.let {
            v.toolbar_layout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
            v.toolbar_layout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
            v.toolbar_layout.setTitle(char_name)
        }
        char_birthday=arguments?.getString("char_birthday")
        char_birthday?.let {
            if (char_birthday!="Unknown") {
                var parcer = DateTimeFormatter.ofPattern("MM-dd-yyyy")
                var formatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
                var number = LocalDate.parse(char_birthday, parcer)
                var formattedDate = number.format(formatter)

                v.tvbirthday.text = formattedDate
            }
            else{
                v.tvbirthday.text = "—"
            }
        }
        char_occupation=arguments?.getStringArrayList("char_occupation")
        char_occupation?.let {

            var occupationpost="";


                var occlist= (char_occupation as ArrayList<String>).toList();
                if(occlist.size>0) {
                    for (i in 0..(occlist.size - 2)) {

                        occupationpost = occupationpost.plus(occlist[i].plus(",\n"))
                    }
                    occupationpost = occupationpost.plus(occlist[occlist.size - 1])
                }

            v.tvoccupation.text=occupationpost
        }
        char_img=arguments?.getString("char_img")
        char_img?.let {
            Picasso.get().load(char_img).transform(CropCircleTransformation()).into(v.ivimg)
            Picasso.get().load(char_img).into(v.expandedImage)

        }
        char_status=arguments?.getString("char_status")
        char_status?.let {
            v.tvstatus.text=char_status
        }
        char_nickname=arguments?.getString("char_nickname")
        char_nickname?.let {
            v.tvnickname.text=char_nickname
        }
        char_appearance=arguments?.getIntegerArrayList("char_appearance")
        char_appearance?.let {
            var appearancepost="";


            var applist= (char_appearance as ArrayList<Int>).toList();
            if (applist.size>0) {
                for (i in 0..(applist.size - 2)) {
                    appearancepost = appearancepost.plus(applist[i].toString().plus(","))
                }
                appearancepost = appearancepost.plus(applist[applist.size - 1].toString())
            }
            v.tvabb.text=appearancepost
        }
        char_portrayed=arguments?.getString("char_portrayed")
        char_portrayed?.let {
            v.tvportrayed.text=char_portrayed
        }

        char_better_call_saul_appearance=arguments?.getIntegerArrayList("char_better_call_saul_appearance")
        char_better_call_saul_appearance?.let {
            var bcspost="";


            var bcslist= (char_better_call_saul_appearance as ArrayList<Int>).toList();
            if (bcslist.size>0) {
                for (i in 0..(bcslist.size - 2)) {
                    bcspost = bcspost.plus(bcslist[i].toString().plus(","))
                }
                bcspost = bcspost.plus(bcslist[bcslist.size - 1].toString())
            }
            v.tvabcs.text=bcspost
        }
        char_category=arguments?.getString("char_category")
        char_category?.let {

            if (char_category=="Breaking Bad"){
                v.tvcbcs.isVisible=false
                v.tvabcs.isVisible=false

            }
            if (char_category=="Better Call Saul"){
                v.tvcbb.isVisible=false
                v.tvabb.isVisible=false
            }
            if (char_category=="Breaking Bad, Better Call Saul"){

            }
            v.toolbar.setNavigationOnClickListener(){getActivity()?.onBackPressed();}

           // v.bback.setOnClickListener {  }
        }

        return v;
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as AppCompatActivity)?.supportActionBar?.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.

         * @return A new instance of fragment BlankFragment.
         */

        @JvmStatic
        fun newInstance(char_id: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    val char_id1= CHAR_ID

                }
            }
    }
}