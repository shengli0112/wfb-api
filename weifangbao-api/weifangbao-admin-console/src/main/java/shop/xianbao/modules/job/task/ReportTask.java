package shop.xianbao.modules.job.task;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.xianbao.modules.message.service.ApiSmsMessageService;
import shop.xianbao.modules.message.service.MessageService;
import shop.xianbao.modules.report.dto.ReportListDTO;
import shop.xianbao.modules.report.entity.ReportEntity;
import shop.xianbao.modules.report.enums.ReportStatusEnum;
import shop.xianbao.modules.report.service.ReportService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ReportTask
 * @description: 报备自动任务
 * @author: yh
 * @create: 2019-11-19 10:51
 **/
@Component("reportTask")
public class ReportTask implements ITask {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportService reportService;

    @Autowired
    private ApiSmsMessageService apiSmsMessageService;

    @Autowired
    private MessageService messageService;

    @Override
    public void run(String params) {
        log.info("{}自动任务[处理报备]-开始", getClass().getName());
        long start = System.currentTimeMillis();
        List<ReportListDTO> dtoList = reportService.getInvalidList();

        List<ReportListDTO> willInvalidList = reportService.getWillInvalidList();

        List<ReportEntity> invalidList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {

            dtoList.forEach(e -> {
                ReportEntity reportEntity = new ReportEntity();
                reportEntity.setId(e.getId());
                reportEntity.setStatus(ReportStatusEnum.INVALID.getValue());
                invalidList.add(reportEntity);
            });
            if (CollectionUtils.isNotEmpty(invalidList)) {
                reportService.updateBatchById(invalidList);
            }
            // 发送失效短信
            apiSmsMessageService.sendInvalidMessage(dtoList);

            messageService.sendInvalidMessage(dtoList);
        }
        if (CollectionUtils.isNotEmpty(willInvalidList)) {
            // 发送报备即将过期短信
            apiSmsMessageService.sendWillInvalidMessage(willInvalidList);
            messageService.sendWillInvalidMessage(willInvalidList);
        }
        log.info("处理报备失效{}条,即将过期提醒{}条", dtoList != null ? dtoList.size() : 0, willInvalidList != null ? willInvalidList.size() : 0);
        log.info("{}自动任务[处理过期报备]-结束，任务耗时：{}", getClass().getName(), System.currentTimeMillis() - start);

    }
}
