Backend assesment
-----------------------

The application has 4 commands:
   show:
         ./application 39855 add {"amount":1.23,"description":"Joes Tacos","date":"2018-12-10","user_id":345}


The application has 4 commands

    add: Appends a transaction to the user
       ./application <user_id> add <transaction_json>
        
       Example:
       ./application 39855 add {"amount":1.23,"description":"Joes Tacos","date":"2018-12-10","user_id":345} 
    
    show: Query for a user transaction
        ./application <user_id> <transaction_id>
        
        Example:
        ./application 39855 3c8cb834-f6f7-4612-9ab8-dadd92afd6a9
    
    list: Show all the user transactions
        ./application <user_id> list

         Example:   
         ./application 39855 list 
    
    sum: Sum user transactions
        ./application <user_id> sum
        
        Example:
        ./application 39855 sum
        

Execute the following command to compile and package the project

    ./gradlew build

To run the application test execute:
`./gradlew test`

We can see the coverage reports en $PWD/build/reports/jacoco/test/html/index.html
