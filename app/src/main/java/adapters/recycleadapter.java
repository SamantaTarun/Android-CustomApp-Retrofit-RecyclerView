package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usermanagement.DashBoardActivity;
import com.example.usermanagement.R;

import java.util.List;

import models.Pojo;

public class recycleadapter extends RecyclerView.Adapter<recycleadapter.MyViewHolder>{

    List<Pojo> list;
    public recycleadapter(List<Pojo>list) {
        this.list=list;
    }
    @NonNull
    @Override
    public recycleadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_layout,null);
        recycleadapter.MyViewHolder viewHolder=new recycleadapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycleadapter.MyViewHolder holder, int position) {

        holder.label.setText(list.get(position).getLabel());
        holder.Rate.setText(list.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView label,Rate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            label=itemView.findViewById(R.id.label);
            Rate=itemView.findViewById(R.id.rate);
            label.setTextIsSelectable(true);
            Rate.setTextIsSelectable(true);
        }
    }
}
