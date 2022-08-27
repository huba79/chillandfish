/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="financial_data")
public class FinancialData extends BaseEntity implements Serializable {
    private String bankAccountNr;

    public FinancialData(String bankAccountNr) {
        this.bankAccountNr = bankAccountNr;
    }
    public FinancialData(){};
}
