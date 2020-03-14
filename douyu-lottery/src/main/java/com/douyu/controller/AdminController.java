package com.douyu.controller;

import com.douyu.dao.LotteryManagementMapper;
import com.douyu.pojo.LotteryLevel;
import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.LotteryManagementExample;
import com.douyu.util.LotteryCommon;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LotteryManagementMapper lotteryManagementMapper;

    /**
     * 获取有效活动
     * @return
     */
    @RequestMapping(value ="/getEffectiveLotteryId",method = RequestMethod.GET)
    public String  getEffectiveLotteryId() {
        List<LotteryManagement> lotteryManagements = lotteryManagementMapper.selectByExample(new LotteryManagementExample());
        LotteryManagement[] strings = new LotteryManagement[lotteryManagements.size()];
        lotteryManagements.toArray(strings);
        HashMap<String, String> lotteryId = LotteryCommon.getLotteryId(strings);
        if(lotteryId==null){
            return "暂无活动";
        }
        return lotteryId.toString();
    }

    /**
     * 查询所有权限
     * @param lotteryId
     * @return
     */
    @RequestMapping(value = "/queryRuleList/{lotteryId}",method = RequestMethod.GET)
    public LotteryLevel[] queryRuleList(@PathVariable String lotteryId) {
        return LotteryCommon.queryRuleList(lotteryId);
    }
}
