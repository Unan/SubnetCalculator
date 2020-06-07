package com.apstra.task.model;

import com.apstra.task.exception.IpValidationException;

public class IpAddress {

    public static final String IP_CHECK_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private boolean[] ip = new boolean[32];

    public IpAddress(boolean[] ip) {
        this.ip = ip;
    }

    public IpAddress(String ipAddress) {
        validate(ipAddress);
        StringBuilder octet = new StringBuilder();
        int octetCounter = 0;
        for (int i = 0; i < ipAddress.length(); i++) {
            if (ipAddress.charAt(i) == '.') {
                toCharArray(Integer.toBinaryString(Integer.parseInt(octet.toString())), octetCounter++);
                octet = new StringBuilder();
            } else {
                octet.append(ipAddress.charAt(i));
            }
            if (i == ipAddress.length() - 1) {
                toCharArray(Integer.toBinaryString(Integer.parseInt(octet.toString())), octetCounter++);
            }
        }
    }

    private void toCharArray(String octetString, int octet) {
        int delta = 8 - octetString.length();
        for (int i = 7; i >= delta; i--) {
            ip[i + (8 * octet)] = octetString.charAt(i - delta) != '0';
        }
        for (int i = delta - 1; i >= 0; i--) {
            ip[i + (8 * octet)] = false;
        }
    }

    private void validate(String ipAddress) {
        if (!ipAddress.matches(IP_CHECK_REGEX)) {
            throw new IpValidationException(ipAddress);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ip.length; i++) {
            if (i % 8 == 0 && i > 0) {
                result.append('.');
            }
            result.append(ip[i] ? '1' : '0');
        }
        return result.toString();
    }

    public int getLength() {
        return ip.length;
    }

    public boolean[] getIp() {
        return ip;
    }
}
