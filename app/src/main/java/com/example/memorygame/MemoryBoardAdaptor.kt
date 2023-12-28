package com.example.memorygame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.models.BoardSize
import kotlin.math.min


/*
This class represents a custom adapter (MemoryBoardAdaptor) for a RecyclerView in an Android app, specifying the context,
the number of pieces, and defining methods for creating and binding views.
 */
class MemoryBoardAdaptor(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cardImages: List<Int> // int represents one of the ic images
):
    RecyclerView.Adapter<MemoryBoardAdaptor.ViveHolder>() {

    companion object{
        private const val MARGIN_SIZE = 10
        private const val TAG = "MemoryBoardAdapter"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoryBoardAdaptor.ViveHolder {
        val cardWith = parent.width/boardSize.getWidth() - (2* MARGIN_SIZE)// adjusting the width and height according to the RecycleView
        val cardHeight = parent.height/boardSize.getHeight() - (2* MARGIN_SIZE)// adjusting the width and height according to the RecycleView
        val cardSideLength = min(cardWith,cardHeight)
        val view = LayoutInflater.from(context).inflate(
            R.layout.memory_card,
            parent,
            false
        ) // The newly created view is not attached to the parent ViewGroup (the RecyclerView in this case) immediately (false is passed as the last parameter). The RecyclerView will handle attaching it later.
/*
this code is adjusting the width and height of a CardView within each item of the RecyclerView to ensure that
the cards are displayed as square elements with dimensions specified by the cardSideLength variable.
 */
        val layoutParams = view.findViewById<CardView>(R.id.MemoryCardView).layoutParams as ViewGroup.MarginLayoutParams // Retrieves the layout parameters of the CardView. Layout parameters define how the view should be arranged within its parent. The as keyword is used to cast or convert the layoutParams from its original type to ViewGroup.MarginLayoutParams.
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViveHolder(view)
    }

    override fun onBindViewHolder(holder: ViveHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = boardSize.numCards


    inner class ViveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

// logging the position when the button is clicked in real-time.
        fun bind(position: Int) {
            imageButton.setImageResource(cardImages[position])
            imageButton.setOnClickListener {
                Log.i(TAG, "Clicked on position $position")// look at the Logcat and search MemoryBoardAdapter
            }
        }

    }

}









