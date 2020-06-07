package com.apstra.task.model;

import com.apstra.task.exception.EmptySubnetException;
import com.apstra.task.exception.IpDuplicationException;

import java.util.HashSet;
import java.util.List;

public class Subnet {

    private List<IpAddress> ipAddresses;
    private int mask;
    private IpAddress subnetAddress;
    private IpAddress broadcastAddress;

    public Subnet(List<IpAddress> ipAddresses) {
        if (ipAddresses.isEmpty()) {
            throw new EmptySubnetException();
        }
        duplicateCheck(ipAddresses);
        this.ipAddresses = ipAddresses;
        this.mask = countMask(ipAddresses);
        this.subnetAddress = countSubnet(ipAddresses.get(0), mask);
        this.broadcastAddress = countBroadcast(subnetAddress, mask);
    }

    private void duplicateCheck(List<IpAddress> ipAddresses) {
        HashSet<String> ipStrings = new HashSet<>();
        ipAddresses.forEach(ipAddress -> {
            if (!ipStrings.add(ipAddress.toString())) {
                throw new IpDuplicationException(ipAddress.toString());
            }
        });
    }

    private int countMask(List<IpAddress> ipAddresses) {
        IpAddress ipAddress = ipAddresses.get(0);
        int minMatch = ipAddresses.get(0).getLength();
        for (int i = 1; i < ipAddresses.size(); i++) {
            for (int j = 0; j < minMatch; j++) {
                if (ipAddresses.get(i).getIp()[j] != ipAddress.getIp()[j]) {
                    minMatch = j - 1;
                }
            }
        }
        return minMatch == 32
                ? minMatch
                : minMatch + 1;
    }

    private IpAddress countSubnet(IpAddress ipAddress, int mask) {
        boolean[] ip = new boolean[32];
        for (int i = 0; i <= Integer.min(mask, ip.length - 1); i++) {
            ip[i] = ipAddress.getIp()[i];
        }
        for (int i = mask; i < ip.length; i++) {
            ip[i] = false;
        }
        return new IpAddress(ip);
    }

    private IpAddress countBroadcast(IpAddress subnetAddress, int mask) {
        IpAddress broadcast = new IpAddress(subnetAddress.getIp().clone());
        for (int i = mask; i < subnetAddress.getIp().length; i++) {
            broadcast.getIp()[i] = true;
        }
        return broadcast;
    }

    @Override
    public String toString() {
        return "Subnet {" +
                "\n\t" + binaryMask(mask) + " - mask" +
                "\n\t" + subnetAddress + " - subnet address" +
                "\n\t" + broadcastAddress + " - broadcast address" +
                "\n\n\tip addresses: " +
                ipAddressesListToString(ipAddresses) +
                "\n}";
    }

    private String binaryMask(int mask) {
        boolean[] ip = new boolean[32];
        for (int i = 0; i < mask; i++) {
            ip[i] = true;
        }
        return new IpAddress(ip).toString();
    }

    private String ipAddressesListToString(List<IpAddress> ipAddresses) {
        StringBuilder stringBuilder = new StringBuilder();
        ipAddresses.forEach(ipAddress -> stringBuilder.append("\n\t").append(ipAddress.toString()));
        return stringBuilder.toString();
    }

    public int getMask() {
        return mask;
    }

    public IpAddress getSubnetAddress() {
        return subnetAddress;
    }

    public IpAddress getBroadcastAddress() {
        return broadcastAddress;
    }
}
