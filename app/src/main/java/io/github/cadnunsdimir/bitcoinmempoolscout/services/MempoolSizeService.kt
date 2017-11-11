package io.github.cadnunsdimir.bitcoinmempoolscout.services

import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by tiagosilva on 11/11/17.
 */
class MempoolSizeService : AsyncTask<Void, Void, String>() {

    var callback: MempoolSizeServiceCallback? = null;


    override fun doInBackground(vararg params: Void?): String? {
        val client = OkHttpClient()

        var request = Request.Builder()
                .url("https://blockchain.info/q/unconfirmedcount")
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "fbfc4d38-abe7-91bd-effd-e10bd9e97dbc")
                .build()

        var response = client.newCall(request).execute()

        return response.body()?.string()
    }
    override fun onPreExecute() {
        super.onPreExecute()
    }
    override fun onPostExecute(result: String?) {
        callback?.done(result)
    }
}

interface MempoolSizeServiceCallback{
    fun done(result: String?)
}