package com.knz21.zipcode.webapi;

import com.knz21.zipcode.store.Address;

import java.util.List;

public class SearchResponse {
    public int status;
    public String message;
    public List<Address> results;
}
