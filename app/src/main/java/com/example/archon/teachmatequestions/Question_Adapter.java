package com.example.archon.teachmatequestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by archon on 11-01-2015.
 */
public class Question_Adapter extends ArrayAdapter<Question_Model> {

    ArrayList<Question_Model> questionmodellist;
    int Resource;
    public Context ctxt;
    LayoutInflater vi;

    public Question_Adapter(Context context, int resource, ArrayList<Question_Model> objects) {
        super(context, resource, objects);
        questionmodellist=objects;
        Resource=resource;
        ctxt=context;
        vi=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= vi.inflate(Resource,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(R.id.imageView);
            //Picasso.with(ctxt).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwiGED7VkaP496dzprPvXdYQ3yx2odZ4sSdW6ih2JWPthQlhcFB9m64ECS"+).into(holder.imageView);

            holder.tvusername=(TextView)convertView.findViewById(R.id.tvusername);
            holder.tvquestion=(TextView)convertView.findViewById(R.id.tvquestion);
            holder.tvquestion_id=(TextView)convertView.findViewById(R.id.tvquestion_id);
            holder.tvaskedtime=(TextView)convertView.findViewById(R.id.tvaskedtime);


            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        Picasso.with(ctxt).load(questionmodellist.get(position).getImage()).into(holder.imageView);
        holder.imageView=(ImageView)convertView.findViewById(R.id.imageView);
        holder.tvusername.setText(questionmodellist.get(position).getUsername());
        holder.tvquestion.setText(questionmodellist.get(position).getQuestion());
        holder.tvquestion_id.setText(questionmodellist.get(position).getQuestion_id());
        holder.tvaskedtime.setText(questionmodellist.get(position).getAsked_time());


        return convertView;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvusername;
        public TextView tvquestion_id;
        public TextView tvaskedtime;
        public TextView tvquestion;


    }
}
