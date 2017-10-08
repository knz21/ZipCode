package com.knz21.zipcode.webapi;

import com.knz21.zipcode.store.Address;

public class ResponseMapper {
    public static Address transform(SearchResponse response) {
        if (response.status != 200 || response.results == null || response.results.isEmpty()) {
            return null;
        }
        return response.results.get(0);
    }
}
