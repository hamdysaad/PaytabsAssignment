package com.hamdy.paytabsassignment.features.payment_result;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hamdy.paytabsassignment.R;
import com.hamdy.paytabsassignment.base.BaseActivity;
import com.hamdy.paytabsassignment.databinding.ActivityPaymentResultBinding;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import org.jetbrains.annotations.Nullable;

public class PaymentResultActivity extends BaseActivity<ActivityPaymentResultBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_payment_result;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

        if(getIntent() != null){
            showResultData();
        }else
            showErrorMsgDialog(getString(R.string.noDataFound));
    }

    private void showResultData() {

        Intent data = getIntent();
        String RESPONSE_CODE = data.getStringExtra(PaymentParams.RESPONSE_CODE);
        String RESULT_MESSAGE = data.getStringExtra(PaymentParams.RESULT_MESSAGE);
        String TRANSACTION_ID = data.getStringExtra(PaymentParams.TRANSACTION_ID);

        binding.setRESPONSECODE(RESPONSE_CODE);
        binding.setRESULTMESSAGE(RESULT_MESSAGE);
        binding.setTRANSACTIONID(TRANSACTION_ID);
    }


    public  static void openPaymentResultActivity(Context context, Intent data){
        data.setComponent(new ComponentName(context , PaymentResultActivity.class));
        context.startActivity(data);
    }
}