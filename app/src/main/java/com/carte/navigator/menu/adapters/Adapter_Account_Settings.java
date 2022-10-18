package com.carte.navigator.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carte.navigator.MainActivity;
import com.carte.navigator.R;
import com.carte.navigator.menu.interfaces.Interface_RecyclerView;
import com.carte.navigator.menu.sub.settings.Fragment_Units;

public class Adapter_Account_Settings extends RecyclerView.Adapter<Adapter_Account_Settings.OptionViewHolder>{
    private final Interface_RecyclerView _interfaceRecyclerView;//(Practical Coding, 2021)
    final LayoutInflater _inflater;
    final String[] _optionsList;
    final int _source;
    final boolean _radioButton, _type;

    public Adapter_Account_Settings(Interface_RecyclerView _interfaceRecyclerView, Context context, String[] optionsList, int source, boolean radioButton, boolean type)
    {
        this._interfaceRecyclerView = _interfaceRecyclerView;
        _inflater = LayoutInflater.from(context);
        _optionsList = optionsList;
        _source = source;
        _radioButton = radioButton;
        _type =type;
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
        if(!_radioButton)
        {
            holder.optionSelectedState.setVisibility(View.GONE);
            //sometimes this should be gone
            //other times it should be data from user view model
            holder.subOptionName.setVisibility(View.GONE);

        }else
        {
            holder.optionTickImage.setVisibility(View.GONE);
            int active_position ;
            if(_type)
            {
                active_position = MainActivity._currentModelUser.getUnitOfMeasurement();
            }else
            {
                active_position = MainActivity._currentModelUser.getUserPreference();
            }
            if(position != active_position )
            {
                holder.optionSelectedState.setBackgroundResource(R.drawable.image_un_selected_radio_button);
            }
            switch (_optionsList[0])
            {
                case "Metric":
                    holder.subOptionName.setText(Fragment_Units._units_distance_sub[position]);
                    break;
                case "Celsius":
                    holder.subOptionName.setText(Fragment_Units._units_temperature_sub[position]);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return _optionsList.length;
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder{

        ImageView optionTickImage, optionSelectedState;
        TextView optionName;
        TextView subOptionName;

        public OptionViewHolder(@NonNull View itemView)
        {
            super(itemView);
            optionTickImage = itemView.findViewById(R.id.imageView_option_layout_arrow_click);
            optionName = itemView.findViewById(R.id.textView_option_layout_title);
            subOptionName = itemView.findViewById(R.id.textView_option_layout_sub_title);
            optionSelectedState = itemView.findViewById(R.id.imageView_selected_state);

            itemView.setOnClickListener(v -> {
                if(_interfaceRecyclerView != null)
                {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        _interfaceRecyclerView.onItemClick(pos,_source);//(Practical Coding, 2021)
                    }
                }
            });
        }
    }
}
