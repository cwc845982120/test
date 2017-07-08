package com.bestpay.creditpay.web.service;


import com.bestpay.creditpay.common.ResponseObj;
import com.bestpay.creditpay.common.util.AES256;
import com.bestpay.creditpay.common.util.JsonUtils;
import com.bestpay.creditpay.common.util.RSA;
import com.bestpay.creditpay.common.util.StringTools;
import com.bestpay.creditpay.web.service.model.creditquery.NewCreConsOrderQueryRespBO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 加解密测试
 */
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class AESAndRSATest {
    /**
     * 加密
     */
    @Test
    public void test() {
        try {
            /**
             * XGxD354B9uvVoI59j1W224Lw67eCMR95
             */
            String str = "{\"requestSystem\":\"WG\",\"traceLogId\":\"qcb2015090071027\",\"productNo\":\"18181212229\",\"acceptChannel\":\"02\",\"acceptTime\":\"20150907102700001\"}";
            String key = AES256.getStringRandom(32);
            String encodeStr = AES256.AES_Encode(str, key);
//            System.out.println("原串：" + str);
//            System.out.println("原key：" + key);
//            System.out.println("长度：" + encodeStr.length());

            String encodeKey = RSA.encrypt(key, RSA.PUB_KEY, AES256.INPUT_CHARSET);
            System.out.println(encodeStr);
            System.out.println(encodeKey);

            key = RSA.decrypt(encodeKey, RSA.PRI_KEY, AES256.INPUT_CHARSET);
            str = AES256.AES_Decode(encodeStr, key);

            System.out.println("解密串：" + str);
            System.out.println("解密key：" + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String str = "20150924";
            Date dt = sdf.parse(str);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR, -180);//日期加10天
//            rightNow.add(Calendar.MONTH,-6);//日期加3个月
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            System.out.println(reStr);
//
//            System.out.println("解密串：" + str);
//            System.out.println("解密key：" + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {
        try {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            rightNow.add(Calendar.MONTH, -1);//日期的上一个月
            int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
            Date dt1 = rightNow.getTime();
            String billDateBegin = sf.format(dt1);//开始时间

            System.out.println(billDateBegin);
            System.out.println(currentDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test4() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, -1);//日期的上一个月
        int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
        Date dt1 = rightNow.getTime();
        String billDateEnd = sf.format(dt1);//开始时间
        //        String billDateBegin=initBillDateBegin(rightNow);
        //判断当前是否是账单日
//            String property = PropertiesFileUtils.getContextProperty("credit.bill.date");
        String property = "28";
        String billDateBegin = "";
        if (currentDay > StringTools.string2Int(property)) {
            //表示当日期已出账单
            rightNow.add(Calendar.MONTH, -6);//
//            rightNow.add(Calendar.DAY_OF_MONTH, +5);
            Date dt2 = rightNow.getTime();
            billDateBegin = sf.format(dt2);//开始时间
        } else if (currentDay == StringTools.string2Int(property)) {
            //表示的是非账单日
            rightNow.add(Calendar.MONTH, -6);//
//            rightNow.add(Calendar.DAY_OF_MONTH, +5);
            Date dt2 = rightNow.getTime();
            billDateBegin = sf.format(dt2);//开始时间
        } else {
            rightNow.add(Calendar.MONTH, -6);//
            Date dt2 = rightNow.getTime();
            billDateBegin = sf.format(dt2);//开始时间
        }
        System.out.println(billDateBegin);
        System.out.println(billDateEnd);
        System.out.println(currentDay);
    }

    /**
     * 生成公私钥对
     */
    @Test
    public void getPrivateKey() {
        try {
            Map<String, Object> keyMap = RSA.generateRSAKey();
            System.out.println("PRIVATE_KEY：" + keyMap.get(RSA.PRIVATE_KEY));
            System.out.println("PUBLIC_KEY：" + keyMap.get(RSA.PUBLIC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test34() {

            Map<String, Object> map = Maps.newHashMap();

            map.put("201506",000000);
            map.put("201505",9877);
            map.put("201504",9877);
            map.put("201503", 9877);

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
    }

    @Test
    public void rsaTest() {
        try {
//            String content = "{\"requestSystem\":\"999999\",\"traceLogId\":\"1231231231231231231\",\"productNo\":\"18516079582\",\"quickCardNo\":\"8919289123123123123\",\n" +
//                    "\"accountName\":\"luchao\",\"acceptChannel\":\"08\",\"acceptTime\":\"20150810095630\",\"acceptOrgCode\":\"121231\",\n" +
//                    "\"acceptSeqNo\":\"qwqwq\",\"acceptUser\":\"1212312\",\"agreementVersion\":\"1212\",\"recommender\":\"teset\"}";
//            String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVUwb66Gpo5aLxLD8vnvz8x4qBZdsVd5E/d+v9zpi6LgpRv2MUk7TEBbNOy7XSLx+zNJkJ91JOZwdHU2Zjc\n" +
//                    "+Q/e/f8I/vCoHaPPwdz0g1mxz/12DY8WDESFqBR85grYzAI50GOLn3zgnBmlIhzej8yG538HTp0q5ZzemYE1vB8VQIDAQAB";
//            String privKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKG5Hp4KQwrSck/zthkkZkQ56xWpA8S98XXPYyQOoKaoapmePxUdKidbt2H0E8d/mJDeEEJGxHVJl0cOWlDy/ZGNs5qNZXYUKiSwU+ahFSZIOTaW+CU3ULs1GIW9wMsaxVgK0VPeCVfVYvRWRL/lduXpFFb15Jc4CFb75nDm0JG1AgMBAAECgYBfdsVKCUi19LAh3f0aLlBn6cCJkrF0y6LjJj6hpDjU6Am1EKKwNf0SKqO/oLKkSv+mVE9IsDl7+qrf+Or7adSfzJyv+CWTijjvHG8aVhYewY3NBdl5Ey1X/r8wtMftkJGkpr7C9PEnvmHAXohfTK5O80Q6g5Nol7J6lqbuVr+bAQJBANONTIhX/xvwipErVHsYg34U5Cg0vmd/k5R/UpfTGcx+EY7NoGMB+azBocQ00w1LzFEugYIfy8Z33Py90zHTkRUCQQDDs7AY13FHrvIjFnlP5fjv1K9De3pQxhUw0rH6IWYF7eEyPAsfhdxyp9Da0nAFONpz/0/qLj+IGJgcjmXl6uYhAkBst+Ds8MwZ/7Q2hp4T5LUuxm26e+CeL+IYHpbDj6lifidnzCTwvMMjFR+dRchwvNNzpQLJbK28gT0AZk1y/ddBAkA21lb13wjDCWaYJmf/13UV3wbtZeA0Wgz+DUwJxuoLp0h/Bwwm3R+UkSmY53a0P+YWsuf1utfajumwtsYe0t+BAkEAq4GvdrMsmlx0Tn69UHJfVVYcU4S1Jk6Vzj9S6iAifq5VByg2UDCFV/wqMJOT+FTlXW1mdL5/+LM3wJ0R3y0anw==";
//            String encryStr = RSA.encrypt(content, pubKey, "UTF-8");
//
//            System.out.println("加密结果：" + encryStr);
//
//            String decryptStr = RSA.decrypt(encryStr, privKey, "UTF-8");
//
//            System.out.println("解密结果：" + decryptStr);
            Map<String, Object> map = Maps.newHashMap();
            List<NewCreConsOrderQueryRespBO> newCreConsOrderQueryRespBOs = Lists.newArrayList();
            NewCreConsOrderQueryRespBO newCreConsOrderQueryRespBO = new NewCreConsOrderQueryRespBO();
            newCreConsOrderQueryRespBO.setTxnAmt(1000L);
            newCreConsOrderQueryRespBO.setTxnDesc("花费代扣");
            newCreConsOrderQueryRespBO.setAcceptOrgCode("012912");
            newCreConsOrderQueryRespBO.setProductNo("18121038608");
            newCreConsOrderQueryRespBO.setOuterTxnType("02");
            newCreConsOrderQueryRespBO.setOuterTxnType("dwadaw");
            newCreConsOrderQueryRespBOs.add(newCreConsOrderQueryRespBO);
            map.put("CreditConsumeOrderList", newCreConsOrderQueryRespBOs);
            map.put("TxnStartDate", "20150823");
            map.put("TxnEndDate", "20150922");
            map.put("RecordsTotal", 1);

            ResponseObj<Map<String, Object>> responseObj = new ResponseObj<Map<String, Object>>(false);
            responseObj.setSuccess(true);
            responseObj.setResult(map);

            String s = JsonUtils.object2Json(responseObj);
            System.out.println(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        try {
            /**
             * XGxD354B9uvVoI59j1W224Lw67eCMR95
             */

            String key = RSA.decrypt("m9c1bAWocgvlq2Ibfrw8kGEi30VN0RqyOo3RwMPyLV2f4mo8GdXJTj0+mrBUL7rTGIAWIvnBIoChveG8hlW5KGjA8xr9BDoiL2qFVtrynusvIhsdaxaCuTz3sNt9kp3P0Tf+rMw68u9mL5sbI095IxrGa5RVNfAwYBhijbzeqts=", RSA.PRI_KEY, AES256.INPUT_CHARSET);
            String str = AES256.AES_Decode("qiuc6c8PCkg5WYfKb2r8KEk5pY6zO7hbTElv4nkqZPzojZRP8gmwGMkMm/Mkdj/KxvfP+NwEijJzMAtOe39lTI8gPJNe4UmSAiQ9ylR1Ds/mamsjj6LopBv7mqLL3sGTBxyfUo4SlKN6wEe5ODUC3QyE5HhBXXd1JdAgvcSEPwL7YHOkOTUPCIYgKQmkgqPmX6tqoOEbPR19w9HScP24ejfOF8m9PiNBsU68B+1Zg8s=", key);
//
            System.out.println("解密串：" + str);
//            System.out.println("解密key：" + key);dongsa
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
