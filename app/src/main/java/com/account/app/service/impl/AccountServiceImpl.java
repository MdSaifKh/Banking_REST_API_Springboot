package com.account.app.service.impl;

import com.account.app.dto.AccountDto;
import com.account.app.entity.Account;
import com.account.app.mapper.AccountMapper;
import com.account.app.repository.AccountRepository;
import com.account.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto findAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account details not found")
        );
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account details not found")
        );
        double total = account.getBalance() + amount;
        account.setBalance(total);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account details not found")
        );
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance!");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Account details not found")
        );
        accountRepository.deleteById(id);
    }
}
