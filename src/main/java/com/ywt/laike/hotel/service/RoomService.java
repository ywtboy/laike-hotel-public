package com.ywt.laike.hotel.service;

import com.ywt.laike.hotel.dto.RoomDTO;
import com.ywt.laike.hotel.dto.RoomStateDTO;
import com.ywt.laike.hotel.model.Floor;
import com.ywt.laike.hotel.model.Room;
import com.ywt.laike.hotel.model.RoomType;

import java.util.List;

/**
 * @Author: ywt
 */
public interface RoomService {
    /**
     * 保存房型及房间信息
     * 需开启事务
     *
     * @param roomDto
     * @return
     */
    RoomDTO saveRoomTypeAndRoom(RoomDTO roomDto, String hotelNo);

    /**
     * 修改房型及房间信息
     * 需开启事务
     *
     * @param roomDto
     * @return
     */
    RoomDTO updateRoomTypeAndRoom(RoomDTO roomDto, String hotelNo);

    /**
     * 查询某宾馆下所有房型及房间
     *
     * @param hotelNo
     * @return
     */
    List<RoomDTO> listRoomTypeAndRooms(String hotelNo);

    /**
     * 删除房型
     * 不实际删除
     *
     * @param roomType
     * @return
     */
    int deleteRoomType(RoomType roomType);

    /**
     * 保存楼层信息
     *
     * @param floor
     * @return
     */
    Floor saveFloor(Floor floor);

    /**
     * 删除楼层
     * 不实际删除
     *
     * @param floor
     * @return
     */
    int deleteFloor(Floor floor);

    /**
     * 修改房间信息
     *
     * @param room
     * @return
     */
    Room updateRoom(Room room);

    /**
     * 删除房间
     * 不实际删除
     *
     * @param room
     * @return
     */
    int delteRoom(Room room);

    /**
     * 获取房态
     * @param hotelNo
     * @return
     */
    List<RoomStateDTO> listRoomStates(String hotelNo);

    Integer updateRoomState(Room room);
}
