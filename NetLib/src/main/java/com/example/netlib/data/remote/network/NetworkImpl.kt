package com.example.netlib.data.remote.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.netlib.domain.network.Network

internal class NetworkImpl(private val context: Context): Network {
    override fun isConnected(): Boolean {
        val сonnectivityManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = сonnectivityManager.activeNetwork?: return false
        val capabilities = сonnectivityManager.getNetworkCapabilities(network)?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}