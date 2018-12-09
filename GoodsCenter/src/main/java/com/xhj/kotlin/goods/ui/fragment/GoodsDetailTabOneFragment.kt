package com.xhj.kotlin.goods.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.base.ui.fragment.BaseFragment
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.base.widgets.BannerImageLoader
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.event.AddCartEvent
import com.xhj.kotlin.goods.event.GoodsDetailImageEvent
import com.xhj.kotlin.goods.event.SkuChangedEvent
import com.xhj.kotlin.goods.event.UpdateCartSizeEvent
import com.xhj.kotlin.goods.injection.component.DaggerGoodsComponent
import com.xhj.kotlin.goods.injection.module.GoodsModule
import com.xhj.kotlin.goods.presenter.GoodsDetailPresenter
import com.xhj.kotlin.goods.presenter.view.GoodsDetailView
import com.xhj.kotlin.goods.ui.activity.GoodsDetailActivity
import com.xhj.kotlin.goods.widgets.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.toast

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class GoodsDetailTabOneFragment: BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation
    //当前详情页所展示的商品
    private var mCurGoods:Goods? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    /**
        初始化视图
     */
    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                0, 0
            )

            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    /**
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity as Activity)
        mSkuPop.setOnDismissListener{
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    /**
        加载数据
     */
    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /**
        Dagger注册
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder()
            .activityComponent(activityComponent)
            .goodsModule(GoodsModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    /**
        获取商品详情 回调
     */
    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    /**
     * 加入购物车 回调
     */
    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

    /**
        加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)
    }

    /**
      初始化缩放动画
   */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
            1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
            0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /**
        监听SKU变化及加入购物车事件
     */
    private fun initObserve(){
        Bus.observe<SkuChangedEvent>()
            .subscribe {
                mSkuSelectedTv.text = mSkuPop.getSelectSku() +GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount()+"件"
            }.registerInBus(this)

        Bus.observe<AddCartEvent>()
            .subscribe{
                addCart()
            }.registerInBus(this)
    }

    /**
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /**
        加入购物车
     */
    private fun addCart(){
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                it.goodsDesc,
                it.goodsDefaultIcon,
                it.goodsDefaultPrice,
                mSkuPop.getSelectCount(),
                mSkuPop.getSelectSku()
            )
        }

    }

}