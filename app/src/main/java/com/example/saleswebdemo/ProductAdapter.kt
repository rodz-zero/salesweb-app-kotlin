package com.example.saleswebdemo

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentActivity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductAdapter(var context:Context, var list:ArrayList<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v:View = LayoutInflater.from(context).inflate(R.layout.product_row,parent,false)
        return ProductHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductHolder).bind(list[position].name,list[position].price,list[position].photo,list[position].id)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ProductHolder(productView:View) : RecyclerView.ViewHolder(productView)
    {
        fun bind(n:String, p:Double, u:String, item_id:Int)
        {
            itemView.prod_name.text = n
            itemView.prod_price.text = p.toString()
            var web = "${IPAddress().getIP}/SalesWeb/resources/$u"
            web = web.replace(" ","%20")
            Picasso.get().load(web).into(itemView.prod_img)
            itemView.setOnClickListener{
                UserInfo.itemId = item_id
                var obj=QtyFragment()
                var manager=(itemView.context as FragmentActivity).supportFragmentManager
                obj.show(manager,"Qty")

            }


        }
    }


}