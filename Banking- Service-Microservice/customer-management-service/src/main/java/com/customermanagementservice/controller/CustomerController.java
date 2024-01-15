package com.customermanagementservice.controller;

import com.customermanagementservice.dto.ApiResponseDto;
import com.customermanagementservice.entity.Customer;
import com.customermanagementservice.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/customer/profile")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping(value = "/getAllCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all customer.",description = "This api endpoint helps the user to fetch all the customer details present.")
    public ApiResponseDto getAllCustomerDetails(){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        apiResponseDtoBuilder.withMessage("Details of all customers are:").withStatus(HttpStatus.OK)
                .withData(customerService.getAllCustomerDetail());
        return apiResponseDtoBuilder.build();
    }

    @PostMapping(value ="/addNewCustomer",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add customer.",description = "This endpoint helps the user to add the new customer details to our database.")
    public ApiResponseDto saveNewCustomer(@RequestBody Customer customer){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        customerService.addNewCustomer(customer);
        apiResponseDtoBuilder.withMessage("Customer successfully added").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }

    @GetMapping(value = "/getCustomerDetailById/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get single Customer Details",description = "This endpoint helps the dealer to edit the open position of job opportunity.")
    public ApiResponseDto getCustomerDetailById(@PathVariable("customerId") int customerId){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        apiResponseDtoBuilder.withMessage("Detail of the customer is:").withStatus(HttpStatus.OK)
                .withData(customerService.getCustomerDetailById(customerId));
        return apiResponseDtoBuilder.build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerDetailById(customerId));

    }

    @DeleteMapping(value = "/deleteCustomer/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete Customer",description = "This api endpoint helps the user to delete the customer detail also delete the customer account from account management service.")
    public ApiResponseDto deleteCustomerDetail(@PathVariable("customerId") int customerId){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        customerService.deleteCustomerDetail(customerId);
        apiResponseDtoBuilder.withMessage("Customer account successfully deleted.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();

    }

    @PutMapping(value = "/updateCustomerDetail/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Customer Details.",description = "This api endpoint helps the user to edit the customer details.")
    public ApiResponseDto updateCustomerDetail(@PathVariable("customerId") int customerId,@Valid @RequestBody Customer customer){
        var apiResponseDtoBuilder = new ApiResponseDto.ApiResponseDtoBuilder();
        customerService.updateCustomerDetail(customerId,customer);
        apiResponseDtoBuilder.withMessage("Customer detail successfully edites.").withStatus(HttpStatus.OK);
        return apiResponseDtoBuilder.build();
    }
}
