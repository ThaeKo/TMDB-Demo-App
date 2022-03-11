package com.mm.samplemovieapp.activities

import android.os.Bundle
import com.mm.samplemovieapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity() {

    lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickEvent()
    }

    private fun clickEvent() {
        binding.apply {
            btnCreateAccount.setOnClickListener {
                startActivity(CreateAccountActivity.newInstance(this@WelcomeActivity))
            }
            ivClose?.setOnClickListener {
                finish()
            }
        }
    }

}