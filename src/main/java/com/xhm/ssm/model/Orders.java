package com.xhm.ssm.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private String oid;

    private Date ordertime;

    //private Long total;
    private BigDecimal total;

    private Short state;//0代表未付款,1代表已付款但未发货,2代表已发货但未确认收货,3代表已确认交易成功

    private String uid;

    private String address;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}