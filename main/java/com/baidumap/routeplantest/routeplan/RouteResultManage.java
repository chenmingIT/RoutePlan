package com.baidumap.routeplantest.routeplan;

import com.baidu.mapapi.search.route.DrivingRouteResult;

/**
 * Created by ChenMing on 2017/1/14.
 * 代理模式，解决路径搜索模块出现异步问题
 */

public interface RouteResultManage {
    //结果成功的处理
    void routeResultSuccess(DrivingRouteResult result) ;
    //结果失败的处理
    void routeResultFail() ;
}
