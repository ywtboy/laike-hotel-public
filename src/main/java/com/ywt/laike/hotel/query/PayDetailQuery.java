package com.ywt.laike.hotel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author ywt
 */
@Setter
@Getter
@ToString
public class PayDetailQuery {
    private String tableName;
    private List<Long> pkIds;
}
