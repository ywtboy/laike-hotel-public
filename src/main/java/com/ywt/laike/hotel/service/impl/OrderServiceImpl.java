package com.ywt.laike.hotel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywt.laike.hotel.dao.*;
import com.ywt.laike.hotel.dto.*;
import com.ywt.laike.hotel.model.*;
import com.ywt.laike.hotel.query.OrderQuery;
import com.ywt.laike.hotel.query.OrderRoomQuery;
import com.ywt.laike.hotel.service.Exception.BusinessException;
import com.ywt.laike.hotel.service.Exception.NotCurrentHotelException;
import com.ywt.laike.hotel.service.Exception.OperationResourcesIllegalException;
import com.ywt.laike.hotel.service.OrderService;
import com.ywt.laike.hotel.util.NumGenerationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ywt
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderIncomeMapper orderIncomeMapper;
    private final OrderRoomMapper orderRoomMapper;
    private final GuestMapper guestMapper;
    private final GuestHotelMapper guestHotelMapper;
    private final RoomMapper roomMapper;
    private final PayDetailMapper payDetailMapper;
    private final FrontCashFlowMapper frontCashFlowMapper;
    private final OrderOperationRecordMapper orderOperationRecordMapper;
    private final OrderConsumeMapper orderConsumeMapper;
    private final OrderReserveMapper orderReserveMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderIncomeMapper orderIncomeMapper,
                            OrderRoomMapper orderRoomMapper, GuestMapper guestMapper,
                            GuestHotelMapper guestHotelMapper, RoomMapper roomMapper,
                            PayDetailMapper payDetailMapper, FrontCashFlowMapper frontCashFlowMapper,
                            OrderOperationRecordMapper orderOperationRecordMapper, OrderConsumeMapper orderConsumeMapper, OrderReserveMapper orderReserveMapper) {
        this.orderMapper = orderMapper;
        this.orderIncomeMapper = orderIncomeMapper;
        this.orderRoomMapper = orderRoomMapper;
        this.guestMapper = guestMapper;
        this.guestHotelMapper = guestHotelMapper;
        this.roomMapper = roomMapper;
        this.payDetailMapper = payDetailMapper;
        this.frontCashFlowMapper = frontCashFlowMapper;
        this.orderOperationRecordMapper = orderOperationRecordMapper;
        this.orderConsumeMapper = orderConsumeMapper;
        this.orderReserveMapper = orderReserveMapper;
    }

    /**
     * 取消订单
     *
     * @param cancelOrderDTO 取消订单dto
     * @return CancelOrderDTO
     */
    @Override
    public CancelOrderDTO cancelOrder(CancelOrderDTO cancelOrderDTO) {
//        Order order = orderMapper.getOrderByNo(cancelOrderDTO.getOrderNo());
//        List<OrderRoom> orderRoomList = orderRoomMapper.listOrderRoomsByOrderNo(order.getOrderNo());
//        List<OrderIncome> orderIncomeList = orderIncomeMapper.listOrderIncomesByOrderNo(order.getOrderNo());
//        List<OrderConsume> orderConsumeList = orderConsumeMapper.listOrderConsumesByOrderNo(order.getOrderNo());

        return null;
    }

    /**
     * 入住/办理入住
     *
     * @param checkInDTO 入住dto
     * @return CheckInDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CheckInDTO checkIn(CheckInDTO checkInDTO) {
        Order order = checkInDTO.getOrder();
        OrderIncome orderIncome = checkInDTO.getOrderIncome();
        List<OrderRoom> orderRooms = checkInDTO.getOrderRooms();
        List<Guest> guests = checkInDTO.getGuests();
        String currentSessionHotelNo = checkInDTO.getCurrentSessionHotelNo();
        Long currentUserId = checkInDTO.getCurrentUserId();
        String currentUserName = checkInDTO.getCurrentUserName();
        Date currentDate = new Date();

        // 如果不是预订办理入住则生成订单号
        boolean isReserved = true;
        if (!checkInDTO.getReserved()) {
            order.setOrderNo(NumGenerationUtils.getOrderNo(Long.parseLong(currentSessionHotelNo)));
            order.setOrderMoney(new BigDecimal(0));
            order.setHotelNo(currentSessionHotelNo);
            order.setOrderStateCode("confirmed");
            isReserved = false;
        }
        String orderNo = order.getOrderNo();

        // 操作具体内容
        List<Map<String, Object>> operationContent = new ArrayList<>(orderRooms.size());

        //处理房间信息
        List<String> rommNos = new ArrayList<>();
        orderRooms.forEach(orderRoom -> {
            rommNos.add(orderRoom.getRoomNo());

            orderRoom.setHotelNo(currentSessionHotelNo);
            orderRoom.setOrderNo(orderNo);
            orderRoom.setOrderRoomStateCode("live");
            orderRoom.setRealInTime(currentDate);
            // 设置公共属性
            setBeanCommonInfo(orderRoom, currentUserId, currentUserName, currentDate);


            // 处理订单消费信息
            OrderConsume orderConsume = new OrderConsume();
            orderConsume.setConsumeTypeCode("income");
            orderConsume.setConsumeItemCode("rent");
            orderConsume.setConsumeItemDetail("首次房租");
            orderConsume.setConsumeMoney(orderRoom.getRoomPrice());
            orderConsume.setBusinessDay(getBusinessDay(currentDate));
            orderConsume.setRoomNo(orderRoom.getRoomNo());
            orderConsume.setRoomName(orderRoom.getRoomName());
            orderConsume.setOrderNo(orderNo);
            orderConsume.setHotelNo(currentSessionHotelNo);
            orderConsume.setOperatorId(1L);
            orderConsume.setOperatorName("系统");
            orderConsume.setDeleted(false);
            orderConsume.setGmtCreate(currentDate);
            orderConsume.setGmtModified(currentDate);
            if (1 <= orderRoom.getRoomPrice().compareTo(new BigDecimal(0))) {
                orderConsumeMapper.insert(orderConsume);
            }

            order.setOrderMoney(order.getOrderMoney().add(orderConsume.getConsumeMoney()));

            if (checkInDTO.getReserved()) {
                orderRoomMapper.updateByPrimaryKey(orderRoom);

                Map<String, Object> content = new HashMap<>();
                content.put("roomTypeName", orderRoom.getRoomTypeName());
                content.put("roomPrice", orderRoom.getRoomPrice());
                content.put("orderRoomId", orderRoom.getOrderRoomId());
                content.put("roomNo", orderRoom.getRoomNo());
                content.put("roomName", orderRoom.getRoomName());
                operationContent.add(content);


            } else {
                orderRoomMapper.insert(orderRoom);
            }

        });
        List<Room> rooms = roomMapper.listRoomsByRoomNo(rommNos);
        rooms.forEach(room -> {
            // 检查房间是否属于当前宾馆
            if (isCurrentHotel(room.getHotelNo(), currentSessionHotelNo)) {
                // 检查房间是否为空房
                if ("vacant".equals(room.getRoomState())) {
                    if ("hour_room".equals(order.getOrderTypeCode())) {
                        room.setRoomState("rented_hour");
                    } else {
                        room.setRoomState("rented");
                    }
                    room.setOrderNo(orderNo);
                    roomMapper.updateByPrimaryKey(room);
                } else {
                    throw new OperationResourcesIllegalException(room.getRoomName() + "不是空房");
                }
            } else {
                throw new NotCurrentHotelException(room.getRoomNo() + "房间不属于当前酒店");
            }
        });

        // 处理付款信息
        if (null != orderIncome && null != orderIncome.getIncomeMoney() && 1 <= orderIncome.getIncomeMoney().compareTo(new BigDecimal(0))) {
            orderIncome.setOrderNo(orderNo);
            orderIncome.setHotelNo(currentSessionHotelNo);
            orderIncome.setIncomeTypeCode("income");
            orderIncome.setIncomeClassCode("receipts");
            // 处理公共属性
            setBeanCommonInfo(orderIncome, currentUserId, currentUserName, currentDate);
            orderIncomeMapper.insert(orderIncome);
            // 保存收款详情及前台现金流
            savePayDetailAndFrontCashFlow(orderIncome, currentDate);

            // 处理订单信息
            order.setPayMoney(orderIncome.getIncomeMoney());
        }

        // 处理客人信息
        if (null != guests) {
            this.saveGuests(guests, currentSessionHotelNo, currentDate);
        }

        // 处理操作信息
        OrderOperationRecord operationRecord = new OrderOperationRecord();
        operationRecord.setOrderNo(orderNo);
        if (isReserved) {
            operationRecord.setOperationCode("check_in");
            operationRecord.setOperationName("办理入住");
            // 操作内容转String
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                operationRecord.setOperationContent(objectMapper.writeValueAsString(operationContent));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("办理入住json转化异常", e);
            }
        } else {
            operationRecord.setOperationCode("new_order");
            operationRecord.setOperationName("新增订单");
        }
        operationRecord.setHotelNo(currentSessionHotelNo);
        // 设置公共属性
        setBeanCommonInfo(operationRecord, currentUserId, currentUserName, currentDate);
        orderOperationRecordMapper.insert(operationRecord);

        // 处理订单信息
        // 设置公共属性
        setBeanCommonInfo(order, currentUserId, currentUserName, currentDate);
        if (checkInDTO.getReserved()) {
            orderMapper.updateByPrimaryKey(order);
        } else {
            orderMapper.insert(order);
        }


        return checkInDTO;
    }

    /**
     * 处理客人信息
     *
     * @param guests
     * @param currentSessionHotelNo
     * @param currentDate
     */
    private void saveGuests(List<Guest> guests, String currentSessionHotelNo, Date currentDate) {
        guests.forEach(guest -> {
            if (null != guest.getCertificateNo() && !"".equals(guest.getCertificateNo())) {
                Guest guestTemp = guestMapper.getGuestByCertificateNo(guest.getCertificateNo());
                if (guestTemp == null) {
                    guest.setDeleted(false);
                    guest.setGmtCreate(currentDate);
                    guest.setGmtModified(currentDate);
                    guestMapper.insert(guest);
                    GuestHotel guestHotel = new GuestHotel();
                    guestHotel.setGuestName(guest.getGuestName());
                    guestHotel.setGuestTel(guest.getGuestTel());
                    guestHotel.setCertificateNo(guest.getCertificateNo());
                    guestHotel.setVip(false);
                    guestHotel.setHotelNo(currentSessionHotelNo);
                    guestHotel.setDeleted(false);
                    guestHotel.setGmtCreate(currentDate);
                    guestHotel.setGmtModified(currentDate);
                    guestHotelMapper.insert(guestHotel);
                }
            }

        });
    }

    /**
     * 房型预订登记
     *
     * @param checkInOfReserveDTO 房型预订dto
     * @return CheckInOfReserveDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CheckInOfReserveDTO checkInOfReserve(CheckInOfReserveDTO checkInOfReserveDTO) {
        Order order = checkInOfReserveDTO.getOrder();

        String currentSessionHotelNo = checkInOfReserveDTO.getCurrentSessionHotelNo();

        // 订单号
        String orderNo = NumGenerationUtils.getOrderNo(Long.parseLong(currentSessionHotelNo));

        OrderIncome orderIncome = checkInOfReserveDTO.getOrderIncome();
        List<OrderReserve> orderReserves = checkInOfReserveDTO.getOrderReserves();
        List<Guest> guests = checkInOfReserveDTO.getGuests();

        Long currentUserId = checkInOfReserveDTO.getCurrentUserId();
        String currentUserName = checkInOfReserveDTO.getCurrentUserName();

        Date currentDate = new Date();

        // 处理付款信息
        orderIncome.setOrderNo(orderNo);
        orderIncome.setHotelNo(currentSessionHotelNo);
        orderIncome.setIncomeTypeCode("income");
        orderIncome.setIncomeClassCode("deposit"); // 订金
        // 处理公共属性
        setBeanCommonInfo(orderIncome, currentUserId, currentUserName, currentDate);
        orderIncomeMapper.insert(orderIncome);
        // 保存收款详情及前台现金流
        savePayDetailAndFrontCashFlow(orderIncome, currentDate);

        // 处理预订房型信息
        orderReserves.forEach(orderReserve -> {
            orderReserve.setHotelNo(currentSessionHotelNo);
            orderReserve.setOrderNo(orderNo);
            orderReserve.setAllottedNum(0);
            setBeanCommonInfo(orderReserve, currentUserId, currentUserName, currentDate);
            orderReserveMapper.insert(orderReserve);
        });

        // 处理客人信息
        this.saveGuests(guests, currentSessionHotelNo, currentDate);

        // 处理订单信息
        order.setOrderNo(orderNo);
        order.setHotelNo(currentSessionHotelNo);
        order.setGuestName(guests.get(0).getGuestName());
        order.setGuestTel(guests.get(0).getGuestTel());
        order.setGuestCertificateNo(guests.get(0).getCertificateNo());
        order.setOrderStateCode("confirmed");
        order.setOrderTypeCode("daily");
        order.setCheckInTime(orderReserves.get(0).getEstimateInTime());
        order.setCheckOutTime(orderReserves.get(0).getEstimateOutTime());
        order.setOrderMoney(new BigDecimal(0));
        order.setPayMoney(orderIncome.getIncomeMoney());
        // 设置公共属性
        setBeanCommonInfo(order, currentUserId, currentUserName, currentDate);
        orderMapper.insert(order);

        // 处理操作信息
        OrderOperationRecord operationRecord = new OrderOperationRecord();
        operationRecord.setOrderNo(orderNo);
        operationRecord.setOperationCode("new_reserve");
        operationRecord.setOperationName("新增预订");
        operationRecord.setHotelNo(currentSessionHotelNo);
        // 设置公共属性
        setBeanCommonInfo(operationRecord, currentUserId, currentUserName, currentDate);
        orderOperationRecordMapper.insert(operationRecord);
        return checkInOfReserveDTO;
    }

    /**
     * 分配房间
     *
     * @param allotRoomDTO 分配房间DTO
     * @return AllotRoomDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AllotRoomDTO allotRoom(AllotRoomDTO allotRoomDTO) {
        Long currentUserId = allotRoomDTO.getCurrentUserId();
        String currentUserName = allotRoomDTO.getCurrentUserName();
        Date currentDate = new Date();
        List<OrderRoom> orderRooms = allotRoomDTO.getOrderRooms();
        // 检查是否属于当前宾馆
        checkOrderNo(orderRooms.get(0).getOrderNo(), allotRoomDTO.getCurrentSessionHotelNo());

        OrderReserve orderReserve = orderReserveMapper.selectByPrimaryKey(orderRooms.get(0).getReserveId());

        // 操作具体内容
        List<Map<String, Object>> operationContent = new ArrayList<>(orderRooms.size());

        // 订单房间信息
        orderRooms.forEach(orderRoom -> {
            orderRoom.setHotelNo(allotRoomDTO.getCurrentSessionHotelNo());
            orderRoom.setOrderRoomStateCode("no_live");
            // 设置公共属性
            setBeanCommonInfo(orderRoom, currentUserId, currentUserName, currentDate);
            orderRoomMapper.insert(orderRoom);

            Map<String, Object> content = new HashMap<>();
            content.put("roomNo", orderRoom.getRoomNo());
            content.put("roomName", orderRoom.getRoomName());
            content.put("roomTypeName", orderRoom.getRoomTypeName());
            content.put("roomPrice", orderRoom.getRoomPrice());
            content.put("orderRoomId", orderRoom.getOrderRoomId());
            operationContent.add(content);
        });

        // 房型预订信息
        int allottedNum = orderReserve.getAllottedNum() + orderRooms.size();
        if (allottedNum > orderReserve.getReserveNum()) {
            throw new BusinessException("分配房间数超过预订房间数");
        }
        orderReserve.setAllottedNum(allottedNum);
        orderReserve.setGmtModified(currentDate);
        orderReserveMapper.updateByPrimaryKey(orderReserve);

        // 处理操作信息
        OrderOperationRecord operationRecord = new OrderOperationRecord();
        operationRecord.setOrderNo(orderReserve.getOrderNo());
        operationRecord.setOperationCode("allot_room");
        operationRecord.setOperationName("分配房间");
        operationRecord.setHotelNo(allotRoomDTO.getCurrentSessionHotelNo());
        // 设置公共属性
        setBeanCommonInfo(operationRecord, currentUserId, currentUserName, currentDate);
        // 操作内容转String
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            operationRecord.setOperationContent(objectMapper.writeValueAsString(operationContent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("分配房间json转化异常", e);
        }
        orderOperationRecordMapper.insert(operationRecord);
        return allotRoomDTO;
    }

    /**
     * 预订办理入住
     *
     * @param handleCheckInDTO 预订入住 DTO
     * @return HandleCheckInDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HandleCheckInDTO handleCheckIn(HandleCheckInDTO handleCheckInDTO) {
        List<OrderRoom> orderRooms = handleCheckInDTO.getOrderRooms();
        List<Guest> guests = handleCheckInDTO.getGuests();
        Order order = orderMapper.getOrderByNo(orderRooms.get(0).getOrderNo());


        return handleCheckInDTO;
    }

    /**
     * 结账退房/单房退房
     *
     * @param checkOutDTO 退房/结账 dto
     * @return CheckOutDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CheckOutDTO checkOut(CheckOutDTO checkOutDTO) {
        Order order = checkOrderNo(checkOutDTO.getOrderNo(), checkOutDTO.getCurrentSessionHotelNo());
        if (!"confirmed".equals(order.getOrderStateCode())) {
            throw new OperationResourcesIllegalException("订单不能结账");
        } //completed
        Date currentDate = new Date();
        List<OrderRoom> orderRooms = orderRoomMapper.listOrderRoomsByOrderNo(checkOutDTO.getOrderNo());
        OrderOperationRecord operationRecord = new OrderOperationRecord();
        operationRecord.setOrderNo(checkOutDTO.getOrderNo());
        Map<String, Object> operationContent = new HashMap<>();
        if (checkOutDTO.getChecked()) { // 判断是否结账退房
            OrderIncome orderIncome = checkOutDTO.getOrderIncome();

            List<Room> rooms = roomMapper.listRoomsByOrderNo(checkOutDTO.getOrderNo());

            orderIncome.setIncomeTypeCode("outgoings"); // 支出
            orderIncome.setIncomeClassCode("refund"); // 退款
            orderIncome.setHotelNo(checkOutDTO.getCurrentSessionHotelNo());
            // 处理公共信息
            setBeanCommonInfo(orderIncome, checkOutDTO.getCurrentUserId(), checkOutDTO.getCurrentUserName(), currentDate);
            orderIncomeMapper.insert(orderIncome);
            // 保存收款详情及前台现金流
            savePayDetailAndFrontCashFlow(orderIncome, currentDate);
            // 修改订单房间信息
            orderRooms.forEach(orderRoom -> {
                if ("live".equals(orderRoom.getOrderRoomStateCode())) {
                    orderRoom.setOrderRoomStateCode("left"); // 已离开
                    orderRoom.setRealOutTime(currentDate); // 实际退房时间
                    orderRoom.setGmtModified(currentDate);
                    orderRoomMapper.updateByPrimaryKey(orderRoom);
                }
            });
            // 修改房态
            rooms.forEach(room -> {
                room.setRoomState("dirty");
                room.setOrderNo(null);
                room.setGmtModified(currentDate);
                roomMapper.updateByPrimaryKey(room);
            });
            // 修改订单收款金额及状态
            Order orderTemp = new Order();
            orderTemp.setOrderNo(orderIncome.getOrderNo());
            orderTemp.setOrderStateCode("completed");
            orderTemp.setPayMoney(order.getPayMoney().subtract(orderIncome.getIncomeMoney()));
            orderTemp.setGmtModified(currentDate);
            orderMapper.updateByOrderNoSelective(orderTemp);
            // 订单操作记录
            operationRecord.setOperationCode("check_out");
            operationRecord.setOperationName("结账退房");
            operationContent.put("refundMoney", orderIncome.getIncomeMoney());
        } else {
            List<String> roomNos = new ArrayList<>();
            roomNos.add(checkOutDTO.getRoomNo());
            Room room = roomMapper.listRoomsByRoomNo(roomNos).get(0);
            room.setRoomState("dirty");
            room.setOrderNo(null);
            room.setGmtModified(currentDate);
            roomMapper.updateByPrimaryKey(room);
            OrderRoom orderRoom = orderRooms.stream().filter(or -> or.getRoomNo().equals(checkOutDTO.getRoomNo()))
                    .collect(Collectors.toList()).get(0);
            if ("live".equals(orderRoom.getOrderRoomStateCode())) {
                orderRoom.setOrderRoomStateCode("left"); // 已离开
                orderRoom.setRealOutTime(currentDate); // 实际退房时间
                orderRoom.setGmtModified(currentDate);
                orderRoomMapper.updateByPrimaryKey(orderRoom);
            }
            // 订单操作记录
            operationRecord.setOperationCode("room_out");
            operationRecord.setOperationName("退房");
            operationContent.put("roomNo", checkOutDTO.getRoomNo());
            operationContent.put("roomName", room.getRoomName());
            operationContent.put("roomTypeName", orderRoom.getRoomTypeName());
        }

        operationRecord.setHotelNo(checkOutDTO.getCurrentSessionHotelNo());
        setBeanCommonInfo(operationRecord, checkOutDTO.getCurrentUserId(), checkOutDTO.getCurrentUserName(), currentDate);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            operationRecord.setOperationContent(objectMapper.writeValueAsString(operationContent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json转化异常", e);
        }
        orderOperationRecordMapper.insert(operationRecord);
        return checkOutDTO;
    }

    /**
     * 续费登记
     *
     * @param checkStayDTO 续费dto
     * @return CheckStayDTO
     */
    @Override
    public CheckStayDTO checkStay(CheckStayDTO checkStayDTO) {
        return null;
    }

    /**
     * 换房
     *
     * @param changeRoomDTO 换房dto
     * @return ChangeRoomDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChangeRoomDTO changeRoom(ChangeRoomDTO changeRoomDTO) {

        OrderRoom orderRoom = orderRoomMapper.selectByPrimaryKey(changeRoomDTO.getOrderRoomId());
        Room oldRoom = roomMapper.getRoomByRoomNo(orderRoom.getRoomNo());
        Room newRoom = roomMapper.getRoomByRoomNo(changeRoomDTO.getNewRoomNo());
        if (orderRoom == null
                || !this.isCurrentHotel(orderRoom.getHotelNo(), changeRoomDTO.getCurrentSessionHotelNo())) {
            throw new OperationResourcesIllegalException("订单房间不存在");
        }
        if (newRoom == null
                || !this.isCurrentHotel(newRoom.getHotelNo(), changeRoomDTO.getCurrentSessionHotelNo())) {
            throw new OperationResourcesIllegalException("新房不存在");
        }
        if (!"vacant".equals(newRoom.getRoomState())) {
            throw new BusinessException("新房不是空房");
        }
        Date currentDate = new Date();

        // 保存操作记录
        OrderOperationRecord operationRecord = new OrderOperationRecord();
        operationRecord.setOrderNo(orderRoom.getOrderNo());
        operationRecord.setHotelNo(orderRoom.getHotelNo());
        operationRecord.setOperationCode("change_room");
        operationRecord.setOperationName("换房");
        Map<String, Object> operationContent = new HashMap<>();
        // 保存操作记录旧房信息
        operationContent.put("oldRoomName", oldRoom.getRoomName());
        operationContent.put("oldRoomNo", oldRoom.getRoomNo());
        operationContent.put("oldRoomPrice", orderRoom.getRoomPrice());
        operationContent.put("oldRoomTypeName", orderRoom.getRoomTypeName());
        // 保存操作记录新房信息
        operationContent.put("newRoomName", newRoom.getRoomName());
        operationContent.put("newRoomNo", newRoom.getRoomNo());
        operationContent.put("newRoomPrice", changeRoomDTO.getNewRoomPrice());
        operationContent.put("newRoomTypeName", changeRoomDTO.getNewRoomTypeName());
        // 将map转为json字符串保存
        operationRecord.setOperationContent(this.map2JsonString(operationContent));
        // 设置操作记录公共信息
        setBeanCommonInfo(operationRecord, changeRoomDTO.getCurrentUserId(),
                changeRoomDTO.getCurrentUserName(), currentDate);
        orderOperationRecordMapper.insert(operationRecord);

        // 修改新房房态等信息
        newRoom.setOrderNo(orderRoom.getOrderNo());
        newRoom.setRoomState(oldRoom.getRoomState());
        newRoom.setGmtModified(currentDate);
        roomMapper.updateByPrimaryKeySelective(newRoom);

        // 修改订单内换房后信息
        orderRoom.setRoomName(newRoom.getRoomName());
        orderRoom.setRoomNo(newRoom.getRoomNo());
        orderRoom.setRoomTypeName(changeRoomDTO.getNewRoomTypeName());
        orderRoom.setRoomPrice(changeRoomDTO.getNewRoomPrice());
        orderRoom.setGmtModified(currentDate);
        orderRoomMapper.updateByPrimaryKeySelective(orderRoom);

        // 修改旧房房态信息
        oldRoom.setRoomState("dirty");
        oldRoom.setOrderNo(null);
        oldRoom.setGmtModified(currentDate);
        roomMapper.updateByPrimaryKey(oldRoom);

        Date orderBusinessDay = this.getBusinessDay(orderRoom.getRealInTime());
        Date currBusinessDay = this.getBusinessDay(new Date());

        // 如果时当天营业日则改消费
        if (orderBusinessDay.toString().equals(currBusinessDay.toString())) {
            List<OrderConsume> orderConsumes = orderConsumeMapper.listOrderConsumesByOrderNo(orderRoom.getOrderNo());
            Order order = orderMapper.getOrderByNo(orderRoom.getOrderNo());
            BigDecimal changeMoney = order.getOrderMoney();
            orderConsumes.forEach(orderConsume -> {
                if (oldRoom.getRoomNo().equals(orderConsume.getRoomNo())
                        && "rent".equals(orderConsume.getConsumeItemCode())
                        && orderConsume.getOperatorId().equals(1L)) {

                    BigDecimal dec = changeRoomDTO.getNewRoomPrice().subtract(orderConsume.getConsumeMoney());
                    order.setOrderMoney(order.getOrderMoney().add(dec));

                    orderConsume.setConsumeMoney(changeRoomDTO.getNewRoomPrice());
                    orderConsume.setRoomName(newRoom.getRoomName());
                    orderConsume.setRoomNo(newRoom.getRoomNo());
                    orderConsume.setGmtModified(currentDate);
                    orderConsumeMapper.updateByPrimaryKeySelective(orderConsume);

                }
            });
            if (changeMoney.compareTo(order.getOrderMoney()) != 0) {
                orderMapper.updateByOrderNoSelective(order);
            }
        }

        return changeRoomDTO;
    }

    private String map2JsonString(Map map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new BusinessException("json转化异常");
        }
        return jsonStr;
    }

    /**
     * 查询订单列表
     *
     * @param orderQuery 订单查询条件
     * @return List<Order>
     */
    @Override
    public List<Order> listOrders(OrderQuery orderQuery) {
        List<Order> orders = orderMapper.listOrders(orderQuery);

        if (orders.size() > 0) {
            List<String> orderNos = new ArrayList<>();
            orders.forEach(order -> orderNos.add(order.getOrderNo()));
            List<OrderRoom> orderRooms = orderRoomMapper.listOrderRoomsByOrderNos(orderNos);
            orders.forEach(order -> {
                List<OrderRoom> orderRoomsTemp = new ArrayList<>();
                orderRooms.forEach(orderRoom -> {
                    if (orderRoom.getOrderNo().equals(order.getOrderNo())) {
                        orderRoomsTemp.add(orderRoom);
                    }
                });
                order.setOrderRooms(orderRoomsTemp);
            });
        }

        return orders;
    }

    /**
     * 查询订单数
     *
     * @param orderQuery 订单查询条件
     * @return Integer
     */
    @Override
    public Integer countOrder(OrderQuery orderQuery) {
        return orderMapper.countOrder(orderQuery);
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param orderQuery 订单查询条件
     * @return OrderDTO
     */
    @Override
    public OrderDTO getOrderInfoByOrderNo(OrderQuery orderQuery) {
        Order order = orderMapper.getOrderByNo(orderQuery.getOrderNo());
        if (!isCurrentHotel(order.getHotelNo(), orderQuery.getCurrentSessionHotelNo())) {
            throw new NotCurrentHotelException("订单号不属于当前酒店");
        }
        List<OrderRoom> orderRooms = orderRoomMapper.listOrderRoomsByOrderNo(order.getOrderNo());
        List<OrderReserve> orderReserves = orderReserveMapper.listOrderReservesByOrderNo(order.getOrderNo());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(order);
        orderDTO.setOrderRooms(orderRooms);
        orderDTO.setOrderReserves(orderReserves);
        return orderDTO;
    }

    /**
     * 根据订单号查询订单消费信息
     *
     * @param orderQuery
     * @return
     */
    @Override
    public List<OrderConsume> listOrderConsumesByOrderNo(OrderQuery orderQuery) {
        checkOrderNo(orderQuery.getOrderNo(), orderQuery.getCurrentSessionHotelNo());
        return orderConsumeMapper.listOrderConsumesByOrderNo(orderQuery.getOrderNo());
    }

    /**
     * 根据订单号查询订单缴费信息
     *
     * @param orderQuery 订单查询条件
     * @return List<OrderIncome>
     */
    @Override
    public List<OrderIncome> listOrderIncomesByOrderNo(OrderQuery orderQuery) {
        checkOrderNo(orderQuery.getOrderNo(), orderQuery.getCurrentSessionHotelNo());
        List<OrderIncome> orderIncomes = orderIncomeMapper.listOrderIncomesByOrderNo(orderQuery.getOrderNo());
        orderIncomes.forEach(orderIncome -> {
            PayDetail payDetail = new PayDetail();
            payDetail.setTableName("order_income");
            payDetail.setPkId(orderIncome.getIncomeId());
            List<PayDetail> payDetails = payDetailMapper.listPayDetails(payDetail);
            orderIncome.setPayDetails(payDetails);
        });
        return orderIncomes;
    }

    /**
     * 保存一条消费信息
     *
     * @param orderConsumeDTO 消费dto
     * @return OrderConsume
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderConsume saveOrderConsume(CommonDTO<OrderConsume> orderConsumeDTO) {
        OrderConsume orderConsume = orderConsumeDTO.getData();
        Order order = checkOrderNo(orderConsume.getOrderNo(), orderConsumeDTO.getCurrentSessionHotelNo());
        if ("offset".equals(orderConsume.getConsumeItemCode())) {
            if (1 <= orderConsume.getConsumeMoney().compareTo(order.getOrderMoney())) {
                throw new BusinessException("冲账金额不能大于总消费额");
            }
            OrderConsume offsetOoderConsume = orderConsumeMapper.selectByPrimaryKey(orderConsume.getOffsetId());
            if (offsetOoderConsume.getOffsetMoney() != null
                    && 1 <= orderConsume.getConsumeMoney()
                    .compareTo(offsetOoderConsume.getConsumeMoney()
                            .subtract(offsetOoderConsume.getOffsetMoney()))) {
                throw new BusinessException("冲账金额不能大于未冲减金额");
            }
            if (offsetOoderConsume.getOffsetMoney() == null) {
                if (1 <= orderConsume.getConsumeMoney().compareTo(offsetOoderConsume.getConsumeMoney())) {
                    throw new BusinessException("冲账金额不能大于消费额");
                }
                offsetOoderConsume.setOffsetMoney(new BigDecimal(0));
            }
            offsetOoderConsume.setOffsetMoney(offsetOoderConsume.getOffsetMoney().add(orderConsume.getConsumeMoney()));
            orderConsumeMapper.updateByPrimaryKeySelective(offsetOoderConsume);

            orderConsume.setConsumeItemDetail("冲减:" + offsetOoderConsume.getConsumeItemDetail());
            orderConsume.setConsumeComment("冲账原因:" + orderConsume.getConsumeComment());
        }
        Date currentDate = new Date();
        // income/outgoings
        orderConsume.setBusinessDay(getBusinessDay(currentDate));
        orderConsume.setHotelNo(orderConsumeDTO.getCurrentSessionHotelNo());
        setBeanCommonInfo(orderConsume, orderConsumeDTO.getCurrentUserId(), orderConsumeDTO.getCurrentUserName(), currentDate);
        orderConsumeMapper.insert(orderConsume);


        // 修改订单消费金额
        Order orderTemp = new Order();
        orderTemp.setOrderNo(orderConsume.getOrderNo());
        if ("income".equals(orderConsume.getConsumeTypeCode())) {
            orderTemp.setOrderMoney(order.getOrderMoney().add(orderConsume.getConsumeMoney()));
        }
        if ("outgoings".equals(orderConsume.getConsumeTypeCode())) {
            orderTemp.setOrderMoney(order.getOrderMoney().subtract(orderConsume.getConsumeMoney()));
        }
        orderMapper.updateByOrderNoSelective(orderTemp);
        return orderConsume;
    }

    /**
     * 保存一条收款信息
     *
     * @param orderIncomeDTO 收款dto
     * @return OrderIncome
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderIncome saveOrderIncome(CommonDTO<OrderIncome> orderIncomeDTO) {
        OrderIncome orderIncome = orderIncomeDTO.getData();
        Order order = checkOrderNo(orderIncome.getOrderNo(), orderIncomeDTO.getCurrentSessionHotelNo());
        Date currentDate = new Date();

        // 收款/退款   receipts/refund

        orderIncome.setHotelNo(orderIncomeDTO.getCurrentSessionHotelNo());
        setBeanCommonInfo(orderIncome, orderIncomeDTO.getCurrentUserId(), orderIncomeDTO.getCurrentUserName(), currentDate);
        if ("offset".equals(orderIncome.getIncomeClassCode())) {
            orderIncome.setIncomeComment("冲账原因:" + orderIncome.getIncomeComment());
        }

        orderIncomeMapper.insert(orderIncome);
        // 收款详情
        savePayDetailAndFrontCashFlow(orderIncome, currentDate);

        // 修改订单收款金额
        Order orderTemp = new Order();
        orderTemp.setOrderNo(orderIncome.getOrderNo());
        if ("income".equals(orderIncome.getIncomeTypeCode())) {
            orderTemp.setPayMoney(order.getPayMoney().add(orderIncome.getIncomeMoney()));
        }
        if ("outgoings".equals(orderIncome.getIncomeTypeCode())) {
            orderTemp.setPayMoney(order.getPayMoney().subtract(orderIncome.getIncomeMoney()));
        }

        orderMapper.updateByOrderNoSelective(orderTemp);

        return orderIncome;
    }

    /**
     * 保存收款详情及前台现金流
     *
     * @param orderIncome 收款详情
     * @param currentDate 当前时间
     */
    private void savePayDetailAndFrontCashFlow(OrderIncome orderIncome, Date currentDate) {
        List<PayDetail> payDetails = orderIncome.getPayDetails();
        if ("offset".equals(orderIncome.getIncomeClassCode())) {
            if (null == orderIncome.getOffsetId()) {
                throw new BusinessException("收款income标识不能为空");
            }
            OrderQuery orderQuery = new OrderQuery();
            orderQuery.setOrderNo(orderIncome.getOrderNo());

            PayDetail payQuery = new PayDetail();
            payQuery.setTableName("order_income");
            payQuery.setPkId(orderIncome.getOffsetId());
            List<PayDetail> offsetPays = payDetailMapper.listPayDetails(payQuery);

            offsetPays.forEach(offsetPay -> payDetails.forEach(payDetail -> {
                if (null == payDetail.getOffsetId()) {
                    throw new BusinessException("收款标识不能为空");
                }
                if (offsetPay.getPayDetailId().equals(payDetail.getOffsetId())) {
                    if (!offsetPay.getPayMethodCode().equals(payDetail.getPayMethodCode())) {
                        throw new BusinessException("支付方式不符合，不能冲账");
                    }
                    if (offsetPay.getOffsetMoney() != null
                            && 1 <= payDetail.getPayMoney().compareTo(offsetPay.getPayMoney().subtract(offsetPay.getOffsetMoney()))) {
                        throw new BusinessException("冲账金额不能大于未冲账金额");
                    }
                    if (offsetPay.getOffsetMoney() == null) {
                        if (1 <= payDetail.getPayMoney().compareTo(offsetPay.getPayMoney())) {
                            throw new BusinessException("冲账金额不能大于收款金额");
                        }
                        offsetPay.setOffsetMoney(new BigDecimal(0));
                    }
                    offsetPay.setOffsetMoney(offsetPay.getOffsetMoney().add(payDetail.getPayMoney()));
                    payDetailMapper.updateByPrimaryKeySelective(offsetPay);
                }

            }));
        }
        payDetails.forEach(payDetail -> {
            if (0 != BigDecimal.valueOf(0).compareTo(payDetail.getPayMoney())) {
                payDetail.setTableName("order_income");
                payDetail.setPkId(orderIncome.getIncomeId());
                payDetail.setDeleted(false);
                payDetail.setGmtCreate(currentDate);
                payDetail.setGmtModified(currentDate);
                payDetail.setPayType(orderIncome.getIncomeTypeCode());
                payDetailMapper.insert(payDetail);
            }

            // 将现金存入前台现金流水
            if ("cash".equals(payDetail.getPayMethodCode()) &&
                    0 != (payDetail.getPayMoney().compareTo(new BigDecimal(0)))) {
                FrontCashFlow frontCashFlow = new FrontCashFlow();
                frontCashFlow.setFlowType(orderIncome.getIncomeTypeCode());
                frontCashFlow.setFlowMoney(payDetail.getPayMoney());
                if ("income".equals(orderIncome.getIncomeTypeCode())) {
                    frontCashFlow.setItemCode("order_in");
                    frontCashFlow.setItemName("订单收款");
                } else {
                    frontCashFlow.setItemCode("order_out");
                    frontCashFlow.setItemName("订单退款");
                }
                if ("offset".equals(orderIncome.getIncomeClassCode())) {
                    frontCashFlow.setItemCode("order_offset");
                    frontCashFlow.setItemName("订单收款冲账");
                }
                frontCashFlow.setFlowClass("order");
                frontCashFlow.setHotelNo(orderIncome.getHotelNo());
                frontCashFlow.setOrderNo(orderIncome.getOrderNo());
                frontCashFlow.setOperatorId(orderIncome.getOperatorId());
                frontCashFlow.setOperatorName(orderIncome.getOperatorName());
                saveFrontCashFlow(frontCashFlow, currentDate);
            }
        });
    }

    /**
     * insert某个entity时设置共有的属性
     *
     * @param entity       某个实体
     * @param operatorId   操作人id
     * @param operatorName 操作人名称
     * @param currentDate  当前时间
     */
    private void setBeanCommonInfo(BaseEntity entity, Long operatorId, String operatorName, Date currentDate) {
        entity.setDeleted(false);
        entity.setGmtCreate(currentDate);
        entity.setGmtModified(currentDate);
        entity.setOperatorId(operatorId);
        entity.setOperatorName(operatorName);
    }

    /**
     * 根据入住时间获取营业日
     *
     * @param date 登记时间
     * @return 年月日
     */
    private Date getBusinessDay(Date date) {
        Calendar openDayDate = Calendar.getInstance();
        openDayDate.setTime(date);
        // @TODO 暂时设置为6
        Integer openDayEndTime = 6;
        if (openDayDate.get(Calendar.HOUR_OF_DAY) < openDayEndTime) {
            openDayDate.set(Calendar.DATE, openDayDate.get(Calendar.DATE) - 1);
        }
        openDayDate.set(Calendar.HOUR_OF_DAY, 0);
        openDayDate.set(Calendar.MINUTE, 0);
        openDayDate.set(Calendar.SECOND, 0);
        return openDayDate.getTime();
    }

    /**
     * 检查当前操作的是否属于当前hotel
     *
     * @param orderNo               订单号
     * @param currentSessionHotelNo 当前宾馆编号
     * @return order
     */
    private Order checkOrderNo(String orderNo, String currentSessionHotelNo) {
        Order order = orderMapper.getOrderByNo(orderNo);
        if (order == null || !isCurrentHotel(order.getHotelNo(), currentSessionHotelNo)) {
            throw new NotCurrentHotelException("订单号不属于当前酒店");
        }
        return order;
    }

    /**
     * 保存前台现金流水
     *
     * @param frontCashFlow 现金流
     * @param date          时间
     */
    private void saveFrontCashFlow(FrontCashFlow frontCashFlow, Date date) {
        BigDecimal balance = BigDecimal.valueOf(0);
        if (frontCashFlowMapper.count(frontCashFlow.getHotelNo()) > 0) {
            FrontCashFlow lastFrontCashFlow = frontCashFlowMapper.getLast(frontCashFlow.getHotelNo());
            balance = balance.add(lastFrontCashFlow.getBalance());
        }
        if ("income".equals(frontCashFlow.getFlowType())) {
            balance = balance.add(frontCashFlow.getFlowMoney());
        } else {
            balance = balance.subtract(frontCashFlow.getFlowMoney());
        }
        frontCashFlow.setBalance(balance);
        frontCashFlow.setGmtCreate(date);
        frontCashFlow.setGmtModified(date);
        frontCashFlowMapper.insert(frontCashFlow);
    }
}
