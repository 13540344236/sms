package com.cs.sms.controller;


import com.cs.sms.pojo.dto.SaleAddNewDTO;
import com.cs.sms.pojo.dto.SaleEditDTO;
import com.cs.sms.pojo.vo.SaleListItemVO;
import com.cs.sms.service.ISaleService;
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
@Api(tags = "销售模块")
@RestController
@RequestMapping("/sales") // 自己修改
public class SaleController {

}
