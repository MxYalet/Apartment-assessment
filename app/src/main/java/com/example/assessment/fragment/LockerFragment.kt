package com.example.assessment.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.db.williamchart.view.BarChartView
import com.example.assessment.R
import com.example.assessment.activity.AnnouncementActivity
import com.example.assessment.activity.MainActivity
import com.example.assessment.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {
    private var _binding: FragmentLockerBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private var selectedYear: String? = null
    private var selectedQuarter: String? = null
    private var selectedPackageType: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLockerBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("popup_pref", Context.MODE_PRIVATE)

        if (shouldShowPopup()) {
            showPopup()
        }
        val chart: BarChartView = binding.chart

        val deliveredPackages = listOf(
            "JAN" to 4F,
            "FEB" to 7F,
            "MAR" to 2F,
            "APR" to 5F,
            "MAY" to 2F,
            "JUN" to 4F
        )

        val pendingPackages = listOf(
            "JAN" to 2F,
            "FEB" to 3F,
            "MAR" to 1F,
            "APR" to 2F,
            "MAY" to 1F,
            "JUN" to 3F
        )

        chart.animation.duration = animationDuration
        chart.animate(
            pendingPackages
        )

        chart.animation.duration = animationDuration
        chart.animate(
            deliveredPackages
        )


        mainActivity = activity as MainActivity

        binding.deliveredLayout.setOnClickListener {
            mainActivity.navigateToFragment(DeliveredPackageFragment())   }

        binding.pendingLayout.setOnClickListener {
            (activity as MainActivity).navigateToFragment(PendingPackageFragment())        }

      binding.ibFilter.setOnClickListener {
          showfilter()
      }
    }



    data class BarEntry(
        val month: String,
        val delivered: Float,
        val pending: Float
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        showPopup()
    }

    private  val animationDuration = 1000L


    private fun shouldShowPopup(): Boolean {
        val lastPopupTime = sharedPreferences.getLong("last_popup_time", 0)
        val currentTime = System.currentTimeMillis()
        val twoWeeksInMillis = 2 * 7 * 24 * 60 * 60 * 1000L

        return currentTime - lastPopupTime >= twoWeeksInMillis
    }

    private fun showPopup() {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.layout_popup, null)
        val closeButton = popupView.findViewById<ImageButton>(R.id.ib_back)
        sharedPreferences.edit().putLong("last_popup_time", System.currentTimeMillis()).apply()

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(popupView)
        val dialog = builder.create()

        closeButton.setOnClickListener {
            Log.d("Info", "Clicked")
            dialog.dismiss()
        }

        dialog.setOnShowListener {
            popupView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    popupView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    val introductionText = popupView.findViewById<TextView>(R.id.learnHowTextView)
                    introductionText.setOnClickListener {
                        val intent = Intent(requireContext(), AnnouncementActivity::class.java)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                }
            })
        }

        dialog.show()
    }



    private fun showfilter() {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.layout_filter, null)
        val closeButton = popupView.findViewById<ImageButton>(R.id.ib_back)
        val apply = popupView.findViewById<Button>(R.id.apply_btn)


        val builder = AlertDialog.Builder(requireContext())
        builder.setView(popupView)
        val dialog = builder.create()

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        apply.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.gravity = Gravity.BOTTOM
        dialog.window?.attributes = layoutParams
    }

}
