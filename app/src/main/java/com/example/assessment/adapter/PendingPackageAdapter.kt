package com.example.assessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assessment.fragment.PendingPackageFragment
import com.example.assessment.R

class PendingPackageAdapter (private val packages: List<PendingPackageFragment.Package>) :
    RecyclerView.Adapter<PendingPackageAdapter.PackageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delivered_package, parent, false)
        return PackageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val packageItem = packages[position]
        holder.bind(packageItem)
    }

    override fun getItemCount(): Int = packages.size

    inner class PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPackageName: TextView = itemView.findViewById(R.id.textViewPackageName)
        private val textViewPackageAddress: TextView = itemView.findViewById(R.id.textViewPackageAddress)
        private val textViewPackageDate: TextView = itemView.findViewById(R.id.textViewPackageDate)

        fun bind(packageItem: PendingPackageFragment.Package) {
            textViewPackageName.text = packageItem.name
            textViewPackageAddress.text = packageItem.address
            textViewPackageDate.text = packageItem.date
        }
    }
}