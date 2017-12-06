package com.mp.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mp.bean.Customer;
import com.mp.common.ServerResponse;
import com.mp.redis.CustomerCache;
import com.mp.service.ICustomerService;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/find-id")
    public ServerResponse<Customer> findById(@NotBlank(message = "参数不能为空") String id){
        Optional<Customer> customer = Optional.ofNullable(customerService.selectById(id));
        return ServerResponse.createBySuccess(customer.get());
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
}

