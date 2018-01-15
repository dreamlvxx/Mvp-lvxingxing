/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sbkj.shunbaowallet.mvp_lvxingxing.utils;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author lvxingxing
 */
public class PermissionUtil {
    public static final String TAG = "PermissionUtil";

    private PermissionUtil() {
        throw new IllegalStateException("无法初始化");
    }

    public interface RequestPermission {
        /**
         * 权限请求成功的回调
         */
        void onRequestPermissionSuccess();

        /**
         * 用户拒绝了权限请求, 权限请求失败, 但还可以继续请求该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailure(List<String> permissions);

        /**
         * 用户拒绝了权限请求并且用户选择了以后不再询问, 权限请求失败, 这时将不能继续请求该权限, 需要提示用户进入设置页面打开该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions);
    }


    public static void requestPermission(final RequestPermission requestPermission, RxPermissions rxPermissions, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }

        List<String> needRequest = new ArrayList<>();
        //过滤调已经申请过的权限
        for (String permission : permissions) {
            if (!rxPermissions.isGranted(permission)) {
                needRequest.add(permission);
            }
        }

        //全部权限都已经申请过，直接执行操作
        if (needRequest.isEmpty()) {
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过,则开始申请
            rxPermissions
                    .requestEach(needRequest.toArray(new String[needRequest.size()]))
                    .buffer(permissions.length)
                    .subscribe(new Consumer<List<Permission>>() {
                        @Override
                        public void accept(List<Permission> permissions) throws Exception {
                            for (Permission p : permissions) {
                                if (!p.granted) {
                                    if (p.shouldShowRequestPermissionRationale) {
                                        requestPermission.onRequestPermissionFailure(Arrays.asList(p.name));
                                        return;
                                    } else {
                                        requestPermission.onRequestPermissionFailureWithAskNeverAgain(Arrays.asList(p.name));
                                        return;
                                    }
                                } else {
                                    requestPermission.onRequestPermissionSuccess();
                                }
                            }
                        }
                    });
        }
    }
}

