package com.example.archon.teachmatequestions;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by archon on 11-01-2015.
 */
public class clicked extends Fragment {
    ListView l;
    Answer_Adapter answer_adapter;
    ArrayList<Answer_Model> answerlist;
    TextView tvusername,tvquestion,tvaskedtime,tvquestion_id;
    ImageView imageView;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentActivity fragmentActivity=(FragmentActivity)super.getActivity();
        RelativeLayout relativeLayout=(RelativeLayout)inflater.inflate(R.layout.clicked,container,false);
        tvusername=(TextView)relativeLayout.findViewById(R.id.tvusername);
        tvquestion=(TextView)relativeLayout.findViewById(R.id.tvquestion);
        tvaskedtime=(TextView)relativeLayout.findViewById(R.id.tvaskedtime);
        tvquestion_id=(TextView)relativeLayout.findViewById(R.id.tvquestion_id);
        Bundle bundle=getArguments();

        imageView=(ImageView)relativeLayout.findViewById(R.id.imageView);
        l = (ListView)relativeLayout. findViewById(R.id.listView2);
        answerlist = new ArrayList<>();
        //new answerfeed().execute("http://10.163.179.199:8222/MvcApplication1/Enigma/PostComment ");

        String username=bundle.getString("username");
        String question=bundle.getString("question");
        String asked_time=bundle.getString("asked_time");
        String qid1=bundle.getString("qid");
        String u=bundle.getString("image");
        Picasso.with(this.getActivity()).load(u).into(imageView);

        /*String username=getActivity().getIntent().getExtras().getString("username");
        String question=getActivity().getIntent().getExtras().getString("question");
        String asked_time=getActivity().getIntent().getExtras().getString("asked_time");
        String qid1=getActivity().getIntent().getExtras().getString("qid");
        String u=getActivity().getIntent().getExtras().getString("image");
        Picasso.with(this.getActivity()).load(u).into(imageView);*/


        tvusername.setText(username);
        tvquestion.setText(question);
        tvquestion_id.setText(qid1);
        tvaskedtime.setText(asked_time);
      //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.clicked,container,false);
    }

    /* @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.clicked);
         tvusername=(TextView)findViewById(R.id.tvusername);
         tvquestion=(TextView)findViewById(R.id.tvquestion);
         tvaskedtime=(TextView)findViewById(R.id.tvaskedtime);
         tvquestion_id=(TextView)findViewById(R.id.tvquestion_id);

         imageView=(ImageView)findViewById(R.id.imageView);
         l = (ListView) findViewById(R.id.listView2);
         answerlist = new ArrayList<>();
         new answerfeed().execute("http://10.163.179.199:8222/MvcApplication1/Enigma/PostComment ");

         String username=getIntent().getExtras().getString("username");
         String question=getIntent().getExtras().getString("question");
         String asked_time=getIntent().getExtras().getString("asked_time");
         String qid1=getIntent().getExtras().getString("qid");
         String u=getIntent().getExtras().getString("image");
         Picasso.with(this).load(u).into(imageView);


         tvusername.setText(username);
         tvquestion.setText(question);
         tvquestion_id.setText(qid1);
         tvaskedtime.setText(asked_time);

     }*/
    public void answer_this_question(View v){
        /*Intent x=new Intent(this.getActivity(),answer_question.class);
        x.putExtra("question_id",tvquestion_id.getText().toString());
        startActivity(x);*/

        Bundle data=new Bundle();
        data.putString("question_id", tvquestion_id.getText().toString());
        Fragment sendanswer=new answer_question();
        sendanswer.setArguments(data);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.layout.clicked,sendanswer);
        transaction.commit();

    }


  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){

            case R.id.new_question:
                Intent i=new Intent(this.getActivity(),Ask_question.class);
                startActivity(i);
                break;

            case R.id.refresh:
                answerfeed ref=new answerfeed();
                answerlist.clear();
                //       ref.execute("http://10.163.179.199:8222/MvcApplication1/Enigma/ListQuestions");
                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }*/


    public class answerfeed extends AsyncTask<String, Void, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpResponse response = client.execute(httpGet);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jobj = new JSONObject(data);
                    for (int i = 0; i < jobj.length(); i++) {

                        Answer_Model answer = new Answer_Model();

                        answer.setActualanswer(jobj.getString("object_or_array"));
                        answer.setAnsweredby("The actual question to be displayed");//jobj.getString("validate")
                        answer.setAnsweredtime(jobj.getString("parse_time_nanoseconds"));

                        answerlist.add(answer);


                    }
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


            if (aBoolean == false) {
                Toast.makeText(getActivity().getApplicationContext(), "data not found", Toast.LENGTH_LONG).show();
            } else {
                Answer_Adapter answer_adapter1 = new Answer_Adapter(getActivity().getApplicationContext(), R.layout.answer_single, answerlist);
                l.setAdapter(answer_adapter1);
                // question_adapter.notifyDataSetChanged();
            }
        }

    }
}
