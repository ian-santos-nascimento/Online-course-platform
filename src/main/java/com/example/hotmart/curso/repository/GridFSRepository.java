package com.example.hotmart.curso.repository;

import com.example.hotmart.curso.model.Video;
import com.example.hotmart.exceptionHandler.ResourceNotFoundException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Repository
public class GridFSRepository {

    @Autowired
    private GridFsOperations gridFsOperations;

    public String saveVideoFile(MultipartFile multipartFile, Video video, int length)  {
        DBObject object = new BasicDBObject();
        object.put("type","video");
        object.put("titulo", video.getTitulo());
        object.put("secao_id", video.getSecao());
        object.put("real_length", length);
        String obj = gridFsOperations.store(new ByteArrayInputStream(video.getBytes()),video.getTitulo()
                , multipartFile.getContentType(),object).toString();
        System.out.println("saveVideoFile: "+obj);
        return obj;
    }

    public GridFSFile retrieveVideo(String id){
         GridFSFile dbObj = gridFsOperations.findOne(new Query(Criteria.where("_id").is(id)));
         if(dbObj != null){
            return dbObj;
         }else{
             throw new ResourceNotFoundException("Não foi possível localizar o vídeo com o ID:" + id);
         }
    }

    public GridFSFindIterable retrieveVideoList(String id){
        return gridFsOperations.find(new Query(Criteria.where("metadata.secao_id").is(id)));
    }

}
