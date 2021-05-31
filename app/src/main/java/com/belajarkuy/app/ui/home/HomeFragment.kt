package com.belajarkuy.app.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajarkuy.app.data.model.CompetencyItem
import com.belajarkuy.app.data.model.CompetencyResponse
import com.belajarkuy.app.data.presenter.MainPresenter
import com.belajarkuy.app.databinding.FragmentHomeBinding
import com.belajarkuy.app.view.GeneralView
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class HomeFragment : Fragment(), GeneralView, RecommendationAdapter.Listener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var recommendationAdapter: RecommendationAdapter

    private lateinit var presenter: MainPresenter
    private var recommendationList: MutableList<CompetencyItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSignInAccount()
        setRecyclerView()
        loadDataCompetency()
    }

    private fun getSignInAccount() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())
        if (acct != null) {
            with(binding) {
                tvName.text = acct.displayName
                Glide.with(requireActivity())
                    .load(acct.photoUrl)
                    .into(avatar)
            }
        }
    }

    private fun setRecyclerView() {
        recommendationAdapter = RecommendationAdapter(recommendationList, this)
        with(binding.rvRecommendation) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = recommendationAdapter
        }
    }

    private fun loadDataCompetency() {
        presenter = MainPresenter(this, requireContext())
        presenter.getCompetency()
    }

    override fun onClick(modules: CompetencyItem) {
    }

    override fun success(response: Any) {
        val data = response as CompetencyResponse

        val labels = arrayListOf<String>()
        data.results.forEach {
            labels.add(it.subject)
        }

        val progress = arrayListOf<RadarEntry>()
        data.results.forEach {
            progress.add(RadarEntry(it.progress.toFloat()))
        }

        showChartCompetency(labels, progress)
        recommendationList.clear()
        recommendationList.addAll(data.results)
        recommendationAdapter.notifyDataSetChanged()
    }

    private fun showChartCompetency(labels: ArrayList<String>, data: ArrayList<RadarEntry>) {
        val dataProgress = RadarDataSet(data, "")
        dataProgress.color = Color.BLUE

        for (i in data) {
            if (i.value == 0F) {
                binding.rdcProgress.setNoDataText("No chart data available.")
            } else {
                val radarData = RadarData()
                radarData.addDataSet(dataProgress)
                radarData.setDrawValues(false)
                val xAxis = binding.rdcProgress.xAxis
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                binding.rdcProgress.data = radarData
                binding.rdcProgress.description.isEnabled = false
                binding.rdcProgress.legend.isEnabled = false
                binding.rdcProgress.invalidate()
            }
        }
    }

    override fun error(error: Throwable?) {

    }

    override fun showLoading() {
        binding.cvCompetency.visibility = View.INVISIBLE
        binding.rvRecommendation.visibility = View.INVISIBLE
        binding.progressHome.startShimmer()
    }

    override fun hideLoading() {
        binding.progressHome.stopShimmer()
        binding.progressHome.visibility = View.GONE
        binding.cvCompetency.visibility = View.VISIBLE
        binding.rvRecommendation.visibility = View.VISIBLE
    }

    override fun empty() {
        binding.viewEmpty.root.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}