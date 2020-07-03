package shop.xianbao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Methods Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>05/15/2019</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MethodsTest {
//    Methods methods = new Methods();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
//        methods.init("1", "2");

    }

    /**
     * Method: init(String client_id, String client_secret, String code)
     */
    @Test
    public void testInitForClient_idClient_secretCode() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: init(String client_id, String client_secret)
     */
    @Test
    public void testInitForClient_idClient_secret() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getToken()
     */
    @Test
    public void testGetToken() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getFreedomToken()
     */
    @Test
    public void testGetFreedomToken() throws Exception {
//        String token = methods.getFreedomToken();
//        System.out.println(token);

    }

    /**
     * Method: refreshToken()
     */
    @Test
    public void testRefreshToken() throws Exception {

    }

    /**
     * Method: addPrinter(String machine_code, String msign)
     */
    @Test
    public void testAddPrinter() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: speedAu(String machine_code, String qr_key)
     */
    @Test
    public void testSpeedAu() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: print(String machine_code, String content, String origin_id)
     */
    @Test
    public void testPrint() throws Exception {
        String content =
//            "<FH2><FW2> **京东到家**</FW2></FH2>\n"
//            + "................................\r"
//            + "下单时间：2017-05-08T10：39：56\r"
//            + "订单编号：1206479504417779722\n"
//            + "***************商品*************\r"
//            + "<FH><FW>荷包蛋 1 3</FW></FH>\r"
//            + "<FH><FW>铁板黑椒牛柳炒饭-黑椒味 </FW></FH>\r"
//            + "<FH><FW> 1 18</FW></FH>\r"
//            + "<FH><FW>铁板黑椒牛柳炒饭-黑椒味特辣 1 3</FW></FH>\r"
//            + "铁板黑椒牛柳炒饭-黑椒味特辣黑椒味特辣特辣 1 3\r"
//            + "<FH2><FW2> **#3 完 **</FW2></FH2>";
            "................................\r"
                + "2017-05-08T10：39：56\r"
                + "................................\r"
                + "<FH2><FW2><center>**喜地**</center></FW2></FH2>\n"
                + "................................\r"
                + "<FH2><FW2> --曹州味道--</FW2></FH2>\r"
                + "订单编号：1206479504417779722\r"
                + "下单时间：2017-05-08T10：39：56\r"
                + "期望配送时间：2017-05-08 10:00\r"
                + "订单总额：¥2323.00\n"
                + "<FH><FW> ****1号篮子****</FW></FH>\r"
                + "<FH><FW>浦之灵新鲜玉米大玉米 X32 ￥212.00</FW></FH>\r"
                + "精品袋装\r"
                + "<FH><FW>浦之灵新鲜玉米大玉米 X32 ￥212.00</FW></FH>\r"
                + "规格名称文字内容\r"
                + "<FH><FW>浦之灵新鲜玉米大玉米 X32 ￥212.00</FW></FH>\r"
                + "*******************************\r"
                + "-请扫描下方二维码查看溯源信息\r"
                + "*******************************\r"
                + "<FH2><FW2> **#3 完 **</FW2></FH2>";
//        String result = methods.print("4004611748", content, "1118022901413449730");
//        System.out.println(result);
    }

    /**
     * Method: deletePrinter(String machine_code)
     */
    @Test
    public void testDeletePrinter() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: addPrintMenu(String machine_code, String content)
     */
    @Test
    public void testAddPrintMenu() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: shutDownRestart(String machine_code, String response_type)
     */
    @Test
    public void testShutDownRestart() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setSound(String machine_code, String response_type, String voice)
     */
    @Test
    public void testSetSound() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getPrintInfo(String machine_code)
     */
    @Test
    public void testGetPrintInfo() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getVersion(String machine_code)
     */
    @Test
    public void testGetVersion() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: cancelAll(String machine_code)
     */
    @Test
    public void testCancelAll() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: cancelOne(String machine_code, String order_id)
     */
    @Test
    public void testCancelOne() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: setIcon(String machine_code, String img_url)
     */
    @Test
    public void testSetIcon() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: deleteIcon(String machine_code)
     */
    @Test
    public void testDeleteIcon() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: btnPrint(String machine_code, String response_type)
     */
    @Test
    public void testBtnPrint() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getOrder(String machine_code, String response_type)
     */
    @Test
    public void testGetOrder() throws Exception {
        //TODO: Test goes here...
    }

} 
