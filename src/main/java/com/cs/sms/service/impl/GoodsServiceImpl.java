package com.cs.sms.service.impl;



import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.repo.impl.GoodsRepositoryImpl;
import com.cs.sms.service.IGoodsService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsRepositoryImpl goodsRepository;

    public GoodsServiceImpl() {
        log.debug("创建业务逻辑对象：GoodsServiceImpl");
    }

    //新增商品
    @Override
    public void addNew(GoodsAddNewDTO goodsAddNewDTO) {
        String url2 = UploadService.url;
        log.debug("url2="+url2);
        // 检查此品牌（尝试创建的品牌）的名称有没有被使用
        // 如果已经被使用，则不允许创建
        String name = goodsAddNewDTO.getName();
        int count = goodsMapper.countByName(name);
        log.debug("count = {}",count);
        if (count > 0){
            String message = "增加商品失败，商品名称【"+ name + "】已被占用";
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建实体对象 (Mapper的方法的参数是实体类)
        Goods good = new Goods();
        goodsAddNewDTO.setUrl(url2);
        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsAddNewDTO,good);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", good);
        //将品牌数据写入到数据库中
        int rows = goodsMapper.insert(good);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    //删除商品信息
    @Override
    public void deleteById(Long id) {
        //根据id查询数据
        GoodsDetailVO goodsDetailVO = goodsMapper.selectById(id);

        //判断查询结果是否为null
        if (goodsDetailVO == null) {
            //抛出异常
            String message = "删除商品失败，尝试删除的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //调用mapper对象执行删除，并获取返回值
        int rows = goodsMapper.deleteById(id);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    //编辑商品信息
    @Override
    public void update(Long id, GoodsEditDTO goodsEditVO) {
        //根据id查询数据
        GoodsDetailVO goodsDetailVO = goodsMapper.selectById(id);

        log.debug("查询到id = {}的商品信息为:{}",id, goodsDetailVO);
        //判断查询结果是否为null
        if (goodsDetailVO == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        Goods goods = new Goods();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsEditVO,goods);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", goodsEditVO);
        log.debug("接受到的参数id = {}",id);

        //调用mapper对象执行编辑，并获取返回值
        int rows = goodsMapper.updateById(goods);
        log.debug("rows = {}",rows);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "编辑商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    //商品列表
    @Override
    public List<GoodsListVO> list() {
        return goodsMapper.list();
    }

    //导出商品报表
    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<GoodsListVO> list = goodsMapper.list();
        //2.设置文件下载
        //设置响应头，告诉浏览器要以附件的形式保存，filename=文件名
        response.setHeader("content-disposition","attachment;filename=goods"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), GoodsListVO.class).sheet("商品详情").doWrite(list);
    }

    //分页查询商品列表
    @Override
    public JsonPage<Goods> getAllGoodsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Goods> list = goodsMapper.findAllGoods();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public List<GoodsListVO> selectByName(String name) {
        log.debug("接收需要查询的商品名:{}",name);
        if(name == null){
            String message="商品名不能为空";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        List<GoodsListVO> goodsListVOS = goodsMapper.selectByName(name);
        if(goodsListVOS.isEmpty()){
            String message="没有该商品";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        return goodsListVOS;
    }
}
