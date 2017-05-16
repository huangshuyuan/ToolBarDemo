package com.hsy.toolbar.toolbardemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    AppBarLayout mAppBarLayout;//标题部分
    CollapsingToolbarLayout mCollapsingToolbarLayout;//折叠式标题栏

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.title_fragment_five));

        //设置状态栏
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给页面设置工具栏
        if (mCollapsingToolbarLayout != null) {
            //设置隐藏图片时候ToolBar的颜色
            mCollapsingToolbarLayout.setContentScrimColor(Color.parseColor("#11B7F3"));
            //设置工具栏标题
            mCollapsingToolbarLayout.setTitle("编程是一种信仰");
        }


        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {

                    //展开状态
                    Toast.makeText(MainActivity.this, "展开", Toast.LENGTH_SHORT).show();

                } else if (state == State.COLLAPSED) {

                    //折叠状态
                    Toast.makeText(MainActivity.this, "折叠状态", Toast.LENGTH_SHORT).show();
                } else {

                    //中间状态
//                    Toast.makeText(getActivity(),"中间状态",Toast.LENGTH_SHORT).show();

                }
            }
        });
        initRecyle();
    }


    private void initRecyle() {
        String[] itemArr = new String[100];
        for (int i = 0; i < 30; i++) {
            itemArr[i] = "第" + i + "行数据";
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyMainItemAdaper adpter = new MyMainItemAdaper(this, itemArr);
        recyclerView.setAdapter(adpter);
        adpter.setOnItemClickListener(new MyMainItemAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("点击", position + "");

            }
        });
    }

}
