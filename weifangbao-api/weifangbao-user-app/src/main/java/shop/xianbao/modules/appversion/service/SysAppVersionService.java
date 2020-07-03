package shop.xianbao.modules.appversion.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.appversion.dto.SysAppVersionDTO;
import shop.xianbao.modules.appversion.dto.SysAppVersionListDTO;
import shop.xianbao.modules.appversion.entity.SysAppVersionEntity;

/**
 * @author will
 * @since 1.0.0 2019-03-22
 */
public interface SysAppVersionService extends XianbaoCrudService<SysAppVersionEntity, SysAppVersionDTO, SysAppVersionListDTO> {

    //    KvEnum USER_ANDROID = new KvEnum(1, "用户Android端");
    //    KvEnum SELLER_ANDROID = new KvEnum(2, "商家Android端");

    //检索版本号
    public SysAppVersionDTO checkAndReturnLast(String version, int type);
}