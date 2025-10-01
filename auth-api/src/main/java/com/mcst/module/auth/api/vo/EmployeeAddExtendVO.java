package com.mcst.module.auth.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liu yijun
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class EmployeeAddExtendVO implements Serializable {

    private String id;

    private Integer orgid;
}
