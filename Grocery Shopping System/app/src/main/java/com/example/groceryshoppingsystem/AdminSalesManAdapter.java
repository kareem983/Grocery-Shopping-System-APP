package com.example.groceryshoppingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

class AdminSalesManAdapter extends RecyclerView.Adapter<AdminSalesManAdapter.SalesManViewHolder> {

    private Context context;
    private List<AdminSalesMan> salesManList;
    private AdminOfferAdapter.onItemClickListener itemListener;
    private AdminOfferAdapter.onLongClickListener longListener;

    public interface onItemClickListener{
        void onItemClick(int pos);
    }
    public interface onLongClickListener{
        void onItemLongClick(int pos);
    }

    public void setOnItemClickListener(AdminOfferAdapter.onItemClickListener listener)
    {
        itemListener = listener;
    }

    public void setOnLongClickListener(AdminOfferAdapter.onLongClickListener listener)
    {
        longListener = listener;
    }

    public AdminSalesManAdapter(Context context, List<AdminSalesMan> salesManList) {
        this.context = context;
        this.salesManList = salesManList;
    }

    public void addList(List<AdminSalesMan> list)
    {
        salesManList.clear();
        Collections.copy(salesManList , list);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SalesManViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admin_salesman_list , parent , false);
        return new SalesManViewHolder(v , itemListener , longListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesManViewHolder holder, int position) {
        Picasso.get().load(salesManList.get(position).getImg()).centerCrop().fit().into(holder.img);
        holder.name.setText("Name: "+salesManList.get(position).getName());
        holder.salary.setText("Salary: "+salesManList.get(position).getSalary()+" EGP");
    }


    @Override
    public int getItemCount() {
        return salesManList.size();
    }

    public static class SalesManViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name , salary;
        public SalesManViewHolder(@NonNull View itemView, final AdminOfferAdapter.onItemClickListener itemlistener , final AdminOfferAdapter.onLongClickListener longClickListener) {
            super(itemView);

            img = itemView.findViewById(R.id.adapterSalesManImage);
            name = itemView.findViewById(R.id.AdapterSalesManName);
            salary = itemView.findViewById(R.id.AdapterSalesManSalary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemlistener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            itemlistener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(longClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                            longClickListener.onItemLongClick(position);
                    }
                    return false;
                }
            });
        }
    }
}
