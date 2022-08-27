package com.digitalways.chillandfish.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="financial_data")
public class FinancialData extends BaseEntity implements Serializable {

    @Column(name="ACCOUNT_NR")
    @JsonProperty("bankAccountNr")
    private String bankAccountNr;

    public FinancialData(Long createUserId, String bankAccountNr) {
        super(createUserId);
        this.bankAccountNr = bankAccountNr;
    }
    public FinancialData(){}

    public String getBankAccountNr() {
        return bankAccountNr;
    }

    public void setBankAccountNr(String bankAccountNr) {
        this.bankAccountNr = bankAccountNr;
    }
}
