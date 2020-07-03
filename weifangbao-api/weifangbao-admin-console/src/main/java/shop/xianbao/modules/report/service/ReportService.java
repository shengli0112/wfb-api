package shop.xianbao.modules.report.service;

import shop.xianbao.common.page.PageData;
import shop.xianbao.common.service.BaseService;
import shop.xianbao.common.utils.Result;
import shop.xianbao.modules.report.dto.ReportDTO;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.report.entity.ReportEntity;

import java.util.List;
import java.util.Map;

/**
 * 交易表
 *
 * @author yanghuan yanghua6013@dingtalk.com
 * @since 1.0.0 2019-11-14
 */
public interface ReportService extends BaseService<ReportEntity> {
    PageData<ReportListDTO> page(Map<String, Object> params);

    List<ReportListDTO> list(Map<String, Object> params);

    List<ReportDTO> comboList(Map<String, Object> params);

    ReportDTO get(Long id);

    boolean add(ReportDTO dto);

    boolean update(ReportDTO dto);

    int delete(Long[] ids);

    /**
     * @param dto
     * @return
     */
    Result updateStatus(ReportDTO dto);

    List<ReportListDTO> getInvalidList();

    List<ReportListDTO> getWillInvalidList();
}