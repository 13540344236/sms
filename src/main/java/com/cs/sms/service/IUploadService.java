package com.cs.sms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {
    String  upload(MultipartFile picFile) throws IOException;
}
