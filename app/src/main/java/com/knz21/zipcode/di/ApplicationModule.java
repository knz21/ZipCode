package com.knz21.zipcode.di;

import com.knz21.zipcode.ZipCodeApp;
import com.knz21.zipcode.store.AddressStore;
import com.knz21.zipcode.store.DataStore;
import com.knz21.zipcode.store.OrmaDatabase;
import com.knz21.zipcode.webapi.ZipCodeApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final ZipCodeApp app;

    public ApplicationModule(ZipCodeApp app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public OrmaDatabase provideOrmaDatabase() {
        return OrmaDatabase.builder(app.getApplicationContext()).build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    public ZipCodeApi provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://zipcloud.ibsnet.co.jp/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ZipCodeApi.class);
    }

    @Provides
    @ApplicationScope
    public DataStore provideAddressStore(AddressStore store) {
        return store;
    }
}