package com.example.android4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.android4a.R
import com.example.android4a.presentation.pokedex.PokedexActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    val intent = Intent(this, PokedexActivity::class.java)
                    startActivity(intent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Votre nom de compte ou mot de passe est incorrect. Veuillez vérifier vos identifiants puis réessayez")
                        .setPositiveButton("Ok") {dialog, which -> dialog.dismiss() }
                        .show()
                }
                SignUpError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Cet identifiant est déjà utilisé")
                        .setPositiveButton("Ok") {dialog, which -> dialog.dismiss() }
                        .show()
                }
            }
        })
        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }

        create_account_button.setOnClickListener {
            mainViewModel.onClickedSignUp(login_edit.text.toString().trim(), password_edit.text.toString())
        }

        /*
        toolbar.setTitle("POKEDEX")
        setSupportActionBar(toolbar)
         */
    }
}