package io.oqurum.reader.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.oqurum.reader.MainActivity.Companion.server_ip
import io.oqurum.reader.databinding.ActivityLocalServerSelectBinding

import io.oqurum.reader.Webby

class LocalServerSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocalServerSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocalServerSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ipaddress = binding.ipaddress
        val login = binding.login

        login.setOnClickListener {
            server_ip = ipaddress.text.toString()

            val intent = Intent(this, Webby::class.java)
            this.startActivity(intent)
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}