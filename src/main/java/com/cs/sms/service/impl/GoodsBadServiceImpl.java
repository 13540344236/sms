package com.cs.sms.service.impl;




import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.GoodsBadMapper;
import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.GoodsBad;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.service.IGoodsBadService;
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
public class GoodsBadServiceImpl implements IGoodsBadService {

    @Autowired
    private GoodsBadMapper goodsBadMapper;

    @Override
    public void addNew(GoodsBadDTO goodsBadDTO) {
        // 检查此品牌（尝试创建的品牌）的名称有没有被使用
        // 如果已经被使用，则不允许创建
        String name = goodsBadDTO.getName();
        int count = goodsBadMapper.countByName(name);
        log.debug("count = {}",count);
        if (count > 0){
            String message = "增加商品失败，商品名称【"+ name + "】已被占用";
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建实体对象 (Mapper的方法的参数是实体类)
        GoodsBad goodsBad = new GoodsBad();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsBadDTO,goodsBad);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", goodsBadDTO);
        //将品牌数据写入到数据库中
        int rows = goodsBadMapper.insert(goodsBad);
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
        GoodsBadVO goodsBadVO = goodsBadMapper.selectById(id);

        //判断查询结果是否为null
        if (goodsBadVO == null) {
            //抛出异常
            String message = "删除商品失败，尝试删除的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //调用mapper对象执行删除，并获取返回值
        int rows = goodsBadMapper.deleteById(id);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public void update(Long id, GoodsBadVO goodsbadVO) {
        //根据id查询数据
        GoodsBadVO goodsBadVO = goodsBadMapper.selectById(id);

        log.debug("查询到id = {}的商品信息为:{}",id, goodsBadVO);
        //判断查询结果是否为null
        if (goodsBadVO == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id ="+ id +"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        GoodsBad goodsBad = new GoodsBad();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(goodsbadVO,goodsBad);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", goodsbadVO);
        log.debug("接受到的参数id = {}",id);

        //调用mapper对象执行编辑，并获取返回值
        int rows = goodsBadMapper.updateById(goodsBad);
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
    public List<GoodsBadVO> list() {
        return goodsBadMapper.list();
    }

    //分页查询商品报损列表
    @Override
    public JsonPage<GoodsBad> getAllGoodsBadByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<GoodsBad> list = goodsBadMapper.findAllGoodsBad();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<GoodsBadVO> list = goodsBadMapper.list();
        //2.设置文件下载
        //设置
        response.setHeader("content-disposition","attachment;filename=goods"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), GoodsListVO.class).sheet("商品详情").doWrite(list);
    }
}
