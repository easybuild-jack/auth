package com.mcst.module.auth.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class MultipleSelectVO implements Serializable {

    private String value;

    private String name;
}
