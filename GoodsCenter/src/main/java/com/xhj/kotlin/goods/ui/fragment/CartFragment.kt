package com.xhj.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ext.setVisible
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.data.protocol.CartGoods
import com.xhj.kotlin.goods.event.AddCartEvent
import com.xhj.kotlin.goods.event.CartAllCheckedEvent
import com.xhj.kotlin.goods.event.UpdateCartSizeEvent
import com.xhj.kotlin.goods.event.UpdateTotalPriceEvent
import com.xhj.kotlin.goods.injection.component.DaggerCartComponent
import com.xhj.kotlin.goods.injection.module.CartModule
import com.xhj.kotlin.goods.presenter.CartListPresenter
import com.xhj.kotlin.goods.presenter.view.CartListView
import com.xhj.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class CartFragment: BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter:CartGoodsAdapter
    private var mTotalPrice:Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /**
        初始化视图
     */
    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter

        //全选或取消全选
        mAllCheckedCb.onClick {
            for(item in mAdapter.dataList){
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        //购物车编辑功能点击事件
        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }
        //删除购物车商品点击事件
        mDeleteBtn.onClick {
            val cartIdList:MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                .mapTo(cartIdList){it.id}
            if(cartIdList.size == 0){
                toast("请选择需要删除的商品")
            }else{
                mPresenter.deleteCartList(cartIdList)
            }
            mPresenter.deleteCartList(cartIdList)
        }
        //提交购物车商品点击事件（也就是点击去结算按钮）
        mSettleAccountsBtn.onClick {
            val cartGoodsList:MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                .mapTo(cartGoodsList){it}
            if(cartGoodsList.size ==0 ){
                toast("请选中要结算的商品")
            }else{
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }
        }
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
            .subscribe {
                mAllCheckedCb.isChecked = it.isAllChecked
                updateTotalPrice()
            }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
            .subscribe{
                updateTotalPrice()
            }.registerInBus(this)
    }

    //购物车编辑切换实现
    private fun refreshEditStatus() {
        //是否是可编辑状态
        val isEditStatus = mHeaderBar.getRightView().text == getString(R.string.common_edit)
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mTotalPriceTv.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)
        //编辑状态headerbar右上角显示完成，非编辑状态显示编辑
        mHeaderBar.getRightView().text = if(isEditStatus)
            getString(R.string.common_complete) else getString(R.string.common_edit)
    }

    override fun injectComponent() {
        DaggerCartComponent.builder()
            .activityComponent(activityComponent)
            .cartModule(CartModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }
    /**
        获取购物车商品列表 回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if(result != null && result.size>0 ){
            mHeaderBar.getRightView().setVisible(true)
            mAdapter.setData(result)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        //更新存储于SharedPreferences中的购物车商品数量
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size?:0)
        //通知主界面也就是MainActivity中进行购物车角标更新
        Bus.send(UpdateCartSizeEvent())
        //加载购物车合计价格
        updateTotalPrice()
    }

    /**
    删除购物车商品 回调
     */
    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }
    /**
    提交购物车商品 回调
     */
    override fun onSubmitCartListResult(result: Int) {
        toast(result.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun updateTotalPrice(){
        mTotalPrice = mAdapter.dataList
            .filter { it.isSelected }
            .map { it.goodsCount * it.goodsPrice }
            .sum()
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2Y(mTotalPrice)}"
    }

    //设置headerbar最左侧返回键是否可见
    fun setBackVisible(isVisible:Boolean){
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}