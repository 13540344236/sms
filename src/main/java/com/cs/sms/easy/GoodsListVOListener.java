package com.cs.sms.easy;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.cs.sms.easy.dao.GoodsListVODAO;
import com.cs.sms.pojo.vo.GoodsListVO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听器
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class GoodsListVOListener extends AnalysisEventListener<GoodsListVO> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    List<GoodsListVO> list = new ArrayList<>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private GoodsListVODAO goodsListVODAO;

    public GoodsListVOListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        goodsListVODAO = new GoodsListVODAO();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param goodsListVODAO
     */
    public GoodsListVOListener(GoodsListVODAO goodsListVODAO) {
        this.goodsListVODAO = goodsListVODAO;
    }

    /*
     * 读取数据会执行invoke 方法
     * DemoData 类型
     * AnalysisContext 分析上下文*/
    @Override
    public void invoke(GoodsListVO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去储存一次数据库，防止几万条数据在内存，容易BOOM
        if (list.size() >=BATCH_COUNT){
            saveData();//持久化逻辑
            //存储完成清理list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        goodsListVODAO.save(list);
        log.info("存储数据库成功！");
    }
}