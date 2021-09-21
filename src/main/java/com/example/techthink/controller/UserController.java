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
import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/all/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.registerStudent(request));
        return body;
    }

    @PostMapping(value = "/admin/registerProfessor")
    public ResponseEntity<UserResponse> addProfessor(@RequestBody RegisterRequest request) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.addProfessor(request));
        return body;
    }

    @GetMapping(value = "/user/readUser/{id}")
    public ResponseEntity<UserResponse> readById(@PathVariable Long id) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readById(id));
        return body;
    }

    @GetMapping(value = "/user/getAll")
    public ResponseEntity<List<UserResponse>> readAll() {
        ResponseEntity<List<UserResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readAll());
        return body;
    }

    @PutMapping(value = "/user/updateUser")
    public ResponseEntity<UserResponse> editProfile(@AuthenticationPrincipal UserPrincipal user, @RequestBody RegisterRequest request) {
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.editProfile(user.getId(), request));
        return body;
    }

    @DeleteMapping(value = "/user/deleteAccount")
    public ResponseEntity<Boolean> deleteAccount(@AuthenticationPrincipal UserPrincipal user) {
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.delete(user.getId()));
        return body;
    }

    @PostMapping(value = "/user/uploadProfilePicture", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserResponse> uploadProfPic(@AuthenticationPrincipal UserPrincipal user, @RequestPart MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAWQHZ4UCXO2VY2VWT",
                "S7tGyJwrvJiGW2CSqBR0koV8eTCRMdWP/f02OJLv"
        );

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        
        //techthinkproject

        PutObjectRequest requestFile = new PutObjectRequest("teckthink", file.getOriginalFilename(), inputStream, objectMetadata);
        requestFile.withCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(requestFile);

        String pictureURL = s3Client.getUrl("teckthink", file.getOriginalFilename()).toExternalForm();

        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.uploadPicture(user.getId(), pictureURL));
        return body;
    }
}
