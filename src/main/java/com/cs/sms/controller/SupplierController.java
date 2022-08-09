package com.cs.sms.controller;


import com.cs.sms.pojo.dto.SupplierAddNewDTO;
import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.pojo.vo.SupplierListVO;
import com.cs.sms.service.ISupplierService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "供应商模块")
@RestController
@RequestMapping("/suppliers") // 自己修改
public class SupplierController {
    @Autowired
    private ISupplierService service;

    @ApiOperation("添加供应商")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody @Valid SupplierAddNewDTO supplierAddNewDTO){
        service.addNew(supplierAddNewDTO);
        return JsonResult.ok("添加成功");
    }

    @ApiOperation("删除供应商")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id){
        service.delete(id);
        return JsonResult.ok("删除成功");
    }

    @ApiOperation("修改供应商")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult update(Supplier supplier){
        service.update(supplier);
        return JsonResult.ok("修改成功");
    }
    /*
    @ApiOperation("修改供应商")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult update(@PathVariable Long id,SupplierListVO supplierListVO){
        service.update(id);
        return JsonResult.ok("修改成功");
    }*/

    @ApiOperation("供应商列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult select(){
        List<SupplierListVO> list = service.list();
        return JsonResult.ok(list);
    }
}
