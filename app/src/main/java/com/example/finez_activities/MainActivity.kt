package com.example.finez_activities


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var logoImageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set your main activity layout
        logoImageView = findViewById(R.id.logoImageView) // Ensure this image view exists in activity_main.xml

        // Apply animation to logoImageView (optional)
        animateLogo()

        // Delayed execution to transition to main content/activity
        val splashDuration = 3000L // 3 seconds
        Handler().postDelayed({
            startActivity(Intent(this, Activity1::class.java))
            finish()
        }, splashDuration)
    }

    private fun animateLogo() {
        // Example of a simple scale animation
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        logoImageView.startAnimation(scaleAnimation)
    }
}
