# VIDEO STORE RENTAL APP #

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [App Structure](#structure)
4. [FAQs](#faqs)

## General Info
This monolithic app is made with Spring Tool Suite for study purpose.\
All libraries are handle by Maven and listed into pom.xml file

## Technologies

Apache Maven 3.8.4\
Spring Tool Suite 3.9.18\
Postman 9.15.0\
Docker Desktop 4.5.1
### Libraries
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- spring-boot-starter-web
- mysql-connector-java
- lombok
- spring-boot-starter-test


## Structure

|Folder    | Purpose|
|--------- | ---------|
|**composite_key**| This folder contains Entity used for table's composite keys
|**Entity**| This folder contains Entity info used by hibernate to create table structure|
|**Controller**| This folder contains all the logic to call methods adn execute query|
|**Service**| This folder contains intermediate code between query stored in Repository and methods in Controller|
|**Payload**| This folder contains DAO and DTO implementations|
|**Repository**| This folder contains query operations|

### Main folders and classes

| composite_key | Entity | Controller | Service | Repository | 
|--- | --- | --- | --- | ---|
| | Customer.java | CustomerController.java | CustomerService.java | CustomerRepository.java |
| | Film.java | FilmController.java | FilmService.java | FilmRepository.java |
| FilmStaffId.java | FilmStaff.java | | | FilmStaffRepository.java |
| | Genre.java | | GenreService.java | GenreRepository.java |
| | Inventory.java | InventoryController.java | InventoryService.java | InventoryRepository.java |
| | Language.java | | LanguageService.java | LanguageRepository.java |
| RentalId.java | Rental.java | RentalController.java | RentalService.java | RentalRepository.java |
| | Role.java | | | |
| | Staff.java | StaffController.java | StaffService.java | StaffRepository.java |
| | Store.java | StoreController.java | StoreService.java | StoreRepository.java |

### Payload folders and classes

| Request | Response |
|--- | ---|
| ActorGenreRequest.java | ActorGenreResponse.java |
| FilmRequest.java | FilmResponse.java |
| RentalRequest.java | |
| | ActorsFilm.java |
| | CustomerStoreResponse.java |
| | FilmMaxRentResponse.java |
| | FilmRentResponse.java |
| | FilmStoreResponse.java |


## FAQs
1. **How to change server port?**
 - Go to src/main/resources/application.properties
 - Set your server port at line 1 (*server.port=* )\
 note: if not set default value is 8080
 

2. **How to connect my own local db?**
 - Go to src/main/resources/application.properties
 - Set your db and port at line 5 (*spring.datasource.url=* )

3. **How to test?**\
On Postman you can set path to method (*localhost:8080/methodName*), http request (*GET, POST, PUT, DELETE*) and variables according to method annotation \
*Example:*\
    @GetMapping("/methodName/{variableName}")
	public ResponseEntity<?> functionName (@PathVariable type variableName){\
    //code block\
    }

    @PutMapping("/methodName/variableName1/variableName2")
	public ResponseEntity<?> functionName (@RequestParam type variableName1, @RequestParam type variableName2){\
    //code block\
    }

    Alternatively you can use swagger by import his dependency on pom.xml file and test by browser

