package com.baidumap.routeplantest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.qukuai.passenger.R;
import com.qukuai.passenger.service.RoutePlan;
import com.qukuai.passenger.service.RouteResultManage;

public class TestActivity extends AppCompatActivity implements RouteResultManage{

    private String city,start,end ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        EditText cityEditText = (EditText)findViewById(R.id.city) ;
        EditText startEditText = (EditText)findViewById(R.id.start) ;
        EditText endEditText = (EditText)findViewById(R.id.end) ;

        city = cityEditText.getText().toString() ;
        start = startEditText.getText().toString() ;
        end = endEditText.getText().toString() ;

        Button search = (Button)findViewById(R.id.search) ;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoutePlan routePlan = new RoutePlan(TestActivity.this,city,start,end) ;
                routePlan.search();
            }
        });
    }


    @Override
    public void routeResultFail() {
        TextView textView = (TextView)findViewById(R.id.result) ;
        textView.setText("路径规划失败");
    }

    @Override
    public void routeResultSuccess(DrivingRouteResult result) {
        TextView textView = (TextView)findViewById(R.id.result) ;
        textView.setText("路径规划成功");
    }
}
