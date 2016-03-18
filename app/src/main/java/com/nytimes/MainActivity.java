package com.candyspace.nytimes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.candyspace.nytimes.api.Article;
import com.candyspace.nytimes.api.MostPopularApi;
import com.candyspace.nytimes.api.RetrofitMostPopularApi;
import com.candyspace.nytimes.hero.HeroArticles;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		GridLayoutManager manager = new GridLayoutManager(this, 2);

		final SampleRecyclerViewAdapter adapter = new SampleRecyclerViewAdapter();
		recyclerView.setAdapter(adapter);

		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return adapter.getItemViewType(position);
			}
		});

		recyclerView.setLayoutManager(manager);

		MostPopularApi api = new RetrofitMostPopularApi();
		api.fetchArticles(new MostPopularApi.Callback() {

			@Override
			public void onSuccess(List<Article> articles) {
				Log.d(TAG, "Got articles");
				HeroArticles ha = new HeroArticles();
				adapter.setArticles(ha.arrangeHeroArticles(articles,5));
			}

			@Override
			public void onFailure(String error) {
				Log.d(TAG, "Failed to get articles");
			}

		});
	}
}
