package com.example.techthink.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.techthink.controller.model.AssignmentRequest;
import com.example.techthink.controller.model.AssignmentResponse;
import com.example.techthink.facade.AssignmentFacade;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentFacade facade;

    public AssignmentController(AssignmentFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/professor/create", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AssignmentResponse> create(@RequestPart String data, @RequestPart MultipartFile file) throws IOException {

        Gson gson = new Gson();
        AssignmentRequest request = gson.fromJson(data, AssignmentRequest.class);

        InputStream inputStream = file.getInputStream();


        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIA6QUEMR4ILMEEBVFY",
                "E5vlbw4y0+Z91CrbCbeVqXu4XtCMdxyjEH6SLog3"
        );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();

        PutObjectRequest requestFile = new PutObjectRequest("teckthink", file.getOriginalFilename(), inputStream, objectMetadata);
        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(requestFile);

        String fileURL = s3Client.getUrl("teckthink", file.getOriginalFilename()).toExternalForm();
        ResponseEntity<AssignmentResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request, fileURL));
        return body;
    }

    @GetMapping(value = "/user/assignment/{id}")
    public ResponseEntity<AssignmentResponse> readById(@PathVariable Long id) {
        ResponseEntity<AssignmentResponse> body = ResponseEntity.status(HttpStatus.OK).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/user/assignments")
    public ResponseEntity<List<AssignmentResponse>> readAll() {
        ResponseEntity<List<AssignmentResponse>> body = ResponseEntity.status(HttpStatus.OK).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/professor/update/assignment/{id}" , consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AssignmentResponse> update(@RequestPart String data, @RequestPart MultipartFile file , @PathVariable Long id) throws IOException {

        Gson gson = new Gson();
        AssignmentRequest request = gson.fromJson(data, AssignmentRequest.class);

        InputStream inputStream = file.getInputStream();

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIA6QUEMR4IAS4FQ7UU",
                "rjegIKvfqzxij3wG4bwCABhiOjkdzTv7CVMcyDZ6"
        );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();

        PutObjectRequest requestFile = new PutObjectRequest("teckthink", file.getOriginalFilename(), inputStream, objectMetadata);
        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(requestFile);

        String fileURL = s3Client.getUrl("teckthink", file.getOriginalFilename()).toExternalForm();

        ResponseEntity<AssignmentResponse> body = ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(facade.update(id, request, fileURL));
        return body;
    }

    @DeleteMapping(value = "/professor/delete/assignment/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.OK).body(facade.delete(id));
        return body;
    }
}
