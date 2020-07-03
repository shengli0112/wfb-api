package shop.xianbao.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import shop.xianbao.common.dao.BaseDao;
import shop.xianbao.modules.member.dto.MemberDTO;
import shop.xianbao.modules.member.entity.MemberBaseinfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员信息表
 *
 * @author wdp
 * @since 1.0.0 2019-01-08
 */
@Mapper
public interface MemberBaseinfoDao extends BaseDao<MemberBaseinfoEntity> {

    /**
     * 查询会员信息
     *
     * @param params
     * @return
     */
    List<MemberDTO> getList(Map<String, Object> params);
}