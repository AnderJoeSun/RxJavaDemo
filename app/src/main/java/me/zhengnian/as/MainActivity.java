package me.zhengnian.as;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends Activity {

    public static final String TAG=MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 定义生产者（ObservableOnSubscribe中的subscribe方法）
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 调用ObservableEmitter类对象的onNext方法生成消息（产生事件）并放入内置队列中
                Log.i(TAG, "ObservableOnSubscribe subscribe");
                Log.i(TAG, "ObservableOnSubscribe subscribe onNext 1");
                emitter.onNext(1);
                Log.i(TAG, "ObservableOnSubscribe subscribe onNext 2");
                emitter.onNext(2);
                Log.i(TAG, "ObservableOnSubscribe subscribe onNext 3");
                emitter.onNext(3);
                Log.i(TAG, "ObservableOnSubscribe subscribe onComplete");
                // 调用ObservableEmitter类对象的onComplete告诉RxJava框架消息或事件生产完毕（并通知观察者）
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 响应被观察者的注册操作（后面的“observable.subscribe(observer);”）
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe 观察者知道已与某个被观察者勾搭上了，将会有活要干");
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "消费来自被观察者的消息或响应来自被观察者的事件：" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError 出错了");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "响应前面的emitter.onComplete()");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}