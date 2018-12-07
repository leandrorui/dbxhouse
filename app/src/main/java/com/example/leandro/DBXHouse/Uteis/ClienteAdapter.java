package com.example.leandro.DBXHouse.Uteis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.leandro.DBXHouse.R;
import com.example.leandro.DBXHouse.model.Cliente;

import java.util.List;


public class ClienteAdapter extends RecyclerView.Adapter<ClienteHolder> {

    private final List<Cliente> clientes;

    public ClienteAdapter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public ClienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClienteHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(ClienteHolder holder, int position) {
        holder.nomeCliente.setText(clientes.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return clientes != null ? clientes.size() : 0;
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
        notifyItemInserted(getItemCount());
    }
}