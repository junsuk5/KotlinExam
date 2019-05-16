package com.example.kotlinexam.item03java;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.kotlinexam.R;

import java.util.Arrays;
import java.util.List;

public class Item03JavaActivity extends AppCompatActivity {
    private List<String> items = Arrays.asList("어벤져스", "배트맨", "배트맨2", "배구", "슈퍼맨");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item03_java);

        SearchView searchView = findViewById(R.id.search_view);
        final TextView resultTextView = findViewById(R.id.textView);
        resultTextView.setText(getResult());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultTextView.setText(search(newText));
                return true;
            }
        });
    }

    private String search(String query) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            if (item.toLowerCase().contains(query.toLowerCase())) {
                sb.append(item);
                if (i != items.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String getResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            sb.append(item);
            if (i != items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
