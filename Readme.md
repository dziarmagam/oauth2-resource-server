#OAuth 2 resource server example

To run keycloack instance using docker use command:

`docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8080:8080 jboss/keycloak`

To log in to keycloack use login 'admin' and password 'admin'

Create new realm 'Test' and import client and roles using `realm-export.json`.

Users cannot be exported so they need to be added manually. Remember to add permissions for users.

It is recommended to regenerate client secret.

####Note

This example contain minimal amount of additional frameworks and API in order
to keep the code as simple as possible and demonstrate how HTTP communications looks like in OAuth2.

##Configuration description:
    oauth2.rsa.public.key.base64 - RSA256 public key encoded in base64.

