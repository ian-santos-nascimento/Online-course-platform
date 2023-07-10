package com.example.hotmart.curso.controller;

import com.example.hotmart.curso.model.Secao;
import com.example.hotmart.curso.model.Video;
import com.example.hotmart.curso.repository.GridFSRepository;
import com.example.hotmart.curso.service.SecaoService;
import com.example.hotmart.curso.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SecaoService secaoService;

    @PostMapping(path ="/{id}" )
    @Operation(summary = "Save a video",description = "Save a video passing the sectionId that the Video is related and the video itself",tags = {"Video"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Video.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<?> saveVideo(@PathVariable("id") String secaoId,
                                       @RequestParam("video")MultipartFile file)  {
        String videoId = videoService.uploadViideo(file, secaoId);
        return ResponseEntity.status(HttpStatus.OK).body(videoId);
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Find a video",description = "Find a specific video passing the videoId" +
            " or a list of videos passing the section ID",tags = {"Video"}, responses = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Video.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}),
    })
    public ResponseEntity<?> retrieveVideo(@PathVariable("id") String id){
        Secao secao  = secaoService.findSecaoById(id);
        if(secao == null){
            Video video = videoService.downloadVideo(id);
            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.valueOf("video/mp4"))
                    .body(video.getBytes());
        }else {
            List<Video> videoList = videoService.downloadVideoListFromSecao(id);  //Find a way to return several videos at once
            return ResponseEntity.status(HttpStatus.OK).
                    body(videoList);
        }
    }




}
