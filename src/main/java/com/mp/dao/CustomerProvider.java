package com.mp.dao;

import org.apache.ibatis.jdbc.SQL;

public class CustomerProvider {

    public String findByOriginal(){
        return new SQL(){{
            SELECT("*");
            FROM("customer");
        }}.toString();
    }
}
