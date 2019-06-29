package com.example.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<Hero> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        setActionBarTitle("Mode List");
        showRecyclerList();
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvCategory.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }


    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvCategory.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallback(new GridHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showRecyclerCard(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvCategory.setAdapter(cardViewHeroAdapter);

    }

    private void showSelectedHero(Hero hero){
        Toast.makeText(this, "Kamu memilih "+ hero.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int selectedMode) {
        switch (selectedMode){
            case R.id.action_list:
                setActionBarTitle("Mode List");
                showRecyclerList();
                break;
            case R.id.action_grid:
                setActionBarTitle("Mode Grid");
                showRecyclerGrid();
                break;
            case R.id.card_view:
                setActionBarTitle("Mode CardView");
                showRecyclerCard();
                break;
        }
    }


}
