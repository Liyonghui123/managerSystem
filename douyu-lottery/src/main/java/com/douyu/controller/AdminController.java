package com.douyu.controller;

import com.douyu.pojo.LotteryLevel;
import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.LotteryManagementExample;
import com.douyu.service.LotteryManagementService;
import com.douyu.util.LotteryCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/14 16:50
 */

@RestController
@Api(tags = "管理员相关接口")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LotteryManagementService lotteryManagementService;

    /**
     * 获取有效活动
     * @return
     */
    @ApiOperation(value = "getEffectiveLotteryId", notes = "获取有效活动Id")
    @RequestMapping(value ="/getEffectiveLotteryId",method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> getEffectiveLotteryId() {
        List<LotteryManagement> lotteryManagements = lotteryManagementService.selectByExample(new LotteryManagementExample());
        LotteryManagement[] strings = new LotteryManagement[lotteryManagements.size()];
        lotteryManagements.toArray(strings);
        HashMap<String, String> lotteryId = LotteryCommon.getLotteryId(strings);
        if(lotteryId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(lotteryId);
    }

    /**
     * 查询所有权限
     * @param lotteryId
     * @return
     */
    @ApiOperation(value = "queryRuleList", notes = "查询权限种类")
    @RequestMapping(value = "/queryRuleList/{lotteryId}",method = RequestMethod.GET)
    public ResponseEntity<LotteryLevel[]> queryRuleList(@PathVariable String lotteryId) {
        LotteryLevel[] lotteryLevels = LotteryCommon.queryRuleList(lotteryId);
        if(lotteryLevels==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(lotteryLevels);
    }
}
