package com.sortscript.amdsystem;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sortscript.amdsystem.Adapters.MyRecommendedCoursesAdapter;
import com.sortscript.amdsystem.Interfaces.GetDataService;
import com.sortscript.amdsystem.Models.Recommendation;
import com.sortscript.amdsystem.Models.Root;
import com.sortscript.amdsystem.Models.SendData;
import com.sortscript.amdsystem.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatBot extends AppCompatActivity implements getItemPosition {
    private ArrayList<Message> messages;
    private RecyclerView recyclerView;
    RecyclerView apiview;
    private recyclerAdapter adapter;
    private EditText msgInput;
    private ImageButton sendButton;
    ArrayList<String> faqQuestions;
    private int pos = 0;
    String TAG = "Failurere";
    private String difficultyLevel;
    private String userId;
    Recommendation_Model recommendation_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);
        getSupportActionBar().hide();
        recommendation_model = new Recommendation_Model();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        faqQuestions = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        apiview = findViewById(R.id.apiview);
        // Set RecyclerView layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Set an animation
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Add elements to ArrayList
        faqQuestions.add(" Whats Your favourite country for study?");
        faqQuestions.add("");
        faqQuestions.add("What should be Quality of education? type number range 1 to 100");
        faqQuestions.add("");
        faqQuestions.add("University Research publications annualy? i.e range 1 to 500");
        faqQuestions.add("");
        faqQuestions.add("Chose Score of university between 1 to 100?");
        faqQuestions.add("");
        faqQuestions.add("What should be quality of Faculty chose number between 1 to 100?");
        faqQuestions.add("");
        faqQuestions.add("Number of patents for university range 1 to 200?");


        msgInput = findViewById(R.id.msgInput);
        sendButton = findViewById(R.id.msgButton);
        messages = new ArrayList<>();
        adapter = new recyclerAdapter(messages, faqQuestions, this::getPosition, this);
        recyclerView.setAdapter(adapter);

        //inital msgs

        if (pos == 0) {
            String message = "Whats Your favourite country for study?";
            if (message.length() != 0) {

                messages.add(new Message(true, message));
                int newPosition = messages.size() - 1;
                adapter.notifyItemInserted(newPosition);
                recyclerView.scrollToPosition(newPosition);
                msgInput.setText("");
//                firstQuestion();
            }
        }


        sendButton.setOnClickListener(v -> {
            String message = msgInput.getText().toString();
            if (message.length() != 0) {
                messages.add(new Message(true, message));
                int newPosition = messages.size() - 1;
                adapter.notifyItemInserted(newPosition);
                recyclerView.scrollToPosition(newPosition);


                if (pos == 0) {

                    difficultyLevel = msgInput.getText().toString();
                }

                final Handler handler = new Handler();
                handler.postDelayed(() -> {
                    if (pos <= 10) {
                        nextQuestion();
                    }
                }, 1000);

                if (pos == 10) {
                    callApi();
                }
            }
        });
    }

    private void nextQuestion() {

        String message = "What should be Quality of education? type number range 1 to 100";
        if (message.length() != 0) {
            messages.add(new Message(true, message));
            int newPosition = messages.size() - 1;
            adapter.notifyItemInserted(newPosition);
            recyclerView.scrollToPosition(newPosition);
            msgInput.setText("");

        }
    }


    private void callApi() {
        SendData sendData = new SendData(difficultyLevel, "80", 4.7, 80, 80, 40);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        service.sentData(sendData).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    ArrayList<Recommendation> name = root.recommendation;
                    ArrayList<Recommendation> newList = new ArrayList<>();
                    for (int j = 0; j < name.size(); j++) {
//                        if (root.recommendation.get(j).getCountry().equals(difficultyLevel)) {
                        newList.add(new Recommendation(root.recommendation.get(j).getCountry(), root.recommendation.get(j).getInstitution(),
                                root.recommendation.get(j).getPatents(), root.recommendation.get(j).getPublications(),
                                root.recommendation.get(j).getQuality_of_education(), root.recommendation.get(j).getQuality_of_faculty(),
                                root.recommendation.get(j).getScore(), root.recommendation.get(j).getWorld_rank()));
                        setAdapter(newList);
//                        }

                    }
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Universities").child("Recommendation");

                    for (int i = 0; i < newList.size(); i++) {
                        String Key = reference.push().getKey();
                        HashMap<String, String> map = new HashMap<>();
                        String country = newList.get(i).getCountry();
                        String institute = newList.get(i).getInstitution();
                        String patent = newList.get(i).getPatents();
                        String publications = newList.get(i).getPublications();
                        String quality = newList.get(i).getQuality_of_education();
                        String faculty = newList.get(i).getQuality_of_faculty();
                        String score = newList.get(i).getScore();
                        String rank = newList.get(i).getWorld_rank();
                        map.put("country", country);
                        map.put("institute", institute);
                        map.put("patent", patent);
                        map.put("publications", publications);
                        map.put("quality", quality);
                        map.put("faculty", faculty);
                        map.put("score", score);
                        map.put("Key", Key);
                        map.put("rank", rank);
                        reference.child(userId).child(Key).setValue(map);

                    }
                }


            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(ChatBot.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setAdapter(ArrayList<Recommendation> body) {
        //api recommendations

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        apiview.setLayoutManager(linearLayoutManager1);
        MyRecommendedCoursesAdapter myRecommendedCoursesAdapter = new MyRecommendedCoursesAdapter(getApplicationContext(), body,recommendation_model);
        apiview.setAdapter(myRecommendedCoursesAdapter);
    }


    @Override
    public void getPosition(int position) {
        pos = position;
        if (pos == 11) {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                LinearLayout chatEdt;
                chatEdt = findViewById(R.id.chatEdt);
                apiview.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                chatEdt.setVisibility(View.GONE);

            }, 1000);

        }

    }


    public void backshow(View view) {
        this.onBackPressed();
    }
}