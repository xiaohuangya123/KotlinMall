package com.xhj.kotlin.pay.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread


/**
 * Author: Created by XHJ on 2018/12/20.
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity: BaseMvpActivity<PayPresenter>(),PayView,View.OnClickListener {

    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int = 0

    @Autowired(name = ProviderConstant.KEY_ORDER_PRICE)
    @JvmField
    var mTotalPrice:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        //设置支付宝沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)

        ARouter.getInstance().inject(this)

        initView()
    }

    private fun initView() {
        mAlipayTypeTv.isSelected = true
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
        mPayBtn.onClick (this)
        mAlipayTypeTv.onClick(this)
        mWeixinTypeTv.onClick(this)
        mBankCardTypeTv.onClick(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mPayBtn ->{
                mPresenter.getPaySign(mOrderId, mTotalPrice)
            }
            R.id.mAlipayTypeTv ->{updatePayType(true,false,false)}
            R.id.mWeixinTypeTv ->{updatePayType(false, true, false)}
            R.id.mBankCardTypeTv ->{updatePayType(false,false,true)}
        }
    }

    private fun updatePayType(isAlipay: Boolean, isWeixinPay: Boolean, isBankCardPay:Boolean){
        mAlipayTypeTv.isSelected = isAlipay
        mWeixinTypeTv.isSelected = isWeixinPay
        mBankCardTypeTv.isSelected = isBankCardPay
    }

    override fun onGetPaySignResult(result: String) {
        doAsync {
            val resultMap:Map<String,String> = PayTask(this@CashRegisterActivity).payV2(result, true)
            uiThread {
                if(resultMap["resultStatus"].equals("9000")){
                    mPresenter.payOrder(mOrderId)
                }else{
                    toast("支付失败 ${resultMap["memo"]}")
                }
            }
        }
    }

    override fun onPayOrderResult(result: Boolean) {
        toast("支付成功")
        finish()
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