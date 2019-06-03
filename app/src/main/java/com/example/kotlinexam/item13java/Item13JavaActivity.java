package com.example.kotlinexam.item13java;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlinexam.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class Item13JavaActivity extends AppCompatActivity {
    // LiveData
    BehaviorSubject<Integer> counter = BehaviorSubject.create();

    int count = 0;
    private Disposable counterObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item13_java);

        findViewById(R.id.add_button).setOnClickListener(v -> {
            // 값 변경
            count++;
            counter.onNext(count);
        });

        TextView counterTextView = findViewById(R.id.counter_text);

        // 관찰 observe
        counterObservable = counter.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> counterTextView.setText(String.valueOf(integer)));
    }

    @Override
    protected void onDestroy() {
        counterObservable.dispose();
        super.onDestroy();
    }
}
