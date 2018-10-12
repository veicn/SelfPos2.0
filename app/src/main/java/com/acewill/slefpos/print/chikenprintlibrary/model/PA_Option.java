package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_Option
{
    public String name;
    public BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
