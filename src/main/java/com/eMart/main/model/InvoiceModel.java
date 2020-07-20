package com.eMart.main.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModel {
    private int id;
    private int numberOfProduct;
    private int  totalAmount;
    private Timestamp timeStamp;
}
