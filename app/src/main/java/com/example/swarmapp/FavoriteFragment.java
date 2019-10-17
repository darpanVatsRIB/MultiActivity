package com.example.swarmapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FavoriteFragment extends Fragment {

    private static final int REQUEST_PERMISSION = 100;

    private TextView textView;
    private  swarmApi mSwarmApi;
    private EditText mEditText;



    private Button mStartCamera;

    public static final String TAG = "YOUR-TAG-NAME";


    public FavoriteFragment() {

        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragmnt_fav, container,false);


        mStartCamera = view.findViewById(R.id.take_picture);
        textView = view.findViewById(R.id.text_response);
        mEditText = view.findViewById(R.id.editTextURL);



        //.... Gson converter factory...//

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://58d36a9a.ngrok.io")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        //.....Scalar converter Factory..//
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://58d36a9a.ngrok.io")
                .build();

        mSwarmApi = retrofit.create(swarmApi.class);



//                 getPosts();
//                 getComments();
//                 createPost();

        mStartCamera.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext() , "Enter URL", Toast.LENGTH_LONG).show();
                String mURL = mEditText.getText().toString();
                if( !mURL.isEmpty()){
                    getPlainPost(mURL);
                }


            }
        });

        return view;

    }


    private void getPlainPost( String finalURL) {



        Call<String> stringCall = mSwarmApi.getStringResponse(finalURL);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    textView.setText(responseString);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getPosts() {
        Call<List<Comment>> call = mSwarmApi
                .getComment("http://58d36a9a.ngrok.io/containers/877ae0c6-5edf-4b93-a35d-6d9def419cb8/content/d2c78f95-29da-4b59-b7b9-be707b0b7b1f/");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

//                for (Comment comment : comments) {
//                    String content = "";
//                    content += "ID: " + comment.getId() + "\n";
//                    content += "Name: " + comment.getName() + "\n";
////                    content += "Text: " + comment.getContent() + "\n\n";
//
//                    textView.append(content);
//                }
//
//                try {
//                    int val=Integer.parseInt(response.body());
//                }catch (NumberFormatException e){
//                    System.out.println("not a number");
//                }

                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
                t.printStackTrace ();
            }
        });
    }

    private void getComments() {

        Call<List<Comment>> call = mSwarmApi.getCommments();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()){

                    textView.setText("Code: "+ response.code());
                }
                List<Comment> comments = response.body();

                for (Comment comment : comments) {

                    String content = "";
                    content += "Id: " + comment.getId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "content: " + comment.getContent() + "\n\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });


    }

    private void createPost() {
//        Post post = new Post();
        Post post = new Post( "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("Name", "Name");
        fields.put("Description", "this is desc");

        Call<Post> call = mSwarmApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getName() + "\n";
                content += "Text: " + postResponse.getContent() + "\n\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }







}
