package com.cs.sms.tests;

import com.alibaba.excel.EasyExcel;
import com.cs.sms.easy.GoodsListVOListener;
import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.pojo.vo.GoodsListVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
public class ExcelTest {
    @Autowired
    GoodsMapper goodsMapper;
    String Path = "D:\\project\\easy\\";

    private List<GoodsListVO> data() {
            List<GoodsListVO> list = goodsMapper.list();
            return list;
    }

    //根据List 写入Easyexcel
    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = Path + "EasyTest5.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, GoodsListVO.class).sheet("商品详情").doWrite(data());

    }

    @Test
    public void simpleRead() throws IOException {
        String fileName2 = "D:\\project\\easy\\EasyTest1.xlsx\\";

        EasyExcel.read(fileName2, GoodsListVO.class, new GoodsListVOListener()).sheet().doRead();
    }
}
