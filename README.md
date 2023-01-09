How to Run ?

Below are the steps you need to perform after you clone the project to your system -

Step 1: Open Command Prompt and Go to Folder Location where repository is cloned

example - cd <project-directory>
  
Step 2: Run below command in command prompt
    
mvn clean install
    
    
Step 3: Now execute jar with below command Example -
    
java -jar LoggerFile-0.0.1-SNAPSHOT.jar E:\logfile.txt

Sample Output will be shown as -

______________________________Result______________________________


Event [index=1, eventId=scsmbstgra, timestampDifference=5, type=APPLICATION_LOG, host=12345, longEventFlag=true]
    
Event [index=2, eventId=scsmbstgrc, timestampDifference=0, type=null, host=null, longEventFlag=false]
    
Event [index=3, eventId=scsmbstgrb, timestampDifference=0, type=null, host=null, longEventFlag=false]
    
Event [index=4, eventId=scsmbstgrc, timestampDifference=8, type=null, host=null, longEventFlag=true]
    
Event [index=5, eventId=scsmbstgrb, timestampDifference=3, type=null, host=null, longEventFlag=false]
    
Event [index=6, eventId=scsmbstgra, timestampDifference=0, type=APPLICATION_LOG, host=12345, longEventFlag=false]
    


___________________________________________________________________
