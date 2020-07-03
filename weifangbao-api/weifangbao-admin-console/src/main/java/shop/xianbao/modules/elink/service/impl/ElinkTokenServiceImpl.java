package shop.xianbao.modules.elink.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.xianbao.common.elink.config.ELinkConfig;
import shop.xianbao.common.elink.entity.ElinkTokenEntity;
import shop.xianbao.common.elink.service.ElinkService;
import shop.xianbao.common.redis.RedisKeys;
import shop.xianbao.common.redis.RedisUtils;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.elink.dao.ElinkTokenDao;
import shop.xianbao.modules.elink.dto.ElinkTokenDTO;
import shop.xianbao.modules.elink.dto.ElinkTokenListDTO;
import shop.xianbao.modules.elink.service.ElinkTokenService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 易联云平台访问令牌表
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Service
public class ElinkTokenServiceImpl extends XianbaoCrudServiceImpl<ElinkTokenDao, ElinkTokenEntity, ElinkTokenDTO, ElinkTokenListDTO> implements ElinkTokenService {

    @Autowired
    private ELinkConfig eLinkConfig;

    @Autowired
    private ElinkService elinkService;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private ElinkTokenDao elinkTokenDao;

    @Override
    public QueryWrapper<ElinkTokenEntity> getWrapper(Map<String, Object> params) {
        // 这里根据业务自定义查询条件

        QueryWrapper<ElinkTokenEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");

        // 这里返回null,表示无条件查询
        return wrapper;
    }

    @Override
    public String getToken() {
        String clientId = eLinkConfig.getClientId();
        String token = String.valueOf(redisUtils.get(RedisKeys.getElinkKey(clientId)));
        String refreshToken = null;
        String scope = null;
        if (StringUtils.isBlank(token) || token.equals("null")) {
            token = elinkTokenDao.selectElinkToken(clientId);
            //保存到redis
            redisUtils.set(RedisKeys.getElinkKey(clientId), token, RedisUtils.NOT_EXPIRE);
        }
        if (StringUtils.isBlank(token)) {
            String result = elinkService.getFreedomToken();
            JSONObject json = new JSONObject(result);
            JSONObject body = json.getJSONObject("body");
            token = body.getString("access_token");
            refreshToken = body.getString("refresh_token");
            scope = body.getString("scope");
            //保存到数据库
            ElinkTokenEntity elinkTokenEntity = new ElinkTokenEntity();
            elinkTokenEntity.setClientId(clientId);
            elinkTokenEntity.setAccessToken(token);
            elinkTokenEntity.setRefreshToken(refreshToken);
            elinkTokenEntity.setScope(scope);
            elinkTokenDao.insert(elinkTokenEntity);
            //保存到redis
            redisUtils.set(RedisKeys.getElinkKey(clientId), token, RedisUtils.NOT_EXPIRE);
        }
        return token;
    }
}