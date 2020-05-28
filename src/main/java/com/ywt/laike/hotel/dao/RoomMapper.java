package com.ywt.laike.hotel.dao;

import com.ywt.laike.hotel.model.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {
    int deleteByPrimaryKey(Long roomId);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Long roomId);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    /**
     * 批量添加房间
     * @param rooms
     * @return
     */
    int insertBatch(List<Room> rooms);

    /**
     * 查询某房型的所有房间
     * @param roomTypeId
     * @return
     */
    List<Room> listRoomsByRoomTypeId(Long roomTypeId);

    /**
     * 查询么偶宾馆下所有房间
     * @param hotelNo
     * @return
     */
    List<Room> listRoomsByHotelNo(String hotelNo);

    /**
     * 修改房态
     * @param room
     * @return
     */
    int updateRoomState(Room room);

    /**
     * 根据房间编号批量查询
     * @param roomNos
     * @return
     */
    List<Room> listRoomsByRoomNo(List<String> roomNos);

    /**
     * 查询订单下正住房间
     * @param orderNo
     * @return
     */
    List<Room> listRoomsByOrderNo(String orderNo);

    Room getRoomByRoomNo(String roomNo);
}