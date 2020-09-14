package cn.com.yky.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class InRoomInfo {
    /** 主键 */
    private Integer id;

    /** 客人姓名 */
    private String customerName;

    /** 性别(1男 0女) */
    private String gender;

    /** 0普通，1vip */
    private String isVip;

    /** 身份证号 */
    private String idcard;

    /** 手机号 */
    private String phone;

    /** 押金 */
    private Float money;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /** 房间表主键 */
    private Integer roomId;

    /** 显示状态：1显示，0隐藏 */
    private String status;

    /** 退房状态：0未退房 1已经退房 */
    private String outRoomStatus;

    //客房对象
    private Rooms rooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip == null ? null : isVip.trim();
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

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOutRoomStatus() {
        return outRoomStatus;
    }

    public void setOutRoomStatus(String outRoomStatus) {
        this.outRoomStatus = outRoomStatus == null ? null : outRoomStatus.trim();
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "InRoomInfo{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", gender='" + gender + '\'' +
                ", isVip='" + isVip + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                ", createDate=" + createDate +
                ", roomId=" + roomId +
                ", status='" + status + '\'' +
                ", outRoomStatus='" + outRoomStatus + '\'' +
                '}';
    }
}