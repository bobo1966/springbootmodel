package com.fan.ersha.controller;

import com.fan.ersha.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: 范
 * @description: UserController
 * @date: Created in 17:54 2018/1/25
 * @modifiedBy: 修改人
 */
@Controller  //这里可以用 @RestController 注解，可以省去方法上面的 @ResponseBoby
@RequestMapping(value = "user")
@CrossOrigin //跨域访问加上这个注解就行了，加在类上就是表示类里面的方法都可以跨域访问，加在方法上就是方法跨域
@Api(value = "用户管理", description = "用户管理")//类描述
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author: fan
     * @description:
     * @date: 10:28 2018/1/26
     */
    @ApiOperation(value = "获取所有用户" , notes = "详细描述" )//方法描述
    @ApiResponses(value = {@ApiResponse(code = 200, message = "用户信息")})//方法返回状态描述，描述API操作可能出现的异常情况
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "行数", required = true, dataType = "Integer")
    })//请求的参数
    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}" , method = RequestMethod.POST)
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        //System.out.println("124785235");
        return userService.findAllUser(pageNum,pageSize);
        
    }


    /**
     * 开启idea自动make功能

     　　CTRL + SHIFT + A --> 查找make project automatically --> 回车后选中
     　　CTRL + SHIFT + alt+/ --> 查找Registry --> 找到并勾选compiler.automake.allow.when.app.running

     最后重启idea

        项目根目录命令行输入一下命令，用mybatis自动生成代码
        mvn mybatis-generator:generate -e
     */



}
