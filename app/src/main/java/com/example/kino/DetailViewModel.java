package com.example.kino;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = "DetailViewModel";
    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public void loadTrailersRx(int id) {
        Disposable disposable = ApiFactory.apiServise.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponce, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponce trailerResponce) throws Throwable {
                        return trailerResponce.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {
                        trailers.setValue(trailerList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
