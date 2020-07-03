package shop.xianbao.modules.elink.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import shop.xianbao.common.exception.ErrorCode;
import shop.xianbao.common.exception.XianbaoException;
import shop.xianbao.common.service.impl.XianbaoCrudServiceImpl;
import shop.xianbao.modules.elink.dao.ElinkPrinterDao;
import shop.xianbao.modules.elink.dto.ElinkPrinterDTO;
import shop.xianbao.modules.elink.dto.ElinkPrinterListDTO;
import shop.xianbao.modules.elink.entity.ElinkPrinterEntity;
import shop.xianbao.modules.elink.service.ElinkPrinterService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 易联云打印机
 *
 * @author yanghuan
 * @since 1.0.0 2019-05-17
 */
@Service
public class ElinkPrinterServiceImpl extends XianbaoCrudServiceImpl<ElinkPrinterDao, ElinkPrinterEntity, ElinkPrinterDTO, ElinkPrinterListDTO> implements ElinkPrinterService {

    @Resource
    private ElinkPrinterDao elinkPrinterDao;

    @Override
    public QueryWrapper<ElinkPrinterEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<ElinkPrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", params.get("storeId"));
        wrapper.eq("is_deleted", 0);
        wrapper.orderByDesc("create_date");
        return wrapper;
    }

    @Override
    public Integer checkPrinter(String machineCode) {
        QueryWrapper<ElinkPrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_code", machineCode);
        wrapper.eq("is_deleted", 0);
        return elinkPrinterDao.selectCount(wrapper);
    }

    @Override
    public void savePrint(Long storeId, String machineCode, String mSign, String printName) {
        ElinkPrinterEntity elinkPrinterEntity = new ElinkPrinterEntity();
        elinkPrinterEntity.setStoreId(storeId);
        elinkPrinterEntity.setPrintName(printName);
        elinkPrinterEntity.setMachineCode(machineCode);
        elinkPrinterEntity.setMsign(mSign);
        elinkPrinterEntity.setIsDeleted(0);
        insert(elinkPrinterEntity);
        //处理默认打印机
        checkDefaultPrinter(storeId);
    }

    @Override
    public void deletePrinter(Long storeId, String machineCode) {
        QueryWrapper<ElinkPrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", storeId);
        wrapper.eq("machine_code", machineCode);
        wrapper.eq("is_deleted", 0);
        ElinkPrinterEntity printerEntity = selectOne(wrapper);
        if (printerEntity != null) {
            printerEntity.setIsDeleted(1);
            updateById(printerEntity);
        } else {
            throw new XianbaoException("打印机不存在");
        }
    }

    @Override
    public String selectDefaultPrinter(Long storeId) {
        QueryWrapper<ElinkPrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", storeId);
        wrapper.eq("is_default", 1);
        wrapper.eq("is_deleted", 0);
        ElinkPrinterEntity printerEntity = selectOne(wrapper);
        if (printerEntity != null) {
            return printerEntity.getMachineCode();
        } else {
            throw new XianbaoException(ErrorCode.NO_PRINTER);
        }
    }

    public void checkDefaultPrinter(Long storeId) {
        QueryWrapper<ElinkPrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("store_id", storeId);
        wrapper.eq("is_deleted", 0);
        wrapper.orderByDesc("create_date");
        List<ElinkPrinterEntity> list = selectList(wrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            Boolean defaultFlag = false;
            for (ElinkPrinterEntity e : list) {
                if (e.getIsDefault() == 1) {
                    defaultFlag = true;
                }
            }
            if (!defaultFlag) {
                //没有默认打印机  将最新添加的打印机设置为默认
                ElinkPrinterEntity elinkPrinterEntity = list.get(0);
                elinkPrinterEntity.setIsDefault(1);
                updateById(elinkPrinterEntity);
            }
        }
    }

}