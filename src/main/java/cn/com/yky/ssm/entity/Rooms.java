package cn.com.yky.ssm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Rooms {
    /** 主键 */
    private Integer id;

    /** 房屋封面图 */
    private String roomPic;

    /** 房间编号 */
    private String roomNum;

    /** 房间的状态(0空闲，1已入住，2打扫) */
    private String roomStatus;

    /** 房间类型主键 */
    private Integer roomTypeId;

    /** 1表示显示0不显示 */
    private Integer flag;

    //客房类型对象
    private RoomType roomType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic == null ? null : roomPic.trim();
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus == null ? null : roomStatus.trim();
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", roomPic='" + roomPic + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", roomTypeId=" + roomTypeId +
                ", flag=" + flag +
                '}';
    }
}