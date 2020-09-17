package com.android.basecore.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public  class _BasePresenter<V> {
    public V view;

    public _BasePresenter() {
    }
    public void setView(V view){
        if(view==null){
            return;
        }
        this.view=view;
    }
    public V getView() {
        return view;
    }
    public void onCreate(Bundle savedInstanceState) {
    }
    public void onResume(boolean isFirstInto) {
    }
    public void onPause() {
    }
    public void onStart() {
    }
    public void onRestartForAct() {
    }
    public void onStopForAct(boolean isFirstHidden) {
    }
    public void onDestroy() {
    }
    public void onDetachedFromWindowForAct() {
    }
    public void onAttachedToWindowForAct() {
    }
    public void onConfigurationChanged(Configuration newConfig) {
    }
    public void onSaveInstanceState(Bundle outState) {
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }
    public void onBackPressedForAct() {
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }
    public void onNewIntentForAct(Intent intent) {
    }
    /////////////////////////////////////////
    public void onResumeForFragment() {
    }
    public void onStopForFragment() {
    }
    public void onViewStateShowForFragment(boolean isFirstShow) {
    }
    public void onViewStateHiddenForFragment(boolean isFirstHidden) {
    }
    public void onViewStateRestoredForFragment(@Nullable Bundle savedInstanceState) {
    }
    public void onDestroyViewForFragment() {
    }
    public void onDetachForFragment() {
    }
    public void onAttachForFragment(Context context) {
    }

}
