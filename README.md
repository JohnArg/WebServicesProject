# Web Services Project

## JAX-WS and REST (Jersey)

---

This is a university (master's) project to learn about web services. The first part of the project was to implement a simple calculator service that will support addition, subtraction, multiplication and division of two real numbers, using JAX-WS. This part was implemented both with Bottom Up and Top Down approaches. The second part is a URL shortener REST application using Jersey, that shortens URLs, by simply assigning incremented integer ids. The operations supported are outlined below.

All web services were tested on Tomcat 9. 

For the calculator services, a corresponding client was written in Java, that uses the *connection.properties* file to read the URL of the service to connect to. It then asks the user to select one of the supported actions (addition, subtraction, ...etc) and provide 2 real numbers. Then it invokes functions of the calculator service and displays the results.

In the REST web service the supported methods are specifically :

* GET "/" : all keys are returned
* GET "/:id" the value (URL) corresponding to this id
* PUT "/:id" changes the value (URL) corresponding to this id
* POST "/" inserts a new URL
* DELETE "/" deletes everything

In PUT and POST the body of the request is a JSON object like : {"url" : "http://www.oracle.com"}
