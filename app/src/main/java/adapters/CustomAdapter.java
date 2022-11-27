package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usermanagement.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import models.Users;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Users> userList;
    private ArrayList<Users> filteredUserList;
    private Context context;

    public CustomAdapter(Context context,ArrayList<Users> userArrayList) {
        this.context = context;
        this.userList = userArrayList;
        this.filteredUserList = userArrayList;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_layout, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder viewHolder, int position) {

        viewHolder.name.setText(filteredUserList.get(position).getName());
        Picasso.get()
                .load(filteredUserList.get(position).getAvatar())
                .resize(150, 150)
                .centerCrop()
                .into(viewHolder.avatar);

    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredUserList = userList;

                } else {

                    ArrayList<Users> tempFilteredList = new ArrayList<>();

                    for (Users user : userList) {
                        if (user.getName().toLowerCase().startsWith(searchString.toLowerCase().trim())) {
                            tempFilteredList.add(user);
                        }
                    }

                    filteredUserList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUserList = (ArrayList<Users>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        ImageView avatar;
        public MyViewHolder(View view) {
            super(view);
            name=itemView.findViewById(R.id.t2);
            avatar=itemView.findViewById(R.id.t1);
            name.setTextIsSelectable(true);
        }
    }
}
