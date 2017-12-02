package com.mp.redis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mp.bean.Customer;
import com.mp.common.Const;
import com.mp.dao.CustomerMapper;
import com.mp.utils.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerCache implements ApplicationListener<ContextRefreshedEvent> {

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    private void init() {
        RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        List<Customer> customers = SpringContextHolder.getBean(CustomerMapper.class).selectList(new EntityWrapper<Customer>());
        customers.forEach((customer) -> {
            operations.set(Const.CUSTOMER_PREFIX + customer.getId(), customer);
        });
    }

    public static Customer get(String id) {
        RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        Customer customer = (Customer) operations.get(Const.CUSTOMER_PREFIX + id);
        return customer;
    }

    //初始化容器后加载缓存
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    //重载缓存
    public static void refresh() {
        delete();
        RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        List<Customer> customers = SpringContextHolder.getBean(CustomerMapper.class).selectList(new EntityWrapper<Customer>());
        customers.forEach((customer) -> {
            operations.set(Const.CUSTOMER_PREFIX + customer.getId(), customer);
        });
    }
    //删除缓存
    public static void delete() {
        RedisTemplate redisTemplate = SpringContextHolder.getBean("redisTemplate");
        List<Customer> customers = SpringContextHolder.getBean(CustomerMapper.class).selectList(new EntityWrapper<Customer>());
        Stream<String> keys = customers.stream().map(customer -> Const.CUSTOMER_PREFIX + customer.getId());
        redisTemplate.delete(keys.collect(Collectors.toList()));
    }
}
