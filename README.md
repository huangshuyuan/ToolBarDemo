# ToolBarDemo
折叠式标题栏，炫酷效果，5.0支持
CollapsingToolbarLayout作用是提供了一个可以折叠的Toolbar，它继承至FrameLayout，给它设置layout_scrollFlags，它可以控制包含在CollapsingToolbarLayout中的控件(如：ImageView、Toolbar)在响应layout_behavior事件时作出相应的scrollFlags滚动事件(移除屏幕或固定在屏幕顶端)。建议在Android5.0+使用比较好。低版本不太适配

![demo](http://upload-images.jianshu.io/upload_images/3805053-17ab22fca23a4466.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
我的简书：http://www.jianshu.com/p/564a0c56022b

### 使用CollapsingToolbarLayout：
```
<android.support.design.widget.AppBarLayout  
        android:layout_width="match_parent"  
        android:layout_height="256dp"  
        android:fitsSystemWindows="true">  
        <android.support.design.widget.CollapsingToolbarLayout  
            android:id="@+id/collapsing_toolbar_layout"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent"  
            app:contentScrim="#30469b"  
            app:expandedTitleMarginStart="48dp"  
            app:layout_scrollFlags="scroll|exitUntilCollapsed">  
  
            <ImageView  
                android:layout_width="match_parent"  
                android:layout_height="match_parent"  
                android:scaleType="centerCrop"  
                android:src="@mipmap/bg"  
                app:layout_collapseMode="parallax"  
                app:layout_collapseParallaxMultiplier="0.7"  />  
  
            <android.support.v7.widget.Toolbar  
                android:id="@+id/toolbar"  
                android:layout_width="match_parent"  
                android:layout_height="?attr/actionBarSize"  
                app:layout_collapseMode="pin" />  
        </android.support.design.widget.CollapsingToolbarLayout>  
    </android.support.design.widget.AppBarLayout>  
```
我们在CollapsingToolbarLayout中设置了一个ImageView和一个Toolbar。并把这个CollapsingToolbarLayout放到AppBarLayout中作为一个整体。
### 1、在CollapsingToolbarLayout中：
我们设置了layout_scrollFlags:关于它的值我这里再说一下：
* scroll - 想滚动就必须设置这个。
* enterAlways - 实现quick return效果, 当向下移动时，立即显示View（比如Toolbar)。
* exitUntilCollapsed - 向上滚动时收缩View，但可以固定Toolbar一直在上面。
* enterAlwaysCollapsed - 当你的View已经设置minHeight属性又使用此标志时，你的View只能以最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。

***
其中还设置了一些属性，简要说明一下：

* contentScrim - 设置当完全CollapsingToolbarLayout折叠(收缩)后的背景颜色。（收缩前，是ToolBar的背景色，透明即可）
* expandedTitleMarginStart - 设置扩张时候(还没有收缩时)title向左填充的距离。
* title:当titleEnable设置为true的时候，在toolbar展开的时候，显示大标题，toolbar收缩时，显示为toolbar上面的小标题。 
* scrimAnimationDuration：该属性控制toolbar收缩时，颜色变化的动画持续时间。即颜色变为contentScrim所指定的颜色进行的动画所需要的时间。 
* expandedTitleGravity：指定toolbar展开时，title所在的位置。类似的还有expandedTitleMargin、collapsedTitleGravity这些属性。 
* collapsedTitleTextAppearance：指定toolbar收缩时，标题字体的样式，类似的还有expandedTitleTextAppearance。

###  2、在ImageView控件中：
我们设置了：
layout_collapseMode (折叠模式) - 有两个值:
* pin -  设置为这个模式时，当CollapsingToolbarLayout完全收缩后，Toolbar还可以保留在屏幕上。
* parallax - 设置为这个模式时，在内容滚动时，CollapsingToolbarLayout中的View（比如ImageView)也可以同时滚动，实现视差滚动效果，通常和layout_collapseParallaxMultiplier(设置视差因子)搭配使用。
* layout_collapseParallaxMultiplier(视差因子) - 设置视差滚动因子，值为：0~1。 
### 3、在Toolbar控件中：
我们设置了layout_collapseMode(折叠模式)：为pin。

