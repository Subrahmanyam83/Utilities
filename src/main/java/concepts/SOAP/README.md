https://dzone.com/articles/simple-java-soap-web-service-using-jdk-tools

1. Run the service by running the command from the folder: wsserver
``` 
java ServiceStarter
```
2. This will run the service and check the wsdl at this path: ``` http://localhost:1212/hello?wsdl ```

3. From the wsdl create a service which will help creating a client to access the service using the command:
``` wsimport -d . -p wsclient -keep http://localhost:1212/hello?wsdl
```

4. Create a HelloClient to test the webservice
``` 
java HelloClient
```