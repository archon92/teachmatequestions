package com.example.archon.teachmatequestions;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by archon on 11-01-2015.
 */
public class mainlistview extends Fragment {

    public ListView list;
    public String imageurl;
    EditText etsearch;
    ImageView imageView;
    Question_Adapter question_adapter;
    public ArrayList<Question_Model> questionlist;
    Boolean flag = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity) super.getActivity();

        RelativeLayout relativeLayout=(RelativeLayout)inflater.inflate(R.layout.mainlistview,container,false);

        imageView = (ImageView)relativeLayout.findViewById(R.id.imageView);
        list = (ListView)relativeLayout.findViewById(R.id.listView);
        etsearch = (EditText)relativeLayout.findViewById(R.id.etsearch);

        //etsearch.setVisibility(View.GONE);
        questionlist = new ArrayList<Question_Model>();
        list.invalidateViews();
        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textlength = s.length();
                ArrayList<Question_Model> tempArrayList = new ArrayList<Question_Model>();
                for(Question_Model c: questionlist){
                    if (textlength <= (c.getQuestion().length())) {
                        if((c.getQuestion().toLowerCase().contains(s.toString().toLowerCase()))||(c.getUsername().toLowerCase().contains(s.toString().toLowerCase()))) {
                            tempArrayList.add(c);
                        }
                    }
                }
                Question_Adapter mAdapter = new Question_Adapter(getActivity().getApplicationContext(), R.layout.singlerow, tempArrayList);
                list.setAdapter(mAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//seed values
        Question_Adapter question_adapter1=new Question_Adapter(getActivity().getApplicationContext(),R.layout.singlerow,questionlist);

        Question_Model question=new Question_Model();
        question.setQuestion_id("question_id");//this would be the question id

        question.setUsername("asked_by");//this is the username of the person who asked the question
        question.setQuestion("question");//jobj.getString("validate")//this would be the question itself

        question.setAsked_time("time");//this is the time the question was asked

        questionlist.add(0,question);

       list.setAdapter(question_adapter1);
        list.setTextFilterEnabled(true);


        //  new questionfeed().execute("http://10.163.179.199:8222/MvcApplication1/Enigma/ListQuestions");



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* TextView username_s = (TextView) view.findViewById(R.id.tvusername);
              //  TextView user_id1 = (TextView) view.findViewById(R.id.tvquestion_id);
                TextView question_s=(TextView)view.findViewById(R.id.tvquestion);
                TextView asked_time=(TextView)view.findViewById(R.id.tvaskedtime);
                TextView q=(TextView)view.findViewById(R.id.tvquestion_id);
                ImageView imageView=(ImageView)view.findViewById(R.id.imageView);


                String user = username_s.getText().toString();
                String qid=q.getText().toString();
                String url=questionlist.get(position).getImage();
                String quest=question_s.getText().toString();
               // String userid = user_id1.getText().toString();
                String asked=asked_time.getText().toString();*/
                String user=questionlist.get(position).getUsername();
                String qid=questionlist.get(position).getQuestion_id();
                String url=questionlist.get(position).getImage();
                String quest=questionlist.get(position).getQuestion();
                String asked=questionlist.get(position).getAsked_time();
                String askedagain=questionlist.get(position).getAsked_time();



                Intent i = new Intent(getActivity().getApplicationContext(), clicked_activity.class);
                i.putExtra("username",user);
                i.putExtra("question",quest);
                i.putExtra("asked_time",asked);
                i.putExtra("question_id",qid);
                i.putExtra("image",url);
                startActivity(i);


                /*Bundle data=new Bundle();
                data.putString("username", user);
                data.putString("question", quest);
                data.putString("asked_time", asked);
                data.putString("question_id", qid);
                data.putString("image", url);
                Fragment clickedfragment=new clicked();



                clickedfragment.setArguments(data);
                FragmentTransaction transaction =mainlistview.this.getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.mainlistview, clickedfragment);
                transaction.addToBackStack(null);


                transaction.commit();*/






            }
        });
     //  return super.onCreateView(inflater, container, savedInstanceState);
        //return inflater.inflate(R.layout.mainlistview,container,false);
        return relativeLayout;
    }



   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.new_question:
               *//* Intent i = new Intent(this.getActivity(), Ask_question.class);
                startActivity(i);*//*
                break;
            case R.id.refresh:

                questionfeed ref = new questionfeed();
                questionlist.clear();

                ref.execute("http://10.163.179.199:8222/MvcApplication1/Enigma/ListQuestions");
                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                break;
            case R.id.search_button:
                if(flag){
                    etsearch.setVisibility(View.VISIBLE);
                    flag=false;}
                else if (!flag){
                    etsearch.setVisibility(View.GONE);
                    etsearch.setText("");
                    flag=true;
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }*/



    public class questionfeed extends AsyncTask<String, Void, Boolean> {
        // ImageView imageView = (ImageView)relativeLayout.findViewById(R.id.imageView);
        ProgressDialog dialog = new ProgressDialog(getActivity().getApplicationContext());

        @Override
        protected void onPreExecute() {
            //
            super.onPreExecute();

            dialog.setProgressStyle(2);
            dialog.setMessage("loading");
            dialog.show();

        }

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
                    JSONArray jarray = jobj.getJSONArray("questions");
                    for (int i = 0; i < jarray.length(); i++) {
                        Question_Model question = new Question_Model();
                        JSONObject real = jarray.getJSONObject(i);

                        question.setQuestion_id(real.getString("question_id"));//this would be the question id
                        // question.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwiGED7VkaP496dzprPvXdYQ3yx2odZ4sSdW6ih2JWPthQlhcFB9m64ECS");
                        question.setImage("http://10.163.180.110:420/TeachMate.Web/MyImages/profile.jpg");
                        question.setUsername(real.getString("asked_by"));//this is the username of the person who asked the question
                        question.setQuestion(real.getString("question"));//jobj.getString("validate")//this would be the question itself
                        question.setQuestion_id(real.getString("question"));

                        question.setAsked_time(real.getString("time"));//this is the time the question was asked

                        questionlist.add(0, question);

                    }

                    return true;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dialog.dismiss();

            if (aBoolean == false) {
                Toast.makeText(getActivity().getApplicationContext(), "data not found", Toast.LENGTH_LONG).show();

            } else {
                final   Question_Adapter question_adapter = new Question_Adapter(getActivity().getApplicationContext(), R.layout.singlerow, questionlist);

                Parcelable state = list.onSaveInstanceState();

                //list.setAdapter(question_adapter);
                list.setTextFilterEnabled(true);
                Toast.makeText(getActivity().getApplicationContext(), "" + list.getCount(), Toast.LENGTH_LONG).show();
                list.onRestoreInstanceState(state);

                //question_adapter.notifyDataSetChanged();
            }
        }
    }
}
