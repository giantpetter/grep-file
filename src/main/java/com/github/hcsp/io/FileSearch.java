package com.github.hcsp.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    // 找到第一个包含text的行的行号，行号从1开始计算。若没找到，则返回-1。
    // 如果指定的文件不存在或者无法被读取，抛出一个IllegalArgumentException。
    // 请不要让这个方法抛出checked exception
    public static int grep(File target, String text) {
        int line = 1;
        List<String> list = new ArrayList<>();
        String str = "";
        try (FileReader stream = new FileReader(target)) {
            BufferedReader inStream = new BufferedReader(stream);
            while ((str = inStream.readLine()) != null) {
                if (str.contains(text)) {
                    return line;
                }
                ++line;
            }
            return -1;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        System.out.println("结果行号：" + grep(new File(projectDir, "log.txt"), "BBB"));
    }
}
