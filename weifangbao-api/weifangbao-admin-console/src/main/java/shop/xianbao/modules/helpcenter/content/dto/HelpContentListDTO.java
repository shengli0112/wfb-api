package shop.xianbao.modules.helpcenter.content.dto;

import lombok.Data;

/**
 * 帮助内容列表DTO
 *
 * @author wangliangyuan wangliangyuan@ruitukeji.com
 * @since 1.0.0 2018-12-29
 */
@Data
public class HelpContentListDTO {

    private Long id;

    private Integer sort;

    private String title;

    /**
     * 分类名称
     */
    private String typeName;
}
