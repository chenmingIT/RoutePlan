package com.baidumap.routeplantest.routeplan;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

/**
 * Created by ChenMing on 2017/1/14.
 * 驾车路线规划功能模块
 * 城市-起止点
 */

public class RoutePlan implements OnGetRoutePlanResultListener{
    //城市
    private String city ;
    //起止点
    private String start,end ;
    //结果处理接口
    RouteResultManage routeResultManage ;

    public RoutePlan(RouteResultManage routeResultManage, String city, String start, String end) {
        this.routeResultManage = routeResultManage ;
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public void search(){
        //路径搜索用到的类
        RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        PlanNode stNode = PlanNode.withCityNameAndPlaceName(city, start);
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(city, end);
        //开始搜索
        boolean searchState = mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode).to(enNode));
    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        //驾车
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            routeResultManage.routeResultFail();
            return;
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            routeResultManage.routeResultFail();
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            if (result.getRouteLines().size() >= 1) {
                routeResultManage.routeResultSuccess(result);
            } else {
                routeResultManage.routeResultFail();
                return;
            }
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }
}
