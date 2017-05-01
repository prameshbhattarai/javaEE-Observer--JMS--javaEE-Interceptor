# javaee-Observer + JMS + javaee-Interceptor

Implementation of __JavaEE-Observer__ with integration of __Java Message Service(JMS)__ and using __Interceptor__ for Persistence Unit        
#### Scenario:             
Client application will invoke a api using POST method, JavaEE-Observer will observe the object          
and insert it into the database and same object is broadcast/sent to Queue resources of JMS.          
By using the Message Listener of JMS, object from Queue resource is listen and Email is          
send to the provide address from client application using Gmail session.                   

#### Requirements:             
Create JDBC Resource as "__jdbc/jpaDatabase1__"            
Create JMS Connection Factories resource as "__jms/__defaultConnectionFactory__"            
Create JMS Destination Resource as "__jms/emailQueue__"
Set Gmail Authentication "__username__" and "__password__" in "__Authentication.properties__" file inside resources
for more detail on Gmail SMTP: [Gmail SMTP](http://mail.google.com/support/bin/answer.py?hl=en&answer=13287)    

#### Client API:          

Request method: __POST method         
Request body:
    `{`
        `"name": "Pramesh Bhattarai",`   
        `"email": "pra4mesh@gmail.com",`   
        `"contact": "9841953894"`   
    `}`        
Response format: __JSON format         
API: (http://localhost:8080/JMS-web/api/callEvent)  