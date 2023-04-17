package com.biniyam.frontendexmple

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.biniyam.frontendexmple.databinding.ActivityAddUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddUserActivity : AppCompatActivity() {

    lateinit var  binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdding.setOnClickListener {
            addUser()
        }
        binding.btnCanceling.setOnClickListener {
            finish()
        }
    }
    fun addUser(){
        val name = binding.etName.text.toString()
        val score = Integer.parseInt( binding.etScore.text.toString())
        val isHuman = binding.swIsHuman.isChecked

        val user = UserModel(null, name, score, isHuman)

        CoroutineScope(Dispatchers.IO).launch {
            val res = UserRepository.addUser(user)

            println("res.code() = ${res.code()}")
         //   println("res.code() = ${res.code()}")

            finish()
        }

    }
}