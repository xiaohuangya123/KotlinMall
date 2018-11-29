package com.xhj.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.injection.component.DaggerCategoryComponent
import com.xhj.kotlin.goods.injection.module.CategoryrModule
import com.xhj.kotlin.goods.presenter.CategoryPresenter
import com.xhj.kotlin.goods.presenter.view.CategoryView
import com.xhj.kotlin.goods.ui.adapter.SecondCategoryAdapter
import com.xhj.kotlin.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class CategoryFragment: BaseMvpFragment<CategoryPresenter>(), CategoryView {

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {

        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                TODO()
            }
        })

    }

    /*
        加载数据
     */
    private fun loadData(parentId: Int = 0) {
//        if (parentId != 0) {
//            mMultiStateView.startLoading()
//        }

        mPresenter.getCategory(0)

    }

    override fun injectComponent() {

        DaggerCategoryComponent.builder()
            .activityComponent(activityComponent)
            .categoryrModule(CategoryrModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }
    /*
        获取商品分类 回调
     */
    override fun onGetCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if(result[0].parentId == 0){
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            }else{
                secondAdapter.setData(result)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        }
    }
}