package com.example.ihe.acmestudy.Interface.homepage;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ihe.acmestudy.R;
import com.example.ihe.acmestudy.Util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class HomePageActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, RvAdapter.OnItemClickListener {
    /**普通商品的（normalHolder）的标题集合*/
    private List<String> normalGoodsTitles =new ArrayList<>();
    private String[] gridMenuTitles=new String[]{"项目一","项目二","项目三","项目四","项目五","项目六","项目七","项目八","项目九","分类"};
//    @Nullable
//    @BindView(R.id.buttomBtnScan)
//    BottomBtn bottomBtnScan;
//    @Nullable
//    @BindView(R.id.bomBtnMsg)
//    BottomBtn bomBtnMsg;
//    @Nullable
//    @BindView(R.id.bomBtnHome)
//    BottomBtn bomBtnHome;
//    @Nullable
//    @BindView(R.id.bomBtnTiny)
//    BottomBtn bomBtnTiny;
//    @Nullable
//    @BindView(R.id.bomBtnAsk)
//    BottomBtn bomBtnAsk;
//    @Nullable
//    @BindView(R.id.bomBtnShopCar)
//    BottomBtn bomBtnShopCar;
//    @Nullable
//    @BindView(R.id.bomBtnMy)
//    BottomBtn bomBtnMy;
    @Nullable
    @BindView(R.id.swp)
    SwipeRefreshLayout swp;
    @Nullable
    @BindView(R.id.rv)
    RecyclerView rv;
    private Handler mHandler;
    private List<String> bigPics;
    private List<String> smallPics=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        initNormalGoodsTitles();
        initBigPics();
        initSmallPics();
        mHandler = new Handler();
//        initTopBtn();
        initBomBtn();
        initRv();
    }

    private void initNormalGoodsTitles() {
        //7个
        normalGoodsTitles.add("高等数学");
        normalGoodsTitles.add("大学物理");
        normalGoodsTitles.add("C语言");
        normalGoodsTitles.add("JAVA");
        normalGoodsTitles.add("线性代数");
        normalGoodsTitles.add("数据库");
        normalGoodsTitles.add("数据结构");
    }


    private void initBigPics() {
        bigPics = new ArrayList<>();
        bigPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
        bigPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
        bigPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
        bigPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
        bigPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
    }
    private void initSmallPics() {
        smallPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");
        smallPics.add("http://img4.imgtn.bdimg.com/it/u=75875508,1261253062&fm=26&gp=0.jpg");

    }
    private void initTopBtn() {
//        bottomBtnScan.setIvAndTv(R.drawable.scan, "扫一扫");
//        bottomBtnScan.setTvColor(Color.WHITE);
//        bomBtnMsg.setIvAndTv(R.drawable.comment, "消息");
//        bomBtnMsg.setTvColor(Color.WHITE);
    }
    private void initBomBtn() {
//        bomBtnHome.setIvAndTv(R.drawable.home_fill,"首页");
//        bomBtnHome.setTvColor(Color.parseColor("#d81e06"));
//
//        bomBtnTiny.setIvAndTv(R.drawable.we,"微社区");
//        bomBtnTiny.setTvColor(getResources().getColor(R.color.font33));
//
//        bomBtnAsk.setIvAndTv(R.drawable.ask,"问大家");
//        bomBtnAsk.setTvColor(getResources().getColor(R.color.font33));
//
//        bomBtnShopCar.setIvAndTv(R.drawable.cart,"知识仓库");
//        bomBtnShopCar.setTvColor(getResources().getColor(R.color.font33));
//
//        bomBtnMy.setIvAndTv(R.drawable.my,"个人");
//        bomBtnMy.setTvColor(getResources().getColor(R.color.font33));
    }
    private void initRv() {
        swp.setOnRefreshListener(this);
        RvAdapter rvAdapter = new RvAdapter(normalGoodsTitles);
        rvAdapter.setmOnItemClickLitener(this);

        initBannerTop(rvAdapter);
        initGridMenu(rvAdapter);
        initHeadLines(rvAdapter);
        initSnapUp(rvAdapter);
        initMiddleBannner(rvAdapter);
        initHotMarket(rvAdapter);
        initGoodsTrend(rvAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                }else if(postion==1){
                    return 2;
                }else if (postion==2){
                    return 2;
                }else if (postion==3){
                    return 2;
                }else if (postion==4){
                    return 2;
                }else if (postion==5){
                    return 2;
                }else if (postion==6){
                    return 2;
                }
                else {
                    return 1;
                }
            }
        });
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(rvAdapter);
        rv.addItemDecoration(new DividerGridItemDecoration(this));

    }
    private void initBannerTop(RvAdapter rvAdapter) {
        View bannerBigView = View.inflate(UIUtils.getContext(), R.layout.banner_top, null);
        FlyBanner bannerTop= (FlyBanner) bannerBigView.findViewById(R.id.bannerTop);
        rvAdapter.addHeadView0(bannerBigView);
        bannerTop.setImagesUrl(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }
    /**初始化10个子菜单*/
    private void initGridMenu(RvAdapter rvAdapter) {
        View gridMenu = View.inflate(UIUtils.getContext(), R.layout.grid_menu_10, null);

        List<GridMenu> gridMenus=new ArrayList<>();
        GridMenu gridCat= (GridMenu) gridMenu.findViewById(R.id.gridCat);
        GridMenu gridJHS= (GridMenu) gridMenu.findViewById(R.id.gridJHS);
        GridMenu gridTMgj= (GridMenu) gridMenu.findViewById(R.id.gridTMgj);
        GridMenu grid_wm= (GridMenu) gridMenu.findViewById(R.id.grid_wm);
        GridMenu grid_tmcs= (GridMenu) gridMenu.findViewById(R.id.grid_tmcs);

        GridMenu grid_czzx= (GridMenu) gridMenu.findViewById(R.id.grid_czzx);
        GridMenu grid_fzlx= (GridMenu) gridMenu.findViewById(R.id.grid_fzlx);
        GridMenu grid_ljb= (GridMenu) gridMenu.findViewById(R.id.grid_ljb);
        GridMenu grid_dj= (GridMenu) gridMenu.findViewById(R.id.grid_dj);
        GridMenu grid_fl= (GridMenu) gridMenu.findViewById(R.id.grid_fl);

        gridMenus.add(gridCat);
        gridMenus.add(gridJHS);
        gridMenus.add(gridTMgj);
        gridMenus.add(grid_wm);
        gridMenus.add(grid_tmcs);
        gridMenus.add(grid_czzx);
        gridMenus.add(grid_fzlx);
        gridMenus.add(grid_ljb);
        gridMenus.add(grid_dj);
        gridMenus.add(grid_fl);

        initGridMenuAttr(gridMenus);
        initOnCLick(gridMenus);
        rvAdapter.addHeaderView1(gridMenu);

    }
    /**初始化头条*/
    private void initHeadLines(RvAdapter rvAdapter) {
        View headLineView = View.inflate(UIUtils.getContext(), R.layout.tao_bao_headline, null);
        LinearLayout ll_headline= (LinearLayout) headLineView.findViewById(R.id.ll_headline);
        ll_headline.setOnClickListener(this);
        rvAdapter.addHeaderView2(headLineView);
    }
    /**初始化抢购*/
    private void initSnapUp(RvAdapter rvAdapter) {
        View snapUpView = View.inflate(UIUtils.getContext(), R.layout.snapup_layout, null);
        snapUpView.findViewById(R.id.ll_qtg).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_yhh).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_agj).setOnClickListener(this);
        snapUpView.findViewById(R.id.ll_bmqd).setOnClickListener(this);

        rvAdapter.addHeaderView3(snapUpView);
    }
    /**初始化中间的banner*/
    private void initMiddleBannner(RvAdapter rvAdapter) {
        View middleBannerView = View.inflate(UIUtils.getContext(), R.layout.banner_middle, null);
        FlyBanner bannerMiddle= (FlyBanner) middleBannerView.findViewById(R.id.bannerMiddle);
        rvAdapter.addHeaderView4(middleBannerView);
        bannerMiddle.setImagesUrl(smallPics);
        bannerMiddle.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtils.showToast("position--->"+position);
            }
        });
    }
    private void initHotMarket(RvAdapter rvAdapter) {
        View hotMarketView = View.inflate(UIUtils.getContext(), R.layout.hot_market, null);
        hotMarketView.findViewById(R.id.rl_hotMarket).setOnClickListener(this);
        rvAdapter.addHeaderView5(hotMarketView);
    }
    /**初始化潮流商品*/
    private void initGoodsTrend(RvAdapter rvAdapter) {
        View goodsTrendView = View.inflate(UIUtils.getContext(), R.layout.goods_trend, null);
        goodsTrendView.findViewById(R.id.goodsSectionIfashion).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionCWC).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionqbb).setOnClickListener(this);
        goodsTrendView.findViewById(R.id.goodsSectionZqtd).setOnClickListener(this);

        rvAdapter.addHeaderView6(goodsTrendView);
    }
    /**设置10个子菜单的图片和文字*/
    private void initGridMenuAttr(List<GridMenu> gridMenus) {
        for (int i = 0; i < gridMenus.size(); i++) {
            GridMenu gridMenu = gridMenus.get(i);
            gridMenu.setAttr(R.drawable.art_collection,gridMenuTitles[i]);
        }
    }
    private void initOnCLick(List<GridMenu> gridMenus) {
        for (GridMenu gridMenu : gridMenus) {
            gridMenu.setOnClickListener(this);
        }
    }
    @Optional
    @OnClick(R.id.buttomBtnScan)
    public void clickScan(View v) {
        UIUtils.showToast("扫一扫");
    }
    @Optional
    @OnClick(R.id.bomBtnMsg)
    public void clickMsg(View v) {
        UIUtils.showToast("消息");
    }
    @Optional
    @OnClick(R.id.ll_search)
    public void clickSearch(View v) {
        UIUtils.showToast("搜索");
    }
    @Optional
    @OnClick(R.id.iv_photo)
    public void clickPhoto(View v) {
        UIUtils.showToast("拍照");
    }
