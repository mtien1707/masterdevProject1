package org.cuminhtien.elasticsearch1.repository;

import org.cuminhtien.elasticsearch1.config.config;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Scope("prototype")
public class repository {

    protected static final Logger logger = LoggerFactory
            .getLogger(repository.class);
    public List<String> getTitle(String input, int size) throws IOException {

        RestHighLevelClient client = new config().client();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SuggestionBuilder termSuggestionBuilder = SuggestBuilders.completionSuggestion("suggest_title").text(input).skipDuplicates(true).size(size);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest", termSuggestionBuilder);

        searchSourceBuilder.suggest(suggestBuilder);

        SearchRequest searchRequest = new SearchRequest("suggest_tiencm8");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(searchResponse.getSuggest().iterator(), Spliterator.ORDERED), false)
                .flatMap(suggestion -> suggestion.getEntries().get(0).getOptions().stream())
                .map((Suggest.Suggestion.Entry.Option option) -> option.getText().toString() )
                .collect(Collectors.toList());
    }
}
