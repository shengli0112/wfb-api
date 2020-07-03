package shop.xianbao.modules.elink.service;

import shop.xianbao.common.elink.entity.ElinkTokenEntity;
import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.elink.dto.ElinkTokenDTO;
import shop.xianbao.modules.elink.dto.ElinkTokenListDTO;

/**
 * 易联云平台访问令牌表
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
public interface ElinkTokenService extends XianbaoCrudService<ElinkTokenEntity, ElinkTokenDTO, ElinkTokenListDTO> {

    /**
     * methd: getToken
     *
     * @Description: 获取elink 令牌
     * 1. 从redis sys：param中根据clientId获取，存在则返回
     * 2. redis不存在就从数据库获取 并保存到redis
     * 3. 数据库不存在，调用elink的getFreedomToken 获取并保存
     * @return: token
     * @author: yh
     * @date: 2019/5/17 10:43
     */
    String getToken();
}