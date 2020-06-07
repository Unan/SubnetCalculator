package com.apstra.task.test;

import com.apstra.task.exception.IpDuplicationException;
import com.apstra.task.model.IpAddress;
import com.apstra.task.model.Subnet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.apstra.task.context.IpAddressStrings.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubnetTest {

    private Subnet subnet1;
    private Subnet subnet2;
    private Subnet subnet3;

    @BeforeEach
    void subnetInitialization() {
        subnet1 = new Subnet(Arrays.asList(
                new IpAddress(IP_ADDRESS_VALID_03),
                new IpAddress(IP_ADDRESS_VALID_04)));

        subnet2 = new Subnet(Collections.singletonList(
                new IpAddress(IP_ADDRESS_VALID_05)));

        subnet3 = new Subnet(Arrays.asList(
                new IpAddress(IP_ADDRESS_VALID_01),
                new IpAddress(IP_ADDRESS_VALID_02),
                new IpAddress(IP_ADDRESS_VALID_03)));
    }

    @Test
    void duplicationCheckTest() {
        assertDoesNotThrow(
                () -> new Subnet(Arrays.asList(
                        new IpAddress(IP_ADDRESS_VALID_03),
                        new IpAddress(IP_ADDRESS_VALID_04))));
        assertThrows(IpDuplicationException.class,
                () -> new Subnet(Arrays.asList(
                        new IpAddress(IP_ADDRESS_VALID_04),
                        new IpAddress(IP_ADDRESS_VALID_04))));
    }

    @Test
    void subnetCalculationTest() {
        assertEquals(
                "11000000.10100000.00000000.00000000",
                subnet1.getSubnetAddress().toString());
        assertEquals(
                "00000001.00000010.00000011.00000100",
                subnet2.getSubnetAddress().toString());
        assertEquals(
                "00000000.00000000.00000000.00000000",
                subnet3.getSubnetAddress().toString());
    }

    @Test
    void maskCalculationTest() {
        assertEquals(subnet1.getMask(), 12);
        assertEquals(subnet2.getMask(), 32);
        assertEquals(subnet3.getMask(), 0);
    }

    @Test
    void broadCastCalculationTest() {
        assertEquals(
                "11000000.10101111.11111111.11111111",
                subnet1.getBroadcastAddress().toString());
        assertEquals(
                "00000001.00000010.00000011.00000100",
                subnet2.getBroadcastAddress().toString());
        assertEquals(
                "11111111.11111111.11111111.11111111",
                subnet3.getBroadcastAddress().toString());
    }
}
