package com.nytimes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytimes.api.Article;
import com.nytimes.model.ArticleWrapper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample RecyclerView adapter.
 */
public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private final List<Article> articles = new ArrayList<>();
	private int hero = 1;
	private int imageId = 0;
	private int titleId = 0;
	private int bodyId = 0;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder viewHolder;
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		switch (viewType) {
			case 2:
				View vHero = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
				imageId = R.id.hero_item_image;
				titleId = R.id.hero_item_title;
				bodyId = R.id.hero_item_body;
				viewHolder = new GenericViewHolder(vHero);
				break;
			default:
				View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
				imageId = R.id.grid_item_image;
				titleId = R.id.grid_item_title;
				bodyId = R.id.grid_item_body;
				viewHolder = new GenericViewHolder(v);
				break;
		}

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		GenericViewHolder vh = (GenericViewHolder) holder;
		setViewHolder(vh, position);
	}

	@Override
	public int getItemCount() {
		return articles.size();
	}

	@Override
	public int getItemViewType(int position) {
		hero = articles.get(position).getHero();
		hero = hero !=2 ? 1 : hero;

		return hero;
	}

	public void setArticles(List<Article> articles){
		this.articles.clear();
		if(articles != null) {
			this.articles.addAll(articles);
		}
		notifyDataSetChanged();
	}

	class GenericViewHolder extends RecyclerView.ViewHolder {
		ImageView image;
		TextView title;
		TextView body;

		public GenericViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(imageId);
			title = (TextView) itemView.findViewById(titleId);
			body = (TextView) itemView.findViewById(bodyId);
		}
	}

	private <T extends GenericViewHolder> void setViewHolder (T holder, int position) {
		Article article = articles.get(position);
		holder.title.setText(article.getTitle());
		holder.body.setText(article.getBody());

		ArticleWrapper articleWrapper = new ArticleWrapper(article);
		if (articleWrapper.getHeroImageUrl() != null) {
			Picasso.with(holder.itemView.getContext()).load(articleWrapper.getThumbImageUrl()).into(holder.image);
		} else {
			holder.image.setImageBitmap(null);
		}
	}
}
