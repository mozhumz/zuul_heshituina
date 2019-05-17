package com.mozhumz.zuul.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lshaci
 * @since 2019-05-05
 */
@Data
//@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("登录")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "web应用地址")
    private String webUrl;


}
