package com.example.assessment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.R
import com.example.assessment.activity.MainActivity
import com.example.assessment.adapter.DeliveredPackagesAdapter

class DeliveredPackageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delivered, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewDeliveredPackages)
        setupRecyclerView()

        val backButton = view.findViewById<ImageButton>(R.id.backButton)

        mainActivity = activity as MainActivity

        backButton.setOnClickListener {

            mainActivity.supportFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        val packages = listOf(
            Package("Teddy Bear", "Ikeja, Lagos", "12-04-24"),
            Package("Teddy bear", "Alimosho", "15-04-24"),
            Package("Gift box", "Salvation road, Lagos", "12-04-24"),
            Package("Track pad", "Berger", "05-01-24")
        )

        val adapter = DeliveredPackagesAdapter(packages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    data class Package(val name: String, val address: String, val date: String)
}