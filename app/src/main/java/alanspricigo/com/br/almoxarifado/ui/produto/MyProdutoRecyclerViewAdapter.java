package alanspricigo.com.br.almoxarifado.ui.produto;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alanspricigo.com.br.almoxarifado.model.Dados;
import alanspricigo.com.br.almoxarifado.ui.produto.placeholder.PlaceholderContent.PlaceholderItem;
import alanspricigo.com.br.almoxarifado.databinding.FragmentConProdutoBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Dados.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProdutoRecyclerViewAdapter extends RecyclerView.Adapter<MyProdutoRecyclerViewAdapter.ViewHolder> {

    private final List<Dados> mValues;

    public MyProdutoRecyclerViewAdapter(List<Dados> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConProdutoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getCodProd());
        holder.mContentView.setText(mValues.get(position).getCodProd());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Dados mItem;

        public ViewHolder(FragmentConProdutoBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}