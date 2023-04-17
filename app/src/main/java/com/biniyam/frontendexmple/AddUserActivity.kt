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


        if (intent.hasExtra("id")){
            binding.etName.setText(intent.getStringExtra("name"))
            binding.etScore.setText(intent.getIntExtra("score",0).toString())
            binding.swIsHuman.isChecked = intent.getBooleanExtra("is_human",true)
            binding.btnAdding.text = "Update User"
        }

        binding.btnAdding.setOnClickListener {
           if (intent.hasExtra("id")){
               updateUser()
           } else
            addUser()
        }
        binding.btnCanceling.setOnClickListener {
            finish()
        }
    }
    fun updateUser(){
         val id  = intent.getIntExtra("id",-1)
        val name = binding.etName.text.toString()
        val score = Integer.parseInt( binding.etScore.text.toString())
        val isHuman = binding.swIsHuman.isChecked

        val updatedUser = UserModel(id,name,score,isHuman)

        CoroutineScope(Dispatchers.IO).launch {
            val res = UserRepository.updateUser(updatedUser)
            println("res.code() = ${res.code()}")
            println("res.body() = ${res.body()}")
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
            println("res.body() = ${res.body()}")

            finish()
        }

    }
}