package cn.com.yky.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class RoomSale {
    /** 消费id */
    private Integer id;

    /** 房间号 */
    private String roomNum;

    /** 客人姓名 */
    private String customerName;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
    private Date startDate;

    /** 退房时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
    private Date endDate;

    /** 天数 */
    private Integer days;

    /** 房屋单价 */
    private Double roomPrice;

    /** 住宿费 */
    private Double rentPrice;

    /** 其它消费 */
    private Double otherPrice;

    /**  */
    private Double salePrice;

    /** 优惠金额 */
    private Double discountPrice;

    //查询的条件:时间范围
    private Date minDate;//时间上限

    private Date maxDate; //时间下限

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Double getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(Double otherPrice) {
        this.otherPrice = otherPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "RoomSale{" +
                "id=" + id +
                ", roomNum='" + roomNum + '\'' +
                ", customerName='" + customerName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", days=" + days +
                ", roomPrice=" + roomPrice +
                ", rentPrice=" + rentPrice +
                ", otherPrice=" + otherPrice +
                ", salePrice=" + salePrice +
                ", discountPrice=" + discountPrice +
                ", minDate=" + minDate +
                ", maxDate=" + maxDate +
                '}';
    }
}