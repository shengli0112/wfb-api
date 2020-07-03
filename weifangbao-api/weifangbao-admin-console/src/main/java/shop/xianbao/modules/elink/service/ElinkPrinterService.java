package shop.xianbao.modules.elink.service;

import shop.xianbao.common.service.XianbaoCrudService;
import shop.xianbao.modules.elink.dto.ElinkPrinterDTO;
import shop.xianbao.modules.elink.dto.ElinkPrinterListDTO;
import shop.xianbao.modules.elink.entity.ElinkPrinterEntity;

/**
 * 易联云打印机
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
public interface ElinkPrinterService extends XianbaoCrudService<ElinkPrinterEntity, ElinkPrinterDTO, ElinkPrinterListDTO> {

    Integer checkPrinter(String machineCode);

    void savePrint(Long storeId, String machineCode, String mSign, String printName);

    void deletePrinter(Long storeId, String machineCode);

    /**
     * 获取默认打印机
     *
     * @return
     */
    String selectDefaultPrinter(Long storeId);

}