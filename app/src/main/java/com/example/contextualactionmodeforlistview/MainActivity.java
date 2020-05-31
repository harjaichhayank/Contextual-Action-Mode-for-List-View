package com.example.contextualactionmodeforlistview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] movieLists;
    ArrayAdapter customAdapter; //2 customAdapters -- 1 from java class (did not use), 2nd this object of ArrayAdapter class
    List data_provider = new ArrayList<>();
    List selections = new ArrayList<>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        movieLists = getResources().getStringArray(R.array.Top_10_movies);

        //final CustomAdapter customAdapter = new CustomAdapter(this, movieLists);

        Collections.addAll(data_provider,movieLists);
        customAdapter = new ArrayAdapter<String>(this, R.layout.content_main,R.id.movieName,data_provider);
        listView.setAdapter(customAdapter);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.my_menu,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if(item.getItemId()==R.id.DELETE) {
                    for (Object Item : selections) {
                        String ITEM = Item.toString();
                        data_provider.remove(ITEM);
                    }
                    customAdapter.notifyDataSetChanged(); //ArrayAdapter's customAdapter is used here to notifyDataSetChanged
                    mode.finish();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count = 0;
                selections.clear();
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(checked){
                    selections.add(data_provider.get(position));
                    count++;
                    mode.setTitle(count + "Item Selected");
                }
                else{
                    selections.remove(data_provider.get(position));
                    count--;
                    mode.setTitle(count + "Item Selected");
                }
            }
        });
    }
}
