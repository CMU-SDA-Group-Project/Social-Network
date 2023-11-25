# Social Network

## Prequiste:

> Redis: 
> 
> + port:6379
> 
> Kafka:
> 
> + use docker compose to set up
> 
> + ```bash
>   docker-compose up
>   ```
> 
> Neo4j:
> 
> + port:7687

This project is used to provide a set of APIs that are used for Social Network

The APIs are listed below:

+ /social/createUser: POST
  
  + request body: 
    
    {  
      "name": "String",  
      "password": "String",  
      "role": "String"  
    }

+ /social/deleteUser: Post
  
  + request body:
    
    {  
      "name": "String"  
    }

+ /social/addFriend: Post
  
  + request body:
    
    {  
      "userName": "String",  
      "friendName": "String"  
    }

+ /social/removeFriend: Post
  
  + request body:
    
    {  
      "userName": "String",  
      "friendName": "String"  
    }

+ /social/getFriends: Post
  
  + request body:
    
    {  
      "name": "String"  
    }

+ /social/addCredit: Post
  
  + request body:
    
    {  
      "name": "String",  
      "credit": 0  
    }

+ /social/getCredit: Post
  
  + request body:
    
    {  
      "name": "String"  
    }

+ /social/getTopList: Get


