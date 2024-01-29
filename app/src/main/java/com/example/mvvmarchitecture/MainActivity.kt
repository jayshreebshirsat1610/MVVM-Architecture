package com.example.mvvmarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitecture.Adapter.UserAdapter
import com.example.mvvmarchitecture.Model.User
import com.example.mvvmarchitecture.ViewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var userViewModel: UserViewModel
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var save:Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.btnAdd)
        userAdapter = UserAdapter(this,ArrayList<User>())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)
        })

        floatingActionButton.setOnClickListener{
            openDialog()
        }

    }
    private fun openDialog(){
        builder=AlertDialog.Builder(this)
        var itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.dialog,null)
        dialog=builder.create()
        dialog.setView(itemView)
        name=itemView.findViewById(R.id.etName)
        age=itemView.findViewById(R.id.etAge)
        save= itemView.findViewById(R.id.btnSave)
        save.setOnClickListener {
            saveDataIntoRommDatabase()
        }
        dialog.show()
    }
    private fun saveDataIntoRommDatabase(){
        val getName= name.text.toString().trim()
        val getAge = age.text.toString().trim()
        if(!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge)){
            userViewModel.insert(this,User(getName,Integer.parseInt(getAge)))
            dialog.dismiss()

        }else{
            Toast.makeText(applicationContext,"Please Fill the all the fields",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }
}