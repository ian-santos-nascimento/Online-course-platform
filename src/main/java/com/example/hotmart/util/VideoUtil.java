package com.example.hotmart.util;

import com.example.hotmart.curso.model.Video;
import com.example.hotmart.exceptionHandler.VideoConverterException;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

@Slf4j
public class VideoUtil {

    public static byte[] compressVideo(byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(baos);

        ZipEntry zipEntry = new ZipEntry("compressedData");
        zipOut.putNextEntry(zipEntry);
        zipOut.write(input);
        zipOut.closeEntry();
        zipOut.close();

        return baos.toByteArray();
    }


    public static byte[] decompressByteArray(byte[] input) {
        ByteArrayInputStream bais = new ByteArrayInputStream(input);
        try {

            ZipInputStream zipIn = new ZipInputStream(bais);
            ZipEntry entry = zipIn.getNextEntry();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            zipIn.closeEntry();
            zipIn.close();
            return baos.toByteArray();
        } catch (IOException e) {
            log.error("Could not decompress Video. Exception:" + e.getMessage());
        }
            return input;
    }

    public static Video objectToVideo(GridFSFile gridFSFile, GridFsOperations gridFsOperations) {
        Document object = gridFSFile.getMetadata();
        Video video = Video.builder().titulo(String.valueOf(object.get("titulo")))
                .id(String.valueOf(gridFSFile.getId()))
                .secao(String.valueOf(object.get("secao_id")))
                .dataCriacao(gridFSFile.getUploadDate())
                .realLength((Integer) object.get("real_length"))
                .bytes(decompressByteArray(getBytesfromGridFS(gridFSFile, gridFsOperations)))
                .build();
        return video;
    }

    public static List<Video> gridFSIterableToList(GridFSFindIterable gridFSFiles, GridFsOperations gridFsOperations) {
        List<Video> videoList = new ArrayList<>();
        gridFSFiles.forEach(file -> videoList.add(objectToVideo(file,gridFsOperations)));
        return videoList;
    }

    private static byte[] getBytesfromGridFS(GridFSFile gridFSFile, GridFsOperations gridFsOperations){
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream =  gridFsOperations.getResource(gridFSFile).getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);}
            return outputStream.toByteArray();
        }catch (Exception e){
             log.error("Could not get bytes from GridFS file. Exception"+ e.getMessage());
        }
        return null;
    }

}
