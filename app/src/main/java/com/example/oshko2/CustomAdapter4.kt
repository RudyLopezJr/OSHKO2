package com.example.oshko2

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.security.AccessController.getContext
import java.text.FieldPosition

class CustomAdapter4 : RecyclerView.Adapter<CustomAdapter4.ViewHolder>(){

    val sizeProducts = products.myFavorites.size

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick (position: Int)
        fun onImageAddClick (position: Int, button : ImageView , text : TextView )
        fun onImageDeleteClick (position: Int, button : ImageView , text : TextView )
        fun onStarSelected (position: Int, button : ImageView)
        fun onButtonSelected(position: Int, text : TextView)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v, mListener)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.productName.text = products.myFavorites[i].tittle
        viewHolder.productDescription.text = products.myFavorites[i].description
        viewHolder.itemImage.setImageResource(products.myFavorites[i].image)
    }

    override fun getItemCount(): Int {
        return sizeProducts
    }

    inner class ViewHolder(itemView: View , listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var productName: TextView
        var plus: ImageView
        var delete: ImageView
        var productDescription: TextView
        var quantity: TextView
        var star : ImageView
        var buttonShopping : Button


        init{
            itemImage = itemView.findViewById(R.id.item_image)
            productName = itemView.findViewById(R.id.name_product)
            plus = itemView.findViewById(R.id.addButton)
            delete = itemView.findViewById(R.id.deleteButton)
            productDescription = itemView.findViewById(R.id.description_product)
            quantity = itemView.findViewById(R.id.quantityToAdd)
            star = itemView.findViewById(R.id.favoriteIcon)
            buttonShopping = itemView.findViewById(R.id.buttonShoppingCart)

            buttonShopping.context.resources?.getColor(R.color.auxiliarColor2)

            itemView.setOnClickListener{
                listener.onItemClick(absoluteAdapterPosition)
            }

            plus.setOnClickListener{
                listener.onImageAddClick(absoluteAdapterPosition, plus, quantity)
            }

            delete.setOnClickListener{
                listener.onImageDeleteClick(absoluteAdapterPosition, delete , quantity)
            }

            star.setOnClickListener{
                listener.onStarSelected(absoluteAdapterPosition, star)
            }

            buttonShopping.setOnClickListener{
                listener.onButtonSelected(absoluteAdapterPosition, quantity)
            }

        }

    }
}