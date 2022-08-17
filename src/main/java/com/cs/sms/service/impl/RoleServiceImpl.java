package com.cs.sms.service.impl;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.RoleMapper;
import com.cs.sms.mapper.RoleMenuMapper;
import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Menu;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.entity.RoleMenu;
import com.cs.sms.pojo.vo.RoleVO;
import com.cs.sms.service.IMenuService;
import com.cs.sms.service.IRoleService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.Results;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private IMenuService menuService;

    @Override
    public void addNew(RoleDTO roleDTO) {
        int select = roleMapper.select(roleDTO.getName());
        if (select>0){
            String message="添加失败,该角色已存在";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
        Role role=new Role();
        LocalDateTime dateTime = LocalDateTime.now();
        roleDTO.setGmtCreate(dateTime);
        BeanUtils.copyProperties(roleDTO,role);
        int insert = roleMapper.insert(role);
        if (insert != 1) {
            String message="服务器忙 请联系管理员";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public List<RoleVO> list() {
        return roleMapper.list();
    }

    //分页查询商品列表
    @Override
    public JsonPage<Role> getAllRoleByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Role> list = roleMapper.findAllRole();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public void delete(Long id) {
        List<RoleVO> byId = roleMapper.getById(id);
        if (byId==null){
            String message="删除失败，该用户不存在";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
        int i = roleMapper.deleteById(id);
        if (i!=1){
            String message="服务器忙 请联系管理员";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }

    }

    @Override
    @Transactional
    public void updateById(Role role) {
        log.debug("需要修改的员工信息:{}", role);
        LocalDateTime dateTime = LocalDateTime.now();
        role.setGmtModified(dateTime);
        int row = roleMapper.updateById(role);
        if (row!=1){
            String message="修改失败,服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }
    @Override
    public Results<Object> upload(MultipartFile file) {
        if (file == null) new Results<>(404, "导入数据失败", null);
        ArrayList<Object> list = new ArrayList<>();
        AnalysisEventListener listener = new AnalysisEventListener() {
            @Override
            public void invoke(Object data, AnalysisContext context) {
                //获取到每一行数据，逐行进行处理
                list.add(data);
                RoleVO clueVO = (RoleVO) data;
                Role role = new Role();
                BeanUtils.copyProperties(clueVO, role);
                //这里将获取到的数据封装回实体类对象中，并在数据库持久化
                roleMapper.ExcelInsert(role);
                System.out.println(Arrays.toString(new ArrayList[]{list}));
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("导入数据完毕");
            }
        };
        try {
            EasyExcel.read(file.getInputStream(), RoleVO.class, listener).sheet(0).doRead();
        } catch (IOException e) {
            log.error("导入出错：{}", e.getMessage());
        }
        return new Results<>(200, "导入数据成功", list);
    }


    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1)查询数据
        List<RoleVO> list = roleMapper.list();

        //2)设置文件下载
        response.setHeader("content-disposition", "attachment;filename=iAdmin_" + System.currentTimeMillis() + ".xlsx");

        EasyExcel.write(response.getOutputStream(), RoleVO.class).sheet(System.currentTimeMillis() + "").doWrite(list);

    }

    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        log.debug("roleId={}",roleId);
        int rows = roleMenuMapper.deleteByRoleId(roleId);
        log.debug("已删除{}条数据",rows);

        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) { // 二级菜单 并且传过来的menuId数组里面没有它的父级id
                // 那么我们就得补上这个父级id
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<RoleMenu> getRoleMenu(Integer roleId) {
        List<RoleMenu> roleMenus = roleMenuMapper.selectByRoleId(roleId);
        for (RoleMenu roleMenu : roleMenus){
            log.debug("数据:{}",roleMenu);
        }
        return roleMenus;
    }
}