综上分析：当设置了layout_behavior的控件响应起了CollapsingToolbarLayout中的layout_scrollFlags事件时，ImageView会有视差效果的向上滚动移除屏幕，当开始折叠时CollapsingToolbarLayout的背景色(也就是Toolbar的背景色)就会变为我们设置好的背景色，Toolbar也一直会固定在最顶端。
***
【注】：使用CollapsingToolbarLayout时必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上不会显示。即：
```
mCollapsingToolbarLayout.setTitle(" ");
```
#### 该变title的字体颜色：
* 扩张时候的title颜色：
mCollapsingToolbarLayout.setExpandedTitleColor();
* 收缩后在Toolbar上显示时的title的颜色：
mCollapsingToolbarLayout.setCollapsedTitleTextColor();
这个颜色的过度变化其实CollapsingToolbarLayout已经帮我们做好，它会自动的过度，比如我们把收缩后的title颜色设为绿色

布局文件：
```
**[html]** [view plain](http://blog.csdn.net/u010687392/article/details/46906657#) [copy](http://blog.csdn.net/u010687392/article/details/46906657#)
 [print](http://blog.csdn.net/u010687392/article/details/46906657#)[?](http://blog.csdn.net/u010687392/article/details/46906657#)

<android.support.design.widget.CoordinatorLayout   
    xmlns:android="http://schemas.android.com/apk/res/android"  
    xmlns:app="http://schemas.android.com/apk/res-auto"  
    xmlns:tools="http://schemas.android.com/tools"  
    android:layout_width="match_parent"  
    android:layout_height="match_parent"  
    tools:context=".MainActivity">  
  
    <android.support.design.widget.AppBarLayout  
        android:layout_width="match_parent"  
        android:layout_height="256dp"  
        android:fitsSystemWindows="true">  
        <android.support.design.widget.CollapsingToolbarLayout  
            android:id="@+id/collapsing_toolbar_layout"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent"  
            app:contentScrim="#30469b"  
            app:expandedTitleMarginStart="48dp"  
            app:layout_scrollFlags="scroll|exitUntilCollapsed">  
  
            <ImageView  
                android:layout_width="match_parent"  
                android:layout_height="match_parent"  
                android:scaleType="centerCrop"  
                android:src="@mipmap/bg"  
                app:layout_collapseMode="parallax"  
                app:layout_collapseParallaxMultiplier="0.7"  />  
  
            <android.support.v7.widget.Toolbar  
                android:id="@+id/toolbar"  
                android:layout_width="match_parent"  
                android:layout_height="?attr/actionBarSize"  
                app:layout_collapseMode="pin" />  
        </android.support.design.widget.CollapsingToolbarLayout>  
    </android.support.design.widget.AppBarLayout>  
  
    <LinearLayout  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical"  
        app:layout_behavior="@string/appbar_scrolling_view_behavior">  
        <android.support.v7.widget.RecyclerView  
            android:id="@+id/recyclerView"  
            android:layout_width="match_parent"  
            android:layout_height="match_parent"  
            android:scrollbars="none" />  
    </LinearLayout>  
</android.support.design.widget.CoordinatorLayout>  
```

#### Java:
```
 //设置状态栏
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0之上
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
 
Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);  
    setSupportActionBar(mToolbar);  
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);  
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {  
        @Override  
        public void onClick(View v) {  
            onBackPressed();  
        }  
    });  
    //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示  
    CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);  
    mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");  
    //通过CollapsingToolbarLayout修改字体颜色  
    mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色  
    mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
```
# CollapsingToolbarLayout的展开与折叠
使用官方提供的 AppBarLayout.OnOffsetChangedListener就能实现了，不过要封装一下才好用。

自定义一个继承了 AppBarLayout.OnOffsetChangedListener的类，这里命名为AppBarStateChangeListener：
```
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
 
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
 
    private State mCurrentState = State.IDLE;
 
    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }
 
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
```
然后这样使用它：
```
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {
                    
                    //展开状态
                    
                }else if(state == State.COLLAPSED){
                    
                    //折叠状态
                     
                }else {
                
                    //中间状态
                
                }
            }
        });\
```

Demo地址：https://github.com/huangshuyuan/ToolBarDemo/
