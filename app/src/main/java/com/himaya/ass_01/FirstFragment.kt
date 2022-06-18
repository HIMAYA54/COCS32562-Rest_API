package com.himaya.ass_01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.himaya.ass_01.databinding.FragmentFirstBinding
import javax.security.auth.callback.Callback
import com.himaya.ass_01.adapter.dataAdapter
import com.himaya.ass_01.model.data
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context)
        val data = retrofitAPIHandler.getdata()

        data.enqueue(object : Callback<List<data>> {

            override fun onResponse(call: Call<List<data>>, response: Response<List<data>>){
                val dataBody = response.body()
                val adapter = dataAdapter(dataBody!!, this,{position ->onListItemClick(position)})
                binding.recyclerView.adapter = adapter
            }
            override fun onFailure(call:Call<List<data>>,t:Throwable){
                Snackbar.make(view, "Failur in callback", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                Log.i("TAG", "onFailure:callback failed")
            }
        })

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onListItemClick(position: Int){
        Snackbar.make(requireView(),"Click on item ${position + 1}", Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
        Log.i("TAG", "onListItemClick: $position clicked")
    }
}