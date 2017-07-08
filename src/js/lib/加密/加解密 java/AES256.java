package com.bestpay.creditpay.common.util;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

/**
 * AES 算法 对称加密，密码学中的高级加密标准 2005年成为有效标准
 * ////////////////异常问题解决///////////////////////
 * 特别注意，出现此异常时需更新JDK加密包
 * 1.Exception in thread "main" java.security.InvalidKeyException: Illegal key size
 *      http://ksgimi.iteye.com/blog/1584716
 * @author
 * @date 2015-07-16
 */
public class AES256 {

    /** 默认编码格式 */
	public static final String INPUT_CHARSET = "UTF-8";

    /** 加密算法 */
    static final String KEY_ALGORITHM = "AES";

    static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    /**
     * AES加密
     *
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String AES_Encode(String str, String key) throws Exception {

        byte[] textBytes = str.getBytes(INPUT_CHARSET);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(INPUT_CHARSET), KEY_ALGORITHM);
        Cipher cipher = null;
        cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        return Base64.encode(cipher.doFinal(textBytes));
    }

    /**
     * AES解密
     *
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String AES_Decode(String str, String key)	throws Exception {

        byte[] textBytes = Base64.decode(str);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes(INPUT_CHARSET), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes), INPUT_CHARSET);
    }

    //生成随机34位数字和字母  
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
    
    
    public static void main(String[] args) throws Exception{

        System.out.print("110");

    }

}
