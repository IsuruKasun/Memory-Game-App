package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import com.example.memorygame.utils.DEFAULT_ICONS


class MainActivity : AppCompatActivity() {

    // late in it = It tells the compiler that the variable will be initialized at a later point before its first usage.
    // var = its value can be changed after it's initialized.
    private lateinit var RecyclerView: RecyclerView
    private lateinit var textViewMoves: TextView
    private lateinit var textViewPairs: TextView

    private var boardSize: BoardSize = BoardSize.HARD


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecyclerView = findViewById(R.id.RecyclerView) // After this line executes, we can work with the recyclerView variable
        textViewMoves = findViewById(R.id.textViewMoves)
        textViewPairs = findViewById(R.id.textViewPairs)

// getting random images from DEFAULT_ICONS. 2 images should be same
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomImages = (chosenImages+chosenImages).shuffled()

// getting data to RecycleView and making two-column grid.
        RecyclerView.adapter = MemoryBoardAdaptor(this,boardSize, randomImages) //The adapter is responsible for providing the data and creating the views to be displayed in the RecyclerView. this = the current instance of the containing class, typically an activity
        RecyclerView.setHasFixedSize(true) // The purpose of this method is to indicate whether the size of the RecyclerView is fixed or can change due to size of phone or tablets.
        RecyclerView.layoutManager = GridLayoutManager(this, boardSize.getWidth()) // This is a property of the RecyclerView class that represents the layout manager responsible for measuring and positioning items within the RecyclerView.



    }
}