package com.lxy.shop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.common.http.Repository;

/**
 * Created by lxy on 2017/9/8.
 */

public class TestActivity extends AppCompatActivity {

    private static Repository mRepository;

    public static void goToPage(Context context,Repository repository){

        mRepository = repository;
        context.startActivity(new Intent(context,TestActivity.class));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void clickTest(View view) {
         Toast.makeText(view.getContext(), "" + mRepository, Toast.LENGTH_SHORT).show();
//        repository.getSkilList("Android", 10, 1, false)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Reply<Response<SkilBean>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Reply<Response<SkilBean>> responseReply) {
//                        Logger.d("testActivity========" + responseReply.getSource());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
