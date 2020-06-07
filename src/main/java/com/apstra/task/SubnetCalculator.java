package com.apstra.task;

import com.apstra.task.input.FileReader;
import com.apstra.task.model.IpAddress;
import com.apstra.task.model.Subnet;

import java.util.List;
import java.util.stream.Collectors;

public class SubnetCalculator {

    public static final String INPUT_FILE_NAME = "input/input.txt";

    public static void main(String[] args) {
        List<IpAddress> ipAddresses = FileReader.read(INPUT_FILE_NAME).stream()
                .map(IpAddress::new)
                .collect(Collectors.toList());
        System.out.println(new Subnet(ipAddresses));
    }
}
