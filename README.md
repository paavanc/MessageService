# MessageService

Running Locally:
Pre-req: Java8 and Maven3
Installation for mac:
1.Install HomeBrew: /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
2.Install Java:
  a.brew tap caskroom/versions
  b.brew cask install java8
3.Install Maven: brew install maven
In the terminal, run the following command in the MessageService directory.
1. mvn spring-boot:run
-the service should be running on port:9080
2.Run tests: mvn package
-will run the tests and package the project into a jar
-if one cds into MessageService/target, one can also run the jar to start the service
    -java -jar service-0.0.1-SNAPSHOT.jar

Sample POST:
curl -X POST \
  http://localhost:9080/message \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 9147477c-4011-fb97-9254-c9a210967868' \
  -d '{
	"message" : "foo"
}'

Sample GET:
curl -X GET \
  http://localhost:9080/message/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 3fcf4b91-b9de-baae-a912-0be710b47337' 

Server:
The restful service is hosted on this url: https://evening-ravine-56999.herokuapp.com. 
Note that since this url is hosted on a free Heroku service there may be some intial latency when one first hits the API.

Sample POST:
curl -X POST \
  https://evening-ravine-56999.herokuapp.com/message \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a31cc6e7-af85-9580-5c17-0ce5eca469f5' \
  -d '{
	"message" : "foo"
}'

Sample GET:
curl -X GET \
  https://evening-ravine-56999.herokuapp.com/message/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: e734415a-3353-aa36-f8d1-2284f09eec42' 


Performance:
Currently we are running one instance of the app in Heroku.  The app relies on an internal memory key value store (Concurrent HashMap).  
The look up time for finding a message is O(1) with the key being the hash.  However, there are pitfalls with this approach.  
The in memory database goes down(clears out) everytime the service goes down or is put to sleep by Heroku.  

If I was building this app to scale, I would probably set up a Redis Database and allocate more nodes to the service.  
I would also increase the number of replicas on each on node depending on the traffic.  I would use tools such as Kubernetes and AWS
in order to achieve this. I would also probably need to set a limit on the size of the hash, as longer keys may cause look up perfromance issues, 
as more keys get added to the Reddis Database.
