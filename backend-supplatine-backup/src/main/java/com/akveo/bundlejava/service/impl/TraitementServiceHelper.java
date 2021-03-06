package com.akveo.bundlejava.service.impl;

import com.akveo.bundlejava.model.index.Traitement;
import com.akveo.bundlejava.repository.search.TraitementRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

public class TraitementServiceHelper {
    @Autowired
    private TraitementRepository traitementRepository;

    public List<Traitement> queryBuilders() {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(new QueryStringQueryBuilder("man").field("processName"));
        FieldSortBuilder sort = SortBuilders.fieldSort("processName").order(SortOrder.DESC);
        PageRequest page = PageRequest.of(0, 1000);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(builder);
        nativeSearchQueryBuilder.withPageable(page);
        nativeSearchQueryBuilder.withSort(sort);
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        Page<Traitement> pages = traitementRepository.search(query);
        List<Traitement> traitements = pages.getContent();
        return traitements;
    }

    public QueryBuilder getExectValue(String fieldName, String fieldValue) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery(fieldName, fieldValue);
        return queryBuilder;
    }
    public QueryBuilder findGreatherThan(String fieldName, String fieldValue) {
        //greater than
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery(fieldName).gt(fieldValue);
        return queryBuilder;
    }
    public QueryBuilder findLessThan(String fieldName, String fieldValue) {
        //less than
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery(fieldName).lt(fieldValue);
        return queryBuilder;
    }



}
