package com.softnik.mvvm_andx_kotlin_dogsapidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.softnik.mvvm_andx_kotlin_dogsapidemo.R
import com.softnik.mvvm_andx_kotlin_dogsapidemo.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private var dogUuid = 0
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModelOperations()
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
            // tvDetailFragment.text = dogUuid.toString()

        }

        /*  btnDetail?.setOnClickListener {
          val action = DetailFragmentDirections.actionListFragment()
          Navigation.findNavController(it).navigate(action)

      }*/

    }

    private fun viewModelOperations() {
        viewModel.apply {






        }

    }

}
