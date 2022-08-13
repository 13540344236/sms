package com.cs.sms.controller;


import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.service.IGoodsBadService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "商品报损模块")
@RestController
@RequestMapping("/goodsbad") // 自己修改
public class GoodsBadController {
    @Autowired
    private IGoodsBadService service;

    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody GoodsBadDTO goodsBadDTO){
        service.addNew(goodsBadDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除商品")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("需要删除的商品的ID = {}",id);
        service.deleteById(id);
        log.debug("删除成功!");
        return JsonResult.ok();
    }

    @ApiOperation("编辑商品")
    @ApiOperationSupport(order = 400)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Long id, @RequestBody GoodsBadVO goodsBadVO) {
        log.debug("接收到的参数：id = {}",id);
        log.debug("接收到的参数：{}",goodsBadVO);
        service.update(id,goodsBadVO);
        log.debug("编辑成功!");
        return JsonResult.ok();
    }

    @ApiOperation("商品列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询品牌列表的请求");
        List<GoodsBadVO> goods = service.list();
        return JsonResult.ok(goods);
    }

    @ApiOperation("导出商品报表")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        service.createExcel(response);

    }
}
