package com.mozhumz.zuul.model.dto;

import com.hyj.util.anno.IsNeed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送邮件")
public class SendEmailDto {
    @ApiModelProperty(value = "发件人邮箱")
    @IsNeed(flag = true)
    private String sendEmail;
    @ApiModelProperty(value = "发件人授权码")
    @IsNeed(flag = true)
    //vtkkubmpydiqcagi
    private String sendPwd;
    @ApiModelProperty(value = "收件人邮箱")
    @IsNeed(flag = true)
    private String receiveEmail;

    @ApiModelProperty(value = "邮件标题")
    @IsNeed(flag = true)
    private String title;
    @ApiModelProperty(value = "邮件内容")
    @IsNeed(flag = true)
    private String content;

    @ApiModelProperty(value = "存入redis的key:前缀+id")
    @IsNeed(flag = true)
    private String key;

    private String username;
}
