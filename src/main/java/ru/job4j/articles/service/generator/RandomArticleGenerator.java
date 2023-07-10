package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;
import ru.job4j.articles.store.Store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {

    private final List<Word> words;
    public RandomArticleGenerator(Store<Word> wordStore) {
        this.words = wordStore.findAll();
    }

    @Override
    public Article generate() {
        Collections.shuffle(words);
        var content = words.stream()
                .map(Word::getValue)
                .collect(StringBuilder::new, StringBuilder::append,
                (a, b) -> a.append(" ").append(b));
        return new Article(content.toString());
    }
}
