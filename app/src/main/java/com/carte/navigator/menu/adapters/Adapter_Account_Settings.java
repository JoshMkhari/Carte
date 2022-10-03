package com.carte.navigator.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carte.navigator.R;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;

public class Adapter_Account_Settings extends RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>{
    private final Interface_RecyclerView _interfaceRecyclerView;//(Practical Coding, 2021)
    final LayoutInflater _inflater;
    final String[] _optionsList;

    public Adapter_Account_Settings(Interface_RecyclerView _interfaceRecyclerView, Context context, String[] optionsList )
    {
        this._interfaceRecyclerView = _interfaceRecyclerView;
        _inflater = LayoutInflater.from(context);
        _optionsList = optionsList;
    }


    @NonNull
    @Override
    public Adapter_Account_Settings.OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_multi_option_layout,parent,false);
        return new Adapter_Account_Settings.OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Account_Settings.OptionViewHolder holder, int position) {
        holder.optionName.setText(_optionsList[position]);
        holder.subOptionName.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        ImageButton optionTickImage;
        TextView optionName;
        TextView subOptionName;

        public OptionViewHolder(@NonNull View itemView)
        {
            super(itemView);
            optionTickImage = itemView.findViewById(R.id.imageButton_option_layout_arrow_click);
            optionName = itemView.findViewById(R.id.textView_option_layout_title);
            subOptionName = itemView.findViewById(R.id.textView_option_layout_sub_title);

            itemView.setOnClickListener(v -> {
                if(_interfaceRecyclerView != null)
                {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        _interfaceRecyclerView.onItemClick(pos,2);//(Practical Coding, 2021)
                    }
                }
            });
        }
    }
}
