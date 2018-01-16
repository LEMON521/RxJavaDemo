package cn.net.darking.rxjavademon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "AppCompatActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "-------" + "testObservableObserver" + "-------" + "开始");
        testObservableObserver();
        Log.e(TAG, "-------" + "testObservableObserver" + "-------" + "结束");
    }

    private void testObservableObserver() {
        //被观察者-水管上游
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();//Complete:完成,结束

            }
        });

        //观察者-水管下游-处理
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "next==" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "error" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };

        //建立连接
        observable.subscribe(observer);

    }


}
