# Bank-Management-System
These are the microservices in spring-boot. Having five different microservice project.
1.**Customer-Management-Service**
2.**Account-Management-Service**
3.**E-Server(eureka server)**
4.**Config-Server(centralised configuration management)**
5.**Api-gateway**

## PORT's
- **Eureka-Server:** http://localhost:8761
- **API-Gateway:** http://localhost:8999
- **Customer-Management-Service:** http://localhost:8082
- **Account-Management-Service:** http://localhost:8081
- **Config-Server:** http://localhost:8085
  (Config server gets configuration from [GitHub Repository](https://github.com/himanshukhunteta001/configuration-server))

  ## Swagger-UI
  -**Customer-Management-Service:** http://localhost:8082/swagger-ui.html
  -**Account-Management-Service:** http://localhost:8081/swagger-ui.html

  

## Customer-Management-Service

### Add customer:
- api endpoint: http://localhost:8082/customer/profile/addNewCustomer

### Get all customers:
- api endpoint: http://localhost:8082/customer/profile/getAllCustomer

### Get single customer details:
- api endpoint: http://localhost:8082/customer/profile/getCustomerDetailById/{customerId}

### Update customer details:
- api endpoint: http://localhost:8082/customer/profile/updateCustomerDetail/{customerId}

### Delete customer:
- api endpoint: http://localhost:8082/customer/profile/deleteCustomer/{customerId}


## Account-Management-Service

### Add money to account:
- api-endpoint: http://localhost:8081/account/profile/addMoney

### Withdraw money from account:
- api-endpoint: http://localhost:8081/account/profile/withdrawMoney

### Get account details:
- api-endpoint: http://localhost:8081/account/profile/getAccountDetailById/{accountId}

### Delete account:
- api-endpoint: http://localhost:8081/account/profile/deleteAccount/{accountId}




