package com.example.loginkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.loginkotlin.databinding.ActivitySignUp1Binding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp1Binding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUp1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()

            if (email.isNotEmpty() && (password.isNotEmpty())){
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task -> if (task.isSuccessful){
                    Toast.makeText(this,"SignUp Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity1::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"SignUp Failed", Toast.LENGTH_SHORT).show()
                }
                }
            } else {
                Toast.makeText(this,"Please Enter Email and Password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginRedirectText.setOnClickListener {
            startActivity(Intent(this, LoginActivity1::class.java))
            finish()
        }
    }
}