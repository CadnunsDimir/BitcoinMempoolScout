package io.github.cadnunsdimir.bitcoinmempoolscout.services

import io.github.cadnunsdimir.bitcoinmempoolscout.models.TransferFee
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * Created by tiagosilva on 12/11/17.
 */
class BTCtoBRLService: AsyncTaskBase<Void, Void, Double>() {

    var url = "http://dolarhoje.com/bitcoin-hoje/"

    override fun doInBackground(vararg params: Void?): Double? {
        val client = OkHttpClient()

        var request = Request.Builder()
                .url(url)
                .get()
                .build()

        var response = client.newCall(request).execute()

        var btcPrice = response.body()?.string()?.split("id=\"nacional\" value=\"")?.get(1)?.split("\"/>")?.get(0)?.replace(",",".")?.toDouble()

        return btcPrice
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: Double) {
        callback?.invoke(result)
    }
}