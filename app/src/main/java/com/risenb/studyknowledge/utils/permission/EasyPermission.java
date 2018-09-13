/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risenb.studyknowledge.utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yonghao zeng on 2017/3/10.
 * 一个动态申请权限的封装类
 * 避免了在activity中冗余的回调和逻辑代码
 */
public class EasyPermission {

    public static final int SETTINGS_REQ_CODE = 16061;

    public interface PermissionCallback extends ActivityCompat.OnRequestPermissionsResultCallback {

        void onEasyPermissionGranted(int requestCode, int resultCode, String... perms);

        void onEasyPermissionDenied(int requestCode, int resultCode, String... perms);

    }

    private Object object;
    private String[] mPermissions;
    private String mRationale;
    private int mRequestCode;
    private int mResultCode;
    @StringRes private int mPositiveButtonText = android.R.string.ok;
    @StringRes private int mNegativeButtonText = android.R.string.cancel;


    private EasyPermission(Object object) {
        this.object = object;
    }

    public static EasyPermission with(Activity activity) {
        return new EasyPermission(activity);
    }

    public static EasyPermission with(Fragment fragment) {
        return new EasyPermission(fragment);
    }

    public EasyPermission permissions(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    public EasyPermission rationale(String rationale) {
        this.mRationale = rationale;
        return this;
    }

    public EasyPermission addRequestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    public EasyPermission addResultCode(int resultCode){
        this.mResultCode=resultCode;
        return this;
    }

    public EasyPermission positveButtonText(@StringRes int positiveButtonText) {
        this.mPositiveButtonText = positiveButtonText;
        return this;
    }

    public EasyPermission nagativeButtonText(@StringRes int negativeButtonText) {
        this.mNegativeButtonText = negativeButtonText;
        return this;
    }

    public void request() {
        requestPermissions(object, mRationale, mPositiveButtonText, mNegativeButtonText,
                mRequestCode,mResultCode, mPermissions);
    }

    /**
     * Check if the calling context has a set of permissions.
     */
    public static boolean hasPermissions(Context context, String... perms) {
        // Always return true for SDK < M, let the system deal with the permissions
        if (!Utils.isOverMarshmallow()) {
            return true;
        }

        if (perms == null || perms.length == 0) {
            return true;
        }

        for (String perm : perms) {
            boolean hasPerm = (ContextCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }

        return true;
    }


    public static void requestPermissions(final Object object, String rationale, final int requestCode,
            final int resultCode,final String... perms) {
        requestPermissions(object, rationale, android.R.string.ok, android.R.string.cancel,
                requestCode,resultCode, perms);
    }


    public static void requestPermissions(final Object object, String rationale, @StringRes int positiveButton,
            @StringRes int negativeButton, final int requestCode,final int resultCode, final
                                          String...
                                                  permissions) {

        checkCallingObjectSuitability(object);

        PermissionCallback mCallBack = (PermissionCallback) object;

        if (!Utils.isOverMarshmallow()) {
            mCallBack.onEasyPermissionGranted(requestCode,resultCode, permissions);
            return;
        }

        final List<String> deniedPermissions = Utils.findDeniedPermissions(Utils.getActivity(object), permissions);
        if (deniedPermissions == null) {
            return;
        }

        boolean shouldShowRationale = false;
        for (String perm : deniedPermissions) {
            shouldShowRationale = shouldShowRationale || Utils.shouldShowRequestPermissionRationale(object, perm);
        }

        if (Utils.isEmpty(deniedPermissions)) {
            mCallBack.onEasyPermissionGranted(requestCode,resultCode, permissions);
        } else {

            final String[] deniedPermissionArray = deniedPermissions.toArray(new String[deniedPermissions.size()]);

            if (false) {
                Activity activity = Utils.getActivity(object);
                if (null == activity) {
                    return;
                }

                new AlertDialog.Builder(activity).setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                executePermissionsRequest(object, deniedPermissionArray, requestCode);
                            }
                        })
                        .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                // act as if the permissions were denied
                                ((PermissionCallback) object).onEasyPermissionDenied(requestCode,
                                        resultCode, deniedPermissionArray);
                            }
                        })
                        .create()
                        .show();
            } else {
                executePermissionsRequest(object, deniedPermissionArray, requestCode);
            }
        }
    }

    @TargetApi(23) private static void executePermissionsRequest(Object object, String[] perms, int requestCode) {
        checkCallingObjectSuitability(object);

        if (object instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) object, perms, requestCode);
        } else if (object instanceof Fragment) {
            ((Fragment) object).requestPermissions(perms, requestCode);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).requestPermissions(perms, requestCode);
        }
    }


    public void onRequestPermissionsResult(Object object, int requestCode, String[] permissions,
                                           int[] grantResults) {
        checkCallingObjectSuitability(object);

        PermissionCallback mCallBack = (PermissionCallback) object;

        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (Utils.isEmpty(deniedPermissions)) {
            mCallBack.onEasyPermissionGranted(requestCode,mResultCode,permissions);
        } else {
            mCallBack.onEasyPermissionDenied(requestCode, mResultCode,
                    deniedPermissions.toArray(new
                    String[deniedPermissions.size()]));
        }
    }


    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object, String rationale,
            String... deniedPerms) {
        return checkDeniedPermissionsNeverAskAgain(object, rationale, android.R.string.ok, android.R.string.cancel,
                                                   null, deniedPerms);
    }

    /**
     * 在OnActivityResult中接收判断是否已经授权
     * 使用{@link EasyPermission#hasPermissions(Context, String...)}进行判断
     */
    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object, String rationale,
            @StringRes int positiveButton, @StringRes int negativeButton,
            @Nullable DialogInterface.OnClickListener negativeButtonOnClickListener, String... deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = Utils.shouldShowRequestPermissionRationale(object, perm);

            if (!shouldShowRationale) {
                final Activity activity = Utils.getActivity(object);
                if (null == activity) {
                    return true;
                }

                new AlertDialog.Builder(activity).setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                intent.setData(uri);

                                startAppSettingsScreen(object, intent);
                            }
                        })
                        .setNegativeButton(negativeButton, negativeButtonOnClickListener)
                        .create()
                        .show();

                return true;
            }
        }

        return false;
    }

    public int getResultCode() {
        return mResultCode;
    }

    @TargetApi(11) private static void startAppSettingsScreen(Object object, Intent intent) {
        if (object instanceof Activity) {
            ((Activity) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        } else if (object instanceof Fragment) {
            ((Fragment) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        }
    }

    private static void checkCallingObjectSuitability(Object object) {

        if (!((object instanceof Fragment)
                || (object instanceof Activity)
                || (object instanceof android.app.Fragment))) {
            throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
        }


        if (!(object instanceof PermissionCallback)) {
            throw new IllegalArgumentException("Caller must implement PermissionCallback.");
        }
    }

}
