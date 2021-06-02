package com.belajarkuy.app.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.belajarkuy.app.data.model.DetailModuleResponse
import com.belajarkuy.app.data.model.QuestionsItem
import com.belajarkuy.app.data.presenter.MainPresenter
import com.belajarkuy.app.databinding.ActivityQuizBinding
import com.belajarkuy.app.ui.home.HomeFragment
import com.belajarkuy.app.view.GeneralView
import java.util.concurrent.TimeUnit

class QuizActivity : AppCompatActivity(), GeneralView {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var quizAdapter: QuizAdapter
    private lateinit var presenter: MainPresenter
    private var quizList: MutableList<QuestionsItem> = mutableListOf()

    companion object {
        const val EXTRA_ID = "extra_id"
        var moduleId: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moduleId = intent.getIntExtra(EXTRA_ID, 0)
        setRecyclerView()
        loadDataQuiz()

        binding.btnFinish.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("finish", "progress")
            val fragObjects = HomeFragment()
            fragObjects.arguments = bundle
            finish()
        }

        val timer = object: CountDownTimer(200000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
            }

            override fun onFinish() {
                // post all answers to backend
                finish()
            }
        }
        timer.start()
    }

    private fun setRecyclerView() {
        quizAdapter = QuizAdapter(quizList)
        with(binding) {
            vpQuestions.adapter = quizAdapter
            ibPrev.setOnClickListener {
                vpQuestions.currentItem = vpQuestions.currentItem - 1
            }
            ibNext.setOnClickListener {
                vpQuestions.currentItem = vpQuestions.currentItem + 1
            }
        }
    }

    private fun loadDataQuiz() {
        presenter = MainPresenter(this)
        presenter.getModuleById(moduleId)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun success(response: Any) {
        val data = response as DetailModuleResponse
        quizList.clear()
        quizList.addAll(data.questions)
        quizAdapter.notifyDataSetChanged()
    }

    override fun error(error: Throwable?) {}

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun empty() {}
}