package com.knz21.zipcode;

import com.knz21.zipcode.di.ApplicationScope;
import com.knz21.zipcode.store.Address;
import com.knz21.zipcode.webapi.ResponseMapper;
import com.knz21.zipcode.webapi.ZipCodeApi;

import javax.inject.Inject;
import java.io.IOException;

@ApplicationScope
public class WebService {
    private ZipCodeApi api;

    @Inject
    public WebService(ZipCodeApi api) {
        this.api = api;
    }

    public Address searchAddress(String zipcode) throws IOException {
        return ResponseMapper.transform(api.search(zipcode).execute().body());
    }
}
