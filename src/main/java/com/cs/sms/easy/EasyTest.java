//package com.cs.sms.easy;
//
//import com.alibaba.excel.EasyExcel;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

//public class EasyTest {
//    String Path = "E:\\ider.maxmax\\sms\\";
//
//    private List<DemoData> data() {
//        List<DemoData> list = new ArrayList<DemoData>();
//        for (int i = 0; i < 10; i++) {
//            DemoData data = new DemoData();
//            data.setString("字符串" + i);
//            data.setDate(new Date());
//            data.setDoubleData(0.56);
//            list.add(data);
//        }
//        return list;
//    }
//
//    //根据List 写入Easyexcel
//    @org.junit.Test
//    public void simpleWrite() {
//        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
//
//        // 写法1 JDK8+
//        // since: 3.0.0-beta1
//        String fileName = Path + "EasyTest.xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
//
//    }
//
//    @Test
//    public void simpleRead() throws IOException {
//        String fileName2 = "E:\\ider.maxmax\\sms\\EasyTest.xlsx\\";
//
//        EasyExcel.read(fileName2, DemoData.class, new DemoDataListener()).sheet().doRead();
//    }
//}
