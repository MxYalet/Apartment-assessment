package com.example.assessment.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.assessment.R
import com.example.assessment.activity.AnnouncementActivity
import com.example.assessment.activity.MainActivity
import com.example.assessment.databinding.FragmentLockerBinding

class LockerFragment : Fragment() {
    private var _binding: FragmentLockerBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var selectedYearTextView: TextView
    private lateinit var selectedQuarterTextView: TextView
    private lateinit var deliveredPackagesTextView: TextView
    private lateinit var pendingPackagesTextView: TextView




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

        val barSet = listOf(
            "JAN" to 4F,
            "FEB" to 7F,
            "MAR" to 2F,
            "MAY" to 2.3F,
            "APR" to 5F,
            "JUN" to 4F
        )

        val horizontalBarSet = listOf(
            "PORRO" to 5F,
            "FUSCE" to 6.4F,
            "EGET" to 3F
        )

        binding.chart.animation.duration = animationDuration

        binding.chart.animate(barSet)
        mainActivity = activity as MainActivity

        binding.deliveredLayout.setOnClickListener {
            mainActivity.navigateToFragment(DeliveredPackageFragment())   }

        binding.pendingLayout.setOnClickListener {
            mainActivity.navigateToFragment(PendingPackageFragment())
        }

      binding.ibFilter.setOnClickListener {
          showfilter()
      }



    }



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


    private fun applyFilters() {}

}
