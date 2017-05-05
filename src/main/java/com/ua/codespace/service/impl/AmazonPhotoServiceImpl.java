package com.ua.codespace.service.impl;

import com.ua.codespace.service.PhotoService;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.management.InstanceAlreadyExistsException;
import java.io.IOException;

@Component
public class AmazonPhotoServiceImpl implements PhotoService {
    @Value("${amazon.s3.bucketName}")
    private String BUCKET_NAME;
    @Value("${amazon.s3.bucketPath}")
    private String BUCKET_PATH;
    @Value("${amazon.s3.userUri}")
    private String USER_URI;
    @Value("${amazon.s3.accessKey}")
    private String awsAccessKey;
    @Value("${amazon.s3.secretKey}")
    private String awsSecretKey;


    @Override
    public String saveUserPhoto(String username, MultipartFile image) {
        return savePhoto(username, image, BUCKET_PATH, USER_URI);
    }

    private String savePhoto(String username, MultipartFile image, String path, String uri) {//todo: split and improve
        AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
        S3Service s3Service;
        s3Service = new RestS3Service(awsCredentials);

        S3Bucket bucket = null;
        try {
            bucket = s3Service.getBucket(BUCKET_NAME);
        } catch (S3ServiceException e) {
            System.out.println("Invalid Bucket name");
            e.printStackTrace();
            return null;
        }

        String objectName = uri + "/" + username + "/" + image.getOriginalFilename();
        S3Object imageObject = new S3Object(objectName);

        try {
            imageObject.setDataInputStream(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image input stream error!");
            return null;
        }

        imageObject.setContentLength(image.getSize());
        imageObject.setContentType(image.getContentType());

        AccessControlList acl = new AccessControlList();
        acl.setOwner(bucket.getOwner());
        acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
        imageObject.setAcl(acl);

        try {
            s3Service.putObject(bucket, imageObject);
        } catch (S3ServiceException e) {
            e.printStackTrace();
            System.out.println("Image saving error!");
            return null;
        }

        String link = path + objectName;
        return link;
    }
}
