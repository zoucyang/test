package com.zcy.rescue.payment.common.util;

import com.zcy.rescue.payment.common.constant.WxPayConstants;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 功能描述: <br>
 *
 * @author zouhx
 * @version 1.0.0
 * @date 2024/2/2 13:45
 */
public class IpAddressUtil {

    /**
     * 获取本机IP
     *
     * @return String
     */
    public static String getIpAddress() {
        try {
            InetAddress ip;
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isLoopback() && !networkInterface.isVirtual() && networkInterface.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        ip = inetAddresses.nextElement();
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            return WxPayConstants.IP_DEFAULT;
        }
        return WxPayConstants.IP_DEFAULT;

    }

}
