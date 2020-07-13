package com.softnik.mvvm_andx_kotlin_dogsapidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.softnik.mvvm_andx_kotlin_dogsapidemo.R
import com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {


    private lateinit var viewModel : ListViewModel
    private  var dogsListAdapter= DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.refresh()
        rvDogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter

        }

        observeViewModel()

      /*  btnList?.setOnClickListener {
            val action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid=5
            Navigation.findNavController(it).navigate(action)

        }*/
    }

    private fun observeViewModel() {
        viewModel.apply {
            dogsListLiveData.observe(this@ListFragment , Observer {
                it?.let {
                    rvDogsList.visibility = View.VISIBLE
                    dogsListAdapter.updateDogsList(it)

                }


            })

            dogsLoadError.observe(this@ListFragment, Observer {
                it?.let {
                    tvErrorMsg.visibility =if(it) View.VISIBLE else View.GONE

                }

            })
            loading.observe(this@ListFragment , Observer {
                it?.let {
                    loadingView.visibility = if(it) View.VISIBLE else View.GONE

                    if(it){
                        tvErrorMsg.visibility = View.GONE
                        rvDogsList.visibility = View.GONE
                    }


                }

            })



        }


    }
}
