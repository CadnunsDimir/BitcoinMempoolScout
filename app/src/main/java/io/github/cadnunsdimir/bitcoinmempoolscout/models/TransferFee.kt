package io.github.cadnunsdimir.bitcoinmempoolscout.models

/**
 * Created by tiagosilva on 12/11/17.
 */
data class TransferFee (val fastestFee: Int,val halfHourFee: Int,val hourFee : Int, val bytesPerTransation: Int)