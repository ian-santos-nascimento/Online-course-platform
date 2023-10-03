package com.example.hotmart.curso.service;

import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.curso.model.Video;
import com.example.hotmart.curso.repository.GridFSRepository;
import com.example.hotmart.curso.repository.SecaoRepository;
import com.example.hotmart.util.VideoUtil;
import com.example.hotmart.util.exceptionHandler.ResourceNotFoundException;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static com.example.hotmart.util.VideoUtil.objectToVideo;

@Service
@Slf4j
public class VideoService {

    private final GridFsOperations gridFsOperations;

    @Autowired
    public VideoService(GridFsOperations gridFsOperations) {
        this.gridFsOperations = gridFsOperations;
    }

    @Autowired
    private GridFSRepository repository;

    @Autowired
    private SecaoRepository secaoRepository;

    public String uploadViideo(MultipartFile multipartFile, String secaoId) {
        try {
            Secao secao = secaoRepository.findById(secaoId).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar a secao"));
            int realLength = multipartFile.getBytes().length;
            byte[] videoCompressed = VideoUtil.compressVideo(multipartFile.getBytes());
            Video video = Video.builder()
                    .bytes(videoCompressed)
                    .dataCriacao(new Date())
                    .secao(secao.getId())
                    .titulo(multipartFile.getOriginalFilename())
                    .build();
            return repository.saveVideoFile(multipartFile, video, realLength);
        } catch (Exception e) {
            log.error("[uploadViideo][Não foi possível realizar o upload do vídeo. Exception:" + e);
            throw new InternalError("Não foi possível fazer upload do vídeo, tente novamente mais tarde");
        }
    }

    public Video downloadVideo(String videoId) {
        GridFSFile gridFSFile = repository.retrieveVideo(videoId);
        if(gridFSFile == null)
            return null;
        return objectToVideo(gridFSFile,gridFsOperations);

    }

    public List<Video> downloadVideoListFromSecao(String secaoId) {
        GridFSFindIterable gridFSFiles = repository.retrieveVideoList(secaoId);
        return VideoUtil.gridFSIterableToList(gridFSFiles, gridFsOperations);
    }


}
