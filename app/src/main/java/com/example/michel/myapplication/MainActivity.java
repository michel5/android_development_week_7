package com.example.michel.myapplication;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.michel.myapplication.models.ModelCheckBox;
import com.example.michel.myapplication.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private List<ModelCheckBox> listCheckBox = new ArrayList<>();
    private MainViewModel mMainViewModel;
    private ReminderAdapter mAdapter;
    private RecyclerView mRecyclerView;

	//Constants used when calling the update activity
	public static final String EXTRA_CHECK_BOX = "CheckBox";
	public static final int REQUESTCODE = 1234;
	private int mModifyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		System.out.println("test"+listCheckBox.size());

		mRecyclerView = findViewById(R.id.recycler_id);

		//create viewmodel
//		mMainViewModel = ViewModelProviders.of(this).get(MainVieuwModel.class);
		mMainViewModel = new MainViewModel(getApplicationContext());
		mMainViewModel.getALlCheckBox().observe(this, new Observer<List<ModelCheckBox>>() {
			@Override
			public void onChanged(@Nullable List<ModelCheckBox> reminders) {
				listCheckBox = reminders;
				updateUI();
			}
		});

		ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
			@Override
			public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
				return false;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
				System.out.println(viewHolder.getAdapterPosition());
				ModelCheckBox modelCheckBox = listCheckBox.get(viewHolder.getAdapterPosition());
				if(modelCheckBox.isActiefCheckbox()){
					System.out.println(modelCheckBox.isActiefCheckbox());
					modelCheckBox.setActiefCheckbox(false);
					System.out.println(modelCheckBox.isActiefCheckbox());
				} else {
					modelCheckBox.setActiefCheckbox(true);
				}

				mMainViewModel.update(modelCheckBox);

				//update ui
				updateUI();
			}
		};

		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
		itemTouchHelper.attachToRecyclerView(mRecyclerView);

		//floating button
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				addAction(MainActivity.this);
			}
		});


		System.out.println(listCheckBox.size());

		//set layout manager
		RecyclerView.LayoutManager mLayoutManager =
				new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mAdapter = new ReminderAdapter(this, listCheckBox);
		mRecyclerView.setAdapter(mAdapter);
	}

	private void addAction(Context context) {
		final EditText mAddAddress = new EditText(context);
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle("Voeg text toe")
				.setView(mAddAddress)
				.setPositiveButton("add text", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						mMainViewModel.insert(new ModelCheckBox(mAddAddress.getText().toString(),
								false));
					}
				})
				.setNegativeButton("Cancel", null)
				.create();
		dialog.show();

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	private void updateUI() {
		if (mAdapter == null) {
			mAdapter = new ReminderAdapter(this,listCheckBox);
			mRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.swapList(listCheckBox);
		}
	}
}