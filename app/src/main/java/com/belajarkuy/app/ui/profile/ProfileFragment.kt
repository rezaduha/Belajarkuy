package com.belajarkuy.app.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajarkuy.app.data.model.ArticlesItem
import com.belajarkuy.app.data.model.NewsResponse
import com.belajarkuy.app.data.presenter.MainPresenter
import com.belajarkuy.app.databinding.FragmentProfileBinding
import com.belajarkuy.app.view.GeneralView
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class ProfileFragment : Fragment(), NewsAdapter.Listener, GeneralView {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var presenter: MainPresenter
    private var newsList: MutableList<ArticlesItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUserProfile()
        setRecyclerView()
        loadDataNews()
    }

    private fun setRecyclerView() {
        newsAdapter = NewsAdapter(newsList, this)
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun loadDataNews() {
        presenter = MainPresenter(this, requireContext())
        presenter.getNews()
    }

    private fun showUserProfile() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val acct = GoogleSignIn.getLastSignedInAccount(requireContext())
        if (acct != null) {
            with(binding) {
                tvName.text = acct.displayName
                tvId.text = acct.id
                Glide.with(requireActivity())
                    .load(acct.photoUrl)
                    .into(avatar)
            }
        }
    }

    override fun success(response: Any) {
        val responseData = response as NewsResponse
        newsList.clear()
        newsList.addAll(responseData.articles)
        newsAdapter.notifyDataSetChanged()
    }

    override fun error(error: Throwable?) {}

    override fun showLoading() {
        binding.rvNews.visibility = View.INVISIBLE
        binding.progressProfile.startShimmer()
    }

    override fun hideLoading() {
        binding.rvNews.visibility = View.VISIBLE
        binding.progressProfile.stopShimmer()
        binding.progressProfile.visibility = View.GONE
    }

    override fun empty() {}

    override fun onClick(news: ArticlesItem) {
        goToUri(news.url)
    }

    private fun goToUri(url: String) {
        val uri = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}