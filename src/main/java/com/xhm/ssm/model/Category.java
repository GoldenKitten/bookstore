package com.xhm.ssm.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

public class Category {
    private String cid;

    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}