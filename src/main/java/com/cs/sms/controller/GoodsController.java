package com.cs.sms.controller;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.service.IGoodsService;
import com.cs.sms.service.impl.UploadService;
import com.cs.sms.util.OssUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@Slf4j
@Api(tags = "商品模块")
@RestController
@RequestMapping("/goods") // 自己修改
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private UploadService uploadService;

    @ApiOperation("新增商品")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody GoodsAddNewDTO goodsAddNewDTO) {
        log.debug("接收到的请求参数：{}", goodsAddNewDTO);
        goodsService.addNew(goodsAddNewDTO);
        log.debug("添加成功!");
        return JsonResult.ok();
    }

    @ApiOperation("删除商品")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("需要删除的商品的ID = {}",id);
        goodsService.deleteById(id);
        log.debug("删除成功!");
        return JsonResult.ok();
    }

    @ApiOperation("编辑商品")
    @ApiOperationSupport(order = 400)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Long id, @RequestBody GoodsEditDTO goodsEditDTO) {
        log.debug("接收到的参数：id = {}",id);
        log.debug("接收到的参数：{}",goodsEditDTO);
        goodsService.update(id,goodsEditDTO);
        log.debug("编辑成功!");
        return JsonResult.ok();
    }

    @ApiOperation("商品列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询商品列表的请求");
        List<GoodsListVO> goods = goodsService.list();
        return JsonResult.ok(goods);
    }

    @ApiOperation("分页查询商品")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Goods>> pageGood(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Goods> allGoodsByPage = goodsService.getAllGoodsByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allGoodsByPage);
    }

    @ApiOperation("导出商品报表")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        goodsService.createExcel(response);

    }

    @RequestMapping("/upload")
    public String upload(MultipartFile picFile) throws IOException {
        log.debug("图片上传");
        log.debug("picFile = " + picFile);
        return uploadService.upload(picFile);
    }
    @RequestMapping("/remove")
    public void remove(String newFileName){
        log.debug("删除的文件名 = " + newFileName);
        String filePath = newFileName;
        //删除文件
        new File(filePath).delete();
        log.debug(filePath);

    }


}
