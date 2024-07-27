package com.example.uccitportal

import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitportal.databinding.ActivityFacultyBinding

// the class for the faculty activity of the app
class FacultyActivity : AppCompatActivity() {

    // Variable for the view binding
    private lateinit var binding: ActivityFacultyBinding

    // The OnCreate method for when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setting up the view binding
        binding = ActivityFacultyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting click listener for the back button
        binding.backButton.setOnClickListener {
            // Finishing the activity and returning to the previous screen
            finish()
        }

        // Setting the click listeners for the emails
        setEmailClickListener(binding.emailNatalie, "deanmst@ucc.edu.jm")
        setEmailClickListener(binding.emailNeil, "itlecturer@ucc.edu.jm")
        setEmailClickListener(binding.emailOtis, "itfaculty@ucc.edu.jm")
        setEmailClickListener(binding.emailSajjad, "srizvi@faculty.ucc.edu.jm")
        setEmailClickListener(binding.emailStephen, "sgentles@faculty.ucc.edu.jm")

        // Setting click listeners for the phone numbers
        setPhoneClickListener(binding.phoneNatalie, "+1-876-838-2408")
        setPhoneClickListener(binding.phoneNeil, "+1-876-880-1589")
        setPhoneClickListener(binding.phoneOtis, "+1-876-218-2935")
        setPhoneClickListener(binding.phoneSajjad, "+92-310-226-6566")
        setPhoneClickListener(binding.phoneStephen, "+1-876-392-0218")
    }

    // Creating a function to set up an email click listener for a faculty member
    private fun setEmailClickListener(textView: TextView, email: String) {
        textView.setOnClickListener {
            // Creating an intent to send an email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            // Checking if there is an app that can handle the email intent
            if (intent.resolveActivity(packageManager) != null) {
                // Starting the email activity
                startActivity(intent)
            } else {
                // Showing an error
                Toast.makeText(this, "There is no email app available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Creating a function to set up a phone click listener for a faculty member
    private fun setPhoneClickListener(textView: TextView, phoneNumber: String) {
        textView.setOnClickListener {
            // Creating an intent to dial a phone number
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            // Starting the activity to dial the number
            startActivity(intent)
        }
    }
}
