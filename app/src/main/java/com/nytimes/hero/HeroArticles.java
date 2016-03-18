package com.candyspace.nytimes.hero;

import com.candyspace.nytimes.api.Article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ketan.sharma on 18/03/2016.
 * This re-arranges the list of articles so that between every iheroSpacing number
 * of articles, a hero article is inserted.
 *
 * This starts by working out how many hero articles there should be, based on the number of
 * articles (iheroArticles).
 *
 * It then gets that number of articles from the beginning, which are the most popular.
 *
 * These articles are then removed from the orginal list and re-inserted into each position, iheroSpacing items apart.
 */
public class HeroArticles {

    public List<Article> arrangeHeroArticles(List<Article> articles, int iheroSpacing) {
        List<Article> heroArticles = new ArrayList<>();
        int iheroArticles = 0;
        int iArticlesSize = articles.size();
        int iheroCounter = 0;

        if (iArticlesSize > 0) {
            iheroSpacing = iheroSpacing==1 ? 2 : iheroSpacing;

            iheroArticles = (iArticlesSize / iheroSpacing);
            if ((articles.size() % iheroSpacing) > 0)
            {
                iheroArticles+=1;
            }

            for (int i = 0; i < iheroArticles; i++) {
                heroArticles.add(articles.get(i));
                heroArticles.get(i).setHero(2);
            }

            Iterator<Article> it = articles.iterator();

            while (it.hasNext()) {
                if (heroArticles.contains(it.next())) {
                    it.remove();
                }
            }

            articles.add(0,heroArticles.get(iheroCounter));

            for (int i = 1; i < iArticlesSize; i++) {
                if (((i+1) % iheroSpacing) == 1) {
                    iheroCounter+=1;
                    articles.add(i,heroArticles.get(iheroCounter));
                }
            }
        }

        return articles;
    }
}
