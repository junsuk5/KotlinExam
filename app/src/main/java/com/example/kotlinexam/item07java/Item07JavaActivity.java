package com.example.kotlinexam.item07java;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kotlinexam.R;

import java.util.Arrays;
import java.util.List;

public class Item07JavaActivity extends AppCompatActivity {

    List<String> items = Arrays.asList("button1", "button2", "button3", "button4");

    int removedIndex = -1;
    View removedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item07_java);

        LinearLayout linearLayout = findViewById(R.id.root_layout);

        for (int i = 0; i < items.size(); i++) {
            final int index = i;
            Button button = new Button(this);
            button.setText(items.get(i));
            button.setOnClickListener(view -> {
                linearLayout.removeView(view);
                removedItem = view;
                removedIndex = index;
                Snackbar.make(linearLayout, "삭제되었음", 1000)
                        .setAction("Undo",
                                v -> linearLayout
                                        .addView(removedItem, removedIndex)
                        )
                        .show();
            });
            linearLayout.addView(button);
        }

//        Stream.of(items)
//                .map(s -> {
//                    Button button = new Button(this);
//                    button.setText(s);
//                    button.setOnClickListener(view -> {
//                        linearLayout.removeView(view);
//                        removedItem = view;
//                        Snackbar.make(linearLayout, "삭제되었음", 1000)
//                                .setAction("Undo",
//                                        v -> linearLayout.addView(removedItem)
//                                )
//                                .show();
//                    });
//                    return button;
//                }).forEach(linearLayout::addView);


    }
}
