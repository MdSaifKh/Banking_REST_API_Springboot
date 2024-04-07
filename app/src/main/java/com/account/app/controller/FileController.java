package com.account.app.controller;

import com.account.app.dto.PassbookDto;
import com.account.app.service.PassbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private PassbookService passbookService;

    @PostMapping("/{accountId}/uploadPassbook")
    public String uploadPassbookFile(@RequestParam("file")MultipartFile file, @PathVariable Long accountId){
        try{
            PassbookDto passbookDto = new PassbookDto();
            passbookDto.setAccountId(accountId);
            passbookDto.setFileName(file.getOriginalFilename());
            passbookDto.setContentType(file.getContentType());
            passbookDto.setData(file.getBytes());
            return passbookService.uploadPassbookByAccountId(passbookDto);
        }catch(Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/download/{accountId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("accountId") Long accountId){
        PassbookDto passbookDto = passbookService.downloadFileByAccountId(accountId);
        if(passbookDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",passbookDto.getFileName());
        return new ResponseEntity<>(passbookDto.getData(),headers,HttpStatus.OK);
    }
}
