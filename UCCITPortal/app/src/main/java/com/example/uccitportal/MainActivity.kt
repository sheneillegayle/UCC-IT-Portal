package com.example.uccitportal

import android.widget.Toast
import android.net.Uri
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uccitportal.databinding.ActivityMainBinding

// The class for the main activity for the app
class MainActivity : AppCompatActivity() {

    // Variable for HOD email
    private val mail: String = "ithod@ucc.edu.jm"
    
    // Variable for email subject
    private val subject: String = "Inquiry"

    // Variable for view binding
    private lateinit var binding: ActivityMainBinding

    // The OnCreate method for when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting up the view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting a listener for when the faculty directory button is clicked
        binding.facultyButton.setOnClickListener {
            // Creating an intent to go to the activity to view the faculty directory
            val intent = Intent(this, FacultyActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the courses button is pressed
        binding.coursesButton.setOnClickListener {
            // Creating an intent to go to the activity to show the courses
            val intent = Intent(this, CoursesActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the admissions button is pressed
        binding.admissionsButton.setOnClickListener {
            // Creating an intent to go to the activity for admissions
            val intent = Intent(this, AdmissionsActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the facebook button is pressed
        binding.facebookButton.setOnClickListener {
            // Opening the facebook webpage
            val intent = Intent(this, FacebookActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the instagram button is pressed
        binding.instagramButton.setOnClickListener {
            // Opening the instagram webpage
            val intent = Intent(this, InstagramActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the twitter button is pressed
        binding.twitterButton.setOnClickListener {
            // Opening the twitter webpage
            val intent = Intent(this, TwitterActivity::class.java)
            startActivity(intent)
        }

        // Setting a listener for when the email fab button is pressed
        binding.fabEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$mail")
        
                // Adding the subject line to the email intent
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
    
            // Checking if there is an app that can handle the email intent
            if (intent.resolveActivity(packageManager) != null) {
                // Starting the email activity
                startActivity(intent)
            } else {
                // Showing an error
                Toast.makeText(this, "No email app available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
