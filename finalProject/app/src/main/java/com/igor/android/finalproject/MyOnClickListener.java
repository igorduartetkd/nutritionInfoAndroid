package com.igor.android.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;



/**
 * Created by igor on 13/04/16.
 */
public class MyOnClickListener implements View.OnClickListener {

    private int position = -1;
    Arvore arvore = null;
    Toolbar toolbar;

    public MyOnClickListener(int position, Arvore arvore, Toolbar toolbar){
        this.position = position;
        this.arvore = arvore;
        this.toolbar = toolbar;
    }




    public void onClick(View v)
    {
        // Preform a function based on the position
        //preciso limpar a grid, e chamar outro adapter para popula-la novamente


        //Resources res = Resources.getSystem();
        //novaLista = res.getStringArray(R.array.primeiro_nivel);
        arvore.setApontando(arvore.getApontando().getProximos().get(position));
        GridView gridView = (GridView) v.getParent();
        gridView.setAdapter(null);



        if(arvore.getApontando().eFolha()){
            //chamar a activity final
            Intent intent = new Intent(v.getContext(), final_activity.class);
            final_activity.arvore = arvore;
            final_activity.gridview = gridView;
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            v.getContext().startActivity(intent);



        }else {

            toolbar.setTitle(arvore.getApontando().getNome());
            toolbar.setTitleTextColor(Color.WHITE);
            gridView.setAdapter(new ButtonAdapter(gridView.getContext(), arvore, toolbar));
        }

    }
}

