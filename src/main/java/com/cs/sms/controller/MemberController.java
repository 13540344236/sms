package com.cs.sms.controller;

import com.cs.sms.pojo.dto.MemberDTO;
import com.cs.sms.pojo.vo.MemberVO;
import com.cs.sms.service.IMemberService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "会员模块")
@RestController
@RequestMapping("/members") // 自己修改
public class MemberController {

    @Autowired
    IMemberService memberService;

    @ApiOperation("添加会员")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody MemberDTO memberDTO){
        log.debug("接收员工信息:{}",memberDTO);
        memberService.addNew(memberDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除会员")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        log.debug("接收需要删除的会员id:{}",id);
        memberService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改会员信息")
    @ApiOperationSupport(order = 300)
    @PostMapping("/update")
    public JsonResult updateById(@RequestBody MemberDTO memberDTO){
        log.debug("接收需要修改的会员信息:{}",memberDTO);
        memberService.updateById(memberDTO);
        return JsonResult.ok();
    }

    @ApiOperation("通过id查询会员信息")
    @ApiOperationSupport(order = 400)
    @PostMapping("/selectById")
    public JsonResult selectById(Long id){
        log.debug("接收需要查询的会员id:{}",id);
        MemberVO memberVO = memberService.selectById(id);
        return JsonResult.ok(memberVO);
    }

    @ApiOperation("通过phone查询会员信息")
    @ApiOperationSupport(order = 400)
    @PostMapping("/selectByPhone")
    public JsonResult selectByPhone(String phone){
        log.debug("接收需要查询的会员号码:{}",phone);
        MemberVO memberVO = memberService.selectByPhone(phone);
        return JsonResult.ok(memberVO);
    }
    @ApiOperation("查询所有员工信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
    public JsonResult list(){
        List<MemberVO> list = memberService.list();
        return JsonResult.ok(list);
    }

}
