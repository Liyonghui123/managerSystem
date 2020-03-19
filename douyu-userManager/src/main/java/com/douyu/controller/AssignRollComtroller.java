package com.douyu.controller;

import com.douyu.pojo.UserRoleExample;
import com.douyu.pojo.UserRoleKey;
import com.douyu.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/9 17:01
 */
@Api(tags = "角色分配相关接口")
@RestController
@RequestMapping("/assign")
public class AssignRollComtroller {
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 分配角色
     * @param userRole
     * @return
     */
    @ApiOperation(value = "saveAssignRole",notes = "分配角色")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveRole(UserRoleKey userRole){
        try {
            userRoleService.insert(userRole);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 删除分配角色
     * @param
     * @return
     */
    @ApiOperation(value = "deleteAssignRole",notes = "删除分配角色")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAssignRole(@RequestParam(value = "userRoleKey")UserRoleKey id ){
        try {
            if(id==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            userRoleService.deleteByPrimaryKey(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据ids查询分配角色
     * @param ids
     * @return
     */
    @ApiOperation(value = "selectAssignRole",notes = "查询分配角色")
    @RequestMapping(value="{ids}",method = RequestMethod.GET)
    public ResponseEntity<List<List<UserRoleKey>>> queryRole(@PathVariable String ids ){
        List<List<UserRoleKey>> userRoles=new ArrayList<>();
        try {
            String[] stringIds=ids.split(",");
            for(int i=0;i<stringIds.length;i++){
                Long id=Long.parseLong(stringIds[i]);
                UserRoleExample userRoleExample=new UserRoleExample();
                userRoleExample.createCriteria().andRoleIdEqualTo(id);

                List<UserRoleKey> userRole = userRoleService.selectByExample(userRoleExample);
                userRoles.add(userRole);
            }
            if(null==userRoles){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(userRoles);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
