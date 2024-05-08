package com.example.assessment.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ActivityFragment : Fragment() {

    companion object {
        private const val ARG_ACTIVITY_CLASS = "activity_class"

        fun newInstance(activityClass: Class<out Activity>): ActivityFragment {
            val fragment = ActivityFragment()
            val args = Bundle()
            args.putSerializable(ARG_ACTIVITY_CLASS, activityClass)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val activityClass = arguments?.getSerializable(ARG_ACTIVITY_CLASS) as? Class<out Activity>
        activityClass?.let {
            startActivity(Intent(requireContext(), it))
        }
        return null
    }
}