package com.cs.sms.schedule;

import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.mapper.SupplierMapper;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.SupplierListVO;
import com.cs.sms.repo.IGoodsRepository;
import com.cs.sms.repo.ISupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CacheSchedule {
//    @Autowired
//    private SupplierMapper supplierMapper;
//    @Autowired
//    private ISupplierRepository supplierRepository;
//    @Autowired
//    private GoodsMapper goodsMapper;
//    @Autowired
//    private IGoodsRepository goodsRepository;
//
//    /**
//     * 计划任务
//     *
//     * 只有添加了@Scheduled注解的方法，才是计划任务的方法，此方法必须在组件类中
//     *
//     * @Scheduled注解必须配置参数，表示何时/多久执行计划任务
//     *
//     * 当配置cron属性时，取值是以空格分隔的6~7个值，其中，最后一个值表示“年”，其它各位置从左至右分别表示：秒 分 时 日 月 周
//     *
//     * 例如：cron = "56 23 18 ? * MON"表示“每个月的周一（无视是几号）的18:23:56将执行此任务”
//     */
//    @Scheduled(fixedRate = 1 *60 * 60 * 1000)
//    public void updateCache() {
//        log.debug("执行缓存任务");
//
//        // 将Redis中的品牌列表清除
//        supplierRepository.deleteList();
//        // 从MySQL中读取品牌列表
//        List<SupplierListVO> suppliers = supplierMapper.list();
//        // 将品牌列表写入到Redis
//        supplierRepository.putList(suppliers);
//
//        // 将Redis中的商品列表清除
//        goodsRepository.deleteList();
//        // 从MySQL中读取商品列表
//        List<GoodsListVO> goods = goodsMapper.list();
//        // 将商品列表写入到Redis
//        goodsRepository.putList(goods);
//
//        log.debug("缓存任务执行完毕");
//    }
}
