package com.example.leandro.DBXHouse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.leandro.DBXHouse.model.Usuario;

import java.util.List;


public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {

    private final List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsuarioHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(UsuarioHolder holder, int position) {
        holder.nomeUsuario.setText(usuarios.get(position).getNomeUsuario());
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
        notifyItemInserted(getItemCount());
    }
}