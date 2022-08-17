package com.cs.sms.controller;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Menu;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.service.IGoodsService;
import com.cs.sms.service.IMenuService;
import com.cs.sms.service.impl.UploadService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu") // 自己修改
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("新增菜单")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody Menu menu) {
        log.debug("接收到的请求参数：{}", menu);
        menuService.addNew(menu);
        log.debug("添加成功!");
        return JsonResult.ok();
    }

    @ApiOperation("删除菜单")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Integer id) {
        log.debug("需要删除的菜单的ID = {}",id);
        menuService.deleteById(id);
        log.debug("删除成功!");
        return JsonResult.ok();
    }

    @ApiOperation("编辑菜单")
    @ApiOperationSupport(order = 400)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Integer id, @RequestBody Menu menu) {
        log.debug("接收到的参数：id = {}",id);
        log.debug("接收到的参数：{}",menu);
        menuService.update(id,menu);
        log.debug("编辑成功!");
        return JsonResult.ok();
    }

    @ApiOperation("菜单列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询菜单列表的请求");
        List<Menu> menus = menuService.list();
        // 找出pid为null的一级菜单
        List<Menu> parentNode = menus.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        // 找出一级菜单的子菜单
        for(Menu menu : parentNode) {
            // 筛选所有数据中pid=父级id的数据就是二级菜单
            menu.setChildren(menus.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return JsonResult.ok(parentNode);
    }

    @ApiOperation("分页查询菜单")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Menu>> pageGood(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Menu> allMenuByPage = menuService.getAllMenuByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allMenuByPage);
    }

    @ApiOperation("根据姓名查询")
    @PostMapping("/selectByName")
    public JsonResult selectByName(@RequestBody Menu menu){
        String name = menu.getName();
        List<Menu> menus = menuService.selectByName(name);
        return JsonResult.ok(menus);
    }


}
