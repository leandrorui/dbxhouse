package com.example.leandro.DBXHouse;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class UsuarioHolder extends RecyclerView.ViewHolder {

    public TextView nomeUsuario;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public UsuarioHolder(View itemView) {
        super(itemView);
        nomeUsuario = (TextView) itemView.findViewById(R.id.nomeCliente);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);
    }
}