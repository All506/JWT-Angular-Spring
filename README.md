# Angular handling requests with token to a Springboot API

Once the user has been logged and validated by the backend, it will have a valid token that will use for one hour minimum. If this token expires, the user must login again as the neither the backend will process his requests and the frontend will not show him protected content.
