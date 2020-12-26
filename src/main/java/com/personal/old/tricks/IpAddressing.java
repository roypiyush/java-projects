package com.personal.old.tricks;

import org.apache.commons.net.util.SubnetUtils;

import java.net.InetAddress;

public class IpAddressing {
    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getByName("128.0.0.0");
        byte[] addressBytes = inetAddress.getAddress();
        for (byte b : addressBytes) {
            System.out.println(String.format("number=%s binary=%s unsigned=%s", b, Integer.toBinaryString(b), Byte.toUnsignedInt(b)));
            System.out.println(String.format("1 bit >>     shift=%s number=%s", Integer.toBinaryString(b >> 1), b >> 1));
            System.out.println(String.format("1 bit >>>    shift=%s number=%s", Integer.toBinaryString(b >>> 1), b >>> 1));
        }
        printNetworkInfo("10.0.0.0/23");
        printNetworkInfo("10.0.0.0/24");
        printNetworkInfo("0.0.0.0/24");
        printNetworkInfo("0.0.0.0/23");
    }

    private static void printNetworkInfo(String cidrNotation) {
        System.out.println(String.format("\nShowing for cidr=%s", cidrNotation));
        SubnetUtils cidr = new SubnetUtils(cidrNotation);   
        System.out.println(String.format("Host Count = %s", cidr.getInfo().getAddressCount()));
        System.out.println(String.format("Network Address %s asInt %s asBinary %s", cidr.getInfo().getNetworkAddress(),
                cidr.getInfo().asInteger(cidr.getInfo().getNetworkAddress()), Integer.toBinaryString(cidr.getInfo().asInteger(cidr.getInfo().getNetworkAddress()))));
        System.out.println(String.format("Lowest Address %s asInt %s asBinary %s", cidr.getInfo().getLowAddress(),
                cidr.getInfo().asInteger(cidr.getInfo().getLowAddress()), Integer.toBinaryString(cidr.getInfo().asInteger(cidr.getInfo().getLowAddress()))));
        System.out.println(String.format("Highest Address %s asInt %s asBinary %s", cidr.getInfo().getHighAddress(),
                cidr.getInfo().asInteger(cidr.getInfo().getHighAddress()), Integer.toBinaryString(cidr.getInfo().asInteger(cidr.getInfo().getHighAddress()))));
        System.out.println(String.format("Broadcast Address %s asInt %s asBinary %s", cidr.getInfo().getBroadcastAddress(),
                cidr.getInfo().asInteger(cidr.getInfo().getBroadcastAddress()), Integer.toBinaryString(cidr.getInfo().asInteger(cidr.getInfo().getBroadcastAddress()))));
    }
}
