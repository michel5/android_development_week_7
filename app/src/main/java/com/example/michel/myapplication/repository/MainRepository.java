package com.example.michel.myapplication.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.michel.myapplication.AppDatabase;
import com.example.michel.myapplication.DataAccesLaag;
import com.example.michel.myapplication.models.ModelCheckBox;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainRepository {
	private AppDatabase mAppDatabase;
	private DataAccesLaag mReminderDao;
	private LiveData<List<ModelCheckBox>> mReminders;
	private Executor mExecutor = Executors.newSingleThreadExecutor();


	public MainRepository(Context context) {
		mAppDatabase = AppDatabase.getInstance(context);
		mReminderDao = mAppDatabase.checkBoxDoa();
		mReminders = mReminderDao.getAllStorgeModel();
	}

	public LiveData<List<ModelCheckBox>> getALlCheckBox() {
		return mReminders;
	}


	public void insert(final ModelCheckBox reminder) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mReminderDao.insertReminders(reminder);
			}
		});
	}


	public void update(final ModelCheckBox reminder) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mReminderDao.updateReminders(reminder);
			}
		});
	}

	public void delete(final ModelCheckBox reminder) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mReminderDao.deleteReminders(reminder);
			}
		});
	}
}
