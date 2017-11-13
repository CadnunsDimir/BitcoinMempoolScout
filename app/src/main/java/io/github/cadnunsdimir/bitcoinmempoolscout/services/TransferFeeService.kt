package io.github.cadnunsdimir.bitcoinmempoolscout.services

import io.github.cadnunsdimir.bitcoinmempoolscout.models.TransferFee
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * Created by tiagosilva on 12/11/17.
 */
//https://bitcoinfees.earn.com/api/v1/fees/recommended
class TransferFeeService : AsyncTaskBase<Void, Void, TransferFee>() {

    var url = "https://bitcoinfees.earn.com/api/v1/fees/recommended"
    var FASTEST_FEE = "fastestFee"
    var HALFHOUR_FEE = "halfHourFee"
    val HOUR_FEE = "hourFee"



    override fun doInBackground(vararg params: Void?): TransferFee? {
        val client = OkHttpClient()

        var request = Request.Builder()
                .url(url)
                .get()
                .build()

        var response = client.newCall(request).execute()

        var json = JSONObject(response.body()?.string())

        return TransferFee(json.getInt(FASTEST_FEE), json.getInt(HALFHOUR_FEE), json.getInt(HOUR_FEE), 229)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: TransferFee) {
        callback?.invoke(result)
    }
}