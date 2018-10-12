package com.acewill.paylibrary.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2015050800065426";
	public static String APPID = "2015042800051766";// 青椒红椒
	public static String PID = "2088702628250222";// 青椒红椒
	public static String MCH_NAME = "";
	// 商户的私钥
	// public static String key =
	// "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANV9Nr856QdnUebmwadJpD7paNgBLR5Q/gpfZC3r5KYxlDlk/oyIFZywealOnVKZi8g6M8e3th9MiaXTiMsFmLvKu+/7CHFWd5uXqJfTsmrFomeuRd+ok5tgFEvPUAhzx+FGIdJ1FwhUCSKuPc6CiizUi/NZBTBtZga9m4R1UhvhAgMBAAECgYBjoOODqgrNobHfB6En6H31vgdnhmT/2Nw1Cv89TvCNfmWlwzPncTpkvRaJpF5WxcEt17uCYIFladpywJz94ca8QlUuPuO+pXvFFKJ7HQEmy5EiTz60jbADUMatJpql4fGw/bvnyQRb4grQkVqgwkNySoGbh7LnwQJi0/D9o3pH4QJBAOotdw7MxRWrFBPrnX87LozROM55xVydrJDub6DGqvg0Z+Hn6pjTtLGx4fBuNLPlnUgxc9I7deh/Jqm1sV2gGY8CQQDpYjRz0RaUjLEpiuQ8hjQDUG5I/OMVFGSGEw+QtMljijVtwsyQsaQR+rAiGE4ZEhMDlQpUgwUmd0haceXwUluPAkEAy76a46mNn/ilE1/gPB6FtccjIaIY8SfOSJrrTHsNrb4K1CYvmqA3ipM1/TYlbI/OdE1yc+t//rUCMo3+r3LoSQJAXHeqbJDVL3om31zGXSyXAxElAQohNsLjqTN4IU9f0255CMn7nwCjDvSSQI/8sXY1zl+Ivj9DFSCBrEQgZH1dBQJBAKvYjnY/k9SZkOWYrcQ5K1Y+U6lgp6tcZ8it1smLsWGvzTiofVaJSAkvMuV2EqlZzudqcPSSMYQ/fNlK6D+V+mI=";
	// 青椒红椒
	public  static String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJZtKL5TGww8ybIP8W1w6TgCv/OcXfAbm7pGdLnn/LbnKLOOeMY0K6/Dv9QqTQ3pIwPJiVE8HWpyL23rdU7tYEoNkaN/8Zg1QG8d0H/UA06WlhYvMg241+CMc4aD07qYg6ZDkZiQHVQsm7H2mz2UxwwfjBrcKkGQonQtKGFWnzdlAgMBAAECgYBLbYUGmmlvo3OHo9Lti7aF7WsO3pOD+HQH3ZheN+NSW2rfTktlJ3fYdJFV87gbh5GVLpN6dmhRndwGhBCYeRLUilhVLVS+O70R31u3BAlD+jKWQO9E7mC04COUHrf5ysr8pnEd5oF5HDrbjtxu1LrLDj3pMc2bj7tJ+JHJEH42wQJBAMRjWeJnv6XDIKopbd4J6MavdXZZzJ9vMVb0JjLyz82s8gPMzYOw/+wv9kUEa5G/5pWRk7KXdIyqqwZcysfXH40CQQDEFkgzQA+eF/mlE5mC7VKnC4C9SfHgS1o+NBMY/cSVXGsqlNGYXs7y6LhUnxyJ+MyBSvk2Y8jQX/k7xKxeNTU5AkBdsEv0L6yFBtaxdvw7/hw3lQMDiAmPUfuG0QjcK2wpYcU/PNgaaDlfUu44tMggix0U45NDku9+H/lhGql+tskNAkB79vo+LEmzUMTqr7FlLFQMdyE0igL4IaszmAhazFPzeTHnAZ5gDdAXkTi3hr+sGjZvps5WrE7QLVyLhJLUHB9BAkBHbuF4OP2F4NXC7vorfw7fwqFepA4i1gnSuAsCy8U8opw5H1T1yvQaqIVfVQi914/Ug1KXZCcD38h506dJraTV";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "MD5";

	public static String appsecret;
}
