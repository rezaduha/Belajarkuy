package com.belajarkuy.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.belajarkuy.app.MainActivity
import com.belajarkuy.app.R
import com.belajarkuy.app.data.model.AuthRequest
import com.belajarkuy.app.data.presenter.MainPresenter
import com.belajarkuy.app.databinding.ActivityLoginBinding
import com.belajarkuy.app.utils.Preference
import com.belajarkuy.app.view.GeneralView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity(), GeneralView {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: MainPresenter
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        const val RC_SIGN_IN = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter(this)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnContinueWGoogle.setOnClickListener {
            signIn()
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Preference.saveToken(this, account?.idToken!!)
            val authData = AuthRequest(account.displayName, account.id, account.photoUrl.toString(),  account.email, account.idToken)
            presenter.continueWithGoogle(authData)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        } catch (e: ApiException) {
            Log.w("onFailure", "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun success(response: Any) {}

    override fun error(error: Throwable?) {}

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun empty() {}
}