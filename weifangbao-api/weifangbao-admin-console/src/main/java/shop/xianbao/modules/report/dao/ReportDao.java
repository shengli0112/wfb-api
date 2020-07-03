package shop.xianbao.modules.report.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
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
@Mapper
public interface ReportDao extends BaseDao<ReportEntity> {
    List<ReportListDTO> getList(Map<String, Object> params);

    ReportDTO getInfo(Long id);

    List<ReportListDTO> getInvalidList();

    List<ReportListDTO> getWillInvalidList();
}