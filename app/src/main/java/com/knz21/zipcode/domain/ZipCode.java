package com.knz21.zipcode.domain;

import android.util.Log;
import com.knz21.zipcode.WebService;
import com.knz21.zipcode.store.Address;
import com.knz21.zipcode.store.DataStore;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class ZipCode {
    private WebService service;
    private DataStore store;
    private Scheduler executionScheduler;
    private Scheduler postScheduler;

    @Inject
    public ZipCode(WebService service, DataStore store,
                   @Named("executeScheduler") Scheduler executionScheduler,
                   @Named("postScheduler") Scheduler postScheduler) {
        this.service = service;
        this.store = store;
        this.executionScheduler = executionScheduler;
        this.postScheduler = postScheduler;
    }

    public void search(String zipcode, Consumer<List<Address>> onSuccess, Consumer<Throwable> onError) {
        Observable.<List<Address>>create(subscriber -> {
            subscriber.onNext(store.getAddresses());

            if (!validate(zipcode)) {
                subscriber.onComplete();
                return;
            }

            Address searchedAddress = service.searchAddress(zipcode);
            if (searchedAddress == null) {
                subscriber.onComplete();
                return;
            }

            List<Address> list = store.getAddresses();
            if (!store.update(searchedAddress)) {
                subscriber.onComplete();
                return;
            }
            list.add(0, searchedAddress);
            subscriber.onNext(list);
            subscriber.onComplete();
        })
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)
                .subscribe(onSuccess, onError);
    }

    private boolean validate(String zipcode) {
        if (zipcode == null) {
            return false;
        }
        String z = zipcode.replaceFirst("-", "");
        if (z.length() != 7) {
            return false;
        }
        for (int i = 0, c = z.length(); i < c; i++) {
            if (!Character.isDigit(z.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void clear(Action onComplete) {
        Completable.create(subscriber -> {
            store.clear();
            subscriber.onComplete();
        })
                .subscribeOn(executionScheduler)
                .observeOn(postScheduler)
                .subscribe(onComplete);
    }
}
