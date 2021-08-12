# tech_test
patrick prunty tech test

to run the application run "mvn install" on the project then run the command "java -jar project-0.0.1-SNAPSHOT.jar" from the target directory 

there is a postman collection with all the rest endpoints included in the repository called "Patrick Prunty.postman_collection.json"
Here are the endpoints listed below and how to call them
Thanks

## endpoints

#### create person
 METHOD:PUT 
 ENDPOINT:localhost:8080/person/create
 BODY:

{"Id":1,
"FirstName":"patrick",
"LastName":"prunty"}

#### update person
METHOD:POST 
ENDPOINT:localhost:8080/person/update
BODY:

{"Id":1,
"FirstName":"patrick",
"LastName":"prunty"}

#### delete person
METHOD:DELETE 
ENDPOINT:localhost:8080/person/delete/{id}

#### get person list
METHOD:GET 
ENDPOINT:localhost:8080/person/getlist

#### get person count
METHOD:GET 
ENDPOINT:localhost:8080/person/count


#### create address
METHOD:PUT 
localhost:8080/address/create
BODY:

{"Id":1,
"Street":"a street",
"City":"a city",
"State":"a State",
"PostalCode":"000000",
"PersonId":1}

#### get address
METHOD:GET 
localhost:8080/address/get/{id}

#### update address
METHOD:POST 
ENDPOINT:localhost:8080/address/update
BODY:

{"Id":1,
"Street":"a street",
"City":"a city",
"State":"a State",
"PostalCode":"000000",
"PersonId":1}

#### delete address
METHOD:DELETE 
ENDPOINT:localhost:8080/address/delete/{id}


