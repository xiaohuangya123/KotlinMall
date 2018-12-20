package com.xhj.kotlin.pay.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.pay.R
import com.xhj.kotlin.pay.injection.component.DaggerPayComponent
import com.xhj.kotlin.pay.injection.module.PayModule
import com.xhj.kotlin.pay.presenter.PayPresenter
import com.xhj.kotlin.pay.presenter.view.PayView
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.toast


/**
 * Author: Created by XHJ on 2018/12/20.
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity: BaseMvpActivity<PayPresenter>(),PayView {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int = 0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        ARouter.getInstance().inject(this)

        initView()
    }

    private fun initView() {
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
        mPayBtn.onClick {
            mPresenter.getPaySign(mOrderId, mTotalPrice)
        }
    }

    override fun onGetPaySignResult(result: String) {
        toast(result)
    }

    override fun injectComponent() {
        DaggerPayComponent.builder()
            .activityComponent(activityComponent)
            .payModule(PayModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }



}