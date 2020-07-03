/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 * <p>
 * https://www.ruitukeji.com
 * <p>
 * 版权所有，侵权必究！
 */

package shop.xianbao.modules.oss.cloud;

import shop.xianbao.common.constant.Constant;
import shop.xianbao.common.utils.SpringContextUtils;
import shop.xianbao.modules.sys.service.SysParamsService;

/**
 * 文件上传Factory
 * @author Tim tim@ruitukeji.com
 */
public final class OSSFactory {
    private static SysParamsService sysParamsService;

    static {
        OSSFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static AbstractCloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysParamsService.getValueObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.FASTDFS.getValue()) {
            return new FastDFSCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.LOCAL.getValue()) {
            return new LocalCloudStorageService(config);
        }

        return null;
    }

}