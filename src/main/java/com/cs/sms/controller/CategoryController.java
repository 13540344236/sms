package com.cs.sms.controller;


import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.dto.CategoryDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Category;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.service.IAdminService;
import com.cs.sms.service.ICategoryService;
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

@Slf4j
@Api(tags = "类别模块")
@RestController
@RequestMapping("/category") // 自己修改
public class CategoryController {

    @Autowired(required = false)
    ICategoryService categoryService;

    /**
     * 增加类别
     *
     * @param categoryDTO
     */
    @ApiOperation("添加类别")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody CategoryDTO categoryDTO) {
        log.debug("接收类别信息:{}", categoryDTO);
        categoryService.addNew(categoryDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除类别")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult deleteById(@PathVariable Long id) {
        log.debug("接收需要删除的类别id:{}", id);
        categoryService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改类别信息")
    @ApiOperationSupport(order = 300)
    @PostMapping("/update")
    public JsonResult updateById(@RequestBody CategoryDTO categoryDTO) {
        log.debug("接收需要修改的类别信息:{}", categoryDTO);
        categoryService.updateById(categoryDTO);
        return JsonResult.ok();
    }

    @ApiOperation("通过id查询类别信息")
    @ApiOperationSupport(order = 400)
    @GetMapping("/{id:[0-9]+}/selectById")
    public JsonResult selectById(@PathVariable Long id) {
        log.debug("接收需要查询的类别id:{}", id);
        Category category = categoryService.selectById(id);
        return JsonResult.ok(category);
    }

    @ApiOperation("通过姓名查询类别信息")
    @ApiOperationSupport(order = 401)
    @PostMapping("/selectByName")
    public JsonResult selectByName(@RequestBody CategoryDTO categoryDTO) {
        String name=categoryDTO.getName();
        log.debug("接收需要查询的类别名:{}", name);
        Category category = categoryService.selectByName(name);
        return JsonResult.ok(category);
    }

    @ApiOperation("查询所有类别信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
    public JsonResult list() {
        List<Category> list = categoryService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("分页查询类别")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")})
    public JsonResult<JsonPage<Category>> pageAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Category> allCategoryByPage = categoryService.getAllCategoryByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allCategoryByPage);
    }

}
