package com.acewill.slefpos.print.chikenprintlibrary.model;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_KDS
{
    private Integer id;
    private String kdsName;
    private String ip;
    private String port;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKdsName() {
        return kdsName;
    }

    public void setKdsName(String kdsName) {
        this.kdsName = kdsName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
