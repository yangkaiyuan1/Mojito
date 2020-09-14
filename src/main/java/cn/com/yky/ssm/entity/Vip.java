package cn.com.yky.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Vip {

    /** 主键 */
    private Integer id;

    /** 会员卡编号 */
    private String vipNum;

    /** 会员姓名 */
    private String customerName;

    /** 1~9折 */
    private Float vipRate;

    /** 会员身份证 */
    private String idcard;

    /** 手机号码 */
    private String phone;

    /** 会员办理日期 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /** 性别：1男 0女 */
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum == null ? null : vipNum.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Float getVipRate() {
        return vipRate;
    }

    public void setVipRate(Float vipRate) {
        this.vipRate = vipRate;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}