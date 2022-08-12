package com.cs.sms.controller;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.service.IGoodsService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "商品图片模块")
@RestController
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private IGoodsService goodsService;

    @ApiOperation("保存商品图片地址")
    @ApiOperationSupport(order = 100)
    @PostMapping("/goods")
    public String addGoodsUrl(String url) {
        log.debug("接收到的请求参数：{}", url);
//        pictureService.addGoodsUrl(url)
        log.debug("添加成功!");
        return url;
    }
}