//    @Optional
//    @OnClick(R.id.bomBtnHome)
//    public void bomBtnHome(View v) {
//        UIUtils.showToast("首页");
//    }
//    @Optional
//    @OnClick(R.id.bomBtnTiny)
//    public void bomBtnTiny(View v) {
//        UIUtils.showToast("社区");
//    }
//    @Optional
//    @OnClick(R.id.bomBtnAsk)
//    public void bomBtnAsk(View v) {
//        UIUtils.showToast("问大家");
//    }
//    @Optional
//    @OnClick(R.id.bomBtnShopCar)
//    public void bomBtnShopCar(View v) {
//        UIUtils.showToast("购物车");
//    }
//    @Optional
//    @OnClick(R.id.bomBtnMy)
//    public void bomBtnMy(View v) {
//        UIUtils.showToast("个人");
//    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swp.setRefreshing(false);
            }
        }, 1500);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.gridCat://天猫
                UIUtils.showToast("项目一");
                break;
            case R.id.gridJHS://聚划算
                UIUtils.showToast("项目二");
                break;
            case R.id.gridTMgj://天猫国际
                UIUtils.showToast("项目三");
                break;
            case R.id.grid_wm://外卖
                UIUtils.showToast("项目四");
                break;
            case R.id.grid_tmcs://天猫超市
                UIUtils.showToast("项目五");
                break;
            case R.id.grid_czzx://充值中心
                UIUtils.showToast("项目六");
                break;
            case R.id.grid_fzlx://飞猪旅行
                UIUtils.showToast("项目七");
                break;
            case R.id.grid_ljb://领金币
                UIUtils.showToast("项目八");
                break;
            case R.id.grid_dj://到家
                UIUtils.showToast("项目九");
                break;
            case R.id.grid_fl://分类
                UIUtils.showToast("分类");
                break;
            case R.id.ll_headline:
                UIUtils.showToast("学习头条");
                break;
            case R.id.ll_qtg:
                UIUtils.showToast("抢购");
                break;
            case R.id.ll_yhh:
                UIUtils.showToast("有好货");
                break;
            case R.id.ll_agj:
                UIUtils.showToast("围观");
                break;
            case R.id.ll_bmqd:
                UIUtils.showToast("必买清单");
                break;
            case R.id.rl_hotMarket:
                UIUtils.showToast("热门");
                break;
            case R.id.goodsSectionIfashion:
            case R.id.goodsSectionCWC:
            case R.id.goodsSectionqbb:
            case R.id.goodsSectionZqtd:
                String msg="";
                if (v.getId()==R.id.goodsSectionIfashion){
                    msg="项目一";
                }else if(v.getId()==R.id.goodsSectionCWC){
                    msg="项目二";
                }else if(v.getId()==R.id.goodsSectionqbb){
                    msg="项目三";
                }else if(v.getId()==R.id.goodsSectionZqtd){
                    msg="项目四";
                }
                UIUtils.showToast(msg);
                break;
        }
    }

    @Override
    public void onItemClick(View v, int postion) {
        UIUtils.showToast("item click postion "+postion);
    }

    @Override
    public void onItemLongClick(View v, int postion) {
        UIUtils.showToast("item long click postion "+postion);

    }
}


