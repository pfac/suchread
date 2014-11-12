SuchRead
========

A simple tracker for the stuff I read.

It is also a proof of concept implementation for a webapp using Spring Security with some specific stuff (like an LDAP server). Because I need to learn this stuff.

## Building the application

``` sh
git clone https://github.com/pfac/suchread.git
cd suchread
mvn clean package install
```

## Running the application

After building the application, there should be a file `target/suchread.war`, which has to be deployed in an application server (tested with [Tomcat 7][1]).

At the moment, authentication is performed against an LDAP server. Change [spring-security.xml](src/main/webapp/WEB-INF/spring-security.xml) with the correct information regarding your target LDAP server.

## Testing the application

After deploying the WAR file to an application server, an *Hello World* page should be acessible through `/suchread/` (if using a local Tomcat, for instance, it would be `http://localhost:8080/suchread/`).

Trying to access the API without authenticating should return `401 Unauthorized`:

```
curl -i -H "Content-Type:application/json" -X GET http://localhost:8080/suchread/api/
```

Authentication can be tested with the following command:

```
curl -i -X POST -d j_username=<username> -d j_password=<password> -c ./cookies.txt http://localhost:8080/suchread/j_spring_security_check
```

It should return `200 OK`, assuming correct credentials are used.

After authenticating, using the saved cookie in a request to the API should result in a `200 OK`:

```
curl -i -H "Content-Type:application/json" -X GET -b ./cookies.txt http://localhost:8080/suchread/api/
```

## License

Project under the [MIT License](http://opensource.org/licenses/MIT). Copyright (c) 2014 [Pedro Costa](https://github.com/pfac)

[1]: http://tomcat.apache.org/