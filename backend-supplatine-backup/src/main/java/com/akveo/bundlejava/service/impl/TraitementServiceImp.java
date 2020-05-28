package com.akveo.bundlejava.service.impl;

import com.akveo.bundlejava.service.TraitementService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TraitementServiceImp implements TraitementService {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

//    @Override
//    public List<Process> findByTitleAndAuthor(String title, String author) {
//        BoolQueryBuilder criteria = QueryBuilders.boolQuery();
//        criteria.must().addAll(Arrays.asList(QueryBuilders.matchQuery("processName", author), QueryBuilders.matchQuery("title", title)));
//        return elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(criteria).build(), Process.class);
//    }
}
