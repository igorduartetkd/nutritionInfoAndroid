package com.igor.android.finalproject;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by igor on 13/04/16.
 */
public class ButtonAdapter extends BaseAdapter {

    private Context myContext;
    Arvore arvore = null;
    Toolbar toolbar;


    public ButtonAdapter(Context context, Arvore arvore, Toolbar toolbar){
        myContext = context;
        this.arvore = arvore;
        this.toolbar = toolbar;
    }

    @Override
    public int getCount() {
        return arvore.getApontando().getQntDeFilhos();
    }

    @Override
    public Object getItem(int position) {
        return arvore.getElemento(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button botao;
        final ArrayList<Elemento> nomes = arvore.getApontando().getProximos();

        if(convertView == null){
            botao = new Button(myContext);
            botao.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));
            botao.setPadding(8, 8, 8, 8);

            Resources res = Resources.getSystem();
            botao.setBackgroundColor(Color.LTGRAY);


            //botao.setBackgroundResource(R.drawable.teste);

        } else {
            botao = (Button) convertView;
        }



        botao.setText(nomes.get(position).getNome());
        botao.setTextColor(Color.BLACK);
        //botao.setTextAppearance(); //nao consegui colocar appearance large
        botao.setId(position);
        botao.setClickable(true);
        botao.setOnClickListener(new MyOnClickListener( position, arvore, toolbar));
        return botao;
    }
}
