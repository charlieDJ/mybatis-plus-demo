package com.mp.web.controller;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.mp.bean.Customer;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> map = Maps.newHashMap();
        map.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"name"),"kalamo");
        map.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"id_card"),"sdfasdfj");
        Customer customer = new Customer();
        BeanUtils.populate(customer,map);
        System.out.println(customer.toString());
    }
}
