package com.mp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mp.bean.Customer;
import com.mp.dao.CustomerMapper;
import com.mp.service.ICustomerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 顾客; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author dengj
 * @since 2017-11-24
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
