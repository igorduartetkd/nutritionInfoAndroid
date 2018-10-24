package com.igor.android.finalproject;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.TextView;

public class final_activity extends AppCompatActivity {

    static Arvore arvore;
    static GridView gridview;
    Toolbar toolbar;
    WebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //nao sei a diferença desse pros dois de cima


        String listaDescricoes[] = getResources().getStringArray(R.array.descricoesIndexadas);




        toolbar.setNavigationIcon(R.drawable.ic_action_setinha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        TextView textView = (TextView)findViewById(R.id.textViewDescricao);
        textView.setText(listaDescricoes[arvore.getApontando().getIndexDescricao()]);


        Intent myIntent = getIntent();
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Intent.ACTION_SEARCH.equals(myIntent.getAction())){

            //pegando o texto digitado pelo usuario
            String query = myIntent.getStringExtra(SearchManager.QUERY);

            //reapontando a arvore:
            arvore.apontarPara(query);

            //remontando a tela em dois casos:
            if(arvore.getApontando().eFolha()){

                toolbar.setTitle(arvore.getApontando().getNome());
                textView.setText(listaDescricoes[arvore.getApontando().getIndexDescricao()]);
                finish();
            }else{
                //arvore.setApontando(arvore.getPai());]
                //getFragmentManager().popBackStackImmediate();
                final_activity.this.finish();
            }
            //myIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP);

        }
    }

    @Override
    protected void onResume() {
        String listaDescricoes[] = getResources().getStringArray(R.array.descricoesIndexadas);


        toolbar.setTitle(arvore.getApontando().getNome());
        toolbar.setTitleTextColor(Color.WHITE);
        TextView textView = (TextView)findViewById(R.id.textViewDescricao);
        textView.setText(listaDescricoes[arvore.getApontando().getIndexDescricao()]);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText(arvore.getApontando().getNome());

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.pesquisar).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        arvore.setApontando(arvore.getPai());
        finish();
        super.onBackPressed();

    }

}
