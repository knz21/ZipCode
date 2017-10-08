package com.knz21.zipcode.store;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

import java.util.Date;

@Table("address")
public class Address {
    @PrimaryKey
    public String zipcode;

    @Column
    public String address1;
    @Column
    public String address2;
    @Column
    public String address3;
    @Column
    public String kana1;
    @Column
    public String kana2;
    @Column
    public String kana3;
    @Column
    public String prefcode;
    @Column(indexed = true)
    public Date createdAt;

    @Override
    public String toString() {
        return zipcode + " " + address1 + address2 + address3;
    }
}
