# Spring Boot Challenge WMB

## Daftar Fitur
1. Auth (Register(POST), Login(POST))
2. Customer (Get by Id(GET), Get All(GET), Update Mobile Phone No by Id(PUT), Delete by Id(DELETE), Update Customer Include Account (PUT))
3. Menu (Create(POST), Get by Id(GET), Get All(GET), Update Menu Price by Menu Name(PUT), Delete by Id(DELETE))
4. Table (Create(POST), Get by Id(GET), Get All(GET), Update Table Name by Id(PUT), Delete by Id(DELETE))
5. TransType (Create(POST), Get by (GET), Get All(GET), Update Description by Id(PUT), Delete by Id(DELETE))
6. Transaction (Create Transaction(POST), Get All Transaction(GET))

### NOTE:

## 1. Sebelum menjalankan program, sesuaikan properties pada file application.properties dengan device

## 2. Ketika ingin menggunakan semua fitur diatas, anda wajib login pada fitur Auth dengan akun di bawah ini:
```
@RequestBody
{
"username": "superadmin",
"password": "password"
}
```
Dengan URL ini:
```
http://localhost:8080/api/v1/auth/login

```

## Ilustrasi Penggunaan Fitur

## 1. Auth

### Auth - Register
URL:
```
http://localhost:8080/api/v1/auth/register
```

Postman:

```
@RequestBody
{
"username": "SuperVegito,
"password": "potara"
}
```

### Auth - Login
URL:
```
http://localhost:8080/api/v1/auth/login
```

Postman:

```
@RequestBody
{
"username": "SuperVegito",
"password": "potara"
}
```

Ambil JWT token untuk dimasukkan ke dalam Bearer Token pada Tab Authorization Postman:

```
eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxZTYwM2VjNC0wMmIyLTQ5MDktOGQyZS01MTY5MGJiZDA4NzMiLCJyb2xlcyI6WyJST0xFX1NVUEVSX0FETUlOIiwiUk9MRV9BRE1JTiIsIlJPTEVfQ1VTVE9NRVIiXSwiaWF0IjoxNzE3OTM4OTQyLCJleHAiOjE3NDY3Mzg5NDIsImlzcyI6IlNhdXFpIn0.5_4fjdJnukfxJoK9gLh4_feJGErm3cLeXlABoKexbIpR4UejRjMf5wJ53M8y64QdfcWkbbBu4mB_9ql4ENytGw
```

## 2. Customer

### Customer - Get by Id
URL:
```
http://localhost:8080/api/v1/customers/{customerId}
```

Postman:

```
@PathVariable (C003)

```

### Customer - Get All Customers
URL:
```
http://localhost:8080/api/v1/customers
```

Postman:
``` 
@RequestParam:

page: 1
size: 5
sortBy: customerName
direction: ASC
customerName: {customerName}
```

### Customer - Update Mobile Phone Number by Id
URL:
``` 
http://localhost:8080/api/v1/customers/{customerId}?mobilePhoneNo={mobilePhoneNo}
```

Postman:
```
@PathVariable(C003)
@RequestParam(081749172929)
```

### Customer - Delete by Id
URL:
```
http://localhost:8080/api/v1/customers/{customerId}
```

Postman:
``` 
@PathVariable(C003)
```

### Customer - Update Customer Include Account
URL:
``` 
http://localhost:8080/api/v1/customers
```

Postman:
```
@RequestBody:

{
    "customerId": "C012",
    "customerName": "Vegito",
    "mobilePhoneNo": "081937261917",
    "isMember": true,
    "userAccount": {
        "id": "88036a70-a2b1-44b5-a06f-3d1c97088f63",
        "username": "SuperVegito",
        "password": "$2a$10$I7ih2br79Q7ixHC97b7vjeXZNwAK0QemfbyxeArjWIzIfFwyPR586",
        "isEnable": true,
        "role": [
            {
                "id": "886a4aef-c39d-4711-9978-ef1b280ff5a7",
                "role": "ROLE_CUSTOMER"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true
    }
}
```

## 3. Menu

### Menu - Create New Menu
URL:
``` 
http://localhost:8080/api/v1/menus
```

Postman:
``` 
@RequestBody:

{
    "menuName" : "Gado-gado",
    "menuPrice" : 19000
}
```

### Menu - Get by Id
URL:
```
http://localhost:8080/api/v1/menus/{menuId}
```

Postman:
``` 
@PathVariable(M004)
```

### Menu - Get All Menus
URL:
``` 
http://localhost:8080/api/v1/menus
```

Postman:
```
@RequestParam:

page: 1
size: 5
sortBy: menuPrice
direction: ASC
menuName: {menuName}
```

### Menu - Update Price by Menu Name
URL:
```
http://localhost:8080/api/v1/menus/updatePrice?
```

Postman:
```
@RequestParam:

menuName: Chicken Cordon Bleu
menuPrice: 43000
```

### Menu - Delete by Id
URL:
```
http://localhost:8080/api/v1/menus/{menuId}
```

Postman:
```
@PathVariable(M008)
```

## 4. Table

### Table - Create New Table
URL:
```
http://localhost:8080/api/v1/tables
```

Postman:
```
@RequestBody

{
    "tableName" : "Meja Private (Rokok)"
}
```

### Table - Get by Id
URL:
```
http://localhost:8080/api/v1/tables/{tableId}
```

Postman:
```
@PathVariable(T003)
```

### Table - Get All Tables
URL:
```
http://localhost:8080/api/v1/tables
```

Postman:
```
@RequestParam:

tableName : Meja Private (Rokok)
```

### Table - Update Table Name by Id
URL:
```
http://localhost:8080/api/v1/tables/{tableId}
```

Postman:
```
@PathVariable(T003)
@RequestParam(Meja VIP)
```

### Table - Delete by Id
URL:
```
http://localhost:8080/api/v1/tables/{tableId}
```

Postman:
```
@PathVariable(T006)
```

## 5. TransType

### TransType - Create New TransType
URL:
```
http://localhost:8080/api/v1/trans-types
```

Postman:
```
@RequestBody:

{
    "description" : "Tunai"
}
```

### TransType - Get by Id
URL:
```
http://localhost:8080/api/v1/trans-types/{transTypeId}
```

Postman:
```
@PathVariable(TYPE001)
```

### TransType - Get All TransTypes
URL:
```
http://localhost:8080/api/v1/trans-types
```

Postman:
```
@RequestParam:

description : ShopeePay
```

### TransType - Update Description by Id
URL:
```
http://localhost:8080/api/v1/trans-types/{transTypeId}
```

Postman:
```
@PathVariable(TYPE003)
@RequestParam(GoPay)
```

### TransType - Delete by Id
URL:
```
http://localhost:8080/api/v1/trans-types/{transTypeId}
```

Postman:
```
@PathVariable(TYPE005)
```

## 6. Transaction

### Transaction - Create New Transaction
URL:
```
http://localhost:8080/api/v1/transactions
```

Postman:
```
@RequestBody:

{
    "customerId":"C004",
    "tableId":"T003",
    "transTypeId":"TYPE001",
    "transactionDetails":[
        {
            "menuId":"M013",
            "qty":2
        },
        {
            "menuId":"M003",
            "qty":3
        }
    ]
}
```

### Transaction - Get All Transactions
URL:
```
http://localhost:8080/api/v1/transactions
```

Postman: No additional request required while testing this method

# DONE