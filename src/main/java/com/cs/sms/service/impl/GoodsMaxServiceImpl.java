package com.cs.sms.service.impl;




import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.GoodsBadMapper;
import com.cs.sms.mapper.GoodsMaxMapper;
import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.dto.GoodsMaxDTO;
import com.cs.sms.pojo.entity.GoodsBad;
import com.cs.sms.pojo.entity.GoodsMax;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.GoodsMaxVO;
import com.cs.sms.service.IGoodsBadService;
import com.cs.sms.service.IGoodsMaxService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@Service
public class GoodsMaxServiceImpl implements IGoodsMaxService {

    @Autowired
    private GoodsMaxMapper goodsMaxMapper;

    @Override
    public void addNew(GoodsMaxDTO goodsMaxDTO) {
        // 检查此品牌（尝试创建的品牌）的名称有没有被使用
        // 如果已经被使用，则不允许创建
        String name = goodsMaxDTO.getName();
        int count = goodsMaxMapper.countByName(name);
        log.debug("count = {}",count);
        if (count > 0){
            String message = "增加商品失败，商品名称【"+ name + "】已被占用";
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建实体对象 (Mapper的方法的参数是实体类)
        GoodsMax goodsMax = new GoodsMax();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsMaxDTO,goodsMax);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", goodsMaxDTO);
        //将品牌数据写入到数据库中
        int rows = goodsMaxMapper.insert(goodsMax);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public void deleteById(Long id) {
        //根据id查询数据
        GoodsMaxVO goodsMaxVO = goodsMaxMapper.selectById(id);

        //判断查询结果是否为null
        if (goodsMaxVO == null) {
            //抛出异常
            String message = "删除商品失败，尝试删除的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //调用mapper对象执行删除，并获取返回值
        int rows = goodsMaxMapper.deleteById(id);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public void update(Long id, GoodsMaxVO goodsMaxVO) {
        //根据id查询数据
        GoodsMaxVO goodsMaxVO1 = goodsMaxMapper.selectById(id);

        log.debug("查询到id = {}的商品信息为:{}",id, goodsMaxVO1);
        //判断查询结果是否为null
        if (goodsMaxVO1 == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        GoodsMax goodsMax = new GoodsMax();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsMaxVO,goodsMax);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", goodsMaxVO1);
        log.debug("接受到的参数id = {}",id);

        //调用mapper对象执行编辑，并获取返回值
        int rows = goodsMaxMapper.updateById(goodsMax);
        log.debug("rows = {}",rows);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "编辑商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public List<GoodsMaxVO> list() {
        return goodsMaxMapper.list();
    }

    //分页查询商品列表
    @Override
    public JsonPage<GoodsMax> getAllGoodsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<GoodsMax> list = goodsMaxMapper.findAllGoods();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<GoodsMaxVO> list = goodsMaxMapper.list();
        //2.设置文件下载
        //设置
        response.setHeader("content-disposition","attachment;filename=goods"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), GoodsListVO.class).sheet("商品详情").doWrite(list);
    }
}
