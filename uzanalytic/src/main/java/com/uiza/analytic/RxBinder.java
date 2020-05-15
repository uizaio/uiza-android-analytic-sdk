package com.uiza.analytic;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class RxBinder {

    private RxBinder() {
    }

    public static <T> Disposable bind(Observable<T> observable, Consumer<T> onNext, Consumer<Throwable> onError) {
        //TODO maybe in some cases we don't need to check internet connection
        /*if (!NetworkUtils.hasConnection(this)) {
            subscriber.onError(new NoConnectionException());
            return;
        }*/
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError);
    }

    public static <T> Disposable bind(Observable<T> observable, Consumer<T> onNext, Consumer<Throwable> onError, Action onComplete) {
        //TODO maybe in some cases we don't need to check internet connection
        /*if (!NetworkUtils.hasConnection(this)) {
            subscriber.onError(new NoConnectionException());
            return;
        }*/
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onComplete);
    }
}
