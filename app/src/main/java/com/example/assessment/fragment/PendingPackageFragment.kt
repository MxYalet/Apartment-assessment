package com.example.assessment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.R
import com.example.assessment.adapter.PendingPackageAdapter

class PendingPackageFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pending, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewPendingPackages)
        setupRecyclerView()    }

    private fun setupRecyclerView() {
        val packages = listOf(
            Package("Package 1", "Address 1", "Date 1"),
            Package("Package 2", "Address 2", "Date 2"),
            Package("Package 3", "Address 3", "Date 3"),
            Package("Package 4", "Address 4", "Date 4"),
            Package("Package 5", "Address 5", "Date 5")
        )

        val adapter = PendingPackageAdapter(packages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    data class Package(val name: String, val address: String, val date: String)
}