package com.joker;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
@RestController
public class MyApplication {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @GetMapping("/index")
    public SearchResponse index() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchRequest rq = new SearchRequest()
                //索引
                .indices("account")
                //各种组合条件
                .source(sourceBuilder);

        //请求
        System.out.println(rq.source().toString());
        return restHighLevelClient.search(rq, RequestOptions.DEFAULT);
    }

    @GetMapping("/search")
    public String search() throws IOException {

        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                //这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
                .matchQuery("name", "afred");
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("入门", "name", "lastname");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(matchQueryBuilder)
                .must(multiMatchQueryBuilder);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                //设置查询，可以是任何类型的QueryBuilder。
                .query(boolQueryBuilder)
                //设置确定结果要从哪个索引开始搜索的from选项，默认为0
                .from(0)
                //设置确定搜素命中返回数的size选项，默认为10
                .size(100)
                //设置一个可选的超时，控制允许搜索的时间。
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        SearchRequest searchRequest = new SearchRequest()
                //索引
                .indices("account")
                .source(sourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("结果总数："+ response.getHits().getTotalHits().value);
        SearchHits hits = response.getHits();  //SearchHits提供有关所有匹配的全局信息，例如总命中数或最高分数：
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            log.info("search -> {}",hit.getSourceAsString());
        }
        return Arrays.toString(searchHits);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
