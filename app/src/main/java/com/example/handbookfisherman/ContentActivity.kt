package com.example.handbookfisherman

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_layout.*

class ContentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)
        tv_title_content.text = intent.getStringExtra("title")
        tv_content_content.text = intent.getStringExtra("content")
        im_view_content.setImageResource(intent.getIntExtra("image",R.drawable.som))
    }
}