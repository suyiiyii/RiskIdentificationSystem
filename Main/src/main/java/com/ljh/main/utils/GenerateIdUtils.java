package com.ljh.main.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateIdUtils {
    public static String generateTaskID() {
        // 获取当前时间的时间戳
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(formatter);

        // 生成一个0-999之间的随机数，不足3位前面补0
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        String randomNumber = String.format("%03d", randomNum);

        // 拼接前缀、时间戳和随机数
        String taskId = "T" + timestamp + randomNumber;

        return taskId;
    }

    public static String generateResultID() {
        // 获取当前时间的时间戳
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);

        // 生成一个0-9999之间的随机数，不足4位前面补0
        Random random = new Random();
        int randomNum = random.nextInt(10000);
        String randomNumber = String.format("%04d", randomNum);

        // 拼接前缀、时间戳和随机数
        String resultId = "R" + timestamp + randomNumber;

        return resultId;
    }

}
