package com.accountmanagementservice.controller;

import com.accountmanagementservice.dto.ApiResponseDto;
import com.accountmanagementservice.entity.AccountDetail;
import com.accountmanagementservice.entity.BalanceTransaction;
import com.accountmanagementservice.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/account/profile")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/createAccount",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create account.",description = "This api endpoint helps the user to create account.")

    public ApiResponseDto createAccount(@RequestBody AccountDetail accountDetail){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        accountService.createAccount(accountDetail);
        apiResponseDtoBuilder.withMessage("Account successfully created").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }


    @GetMapping(value = "/getAllAccountDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all accounts details.",description = "This api endpoint helps the user to fetch all the account details present.")
    public ApiResponseDto getAllAccounts(){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        apiResponseDtoBuilder.withMessage("Details of all accounts are:").withStatus(HttpStatus.OK)
                .withData(accountService.getAllAccountDetails());
        return apiResponseDtoBuilder.build();
    }

    @GetMapping(value = "/getAccountDetailById/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all account detail by id.",description = "This api endpoint helps the user to fetch the account detail by using the account id.")
    public AccountDetail getAccountDetails(@PathVariable("accountId") int accountId) {
        return accountService.getAccountDetails(accountId);
    }

//    @GetMapping(value = "/getAccountDetailById/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponseDto getAccountDetailById(@PathVariable("accountId") int accountId){
//        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
//        apiResponseDtoBuilder.withMessage("Details of the account are:").withStatus(HttpStatus.OK)
//                .withData(accountService.getAccountDetails(accountId));
//        return apiResponseDtoBuilder.build();
//    }


    @PostMapping(value = "/addMoney",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add money to account.",description = "This api endpoint helps the user to add the money in the account and also Before adding money to account it is verifying the customer details passed in the request are valid or not.")
    public ApiResponseDto addMoney(@RequestBody BalanceTransaction balanceTransaction){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        accountService.addMoneyToAccount(balanceTransaction);
        apiResponseDtoBuilder.withMessage("Balance added successfully in the account.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }

    @PostMapping(value = "/withdrawMoney",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Withdraw money to account.",description = "This api endpoint helps the user to withdraw the money in the account and also Before adding money to account it is verifying the customer details passed in the request are valid or not.")
    public ApiResponseDto withdrawMoney(@RequestBody BalanceTransaction balanceTransaction){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        accountService.withdrawMoneyToAccount(balanceTransaction);
        apiResponseDtoBuilder.withMessage("Balance withdraw successfully from the account.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }

    @DeleteMapping(value = "/deleteAccount/{accountId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete account.",description = "This api endpoint helps the user to delete the account detail by using the account id.")
    public ApiResponseDto deleteAccount(@PathVariable("accountId") int accountId){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        accountService.deleteAccountId(accountId);
        apiResponseDtoBuilder.withMessage("Account Successfully deleted.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }

    @GetMapping(value = "/getAccountDetailByCustomerId/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Account details..",description = "This api endpoint helps the user to fetch the account detail by using the  customer id.")
    public ApiResponseDto getAccountDetail(@PathVariable("customerId") int customerId){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        apiResponseDtoBuilder.withMessage("Details of the account are:").withStatus(HttpStatus.OK)
                .withData(accountService.getAccountDetailByCustomerId(customerId));
        return apiResponseDtoBuilder.build();

    }

    @DeleteMapping(value = "/deleteAccountByCustomerId/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Account details..",description = "This api endpoint helps the user to delete the account by using the  customer id.")
    public ApiResponseDto deleteAccountByCustomerId(@PathVariable("customerId") int customerId){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        accountService.deleteAccountByCustomerId(customerId);
        apiResponseDtoBuilder.withMessage("Account Successfully deleted.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }
}
