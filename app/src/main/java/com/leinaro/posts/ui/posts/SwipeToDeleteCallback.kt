package com.leinaro.posts.ui.posts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteCallback(private val adapter: PostsAdapter, val context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

    private val icon: Drawable? =
        ContextCompat.getDrawable(context, android.R.drawable.ic_menu_delete)
    private val background: ColorDrawable = ColorDrawable(Color.RED)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //    TODO("Not yet implemented")
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        adapter.deleteItem(position, viewHolder)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView: View = viewHolder.itemView

   //     setBackground(dX, itemView, c)
        setRemoveIcon(itemView, dX,c)
    }

    private fun setBackground(
        dX: Float,
        itemView: View,
        c: Canvas
    ) {
        val backgroundCornerOffset = 20
        when {
            dX > 0 -> { // Swiping to the right
                background!!.setBounds(
                    itemView.left, itemView.top,
                    itemView.left + dX.toInt() + backgroundCornerOffset,
                    itemView.bottom
                )
            }
            dX < 0 -> { // Swiping to the left
                background!!.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top, itemView.right, itemView.bottom
                )
            }
            else -> { // view is unSwiped
                background!!.setBounds(0, 0, 0, 0)
            }
        }
        background.draw(c)
    }

    private fun setRemoveIcon(itemView: View, dX: Float, c: Canvas){
        val backgroundCornerOffset = 20
        val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight

        when {
            dX > 0 -> { // Swiping to the right

                val iconLeft = itemView.left + iconMargin
                val iconRight = iconLeft+ icon.intrinsicWidth

                if (dX > 100){
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                } else {
                    icon.setBounds(0, 0, 0, 0)
                }
                background.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.left + dX.toInt() + backgroundCornerOffset,
                    itemView.bottom
                )
            }
            dX < 0 -> { // Swiping to the left
                val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                val iconRight = itemView.right - iconMargin

                if (dX < -100){
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                } else {
                    icon.setBounds(0, 0, 0, 0)
                }
                background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
            }
            else -> { // view is unSwiped
                icon.setBounds(0, 0, 0, 0)
                background.setBounds(0, 0, 0, 0)
            }
        }

       background.draw(c)
       icon.draw(c)
    }
}
