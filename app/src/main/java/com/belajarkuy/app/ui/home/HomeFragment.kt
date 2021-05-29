package com.belajarkuy.app.ui.home

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

class HomeFragment : Fragment(), GeneralView, RecommendationAdapter.Listener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecyclerView()
        loadDataCompetency()
    }

    private fun setRecyclerView() {
        recommendationAdapter = RecommendationAdapter(recommendationList, this)
        with(binding.rvRecommendation) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recommendationAdapter
        }
    }

    private fun loadDataCompetency() {
        presenter = MainPresenter(this, requireContext())
        presenter.getCompetency(1)
    }

    override fun onClick(modules: CompetencyItem) {

    }

    override fun success(response: Any) {
        val data = response as CompetencyResponse
        val labels = ArrayList<String>()
//        val label = data.results.forEach { it.subject }
        data.results.forEach {
            labels.add(it.subject)
        }
    }

    override fun error(error: Throwable?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}