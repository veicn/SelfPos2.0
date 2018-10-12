package com.acewill.slefpos.printer.vendor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * 通过指令来控制打印机
 * http://www.cnblogs.com/rinack/p/5227133.html
 * http://www.v5blogs.com/blog/902bf470afb03bf9e79cf05a0b6de2cd
 * http://www.jiacheo.org/blog/594
 * http://blog.csdn.net/xiaoxian8023/article/details/8440625
 * http://blog.csdn.net/laner0515/article/details/8457337 打印位图
 * http://www.cnblogs.com/hnxxcxg/p/3580402.html
 *
 *
 * Created by Acewill on 2016/6/7.
 */
public class WifiPrinter extends CommandPrinter  {
    private String host;
    Socket client;
    private boolean init;

    public WifiPrinter(String host, int maxCharacterSizePerSize) {
        super(maxCharacterSizePerSize);
        this.host = host;
    }

    public void init() throws IOException {
    	if(init){
    		return;
    	}
        client = new Socket();
        client.connect(new InetSocketAddress(host, 9100), 10000);
        init = true;
        super.setOutputStream(client.getOutputStream());
    }

    //必须调用close，否则不会真正打印出来
    public void close() throws IOException {
    	if(init){
    		
    		super.close();
    		client.close();
    	}
    }
   
}
