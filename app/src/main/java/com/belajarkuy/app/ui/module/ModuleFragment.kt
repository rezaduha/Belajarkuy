package com.belajarkuy.app.ui.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajarkuy.app.data.model.ModuleResponse
import com.belajarkuy.app.data.model.ModulesItem
import com.belajarkuy.app.data.presenter.MainPresenter
import com.belajarkuy.app.databinding.FragmentModuleBinding
import com.belajarkuy.app.view.GeneralView

class ModuleFragment : Fragment(), ModuleAdapter.Listener, GeneralView {

    private var _binding: FragmentModuleBinding? = null
    private val binding get() = _binding!!
    private lateinit var moduleAdapter: ModuleAdapter
    private lateinit var presenter: MainPresenter
    private var moduleList: MutableList<ModulesItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentModuleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        loadDataModule()
    }

    private fun setRecyclerView() {
        moduleAdapter = ModuleAdapter(moduleList, this)
        with(binding.rvModule) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moduleAdapter
        }
    }

    private fun loadDataModule() {
        presenter = MainPresenter(this, requireContext())
        presenter.getAllModule()
    }

    override fun onClick(modules: ModulesItem) {
        Toast.makeText(context, modules.id.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun success(response: Any) {
        val responseData = response as ModuleResponse
        moduleList.clear()
        moduleList.addAll(responseData.modules)
        moduleAdapter.notifyDataSetChanged()
    }

    override fun error(error: Throwable?) {

    }

    override fun showLoading() {
        binding.rvModule.visibility = View.INVISIBLE
        binding.progressModule.startShimmer()
    }

    override fun hideLoading() {
        binding.rvModule.visibility = View.VISIBLE
        binding.progressModule.stopShimmer()
        binding.progressModule.visibility = View.GONE
    }

    override fun empty() {
        /**
        * ALWAYS NOT EMPTY
        **/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}