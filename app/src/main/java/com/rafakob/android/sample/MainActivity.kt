package com.rafakob.android.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rafakob.android.kotlinextensions.consume

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        consume {  }
    }
}
