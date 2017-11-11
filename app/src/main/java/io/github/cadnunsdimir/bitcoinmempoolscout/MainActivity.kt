package io.github.cadnunsdimir.bitcoinmempoolscout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.cadnunsdimir.bitcoinmempoolscout.services.MempoolSizeService
import kotlinx.android.synthetic.main.activity_main.*

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
        service.setOnFinish ({ result -> tvMempoolSize.text = result})
        service.execute()
    }
}


