package shop.xianbao.common.elink.service;

import org.springframework.stereotype.Component;

/**
 * @program: PrinterService
 * @description:
 * @author: yh
 * @create: 2019-05-16 18:08
 **/
@Component
public interface ElinkService {
    String getFreedomToken();

    String refreshToken(String refresh_token);

    String addPrinter(String token, String machine_code, String msign, String printName);

    String speedAu(String machine_code, String qr_key);

    String print(String token, String machine_code, String content, String origin_id);

    String deletePrinter(String token, String machine_code);

    String addPrintMenu(String token, String machine_code, String content);

    String shutDownRestart(String token, String machine_code, String response_type);

    String setSound(String token, String machine_code, String response_type, String voice);

    String getPrintInfo(String token, String machine_code);

    String getVersion(String token, String machine_code);

    String cancelAll(String token, String machine_code);

    String cancelOne(String token, String machine_code, String order_id);

    String setIcon(String token, String machine_code, String img_url);

    String deleteIcon(String token, String machine_code);

    String btnPrint(String token, String machine_code, String response_type);

    String getOrder(String token, String machine_code, String response_type);
}
