package com.dicoding.naufal.myfirebasecloudmessaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dicoding.naufal.myfirebasecloudmessaging.databinding.ActivityMainBinding
import com.dicoding.naufal.myfirebasecloudmessaging.service.MyFirebaseMessagingService
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubscribe.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("news").addOnSuccessListener {
                Toast.makeText(this, "Subscribe berhasil", Toast.LENGTH_SHORT).show()
            }
            val msg = getString(R.string.msg_subscribed)
            Log.d(TAG, msg)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }

        binding.btnToken.setOnClickListener {
            val deviceToken = MyFirebaseMessagingService
            val msg = getString(R.string.msg_token_fmt, deviceToken)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Refreshed token: $deviceToken")
            FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
                val msg = getString(R.string.msg_token_fmt, deviceToken)
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Refreshed token: $deviceToken")
            }
        }
    }
}