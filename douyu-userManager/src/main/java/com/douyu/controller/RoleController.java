package com.douyu.controller;

import com.douyu.pojo.Role;
import com.douyu.pojo.UserRoleExample;
import com.douyu.pojo.UserRoleKey;
import com.douyu.service.RoleService;
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
 * @time: 2020/3/9 16:14
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色相关接口")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    /**
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "saveRole",notes = "添加角色")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveRole(Role role){
        try {
            roleService.insert(role);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    /**
     * 更新角色
     * @param role
     * @return
     */
    @ApiOperation(value = "updateRole",notes = "更新角色")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRole(Role role){
        try {
            roleService.updateByPrimaryKey(role);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 删除角色
     * @param
     * @return
     */
    //TODO 考虑先查询角色当前是否含有用户
    @ApiOperation(value = "deleteRole",notes = "删除角色")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRole(@RequestParam(value = "id",defaultValue = "0")Long id ){
        try {
            if(id.intValue()==0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            UserRoleExample userRoleExample=new UserRoleExample();
            userRoleExample.createCriteria().andRoleIdEqualTo(id);
            List<UserRoleKey> userRoleKeys = userRoleService.selectByExample(userRoleExample);
            if(userRoleKeys!=null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("还存在该角色的用户，不能删除!");
            }
            roleService.deleteByPrimaryKey(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据ids查询角色
     * @param ids
     * @return
     */
    @ApiOperation(value = "queryRoleByIds",notes = "根据多个id查询角色")
    @RequestMapping(value="{ids}",method = RequestMethod.GET)
    public ResponseEntity<List<Role>> queryRole(@PathVariable String ids ){
        List<Role> roles=new ArrayList<>();
        try {
            String[] stringIds=ids.split(",");
            for(int i=0;i<stringIds.length;i++){
                Long id=Long.parseLong(stringIds[i]);
                Role role = roleService.selectByPrimaryKey(id);
                roles.add(role);
            }
            if(null==roles){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
