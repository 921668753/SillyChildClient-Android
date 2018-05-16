package com.yinglan.scc.homepage.moreclassification;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.yinglan.scc.R;
import com.yinglan.scc.adapter.homepage.moreclassification.ClassificationViewAdapter;
import com.yinglan.scc.adapter.homepage.moreclassification.MoreClassificationViewAdapter;
import com.yinglan.scc.entity.homepage.moreclassification.ClassificationBean;
import com.yinglan.scc.entity.homepage.moreclassification.MoreClassificationBean;
import com.yinglan.scc.homepage.goodslist.GoodsListActivity;

import java.util.List;

/**
 * 更多分类
 * Created by Admin on 2017/8/20.
 */

public class MoreClassificationActivity extends BaseActivity implements MoreClassificationContract.View, AdapterView.OnItemClickListener {

    /**
     * 分类
     */
    @BindView(id = R.id.lv_moreClassification)
    private ChildListView lv_moreClassification;

    /**
     * 分类详情
     */
    @BindView(id = R.id.gv_classification)
    private NoScrollGridView gv_classification;

    private MoreClassificationViewAdapter moreClassificationViewAdapter = null;
    private ClassificationViewAdapter classificationViewAdapter = null;
    private List<MoreClassificationBean.ResultBean.ListBean> moreClassificationList;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_moreclassification);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new MoreClassificationPresenter(this);
        moreClassificationViewAdapter = new MoreClassificationViewAdapter(this);
        classificationViewAdapter = new ClassificationViewAdapter(this);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.moreClassification), true, R.id.titlebar);
        lv_moreClassification.setAdapter(moreClassificationViewAdapter);
        lv_moreClassification.setOnItemClickListener(this);
        gv_classification.setAdapter(classificationViewAdapter);
        gv_classification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MoreClassificationContract.Presenter) mPresenter).getMoreClassification();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.lv_moreClassification) {
            selectClassification(i);
        } else if (adapterView.getId() == R.id.gv_classification) {
            Intent goodsListIntent = new Intent(aty, GoodsListActivity.class);
            goodsListIntent.putExtra("classification", "");
            showActivity(aty, goodsListIntent);
        }
    }

    @Override
    public void setPresenter(MoreClassificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MoreClassificationBean moreClassificationBean = (MoreClassificationBean) JsonUtil.getInstance().json2Obj(success, MoreClassificationBean.class);
            moreClassificationList = moreClassificationBean.getData().getList();
            if (moreClassificationList != null && moreClassificationList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            ClassificationBean classificationBean = (ClassificationBean) JsonUtil.getInstance().json2Obj(success, ClassificationBean.class);

            classificationViewAdapter.clear();
            //classificationViewAdapter.addNewData();
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < moreClassificationList.size(); i++) {
            if (position == moreClassificationList.get(i).getId() || position == i && position == 0) {
//                moreClassificationBean = moreClassificationList.get(i);
//                moreClassificationBean.setStatus(1);
                ((MoreClassificationContract.Presenter) mPresenter).getMoreClassification();
            } else {
              //  moreClassificationList.get(i).setStatus(0);
            }
        }
        moreClassificationViewAdapter.clear();
      //  moreClassificationViewAdapter.addNewData(lengthBeanlist);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


}
