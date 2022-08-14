package com.cs.sms.controller;

import com.cs.sms.pojo.dto.MemberDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Member;
import com.cs.sms.pojo.vo.MemberVO;
import com.cs.sms.service.IMemberService;
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
    @GetMapping("/selectById")
    public JsonResult selectById(Long id){
        log.debug("接收需要查询的会员id:{}",id);
        MemberVO memberVO = memberService.selectById(id);
        return JsonResult.ok(memberVO);
    }

    @ApiOperation("通过phone查询会员信息")
    @ApiOperationSupport(order = 400)
    @GetMapping("/{phone:[0-9]+}/selectByPhone")
    public JsonResult selectByPhone(@PathVariable Long phone){
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

    @ApiOperation("分页查询会员")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Member>> pageGood(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Member> allMemberByPage = memberService.getAllMemberByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allMemberByPage);
    }

}
