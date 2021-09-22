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
import com.example.techthink.config.UserPrincipal;
import com.example.techthink.controller.model.CourseSectionRequest;
import com.example.techthink.controller.model.CourseSectionResponse;
import com.example.techthink.controller.model.SectionParticipantsResponse;
import com.example.techthink.facade.SectionFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class CourseSectionController {

    private final SectionFacade facade;

    public CourseSectionController(SectionFacade facade) {
        this.facade = facade;
    }


    @PostMapping(value = "/professor/createSection")
    public ResponseEntity<CourseSectionResponse> create(@AuthenticationPrincipal UserPrincipal professor, @RequestBody CourseSectionRequest request) {
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.createSection(professor.getId(), request));
        return body;
    }

    @PostMapping(value = "/student/section/{sectionId}")
    public ResponseEntity<SectionParticipantsResponse> enrollIn(@AuthenticationPrincipal UserPrincipal user, @PathVariable Long sectionId) {

        ResponseEntity<SectionParticipantsResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.enrollIn(user.getId(), sectionId));
        return body;
    }

    @PostMapping(value = "/all/search/{search}")
    public ResponseEntity<List<CourseSectionResponse>> search(@PathVariable String search) {
        ResponseEntity<List<CourseSectionResponse>> body = ResponseEntity
                .status(HttpStatus.OK)
                .body(facade.search(search));
        return body;
    }

    @GetMapping(value = "/all/section/{id}")
    public ResponseEntity<CourseSectionResponse> readById(@PathVariable Long id) {
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/all/section/getAll")
    public ResponseEntity<List<CourseSectionResponse>> readAll() {
        ResponseEntity<List<CourseSectionResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/professor/section/{id}")
    public ResponseEntity<CourseSectionResponse> update(@PathVariable Long id, @RequestBody CourseSectionRequest request) {
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/professor/section/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }

    //professor can delete students from the section
    ///MAYBE need to check if the professor own the section??
    @DeleteMapping(value = "/professor/deleteStudent/{studentId}/{sectionId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long studentId, @PathVariable Long sectionId) {
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.deleteStudentFromSection(studentId, sectionId));
        return body;
    }


    @PostMapping(value = "/professor/setSectionPic/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CourseSectionResponse> uploadProfPic(@PathVariable Long id, @RequestPart MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ;


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

        String pictureURL = s3Client.getUrl("teckthink", file.getOriginalFilename()).toExternalForm();
        //System.out.println(pictureURL);

        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.setSectionPic(id, pictureURL));
        return body;
    }

}
