package io.github.cadnunsdimir.bitcoinmempoolscout

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.cadnunsdimir.bitcoinmempoolscout.services.BTCtoBRLService
import io.github.cadnunsdimir.bitcoinmempoolscout.services.MempoolSizeService
import io.github.cadnunsdimir.bitcoinmempoolscout.services.TransferFeeService
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btUpdate.setOnClickListener {
            updateStats()
        }
        updateStats()
    }

    private fun updateStats(){
        updateMempoolSize()
        updateFee()
        updateBTCPrice()
    }

    private fun updateMempoolSize() {
        var service = MempoolSizeService()
        service.setOnFinish { result -> tvMempoolSize.text = result}
        service.execute()
    }

    private fun updateFee(){
        var service = TransferFeeService()
        service.setOnFinish { result ->
            kotlin.run {
                val satochi =  0.00000001
                val satochi_mBTC =  1000
                val exchangeFactor = satochi  * satochi_mBTC
                val currency = "mBTC"
                val format = DecimalFormat("0.00000")

                val fastest = format.format(result.fastestFee * result.bytesPerTransation * exchangeFactor)
                val halfHour = format.format(result.halfHourFee * result.bytesPerTransation * exchangeFactor)
                val hour = format.format(result.hourFee * result.bytesPerTransation * exchangeFactor)

                tvFastestFee.text = "Maior taxa possível/mais rapida : $fastest $currency"
                tvHalfHourFee.text = "Até 30min: $halfHour $currency"
                tvHourFee.text = "Até 1h: $hour $currency"
            }
        }
        service.execute()
    }

    private fun updateBTCPrice(){
        var service = BTCtoBRLService()
        service.setOnFinish { d -> tvBtcPriceBRL.text = "Preço BTC : R$ $d" }
        service.execute()
    }

}


