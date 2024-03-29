package br.com.frotch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.frotch.R;
import br.com.frotch.domain.Veiculo;

public abstract class VeiculosAdapter extends RecyclerView.Adapter<VeiculosAdapter.ViewHolder> {

    private List<Veiculo> mDataset;

    public abstract void onVeiculoClick(Veiculo veiculo);

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivVeiculo;
        public TextView tvPlaca;
        public TextView tvMarcaModelo;
        public TextView tvTipo;
        public TextView tvAno;

        public ViewHolder(View v) {
            super(v);
            ivVeiculo = v.findViewById(R.id.ivVeiculo);
            tvPlaca = v.findViewById(R.id.tvPlaca);
            tvMarcaModelo = v.findViewById(R.id.tvMarcaModelo);
            tvTipo = v.findViewById(R.id.tvTipo);
            tvAno = v.findViewById(R.id.tvAno);
        }
    }

    public VeiculosAdapter(List<Veiculo> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_veiculos, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Veiculo veiculo = mDataset.get(position);

        holder.tvPlaca.setText(veiculo.getPlaca());
        String marcaModelo = String.format("%s/%s", veiculo.getMarca(), veiculo.getModelo());
        holder.tvMarcaModelo.setText(marcaModelo);
        holder.tvTipo.setText(veiculo.getTipo().name());
        holder.tvAno.setText(veiculo.getAno().toString());

        Picasso.get().load(veiculo.getImagem())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivVeiculo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVeiculoClick(veiculo);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
