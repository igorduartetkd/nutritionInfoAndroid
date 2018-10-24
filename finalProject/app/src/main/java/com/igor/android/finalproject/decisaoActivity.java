package com.igor.android.finalproject;

import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;



public class decisaoActivity extends AppCompatActivity {

    static GridView gridview;
    static Toolbar toolbar;
    static Arvore arvore  = new Arvore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisao);
    }

    @Override
    protected void onResume() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridview = (GridView) findViewById(R.id.gridView1);

        toolbar.setTitle(arvore.getApontando().getNome());
        toolbar.setTitleTextColor(Color.WHITE);
        gridview.setAdapter(new ButtonAdapter(this, arvore, toolbar));
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //para permitir a navegaçao de activitys

        toolbar.setNavigationIcon(R.drawable.ic_action_setinha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


        Intent myIntent = getIntent();
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(Intent.ACTION_SEARCH.equals(myIntent.getAction())){


            //pegando o texto digitado pelo usuario
            String query = myIntent.getStringExtra(SearchManager.QUERY);

            //reapontando a arvore:
            arvore.apontarPara(query);

            //remontando a tela em dois casos:
            if(arvore.getApontando().eFolha()){

                gridview.setAdapter(null);
                //chamar a activity final
                Intent intent = new Intent(decisaoActivity.this, final_activity.class);
                final_activity.arvore = arvore;
                final_activity.gridview = gridview;
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                decisaoActivity.this.startActivity(intent);


                finish();

            }else {
                finish();
                gridview.setAdapter(new ButtonAdapter(gridview.getContext(), arvore, toolbar));

            }


        }

        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //instancia os objetos do menu

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.pesquisar).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;

    }


    //botao de voltar do celular
    @Override
    public void onBackPressed() {
        if(arvore.eRaiz()){
            //caso estiver na primeira tela (raiz da arvore)
            super.onBackPressed();
        }else {
            //voltando para a etapa anterior:
            arvore.setApontando(arvore.getPai());
            onResume();
        }

    }
}
