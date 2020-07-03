package shop.xianbao.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 数据处理工具类
 * @author Plator
 * @email PlatorDream@gmail.com
 * @date 2018/9/30 22:21
 */
public class DataUtils {
    private Logger logger = LoggerFactory.getLogger( getClass() );

    /**
     * 生成32位md5码
     * @param originString
     * @return
     */
    public static String encryByMd5( String originString ) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest( originString.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String md5( String originString ) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(originString.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

//		LoggerFactory.getInstance().createLogger (this).info("result: " + buf.toString());//32位的加密
//
//		LoggerFactory.getInstance().createLogger (this).info("result: " + buf.toString().substring(8,24));//16位的加密

        } catch (NoSuchAlgorithmException e) {
            e.toString();
        } catch (Exception e) {
            e.toString();
        }
        return buf.toString();
    }

    /**
     * 根据时间戳生成订单编号
     * @return
     */
    public static String getOrderSn() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

    /**
     * 根据UUID生成订单编号
     * @return
     */
    public static String getOrderSnByUUID() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 将list转为string 拼接
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List list, String separator) {
         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < list.size(); i++) {
             sb.append(list.get(i)).append(separator);
         }

         return sb.toString().substring(0,sb.toString().length()-1);
     }
}
