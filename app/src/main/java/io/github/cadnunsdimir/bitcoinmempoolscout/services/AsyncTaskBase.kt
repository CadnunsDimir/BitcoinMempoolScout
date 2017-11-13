package io.github.cadnunsdimir.bitcoinmempoolscout.services

import android.os.AsyncTask

/**
 * Created by tiagosilva on 12/11/17.
 */
abstract class AsyncTaskBase<T1,T2, TReturn> : AsyncTask<T1,T2, TReturn>() {
    protected var callback: ((TReturn) -> Unit)? = null

    fun setOnFinish(_callback: (TReturn) -> Unit) {
        callback = _callback
    }
}