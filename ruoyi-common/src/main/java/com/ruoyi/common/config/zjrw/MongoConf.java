package com.ruoyi.common.config.zjrw;//package com.ruoyi.common.config.zjrw;
//
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.gridfs.GridFSBucket;
//import com.mongodb.client.gridfs.GridFSBuckets;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;
//
///**
// * @author ：lin_zhixiong
// * @date ：Created in 2021/6/28 9:15 上午
// * @version: V1.0
// * @slogan: 天下风云出我辈，一入代码苦耕耘
// * @description:
// **/
//@Configuration
//public class MongoConf {
//
//    @Autowired(required = true)
//    private MongoDatabaseFactorySupport mongoDbFactory;
//
//
//    @Autowired
//    private GridFSBucket gridFSBucket;
//
//
//    @Bean
//    public GridFSBucket getGridFSBucket() {
//        MongoDatabase db = mongoDbFactory.getMongoDatabase();
//        return GridFSBuckets.create(db);
//    }
//}
//
