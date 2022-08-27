
package com.digitalways.chillandfish.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity implements Serializable{
    @Column(name="ADDRESS_TYPE")
    private AddressTypeEnum addressType;
    
    @Column(name="COUNTRY")
    private String country;

    @Column(name="COUNTY")
    private String county;
    
    @Column(name="SETTLEMENT")
    private String settlement;
    
    @Column(name="STREET")
    private String street;
    
    @Column(name="STREET_NR")
    private int nr;
    
    @Column(name="STAIRCASE")
    private int staircase;
    
    @Column(name="APARTMENT_NR")
    private int apartment;

    public Address() {
    }

    public Address(AddressTypeEnum addressType, String country, String county, String settlement, String street, int nr, Long createUserId) {
        super(createUserId);
        this.addressType = addressType;
        this.country = country;
        this.county = county;
        this.settlement = settlement;
        this.street = street;
        this.nr = nr;
    }

    public Address(AddressTypeEnum addressType, String country, String county, String settlement, String street, int nr, int staircase, int apartment, Long createUserId) {
        super(createUserId);
        this.addressType = addressType;
        this.country = country;
        this.county = county;
        this.settlement = settlement;
        this.street = street;
        this.nr = nr;
        this.staircase = staircase;
        this.apartment = apartment;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getSettlement() {
        return settlement;
    }

    public String getStreet() {
        return street;
    }

    public int getNr() {
        return nr;
    }

    public int getStaircase() {
        return staircase;
    }

    public int getApartment() {
        return apartment;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setStaircase(int staircase) {
        this.staircase = staircase;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }



  
}
