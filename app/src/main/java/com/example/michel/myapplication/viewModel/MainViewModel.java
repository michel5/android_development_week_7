package com.example.michel.myapplication.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.example.michel.myapplication.models.ModelCheckBox;
import com.example.michel.myapplication.repository.MainRepository;
import java.util.List;

public class MainViewModel extends ViewModel {
	//rrivate LiveData<List<ModelCheckBox>> livaDataGridcell;
	private MainRepository mRepository;
	private LiveData<List<ModelCheckBox>> mReminders;

	public MainViewModel(Context context) {
		mRepository = new MainRepository(context);
		mReminders = mRepository.getALlCheckBox();
	}

	public LiveData<List<ModelCheckBox>> getALlCheckBox() {
		return mReminders;
	}

	public void insert(ModelCheckBox reminder) {
		mRepository.insert(reminder);
	}

	public void update(ModelCheckBox reminder) {
		mRepository.update(reminder);
	}

	public void delete(ModelCheckBox reminder) {
		mRepository.delete(reminder);
	}


}
