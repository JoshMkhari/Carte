package com.carte.navigator.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carte.navigator.R;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.models.Model_Image_Text;

import java.util.ArrayList;

public class Adapter_Destination_Options extends RecyclerView.Adapter<Adapter_Destination_Options.OptionViewHolder> { //(Professor Sluiter, 2020).

    private final Interface_RecyclerView _interfaceRecyclerView;//(Practical Coding, 2021)
    final LayoutInflater _inflater;
    final String[] _optionsList;

    public Adapter_Destination_Options(Interface_RecyclerView _interfaceRecyclerView, Context context, String[] optionsList )
    {
        this._interfaceRecyclerView = _interfaceRecyclerView;
        _inflater = LayoutInflater.from(context);
        _optionsList = optionsList;
    }


    @NonNull
    @Override
    public Adapter_Destination_Options.OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_destination_filter_options_layout,parent,false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Destination_Options.OptionViewHolder holder, int position) {

        //holder.optionImage = _optionsList.get(position).getOptionImage();
        holder.optionName.setText(_optionsList[position]);
        switch (position)
        {
            case 0:
                holder.optionImage.setBackgroundResource(R.drawable.image_restaurant_icon);
                //holder.optionName.setText(R.string.string_navigation_option_restaurant);
                break;
            case 1:
                holder.optionImage.setBackgroundResource(R.drawable.image_supermarket_icon);
                //holder.optionName.setText(R.string.string_navigation_option_supermarket);
                break;
            case 2:
                holder.optionImage.setBackgroundResource(R.drawable.image_attractions_icon);
               // holder.optionName.setText(R.string.string_navigation_option_attractions);
                break;
            case 3:
                holder.optionImage.setBackgroundResource(R.drawable.image_fast_food_icon);
                //holder.optionName.setText(R.string.string_navigation_option_fast_food);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        ImageView optionImage;
        TextView optionName;

        public OptionViewHolder(@NonNull View itemView)
        {
            super(itemView);
            optionImage = itemView.findViewById(R.id.image_option);
            optionName = itemView.findViewById(R.id.textview_option);

            itemView.setOnClickListener(v -> {
                if(_interfaceRecyclerView != null)
                {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        _interfaceRecyclerView.onItemClick(pos,0);//(Practical Coding, 2021)
                    }
                }
            });
        }
    }
}
