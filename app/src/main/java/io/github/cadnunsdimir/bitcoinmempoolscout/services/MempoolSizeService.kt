package io.github.cadnunsdimir.bitcoinmempoolscout.services

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by tiagosilva on 11/11/17.
 */
class MempoolSizeService : AsyncTask<Void, Void, String>() {

    private var callback: ((String) -> Unit)? = null

    fun setOnFinish(_callback: (String) -> Unit){
        callback = _callback
    }

    override fun doInBackground(vararg params: Void?): String? {
        val client = OkHttpClient()

        var request = Request.Builder()
                .url("https://blockchain.info/q/unconfirmedcount")
                .get()
                .build()

        var response = client.newCall(request).execute()

        return response.body()?.string()
    }
    override fun onPreExecute() {
        super.onPreExecute()
    }
    override fun onPostExecute(result: String) {
        callback?.invoke(result)
    }
}
