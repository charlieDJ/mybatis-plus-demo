package com.mp.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mp.bean.Customer;
import com.mp.common.ServerResponse;
import com.mp.dao.CustomerMapper;
import com.mp.redis.CustomerCache;
import com.mp.service.ICustomerService;
import com.mp.validator.PhoneNum;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 顾客; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author dengj
 * @since 2017-11-24
 */
@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        List<Customer> customers = customerService.selectPage(
                new Page<Customer>(1, 2),
                new EntityWrapper<Customer>()
        ).getRecords();
        return customers;
    }

    @GetMapping("/find")
    public List<Customer> find(){
        Customer customer = new Customer();
        List<Customer> customers = customer.selectPage(
                new Page<Customer>(1, 3),
                new EntityWrapper<Customer>()
        ).getRecords();
        return customers;
    }

    @GetMapping("/find-original")
    public List<Customer> findByOriginal(){
        List<Customer> customers = customerMapper.findByOriginal();
        return customers;
    }

    @GetMapping("/find-id")
    public ServerResponse<Customer> findById(@NotBlank(message = "参数不能为空") String id){
        Optional<Customer> customer = Optional.ofNullable(customerService.selectById(id));
        return ServerResponse.createBySuccess(customer.get());
    }

    @GetMapping("/validate-phone-num")
    public ServerResponse<String> validatePhoneNum(@PhoneNum String phoneNum){
        return ServerResponse.createBySuccess();
    }


    @GetMapping("/cache")
    public Customer cache(){
//        CustomerCache.refresh();
        Customer customer = CustomerCache.get("1");
        logger.info(customer.toString());
        CustomerCache.delete();
        customer = CustomerCache.get("1");
        return customer;
    }

    @GetMapping("/log")
    public String log(){
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        return "Success";
    }

    @GetMapping("/cache2")
    @Cacheable("customerCache")
    public Customer cache2(String id){
        //只要缓存里面有这个键值对，不再进入方法。
        Customer customer = customerService.selectById(id);
        return customer;
    }
}

