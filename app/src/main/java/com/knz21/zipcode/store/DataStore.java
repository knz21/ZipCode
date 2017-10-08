package com.knz21.zipcode.store;

import java.util.List;

public interface DataStore {
    List<Address> getAddresses();

    boolean update(Address address);

    void clear();
}
