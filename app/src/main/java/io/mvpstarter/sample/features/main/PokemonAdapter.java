package io.mvpstarter.sample.features.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.mvpstarter.sample.R;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<String> pokemonList;
    private ClickListener clickListener;

    @Inject
    public PokemonAdapter() {
        pokemonList = Collections.emptyList();
    }

    public void setPokemon(List<String> pokemon) {
        this.pokemonList = pokemon;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        String pokemon = this.pokemonList.get(position);
        holder.pokemon = pokemon;
        holder.nameText.setText(
                String.format("%s%s", pokemon.substring(0, 1).toUpperCase(), pokemon.substring(1)));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public interface ClickListener {
        void onPokemonClick(String pokemon);
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        String pokemon;

        @BindView(R.id.text_name)
        TextView nameText;

        PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(
                    v -> {
                        if (clickListener != null) clickListener.onPokemonClick(pokemon);
                    });
        }
    }
}
