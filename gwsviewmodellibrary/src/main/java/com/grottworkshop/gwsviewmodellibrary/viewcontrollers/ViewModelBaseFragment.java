/*
 * Copyright (c) 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions andlimitations under the License.
 */
package com.grottworkshop.gwsviewmodellibrary.viewcontrollers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.grottworkshop.gwsviewmodellibrary.viewmodel.view.IView;
import com.grottworkshop.gwsviewmodellibrary.viewmodel.AbstractViewModel;
import com.grottworkshop.gwsviewmodellibrary.viewmodel.ViewModelHelper;

/**
 * ViewModelBaseFragment class, you will extend this class when using fragments to take full
 * advantage of the features of this ViewModel Framework.
 *
 * @author Fred Grott
 * Created by fgrott on 3/30/2015.
 */
@SuppressWarnings("unused")
public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment {

    private ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mViewModeHelper.onCreate(getActivity(), savedInstanceState, getViewModelClass());
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    public abstract Class<R> getViewModelClass();

    @Override
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModeHelper.initWithView((T) this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroyView() {
        mViewModeHelper.onDestroyView(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }
}
