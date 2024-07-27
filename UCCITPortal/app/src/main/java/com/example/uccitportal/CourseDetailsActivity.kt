package com.example.uccitportal

import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitportal.databinding.ActivityCourseDetailsBinding

// the class for the course details activity
class CourseDetailsActivity : AppCompatActivity() {

    // Variable for the firestore database
    private lateinit var firestoreDB: FirebaseFirestore

    // Variable for view binding
    private lateinit var binding: ActivityCourseDetailsBinding

    // Declaring a variable to store the course ID
    private lateinit var courseId: String


    // Function that is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflating the layout
        binding = ActivityCourseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the firestore database instance
        firestoreDB = FirebaseFirestore.getInstance()

        // Getting the course ID passed from the previous activity
        courseId = intent.getStringExtra("courseId") ?: ""

        // Loading the course data from the database
        loadCourseData()

        binding.backButton.setOnClickListener {
            // Goind back to the previous screen
            finish()
        }

    }

    // Creating a method to load the course data from the database
    private fun loadCourseData() {
        // Accessing the specific course by its ID
        firestoreDB.collection("courses").document(courseId)
            .get()
            .addOnSuccessListener { document ->
                // Checking if the course exists
                if (document.exists()) {
                    // Setting the retrieved data in the text views
                    binding.code.text = document.getString("code") ?: "N/A"
                    binding.name.text = document.getString("name") ?: "N/A"
                    binding.credits.text = document.getLong("credits")?.toString() ?: "N/A"
                    binding.prerequisites.text = document.getString("prerequisites") ?: "N/A"
                    binding.description.text = document.getString("description") ?: "N/A"
                } else {
                    Toast.makeText(this, "No such course found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                // Handling failure
                Toast.makeText(this, "Failed to load course data: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
