package com.example.hotmart.config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
@AllArgsConstructor
public class MongoConfig {

    private final MongoTemplate mongoTemplate;

    @Bean
    public GridFsOperations gridFsTemplate() {
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoTemplate.getDb());
        return new GridFsTemplate(mongoTemplate.getMongoDatabaseFactory(), mongoTemplate.getConverter(),gridFSBucket.getBucketName());
    }

}
