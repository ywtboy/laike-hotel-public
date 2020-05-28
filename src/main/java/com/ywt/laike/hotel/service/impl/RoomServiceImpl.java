package com.ywt.laike.hotel.service.impl;

import com.ywt.laike.hotel.dao.*;
import com.ywt.laike.hotel.dto.RoomDTO;
import com.ywt.laike.hotel.dto.RoomStateDTO;
import com.ywt.laike.hotel.model.*;
import com.ywt.laike.hotel.query.DicCommonQuery;
import com.ywt.laike.hotel.service.Exception.BusinessException;
import com.ywt.laike.hotel.service.Exception.ValueDuplicationException;
import com.ywt.laike.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ywt
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private DicTypeMapper dicTypeMapper;
    @Autowired
    private DicCommonMapper dicCommonMapper;

    /**
     * 保存房型及房间信息
     * 需开启事务
     *
     * @param roomDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomDTO saveRoomTypeAndRoom(RoomDTO roomDto, String hotelNo) {
        Date date = new Date();

        RoomType roomType = new RoomType();
        roomType.setRoomTypeName(roomDto.getRoomTypeName());
        roomType.setRoomTypePrice(roomDto.getRoomTypePrice());
        roomType.setHotelNo(hotelNo);
        roomType.setSortNo(roomDto.getSortNo());
        roomType.setDeleted(false);
        roomType.setGmtCreate(date);
        roomType.setGmtModified(date);
        roomTypeMapper.insert(roomType);
        List<Room> rooms = roomDto.getRooms();
        setRoomDefaultValue(rooms, roomType.getRoomTypeId(), hotelNo);
        System.out.println(rooms);
        if (rooms != null && rooms.size() > 0) {
            roomMapper.insertBatch(rooms);
        }
//        roomMapper.insert(rooms.get(0));
        return roomDto;
    }

    /**
     * 修改房型及房间信息
     * 需开启事务
     *
     * @param roomDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomDTO updateRoomTypeAndRoom(RoomDTO roomDto, String hotelNo) {
        Date date = new Date();
        RoomType roomType = roomTypeMapper.selectByPrimaryKey(roomDto.getRoomTypeId());
        roomType.setRoomTypeName(roomDto.getRoomTypeName());
        roomType.setRoomTypePrice(roomDto.getRoomTypePrice());
        List<Room> roomsTemp = roomDto.getRooms();
        roomType.setGmtModified(date);
        roomTypeMapper.updateByPrimaryKeySelective(roomType);
        if (roomsTemp != null && roomsTemp.size() > 0) {

            List<Room> rooms = roomMapper.listRoomsByRoomTypeId(roomType.getRoomTypeId());
            if (rooms != null && rooms.size() > 0) {
                for (Room room : rooms) {
                    Iterator<Room> it = roomsTemp.iterator();
                    while (it.hasNext()) {
                        Room roomTemp = it.next();
                        // 将已存在的房间移除
                        if (room.getRoomNo().equals(roomTemp.getRoomNo())) {
                            it.remove();
                        }
                    }
                }
            }
            // 设置默认值
            setRoomDefaultValue(roomsTemp, roomType.getRoomTypeId(), hotelNo);
            System.out.println(rooms);
            if (roomsTemp != null && roomsTemp.size() > 0) {
                roomMapper.insertBatch(roomsTemp);
            }

        }
        return roomDto;
    }

    /**
     * 查询某宾馆下所有房型及房间
     *
     * @param hotelNo
     * @return
     */

    @Override
    public List<RoomDTO> listRoomTypeAndRooms(String hotelNo) {
        long begintime = System.currentTimeMillis();

        List<RoomType> roomTypes = roomTypeMapper.listRoomTypesByHotelNo(hotelNo);
        System.out.println(roomTypes);
        List<Room> rooms = roomMapper.listRoomsByHotelNo(hotelNo);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (RoomType roomType : roomTypes) {
            RoomDTO roomDto = new RoomDTO();
            roomDto.setRoomTypeId(roomType.getRoomTypeId());
            roomDto.setRoomTypeName(roomType.getRoomTypeName());
            roomDto.setRoomTypePrice(roomType.getRoomTypePrice());
            roomDto.setHotelNo(roomType.getHotelNo());
            roomDto.setSortNo(roomType.getSortNo());
            List<Room> roomList = new ArrayList<>();
            Iterator<Room> it = rooms.iterator();
            while (it.hasNext()) {
                Room room = it.next();
                // 将此房型的房间添加到房型
                if (roomType.getRoomTypeId().equals(room.getRoomTypeId())) {
                    roomList.add(room);
                    // 将此房型的房间添加后移除
                    it.remove();
                }
            }
            roomDto.setRooms(roomList);
            roomDTOS.add(roomDto);
        }

        long endtime = System.currentTimeMillis();
        long costTime = (endtime - begintime);
        System.out.println("3本次用时：" + costTime);

        return roomDTOS;
    }

    /**
     * 删除房型
     * 不实际删除
     *
     * @param roomType
     * @return
     */
    @Override
    public int deleteRoomType(RoomType roomType) {
        return 0;
    }

    /**
     * 保存楼层信息
     *
     * @param floor
     * @return
     */
    @Override
    public Floor saveFloor(Floor floor) {
        return null;
    }

    /**
     * 删除楼层
     * 不实际删除
     *
     * @param floor
     * @return
     */
    @Override
    public int deleteFloor(Floor floor) {
        return 0;
    }

    /**
     * 修改房间信息
     *
     * @param room
     * @return
     */
    @Override
    public Room updateRoom(Room room) {
        return null;
    }

    /**
     * 删除房间
     * 不实际删除
     *
     * @param room
     * @return
     */
    @Override
    public int delteRoom(Room room) {
        return 0;
    }

    /**
     * 获取房态
     *
     * @param hotelNo
     * @return
     */
    @Override
    public List<RoomStateDTO> listRoomStates(String hotelNo) {
        List<Room> rooms = roomMapper.listRoomsByHotelNo(hotelNo);
        List<RoomType> roomTypes = roomTypeMapper.listRoomTypesByHotelNo(hotelNo);
        // 查询房态字典
        DicType dicType = dicTypeMapper.getDicTypeByCode("roomState");
        DicCommonQuery dicCommonQuery = new DicCommonQuery();
        dicCommonQuery.setDicTypeId(dicType.getDicTypeId());
        List<DicCommon> dicCommons = dicCommonMapper.listDicCommons(dicCommonQuery);

        if (null != rooms && rooms.size() > 0) {
            List<RoomStateDTO> roomStateDTOS = new ArrayList<>();
            rooms.forEach(
                    room -> {
                        RoomStateDTO roomStateDTO = new RoomStateDTO();
                        roomStateDTO.setRoomName(room.getRoomName());
                        roomStateDTO.setRoomNo(room.getRoomNo());
                        roomStateDTO.setRoomState(room.getRoomState());
                        roomStateDTO.setHotelNo(room.getHotelNo());
                        roomStateDTO.setOrderNo(room.getOrderNo());
                        roomStateDTO.setRoomTag(room.getRoomTag());
                        roomStateDTO.setLocked(room.getLocked());
                        roomStateDTO.setLockedReason(room.getLockedReason());

                        roomStateDTO.setRoomTypeId(room.getRoomTypeId());
                        roomStateDTO.setFloorId(room.getFloorId());

                        DicCommon dicCommon = dicCommons.stream()
                                .filter(dic -> roomStateDTO.getRoomState().equals(dic.getDicCommonCode()))
                                .collect(Collectors.toList()).get(0);
//                        System.out.println(dicCommon);
                        roomStateDTO.setRoomStateName(dicCommon.getDicCommonName());

                        roomTypes.forEach(roomType -> {
                            if (room.getRoomTypeId().equals(roomType.getRoomTypeId())) {
                                roomStateDTO.setRoomTypeName(roomType.getRoomTypeName());
                                roomStateDTO.setRoomTypePrice(roomType.getRoomTypePrice());
                            }
                        });
                        roomStateDTOS.add(roomStateDTO);
                    }
            );
            return roomStateDTOS;
        } else {
            return null;
        }

    }

    @Override
    public Integer updateRoomState(Room room) {
        Room checkRoom = roomMapper.getRoomByRoomNo(room.getRoomNo());
        System.out.println(room);
        if ("rented".equals(checkRoom.getRoomState()) || "rented_hour".equals(checkRoom.getRoomState())) {
            throw new BusinessException("已租房不能修改房态，请先退房");
        }
        if ("rented".equals(room.getRoomState()) || "rented_hour".equals(room.getRoomState())) {
            throw new BusinessException("房态不能直接改为已租，请入住");
        }
        return roomMapper.updateRoomState(room);
    }

    /**
     * 添加房间时设置默认值
     *
     * @param rooms Room List集合
     * @return
     */
    public void setRoomDefaultValue(List<Room> rooms, Long typeId, String hotelNo) {
        Date date = new Date();
        Iterator<Room> it = rooms.iterator();
        while (it.hasNext()) {
            Room room = it.next();
            if (room.getRoomName() != null && !"".equals(room.getRoomName())) {
                room.setRoomTypeId(typeId);
                // @TODO 暂时设置为 房间编号临时
                room.setRoomNo(hotelNo + room.getRoomName());
                room.setRoomState("vacant");
                room.setHotelNo(hotelNo);
                room.setLocked(false);
                room.setDeleted(false);
                room.setGmtCreate(date);
                room.setGmtModified(date);
            } else {
                it.remove();
            }
        }
    }


}
