# LogAnalysisProject
Log Events Analysis

Our custom-build server logs different events to a file named logfile.txt. Every event has 2 entries in the file
- one entry when the event was started and another when the event was finished. The entries in the file
have no specific order (a finish event could occur before a start event for a given id)
Every line in the file is a JSON object containing the following event data:
 id - the unique event identifier
 state - whether the event was started or finished (can have values "STARTED" or "FINISHED"
 timestamp - the timestamp of the event in milliseconds
Application Server logs also have the following additional attributes:
 type - type of log
 host - hostname

* Take the path to logfile.txt as an input argument
* Parse the contents of logfile.txt
* Flag any long events that take longer than 4ms
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
* The application should create a new table if necessary and store the following values:
* Event id
* Event duration
* Type and Host if applicable
* Alert (true if the event took longer than 4ms, otherwise false)


## How to Run the program

The Application analysis the logs events from log file.
- Log File Location :- 
classpath:resources/Input
```
We need to pass the value of log file in args which running the application from gradle
```

Below are the steps
- Step 1: Clone the repository 
```
 git clone https://github.com/ishantkul86/LogAnalysisProject.git
```

- Step 2: Run below command to clean and build project
```
./gradlew clean build
```

- Step 3: Now execute the application from gradle

```
./gradlew bootRun --args=Input/LogFile.txt 
```
