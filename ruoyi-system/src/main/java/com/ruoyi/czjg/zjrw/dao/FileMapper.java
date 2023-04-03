package com.ruoyi.czjg.zjrw.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Component
public class FileMapper {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    MongoTemplate mongoTemplate;

    public String save(InputStream file, String fileName, String contentType) {

        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
        ObjectId store = gridFsTemplate.store(file, fileName, contentType);
        String id = store.toString();
        if (StringUtils.hasLength(id)) {

            // todo 并把该id存到集合中
            return id;
        }
        return null;
    }
    public GridFSFile find(String fileId) {
        return gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
    }

    private byte[] getFileBytes(String id) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
        gridFSBucket.downloadToStream(new ObjectId(id), out);
        return out.toByteArray();
    }

    public void delete(String id){
        //根据文件id删除fs.file和fs.chunks中的记录
        try {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public GridFSFindIterable findAll(){
        Query query = new Query();
        return gridFsTemplate.find(query);
    }
}
