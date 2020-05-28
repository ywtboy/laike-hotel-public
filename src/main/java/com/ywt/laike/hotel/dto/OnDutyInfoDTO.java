package com.ywt.laike.hotel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywt.laike.hotel.model.FrontCashFlow;
import com.ywt.laike.hotel.model.PayDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 当班信息Dto 接班时间 当前现金 收入 支出 等
 * @author ywt
 */
@Getter
@Setter
@ToString
public class OnDutyInfoDTO {
    /**
     * 接班时间 即上次交班时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date onComingTime;

    /**
     * 当前当班人
     */
    private String onComingRealName;

    private Long onComingUserId;

    /**
     * 当前现金
     */
    private BigDecimal currentCash;
    /**
     * 上次交班现金
     */
    private BigDecimal lastHandoverCash;

    /**
     * 当前订单结余
     */
    private BigDecimal currentOrderBalance;

    /**
     * 当前订单收支详情 如现金收支 微信收支。。。
     */
//    private List<PayDetail> currentOrderInOutDetail;
    private List<FrontCashFlow> otherFrontCashFlows;

    /**
     * 当前订单收支详情 如现金收支 微信收支。。。
     */
    private List<InOut> orderInOut;

    public void setOrderInOut(List<PayDetail> payDetails) {
        if (null == orderInOut) {
            orderInOut = new ArrayList<>();
        }
        setCurrentOrderBalance(new BigDecimal(0));
        payDetails.forEach(payDetail -> {

            if ("income".equals(payDetail.getPayType())) {
                    setCurrentOrderBalance(getCurrentOrderBalance()
                            .add(payDetail.getPayMoney()));
                }
            if ("outgoings".equals(payDetail.getPayType())) {
                setCurrentOrderBalance(getCurrentOrderBalance()
                        .subtract(payDetail.getPayMoney()));
            }

            boolean isExisted = false;
            if (orderInOut.size() > 0) {
                Iterator<InOut> iterator = orderInOut.iterator();
                while (iterator.hasNext()) {
                    InOut inOut = iterator.next();
                    if (payDetail.getPayMethodCode().equals(inOut.getPayMethodCode())) {
                        if ("income".equals(payDetail.getPayType())) {
                            inOut.setIncome(inOut.getIncome().add(payDetail.getPayMoney()));
                            inOut.setBalance(inOut.getBalance().add(payDetail.getPayMoney()));
                        }
                        if ("outgoings".equals(payDetail.getPayType())) {
                            inOut.setOutgoings(inOut.getOutgoings().add(payDetail.getPayMoney()));
                            inOut.setBalance(inOut.getBalance().subtract(payDetail.getPayMoney()));
                        }
                        isExisted = true;
                        continue;
                    }
                }
            }
            if (!isExisted) {
                OnDutyInfoDTO.InOut inOut = getInOut();
                inOut.setPayMethodCode(payDetail.getPayMethodCode());
                if ("income".equals(payDetail.getPayType())) {
                    inOut.setIncome(payDetail.getPayMoney());
                    inOut.setBalance(inOut.getBalance().add(payDetail.getPayMoney()));
                }else {
                    inOut.setOutgoings(payDetail.getPayMoney());
                    inOut.setBalance(inOut.getBalance().subtract(payDetail.getPayMoney()));
                }
                orderInOut.add(inOut);
            }
        });
    }

    private InOut getInOut() {
        return new InOut();
    }

    @Setter
    @Getter
    class InOut {
        private String payMethodCode;
        private BigDecimal balance = new BigDecimal(0);
        private BigDecimal income = new BigDecimal(0);
        private BigDecimal outgoings = new BigDecimal(0);
    }

}
