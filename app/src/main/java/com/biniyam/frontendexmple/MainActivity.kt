package com.biniyam.frontendexmple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import com.biniyam.frontendexmple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onResume() {
        super.onResume()
        showUsers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        showUsers()

        binding.btnShow.setOnClickListener {
            showUsers()
        }

        binding.btnAdd.setOnClickListener {
            intent = Intent( this, AddUserActivity::class.java)
            startActivity(intent)
        }
        
        binding.lvUsers.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent( this, UserDetailActivity::class.java)
            val selectedUser = parent.getItemAtPosition(position) as UserModel
            intent.putExtra("item_id", selectedUser.id)
            startActivity(intent)
        }
    }
    fun showUsers(){
        UserRepository.getUsers {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,it)
            binding.lvUsers.adapter = adapter
        }
    }
}