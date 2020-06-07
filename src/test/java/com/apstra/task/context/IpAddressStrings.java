package com.apstra.task.context;

public class IpAddressStrings {

    private IpAddressStrings() {
    }

    public static final String IP_ADDRESS_VALID_01 = "0.0.0.0";
    public static final String IP_ADDRESS_VALID_02 = "255.255.255.255";
    public static final String IP_ADDRESS_VALID_03 = "192.160.96.11";
    public static final String IP_ADDRESS_VALID_04 = "192.168.96.11";
    public static final String IP_ADDRESS_VALID_05 = "1.02.03.004";

    public static final String IP_ADDRESS_INVALID_06 = "256.255.255.255";
    public static final String IP_ADDRESS_INVALID_07 = "255.256.255.255";
    public static final String IP_ADDRESS_INVALID_08 = "255.255.256.255";
    public static final String IP_ADDRESS_INVALID_09 = "255.255.255.256";
    public static final String IP_ADDRESS_INVALID_10 = "$.-1.R.m";
    public static final String IP_ADDRESS_INVALID_11 = "...";
}
