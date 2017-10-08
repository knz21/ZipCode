package com.knz21.zipcode.store;

import com.knz21.zipcode.di.ApplicationScope;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScope
public class AddressStore implements DataStore {
    private OrmaDatabase orma;

    @Inject
    public AddressStore(OrmaDatabase orma) {
        this.orma = orma;
    }

    @Override
    public List<Address> getAddresses() {
        return orma.selectFromAddress().orderByCreatedAtDesc().toList();
    }

    @Override
    public boolean update(Address address) {
        if (orma.selectFromAddress().zipcodeEq(address.zipcode).toList().isEmpty()) {
            address.createdAt = new Date();
            orma.insertIntoAddress(address);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        orma.deleteAll();
    }
}
