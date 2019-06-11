package com.rakapermanaputra.popcorn.feature.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rakapermanaputra.popcorn.HomeActivity
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.model.Login
import com.rakapermanaputra.popcorn.model.RequestToken
import com.rakapermanaputra.popcorn.model.Session
import com.rakapermanaputra.popcorn.model.Token
import com.rakapermanaputra.popcorn.model.repository.LoginRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    private lateinit var rToken: Token
    private lateinit var login: Login
    private lateinit var requestToken: RequestToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = LoginRepoImpl(service)
        presenter = LoginPresenter(this, request)
        presenter.getReqToken()


        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val pass = edtPassword.text.toString()
            val token = rToken.requestToken

            requestToken = RequestToken(rToken.requestToken)

            login = Login(pass, token, username)

            presenter.getToken(login)

            presenter.getSession(requestToken)

        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showReqToken(token: Token) {
        rToken = Token(token.expiresAt, token.requestToken, token.success)
        Log.d("Data", "Get Request Token : " + token.requestToken)
    }

    override fun showToken(token: Token) {
        Log.d("Data", "Token : " + token.requestToken)
        Log.d("Data", "Token Acc ? " + token.success)
        startActivity<HomeActivity>()
    }

    override fun showSessionId(session: Session) {
        Log.d("Data", "Session id : " + session.sessionId)
    }

}
