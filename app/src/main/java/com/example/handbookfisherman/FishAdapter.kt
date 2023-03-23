package com.example.handbookfisherman

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FishAdapter(listArray: ArrayList<ListItemInfo>, context: Context) :
    RecyclerView.Adapter<FishAdapter.ViewHolder>() {

    var listArrayrv = listArray
    var contextrv = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvContent = view.findViewById<TextView>(R.id.tv_content_item)
        val tvImage = view.findViewById<ImageView>(R.id.im_view_item)

        fun bind(listItem: ListItemInfo, context: Context) {
            val textCont = listItem.contentTxt.substring(0,40) + "..."
            tvTitle.text = listItem.titleTxt
            tvContent.text = textCont
            tvImage.setImageResource(listItem.imageId)
            itemView.setOnClickListener {
                Toast.makeText(context, "Pressed : ${tvTitle.text}", Toast.LENGTH_SHORT).show()
                val i = Intent(context,ContentActivity::class.java).apply {
                    putExtra("title",tvTitle.text.toString())
                    putExtra("content",listItem.contentTxt)
                    putExtra("image",listItem.imageId)
                }
                context.startActivity(i)
            }
        }

    } //через viewHolder заполняем все наши элементы

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextrv)
        return ViewHolder(inflater.inflate(R.layout.item_layout_rv, parent, false))
    }
    // рисуем - надуваем itemLayout, передаем в ViewHolder, чтобы искать и заполнять нужные элементы

    override fun getItemCount(): Int {
        return listArrayrv.size
    }
    // передал адаптеру сколько элементов в нашем массиве size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItemB = listArrayrv[position]
        holder.bind(listItemB, contextrv)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listArray: List<ListItemInfo>){
        listArrayrv.clear()
        listArrayrv.addAll(listArray)
        notifyDataSetChanged()
        //обновились данные сообщаем адаптеру
    }
    // фун для обновления адаптера
}