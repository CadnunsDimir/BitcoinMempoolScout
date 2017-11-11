package io.github.cadnunsdimir.bitcoinmempoolscout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.cadnunsdimir.bitcoinmempoolscout.services.MempoolSizeService
import io.github.cadnunsdimir.bitcoinmempoolscout.services.MempoolSizeServiceCallback
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit.SECONDS

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btUpdate.setOnClickListener({
            view -> updateMempoolSize()
        })
        updateMempoolSize()
    }

    private fun updateMempoolSize() {

        var service = MempoolSizeService()

        service.callback = object: MempoolSizeServiceCallback{
            override fun done(result: String?) {
                tvMempoolSize.text = result
            }
        }

        service.execute()
    }
}
