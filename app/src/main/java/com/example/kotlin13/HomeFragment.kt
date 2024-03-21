package com.example.kotlin13

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin13.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var myAdapter: MyAdapter
    private val handler = Handler(Looper.getMainLooper())
    private val list = listOf(
        "some text 1",
        "some text 2",
        "some text 3",
        "some text 4",
        "some text 5",
        "some text 6",
        "some text 7",
        "some text 8",
        "some text 9",
        "some text 10",
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = MyAdapter(list)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        handler.post(runnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
        _binding = null
    }

    private val runnable = object : Runnable {
        override fun run() {
            myAdapter.changeStyle()
            handler.postDelayed(this, 5000)
        }
    }
}
