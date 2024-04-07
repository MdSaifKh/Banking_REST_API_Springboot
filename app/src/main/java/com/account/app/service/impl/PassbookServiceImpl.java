package com.account.app.service.impl;

import com.account.app.dto.PassbookDto;
import com.account.app.entity.PassbookFile;
import com.account.app.mapper.PassbookMapper;
import com.account.app.repository.AccountRepository;
import com.account.app.repository.PassbookRepository;
import com.account.app.service.PassbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassbookServiceImpl implements PassbookService {

    @Autowired
    private PassbookRepository passbookRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public String uploadPassbookByAccountId(PassbookDto passbookDto) {
        try{
            Long accountId = passbookDto.getAccountId();
            accountRepository.findById(accountId).orElseThrow(
                    ()->  new RuntimeException("Account with id "+accountId+"  does not exist")
            );
            PassbookFile passbookFile = PassbookMapper.mapToPassbookFile(passbookDto);
            passbookRepository.save(passbookFile);
            return "File uploaded successfully!";
        }catch(RuntimeException e){
            return e.getMessage();
        }catch(Exception e1){
            return "File upload failed "+e1.getMessage();
        }
    }

    @Override
    public PassbookDto downloadFileByAccountId(Long accountId) {
        PassbookFile passbookFile = passbookRepository.findByAccountId(accountId);
        return PassbookMapper.mapToPassbookDto(passbookFile);
    }
}
