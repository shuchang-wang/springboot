package com.alibaba.apache_commons_csv;

import com.alibaba.apache_commons_csv.bean.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudyApacheCommonsCsvApplicationTests {

    @Test
    public void testWriteCsvFile() throws FileNotFoundException, UnsupportedEncodingException {
        //CSV文件分隔符
        String NEW_LINE_SEPARATOR = "\n";
        //CSV文件头
        String[] header = {"ID", "用户名", "密码", "性别", "年龄", "出生日期"};
        String filename = "user.csv";
        CSVPrinter csvFilePrinter = null;
        //创建 CSVFormat
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //初始化FileWriter
            FileOutputStream fos = new FileOutputStream(filename);
            //多一层封装解决文件名中文乱码问题
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            //初始化 CSVPrinter
            csvFilePrinter = new CSVPrinter(osw, csvFileFormat);
            //创建CSV文件头
            csvFilePrinter.printRecord(header);

            // 用户对象放入List
            List<User> userList = new ArrayList<>();
            userList.add(new User(1, "zhuyi", "123456", "0-man", new Date(), 21));
            userList.add(new User(2, "tianer", "123456", "1-woman", new Date(), 22));
            userList.add(new User(3, "zhangsan", "123456", "0-man", new Date(), 23));
            userList.add(new User(4, "lisi", "123456", "0-man", new Date(), 24));
            userList.add(new User(5, "wangwu", "123456", "1-woman", new Date(), 25));
            userList.add(new User(6, "zhaoliu", "123456", "1-woman", new Date(), 26));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 遍历List写入CSV
            for (User user : userList) {
                List<Object> userDataRecord = new ArrayList<>();
                userDataRecord.add(user.getId());
                userDataRecord.add(user.getUsername());
                userDataRecord.add(user.getPassword());
                userDataRecord.add(user.getSex());
                userDataRecord.add(user.getAge());
                userDataRecord.add(format.format(user.getBirthday()));
                csvFilePrinter.printRecord(userDataRecord);
            }
            System.out.println("CSV文件创建成功~~~");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadCsvFile() {
        //CSV文件头
        String[] header = {"ID", "用户名", "密码", "性别", "年龄", "出生日期"};
        String filename = "user.csv";
        FileReader fileReader = null;
        OutputStreamWriter outputStreamWriter = null;
        CSVParser csvFileParser = null;
        //创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(header);
        try {
            //初始化FileReader object
            fileReader = new FileReader(filename);
            //初始化 CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //CSV文件records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            // CSV
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                System.out.println(record.get("ID"));
                System.out.println(record.get("用户名"));
                System.out.println(record.get("密码"));
                System.out.println(record.get("性别"));
                System.out.println(record.get("出生日期"));
                System.out.println(record.get("年龄"));
                System.out.println("=========================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
