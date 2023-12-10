# 																		bug库

## 一、数据返回

### 1. 返回覆盖问题

```java
public Result<?> add(@RequestBody StuCourseFace stuCourseFace) {
        //这种写法
  		 stuCourseFaceService.add(stuCourseFace);
        return Result.ok(stuCourseFace);
}
 public Result<?> add(StuCourseFace face) {//实现
        String administrator = this.getAdministrator();
        if (StringUtils.isBlank(administrator)){
            return Result.error("您所在的公司不存在管理员,无法提交");
        }
        face.setAuditor(administrator);
        face.setCreateName(getLoginUser().getUserInfo().getRealname());
        baseMapper.insert(face);
        return Result.OK(face);    
 				//这种实现的写法，不管有没有错误，最终都会走到return Result.OK(face)，覆盖掉"您所在的公司不存在管理员,无法提交"，若是有错误，而在controller中add的返回return Result.ok(stuCourseFace)，最终返回的是没有id、Auditor、creatTime的stuCourseFace; 
}
//正确写法如下：
public Result<?> add(@RequestBody StuCourseFace stuCourseFace) {
    return stuCourseFaceService.add(stuCourseFace);
}
public Result<?> add(StuCourseFace face) {
        String administrator = this.getAdministrator();
        if (StringUtils.isBlank(administrator)){
            return Result.error("您所在的公司不存在管理员,无法提交");
        } else {
            face.setAuditor(administrator);
            face.setCreateName(getLoginUser().getUserInfo().getRealname());
            baseMapper.insert(face);
            return Result.OK(face);
        }
    }
```

## 二、空指针异常

```java
appTaskDetail.setUploadList(uploadList);
如果 uploadList 是 null,时会抛出空指针异常（NullPointerException）
```

## 三、sql异常

```java
SELECT
        *
FROM
        stu_activity_base b
inner JOIN stu_activity_user u ON b.id = u.activity_id
WHERE
        b.dr = 0
AND u.exam_user_code = 'lil987'
AND b.activity_status IN ( 2,3)
AND b.remark1 != 'SOP'；
//正确写法：
AND (b.remark1 != 'SOP' OR b.remark1 IS NULL)
如果在stu_activity_base表中存在remark1为null的记录，那么在执行上述SQL查询时，这些记录将被忽略，不会出现在结果集中。
这是因为在SQL中，使用"!="或"<>"运算符进行不等于比较时，如果左侧的值为null，那么比较结果被认为是false。所以在WHERE子句中，当b.remark1 != 'SOP'时，如果b.remark1为null，这个条件会被认为是false，导致这条记录不会被选中。
```



