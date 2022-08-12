package com.cs.sms.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *  通用的,支持保存各种分页查询结果信息的类
 */
@Data
public class JsonPage<T> implements Serializable {
    // 真实开发中,根据实际需求,定义分页信息
    // 这里声明最基本的4个分页信息属性
    @ApiModelProperty(value = "总页数",name = "totalPages")
    private Integer totalPages;
    @ApiModelProperty(value = "总条数",name = "totalCount")
    private Long totalCount;
    @ApiModelProperty(value = "当前页码",name = "pageNum")
    private Integer pageNum;
    @ApiModelProperty(value = "每条页数",name = "pageSize")
    private Integer pageSize;
    // 如果需要可以添加其他属性...

    // 声明一个属性,来保存分页查询的数据结果
    @ApiModelProperty(value = "分页数据",name = "list")
    private List<T> list;

    // 上面定义的所有属性,下面要编写将 PageInfo 装换为 JsonPage 的方法
    // 我们学习的 SpringData 分页查询返回的类型是Page,在需要时也可以添加针对他的转换方法
    public static <T> JsonPage<T> restPage(PageInfo<T> pageInfo) {
        // 开始转换,思路是将pageInfo的对应的属性赋值给JsonPage对象
        JsonPage<T> result = new JsonPage<>();
        // 赋值分页信息
        result.setTotalPages(pageInfo.getPages());
        result.setTotalCount(pageInfo.getTotal());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        // 赋值分页信息
        result.setList(pageInfo.getList());
        // 别忘了返回
        return result;
    }
}
