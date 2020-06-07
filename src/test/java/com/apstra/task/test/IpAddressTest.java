package com.apstra.task.test;

import com.apstra.task.exception.IpValidationException;
import com.apstra.task.model.IpAddress;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.apstra.task.context.IpAddressStrings.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IpAddressTest {

    @Test
    void positiveValidationTest() {
        assertDoesNotThrow(() -> new IpAddress(IP_ADDRESS_VALID_01));
        assertDoesNotThrow(() -> new IpAddress(IP_ADDRESS_VALID_02));
        assertDoesNotThrow(() -> new IpAddress(IP_ADDRESS_VALID_03));
        assertDoesNotThrow(() -> new IpAddress(IP_ADDRESS_VALID_04));
        assertDoesNotThrow(() -> new IpAddress(IP_ADDRESS_VALID_05));
    }

    @Test
    void negativeValidationTest() {
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_06));
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_07));
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_08));
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_09));
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_10));
        assertThrows(IpValidationException.class, () -> new IpAddress(IP_ADDRESS_INVALID_11));
    }

    @Test
    void ipInitializationTest() {
        boolean[] ip = new boolean[32];
        Arrays.fill(ip, false);
        assertEquals(
                Arrays.toString(ip),
                Arrays.toString(new IpAddress(IP_ADDRESS_VALID_01).getIp()));

        Arrays.fill(ip, true);
        assertEquals(
                Arrays.toString(ip),
                Arrays.toString(new IpAddress(IP_ADDRESS_VALID_02).getIp()));

        assertEquals(
                "11000000.10100000.01100000.00001011",
                new IpAddress(IP_ADDRESS_VALID_03).toString());

        assertEquals(
                "00000001.00000010.00000011.00000100",
                new IpAddress(IP_ADDRESS_VALID_05).toString());
    }
}
