package com.mp.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 顾客; InnoDB free: 11264 kB
 * </p>
 *
 * @author dengj
 * @since 2017-11-24
 */
@TableName("customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 姓名
     */
	private String name;
    /**
     * 年龄
     */
	private String age;
    /**
     * 住址
     */
	private String address;
    /**
     * 身份号码
     */
	@TableField("id_card")
	private String idCard;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Customer{" +
			", id=" + id +
			", name=" + name +
			", age=" + age +
			", address=" + address +
			", idCard=" + idCard +
			"}";
	}
}
