package com.example.saleswebdemo

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_product.*
import org.json.JSONArray

class ProductAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        var cat:String = intent.getStringExtra("cat").toString()
        var url = "${IPAddress().getIP}/SalesWeb/get_items.php?category=$cat"
        var list=ArrayList<Product>()

        var rq:RequestQueue=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null, { response ->
            for (x in 0 until response.length()){
                var id =response.getJSONObject(x).getInt("prod_id")
                var name=response.getJSONObject(x).getString("prod_name");
                var price=response.getJSONObject(x).getDouble("prod_price")
                var img=response.getJSONObject(x).getString("prod_img")
                list.add(Product(id,name,price,img))
            }
            var adp=ProductAdapter(this,list)
            product_rv.layoutManager=LinearLayoutManager(this)
            product_rv.adapter=adp

        }, { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show();
        })

        rq.add(jar)
    }
}