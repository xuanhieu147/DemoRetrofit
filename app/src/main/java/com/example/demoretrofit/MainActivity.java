package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view_result);
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonplaceholder = retrofit2.create(JsonApi.class);
        Call<List<Example>> call = jsonplaceholder.getExamples();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                List<Example> examples = response.body();
                for (Example example : examples) {
                    String content = "";
                    content += "ID: " + example.getId() + "\n";
                    content += "Title " + example.getTitle() + "\n";
                    content += "Text" + example.getBody() + "\n" + "\n";
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}