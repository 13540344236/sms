package com.cs.sms.controller;


import com.cs.sms.service.IBrandService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "品牌模块")
@RestController
@RequestMapping("") // 自己修改
public class BrandController {
    @Autowired
    private IBrandService brandService;

    public BrandController() {
        System.out.println("创建控制器对象：BrandController");
    }
}
