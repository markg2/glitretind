*******************************************************************************
CURL - LOCALHOST
*******************************************************************************

 curl -i -H "Accept: application/json" -X GET http://localhost:8080/readinglist/resource/collections
 curl -i -H "Accept: application/json" -X GET http://localhost:8080/readinglist/resource/collections/d0725f66-c6ef-4b61-84e9-3a7b4a0920a7
 curl -i -H "Accept: application/json" -X GET http://localhost:8080/readinglist/resource/books/



*******************************************************************************
CURL - AWS Elastic Beanstalk
*******************************************************************************

http://myfirstelasticbeans-env6426.elasticbeanstalk.com/

curl -i -H "Accept: application/json" -X GET http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections
[
    {
        "_id": "55278b76-648e-4481-ba59-c8b5ec7db0fb",
        "count": 9,
        "name": "In Death",
        "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/55278b76-648e-4481-ba59-c8b5ec7db0fb"
    },
    {
        "_id": "dd18dc46-e3cf-45ef-ac2c-c569e13574c2",
        "count": 6,
        "name": "Once Upon A",
        "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
    }
]


curl -i -H "Accept: application/json" -X GET http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2
{
    "_id": "dd18dc46-e3cf-45ef-ac2c-c569e13574c2",
    "count": 6,
    "name": "Once Upon A",
    "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2",
    "books": [
        {
            "_id": "5f3f0f8e-2c16-49a9-a9d0-82b32c38e2bc",
            "name": "Once Upon a Kiss",
            "itemId": "0515133868",
            "state": "own",
            "found": "",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "2002",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/5f3f0f8e-2c16-49a9-a9d0-82b32c38e2bc",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        },
        {
            "_id": "b3201eb3-3f03-4e7d-b384-251fd0289bf0",
            "name": "Once Upon a Dream",
            "itemId": "051512947X",
            "state": "own",
            "found": "",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "2000",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/b3201eb3-3f03-4e7d-b384-251fd0289bf0",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        },
        {
            "_id": "bba302d9-59c5-41d4-9cfa-297a74118033",
            "name": "Once Upon a Star",
            "itemId": "0515127000",
            "state": "own",
            "found": "",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "1999",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/bba302d9-59c5-41d4-9cfa-297a74118033",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        },
        {
            "_id": "152c5577-4e66-40a4-ac20-59026979ec8e",
            "name": "Once Upon a Castle",
            "itemId": "0515122416",
            "state": "own",
            "found": "",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "1998",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/152c5577-4e66-40a4-ac20-59026979ec8e",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        },
        {
            "_id": "cebeba20-ad9b-4f93-a1dc-2422c6993c0b",
            "name": "Once Upon A Midnight",
            "itemId": "0515136190",
            "state": "want",
            "found": "false",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "2003",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/cebeba20-ad9b-4f93-a1dc-2422c6993c0b",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        },
        {
            "_id": "4729632b-e8e9-44af-8ed2-bce253636751",
            "name": "Once Upon A Rose",
            "itemId": "0515131660",
            "state": "want",
            "found": "false",
            "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
            "author": "Nora Roberts",
            "sequence": "2001",
            "privacy": "public",
            "manufacturer": "Jove",
            "format": "pocket",
            "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/4729632b-e8e9-44af-8ed2-bce253636751",
            "collections": [
                "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
            ]
        }
    ]
}


curl -i -H "Accept: application/json" -X GET http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/4729632b-e8e9-44af-8ed2-bce253636751
{
    "_id": "4729632b-e8e9-44af-8ed2-bce253636751",
    "name": "Once Upon A Rose",
    "itemId": "0515131660",
    "state": "want",
    "found": "false",
    "user": "3a40d07c-e7a0-40e7-ab64-81038364a10d",
    "author": "Nora Roberts",
    "sequence": "2001",
    "privacy": "public",
    "manufacturer": "Jove",
    "format": "pocket",
    "href": "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/books/4729632b-e8e9-44af-8ed2-bce253636751",
    "collections": [
        "http://myfirstelasticbeans-env6426.elasticbeanstalk.com/resource/collections/dd18dc46-e3cf-45ef-ac2c-c569e13574c2"
    ]
}


