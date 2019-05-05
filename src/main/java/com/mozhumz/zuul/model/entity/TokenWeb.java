package com.mozhumz.zuul.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 认证中心-web应用-用户退出地址
 * </p>
 *
 * @author lshaci
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_token_web")
@ApiModel(value="TokenWeb对象", description="认证中心-web应用-用户退出地址")
public class TokenWeb extends Model<TokenWeb> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "对应t_token id")
    @TableField("tokenId")
    private Long tokenId;

    @ApiModelProperty(value = "web应用登出地址")
    @TableField("outUrl")
    private String outUrl;

    @ApiModelProperty(value = "web应用-用户-sessionId")
    @TableField("sessionId")
    private String sessionId;

    @TableField("createDate")
    private LocalDateTime createDate;

    @TableField("updateDate")
    private LocalDateTime updateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
