package com.ywt.laike.hotel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: ywt
 */
@Getter
@Setter
@ToString
public class DicCommonQuery extends BaseQuery implements Serializable {
    private Long dicTypeId;
    private Long parentId;
    private Integer dicLevel;
    private Boolean deleted;
    private static final long serialVersionUID = 1L;
}
