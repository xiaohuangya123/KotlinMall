package com.xhj.kotlin.mall.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.xhj.kotlin.base.common.AppManager
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.event.UpdateCartSizeEvent
import com.xhj.kotlin.goods.ui.fragment.CartFragment
import com.xhj.kotlin.goods.ui.fragment.CategoryFragment
import com.xhj.kotlin.mall.R
import com.xhj.kotlin.mall.ui.fragment.HomeFragment
import com.xhj.kotlin.mall.ui.fragment.MeFragment
import com.xhj.kotlin.message.ui.fragment.MessageFragment
import com.xhj.kotlin.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : AppCompatActivity() {

    private var pressTime : Long = 0

    private val mStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mHomeFragment)
        manager.add(R.id.mContaier, mCategoryFragment)
        manager.add(R.id.mContaier, mCartFragment)
        manager.add(R.id.mContaier, mMsgFragment)
        manager.add(R.id.mContaier, mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
        mBottomNavBar.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
            .subscribe{
                mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
            }.registerInBus(this)

        Bus.observe<MessageBadgeEvent>()
            .subscribe{
                mBottomNavBar.checkMsgBadge(it.isVisible)
            }.registerInBus(this)
    }

    private fun loadCartSize(){
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if(time - pressTime > 2000){
            toast("在按一次退出应用程序")
            pressTime = time
        }else{
            AppManager.instance.exitApp(this)
        }
    }
}
