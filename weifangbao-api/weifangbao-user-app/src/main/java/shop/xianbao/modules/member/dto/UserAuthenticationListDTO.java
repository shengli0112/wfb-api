package shop.xianbao.modules.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import shop.xianbao.common.dto.XianbaoBaseDTO;

import java.util.Date;

/**
 * 用户实名认证表
 * 列表DTO
 *
 * @author yanghuan
 * @since 1.0.0 2019-04-08
 */
@Data
@ApiModel(value = "用户实名认证表-列表")
public class UserAuthenticationListDTO extends XianbaoBaseDTO {
    private static final long serialVersionUID = 1L;

    // 出于代码规范的考虑,提出建议:
    // 从以下属性中选取显示列表需要的字段,其余的一律删除(包括这两行注释)

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "union_id")
    private Long unionId;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "身份证照片 正面")
    private String idCertFront;

    @ApiModelProperty(value = "身份证照片 反面")
    private String idCertBack;

    @ApiModelProperty(value = "身份证照片 手持")
    private String idCertHand;

    @ApiModelProperty(value = "审核状态 0申请中 1待审核 2审核通过 3审核驳回")
    private Integer status;

    @ApiModelProperty(value = "排序字段 小值在前")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "更新者id")
    private Long updater;

    @ApiModelProperty(value = "删除标识位：0正常 1删除")
    private Integer isDeleted;

}