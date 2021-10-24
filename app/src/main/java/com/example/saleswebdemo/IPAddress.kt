package com.example.saleswebdemo

class IPAddress{
    companion object{
        val ip_addr: String = "http://10.0.34.0:78"
        val ip_addr2: String = "http://192.168.43.162:78"
    }
    val getIP get() = ip_addr  // getter for test
}