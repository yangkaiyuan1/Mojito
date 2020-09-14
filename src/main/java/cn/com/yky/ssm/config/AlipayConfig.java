package cn.com.yky.ssm.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000118642162";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCf/HcXBCBTKNtu04ktTcuNXbAambG0FlVTvSE2gvw77AZLF0LlSDoZ42CR2Omm8vwlhis9AL6VHBF/2VRJNWsAol654FfCqcLs8dcFPog4JAzKdQMdIep7+DlkUY9mnhMvX1LVgF4YofdOOXjp9c+a6+m+9I4IY8CPBVewRhUT6RMULrrjMkpHIu2RGM/J267eI5jxhxVwEy5ksQfkGi3Pyi7M7pKepqnAe8Uc40K17h6hKUibgsl9vKZPaWZCXNd7imvlVfMFZgls6toGqxsq2yIXWmsxgkOCW+yfOvn1L86vz6rO6JWIwGwhD2Iss21LzKev/R68cXfC6/fWa85XAgMBAAECggEBAJBa/yHXaUtf7E9ds8MbFPXjeUknMP7rv+YlG1jSa8GtwCf1PzkfKL2/Op2e+YTYbsd0/oK2r0Bf6TJQ6Qp9lqUl3Q8e7FELWITv2jIzMDpn3Nq5JiwdNDTTzwVDyn6vV2RBQL7AaycSzdDZbs68K6z//RN+GsJbL55dFbhKJC6njIZuLDcOaYuBfqaP9Ee+rssM0atNDI0fs5vYjWruEOQYm5kqKTKS01ayStzWR9fatq/M5ni0AIcMbEnLnBsn8sfin9GpMHxY7y8IEAgoLSrlOTvdbMvr8gnemBfUMRgO8GPs/2ZA+flFaNHPDtiGNh/yF/t+5zBX+MwnwXGb0RECgYEAyphVEW7LCpp6PZ5xm1R065m9u2JtuHUb2enhKPs+q00dOczgRdXfEgVY5UscRRrM36lLilUnQ3Yu1HvyppwlC8R9HvjAyHUdysvQ5vclEnNLxadAu4yaJMV7lgyyl0Nqsjv6E+m0AlAea+N/0CKX7aaNCQkgVBUCet1QHM5ULn0CgYEAyijFVFaZRhcx4J2tjnRMMl8lJDx2DQvQaGMa2J4EcDkx3QAZpj9SA2qW8wttkJR+xUYI/YRMBRXljGToDTUAc8GOmtbG1psmoGG1YyihuenGxg5I3vkoTeIPnNdh4Mi/u346GRFGRYvpOUattQnD4ehrLhHXMgUlXrwz0bWZZGMCgYByN7eeAGyQKc9I9g7ul4rjWOXSwwdAuyVckbLTO/J0+SDE+XBnbtA4hLM9cj8Z5qYiYnuoHaHpMrPsL4T50YZBQCGaWHu3h++n6Yh47lUENeQ+JrEkC3zLxlqKmdYF2YhJu+6dU2Cc/TZpdPUWDqrA3Okecvq67K1dP3BvDRQAsQKBgG+wdtVRwvzmc7gVZX3+4toxluOeQ9qRXGNP3dUv+GPINbRUCde/xB//E8bT2YS3hhKXzv/Bx/5HSrt2RH6Heeh6V79USibnMUAKu5w9Jp0Ae8GQwtHt9kaRTu7oUDtQrh14b8DJunlSA1iRpAcRjheRwSWC1dHc5fihIkAKhOThAoGAGIDdgTqCXZNIcLl8kpETWo7yunVgpzmy7+ukoLhAudGODKOiP9x9xsmlH9oYeWfM3d5lNoO4jUHr95dH6/CwbjNjyF2c6gj+6YQThmr6TpyOPIEffhV3xhCQfk/PPWe3+hJyTQJj71RIqD0VePXwNzbryqFevOla1k63vGldW4U=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn/x3FwQgUyjbbtOJLU3LjV2wGpmxtBZVU70hNoL8O+wGSxdC5Ug6GeNgkdjppvL8JYYrPQC+lRwRf9lUSTVrAKJeueBXwqnC7PHXBT6IOCQMynUDHSHqe/g5ZFGPZp4TL19S1YBeGKH3Tjl46fXPmuvpvvSOCGPAjwVXsEYVE+kTFC664zJKRyLtkRjPyduu3iOY8YcVcBMuZLEH5Botz8ouzO6SnqapwHvFHONCte4eoSlIm4LJfbymT2lmQlzXe4pr5VXzBWYJbOraBqsbKtsiF1prMYJDglvsnzr59S/Or8+qzuiViMBsIQ9iLLNtS8ynr/0evHF3wuv31mvOVwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/orders/afterOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

