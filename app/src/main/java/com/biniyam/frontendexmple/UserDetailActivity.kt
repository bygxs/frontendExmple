package com.biniyam.frontendexmple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.biniyam.frontendexmple.databinding.ActivityUserDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserDetailBinding
    lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        showUsers()


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