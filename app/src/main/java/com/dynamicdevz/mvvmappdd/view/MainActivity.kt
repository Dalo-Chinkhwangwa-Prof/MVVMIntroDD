package com.dynamicdevz.mvvmappdd.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dynamicdevz.mvvmappdd.R
import com.dynamicdevz.mvvmappdd.viewmodel.GitViewModel
import java.lang.StringBuilder

//https://api.github.com/users/Dalo-Chinkhwangwa-Prof/repos
class MainActivity : AppCompatActivity() {

    private lateinit var seconds: TextView
    private lateinit var userNameET: EditText
    private lateinit var searchButton: Button

    private val viewModel: GitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seconds = findViewById(R.id.seconds)
        searchButton = findViewById(R.id.search_button)
        userNameET = findViewById(R.id.search_user)

        searchButton.setOnClickListener {

            val username = userNameET.text.toString().trim()
            userNameET.text.clear()
            viewModel.searchRepositories(username)
        }

        viewModel.liveData.observe(this, Observer {
            seconds.text = "Counted $it out of 1000"
        })

        viewModel.gitLiveData.observe(this, {
            val sb = StringBuilder()
            it.forEach{ item ->
                sb.append(item.name).append("\n")
                Log.d("TAG_X", item.name)
            }
            seconds.text = sb.toString()
        })



    }
}












