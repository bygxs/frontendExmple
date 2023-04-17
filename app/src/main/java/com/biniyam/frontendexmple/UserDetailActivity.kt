package com.biniyam.frontendexmple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.biniyam.frontendexmple.databinding.ActivityUserDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserDetailBinding
    lateinit var user: UserModel

    override fun onResume() {
        super.onResume()
        showUsers()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        showUsers()

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)

            intent.putExtra("id", user.id)
            intent.putExtra("name", user.name)
            intent.putExtra("score", user.score)
            intent.putExtra("is_human", user.isHuman)

            startActivity(intent)
        }
        binding.btnDelete.setOnClickListener {
            deletUser()
        }

    }
    fun deletUser(){
        val id = user.id
        CoroutineScope(Dispatchers.IO).launch {
            val res = UserRepository.delteUser(id!!)
            println(res.code())
            println(res.body())
            finish()
        }
    }
    fun showUsers(){
        val userId = intent.getIntExtra("item_id", -1)
        CoroutineScope(Dispatchers.Main).launch {
            user = UserRepository.getUserById(userId)

            binding.tvId.text = "ID: ${user.id}"
            binding.tvName.text = "Name: ${user.name}"
            binding.tvScore.text = "Name: ${user.score}"
            binding.tvIsHuman.text = "Name: ${user.isHuman}"
        }
    }
}