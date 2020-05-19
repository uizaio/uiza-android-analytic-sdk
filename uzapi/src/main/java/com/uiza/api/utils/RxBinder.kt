@file:JvmName("RxBinder")

package com.uiza.api.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.Schedulers


//fun <T> Observable<T>.bind(onNext: Consumer<T>, onError: Consumer<Throwable>?): Disposable {
//    //TODO maybe in some cases we don't need to check internet connection
//    /*if (!NetworkUtils.hasConnection(this)) {
//            subscriber.onError(new NoConnectionException());
//            return;
//        }*/
//    return this.subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(onNext, onError)
//}

@JvmOverloads
fun <T> Observable<T>.bind(onNext: Consumer<T>, onError: Consumer<Throwable> = Functions.ON_ERROR_MISSING, onComplete: Action = Functions.EMPTY_ACTION): Disposable {
    //TODO maybe in some cases we don't need to check internet connection
    /*if (!NetworkUtils.hasConnection(this)) {
            subscriber.onError(new NoConnectionException());
            return;
        }*/
    return this.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNext, onError, onComplete)
}