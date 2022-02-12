package com.example.lugaresv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugaresv2.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //metodo para hacer el login
        binding.btLogin.setOnClickListener {
            haceLogin()
        }

        //metodo para hacer el login
        binding.btRegister.setOnClickListener {
            haceRegister()
        }
    }

    private fun haceRegister() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etEmail.text.toString()

        //hacer el registro
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.d("Creando Usuario", "Registrado")
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Creando Usuario", "Error")
                    Toast.makeText(baseContext,"Error", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }

    }

    private fun actualiza(user: FirebaseUser?) {
        if (null != user) {

            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    //Esto es para que no pide credenciales si ya el usuario esta loggueado solo las pida si cerro la sesion
    public override fun onStart(){
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }

    private fun haceLogin() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etEmail.text.toString()

        //hacer el login
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.d("Autenticando", "Autenticado")
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Autenticando", "Error")
                    Toast.makeText(baseContext,"Error", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }
}