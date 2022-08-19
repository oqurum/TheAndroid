package io.oqurum.reader

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import io.oqurum.reader.ui.login.LocalServerSelectActivity


class MainActivity : AppCompatActivity() {
    companion object {
        var server_ip = "0.0.0.0"
    }
        @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, LocalServerSelectActivity::class.java)
        this.startActivity(intent)
    }
}