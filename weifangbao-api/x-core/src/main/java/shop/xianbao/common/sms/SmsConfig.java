/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 短信配置信息
 *
 * @author Tim tim@ruitukeji.com
 */
@Data
@ApiModel(value = "短信配置信息")
public class SmsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "平台 1：阿里云  2：腾讯云  3:聚合数据")
    @Range(min=1, max=2, message = "{sms.platform.range}")
    private Integer platform;

    @ApiModelProperty(value = "阿里云AccessKeyId")
    @NotBlank(message="{aliyun.accesskeyid.require}")
    private String aliyunAccessKeyId;

    @ApiModelProperty(value = "阿里云AccessKeySecret")
    @NotBlank(message="{aliyun.accesskeysecret.require}")
    private String aliyunAccessKeySecret;

    @ApiModelProperty(value = "阿里云短信签名")
    @NotBlank(message="{aliyun.signname.require}")
    private String aliyunSignName;

    @ApiModelProperty(value = "阿里云短信模板")
    @NotBlank(message="{aliyun.templatecode.require}")
    private String aliyunTemplateCode;

    @ApiModelProperty(value = "腾讯云AppId")
    @NotNull(message="{qcloud.appid.require}")
    private Integer qcloudAppId;

    @ApiModelProperty(value = "腾讯云AppKey")
    @NotBlank(message="{qcloud.appkey.require}")
    private String qcloudAppKey;

    @ApiModelProperty(value = "腾讯云短信签名")
    @NotBlank(message="{qcloud.signname.require}")
    private String qcloudSignName;

    @ApiModelProperty(value = "腾讯云短信模板ID")
    @NotBlank(message="{qcloud.templateid.require}")
    private String qcloudTemplateId;

    @ApiModelProperty(value = "聚合数据AppKey")
    @NotBlank(message="{juhe.juheAppKey.require}")
    private String juheAppKey;
}