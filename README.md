SuchRead
========

[![Build Status](https://travis-ci.org/pfac/suchread.svg?branch=master)](https://travis-ci.org/pfac/suchread)
[![Dependency Status](https://www.versioneye.com/user/projects/54b58815050646ca5c0000ac/badge.svg?style=flat)](https://www.versioneye.com/user/projects/54b58815050646ca5c0000ac)

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

### Authenticating with cookie

Authentication can be tested with the following command:

```
curl -i -X POST -d j_username=<username> -d j_password=<password> -c ./cookies.txt http://localhost:8080/suchread/j_spring_security_check
```

It should return `200 OK`, assuming correct credentials are used.

After authenticating, using the saved cookie in a request to the API should result in a `200 OK`:

```
curl -i -H "Content-Type:application/json" -X GET -b ./cookies.txt http://localhost:8080/suchread/api/
```

### Authenticating with token

If the credentials are correct, the system generates an **authentication token** and adds it to the response as a custom header `X-Auth-Token`, along with the username as the custom header `X-Username`:

```
$ curl -i -X POST -d j_username=pfac -d j_password=secret http://localhost:8080/suchread/j_spring_security_check
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
X-Username: pfac
X-Auth-Token: DLwIe164b6nbPlrIHNYgYYxNTo6mJhEacaurP0chvnjSR9hlh2
Set-Cookie: JSESSIONID=AEB71995D38F9E998CDCE3B7B36DA0B6; Path=/suchread/; HttpOnly
Content-Length: 0
Date: Fri, 14 Nov 2014 18:17:17 GMT

```

You can also use these tokens to authenticate yourself with the API:

```
 $ curl -i -H "Content-Type:application/json" -H "X-Username:pfac" -H "X-Auth-Token:DLwIe164b6nbPlrIHNYgYYxNTo6mJhEacaurP0chvnjSR9hlh2" -X GET http://localhost:8080/suchread/api/
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Set-Cookie: JSESSIONID=C95171A0E4675B91C3F3DFF5254B9EF6; Path=/suchread/; HttpOnly
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Fri, 14 Nov 2014 18:17:40 GMT

{"name":"World","message":"Hello World!"}
```

Note that, at the moment, tokens do not expire, but are only persisted in memory. So they will only be valid until the next restart of the application.

## License

Project under the [MIT License](http://opensource.org/licenses/MIT). Copyright (c) 2014 [Pedro Costa](https://github.com/pfac)

[1]: http://tomcat.apache.org/