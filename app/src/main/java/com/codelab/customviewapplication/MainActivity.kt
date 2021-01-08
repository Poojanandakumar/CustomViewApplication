package com.codelab.customviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity() : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val barGraph:BarGraph=findViewById(R.id.bar_graph)
        val button:Button=findViewById(R.id.animate_button)
        button.setOnClickListener {
            barGraph.animateGraph()
        }


    }

}