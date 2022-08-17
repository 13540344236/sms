package com.cs.sms.service.impl;



import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.mapper.MenuMapper;
import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Menu;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.repo.impl.GoodsRepositoryImpl;
import com.cs.sms.service.IMenuService;
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
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private GoodsRepositoryImpl goodsRepository;

    public MenuServiceImpl() {
        log.debug("创建业务逻辑对象：GoodsServiceImpl");
    }

    //新增商品
    @Override
    public void addNew(Menu menu) {
        // 检查此菜单（尝试创建的菜单）的名称有没有被使用
        // 如果已经被使用，则不允许创建
        String name = menu.getName();
        int count = menuMapper.countByName(name);
        log.debug("count = {}",count);
        if (count > 0){
            String message = "增加菜单失败，菜单名称【"+ name + "】已被占用";
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建实体对象 (Mapper的方法的参数是实体类)
        Menu menus = new Menu();
        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(menu,menus);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", menus);
        //将品牌数据写入到数据库中
        int rows = menuMapper.insert(menus);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    //删除商品信息
    @Override
    public void deleteById(Integer id) {
        //调用mapper对象执行删除，并获取返回值
        int rows = menuMapper.deleteById(id);
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
    public void update(Integer id, Menu menu) {

        Menu menus = new Menu();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(menu,menus);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", menu);
        log.debug("接受到的参数id:  {}",id);

        //调用mapper对象执行编辑，并获取返回值
        int rows = menuMapper.updateById(menu);
        log.debug("rows = {}",rows);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "编辑商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }
    //根据商品名模糊查询
    @Override
    public List<Menu> selectByName(String name) {
        log.debug("接收需要查询的商品名={}",name);
        if(name == null){
            String message="商品名不能为空";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        List<Menu> menu = menuMapper.selectByName(name);
        if(menu.isEmpty()){
            String message="没有该商品";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        return menu;
    }


    //商品列表
    @Override
    public List<Menu> list() {
        return menuMapper.list();
    }


    //分页查询商品列表
    @Override
    public JsonPage<Menu> getAllMenuByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Menu> list = menuMapper.findAllMenu();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public Menu getById(Integer menuId) {
        return menuMapper.getById(menuId);
    }

}
