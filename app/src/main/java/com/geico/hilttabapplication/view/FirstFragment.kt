package com.geico.hilttabapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geico.hilttabapplication.R
import com.geico.hilttabapplication.databinding.FragmentFirstBinding
import com.geico.hilttabapplication.model.User
import com.geico.hilttabapplication.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    lateinit var viewModel: MainViewModel


    private val TAG = "FirstFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        //binding.nameTv.setOnFocusChangeListener()

        binding.submitButton.setOnClickListener { onSubmitData() }

        binding.skipButton.setOnClickListener { onSkipData() }

        logdata()

    }

    private fun onSkipData() {
        Log.d(TAG, "onSkipData: ")
    }

    private fun onSubmitData() {

        val name = binding.nameTv.text.toString()
        val lastName=binding.lastNameTv.text.toString()
        val pass =binding.passwordTv.text.toString()

        if (name.isEmpty()){
            Toast.makeText(requireContext(),"please enter name", Toast.LENGTH_SHORT).show()
        }else if(lastName.isEmpty()){
            Toast.makeText(requireContext(),"please enter lastName", Toast.LENGTH_SHORT).show()
        }else if (pass.isEmpty()){
            Toast.makeText(requireContext(),"please enter pass", Toast.LENGTH_SHORT).show()
        }else{
            alertDialogue("Save Confirm", "Are you sure to save?", User(0,name,lastName,pass))
        }

    }

    fun alertDialogue(title:String, supportingText:String, user: User){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(supportingText)
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                Toast.makeText(requireContext(),"cancel", Toast.LENGTH_SHORT).show()
                binding.nameTv.text?.clear()
                binding.lastNameTv.text?.clear()
                binding.passwordTv.text?.clear()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                viewModel.insertRecord(user)
                Toast.makeText(requireContext(),"Successful", Toast.LENGTH_SHORT).show()
                binding.nameTv.text?.clear()
                binding.lastNameTv.text?.clear()
                binding.passwordTv.text?.clear()
            }
            .show()
    }
    private fun logdata(){

        viewModel.getRecords().observe(requireActivity(), object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                    Log.d("TAG", "onChanged: "+t)
            }
        })
    }


}