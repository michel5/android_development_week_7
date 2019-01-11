package com.example.michel.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.example.michel.myapplication.models.ModelCheckBox;

import java.util.List;

public class ReminderAdapter  extends RecyclerView.Adapter<ReminderAdapter.GridcellHolder>{
	private Context context;
	public List<ModelCheckBox> listGridcell;

	public ReminderAdapter(Context context, List<ModelCheckBox> listGridcell) {
		this.context = context;
		this.listGridcell = listGridcell;
	}

	@NonNull
	@Override
	public GridcellHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from
				(viewGroup.getContext()).inflate(R.layout.grid_cell, viewGroup, false);
		return new GridcellHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull GridcellHolder gridcellHolder, int i) {

		ModelCheckBox gridcell = listGridcell.get(i);
		gridcellHolder.checkBox.setText(gridcell.getText());
		gridcellHolder.checkBox.setChecked(gridcell.isActiefCheckbox());
	}

	@Override
	public int getItemCount() {
		return this.listGridcell.size();
	}

	public void swapList(List<ModelCheckBox> mNewListBlockChainAddressObject) {
		listGridcell = mNewListBlockChainAddressObject;
		if (listGridcell != null) {

			// Force the RecyclerView to refresh
			this.notifyDataSetChanged();
		}
	}


	public class GridcellHolder extends RecyclerView.ViewHolder {
		public CheckBox checkBox;
		public View view;

		/**
		 *
		 * @param itemView
		 */
		public GridcellHolder(View itemView) {
			super(itemView);
			checkBox = itemView.findViewById(R.id.checkBox_id);
			view = itemView;
		}


	}
}
