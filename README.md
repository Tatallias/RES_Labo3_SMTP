# RES_Labo3_SMTP

# Introduction
“RES” is the short name for a bachelor course taught during the 4th semester at the University of Applied Sciences and Arts Western Switzerland. It stands for “Réseaux” (Networks in french), which is not the most appropriate name for it. The course is not really about computer networks. It is about network programming, application-level protocols and infrastructures for network services.

It is mandatory for all students across our four specializations: software engineering, network/systems engineering, security engineering and embedded systems engineering.

In this laboratory, students have to implement an April's Fools joke. The program should send mail pranks to groups of victims from a false sender. The specific requierments are
* The user should be able to define a list of victims (concretely, you should be able to create a file containing a list of e-mail addresses).
* The user should be able to define how many groups of victims should be formed in a given campaign. In every group of victims, there should be 1 sender and at least 2 recipients (i.e. the minimum size for a group is 3).
* The user should be able to define a list of e-mail messages. When a prank is played on a group of victims, then one of these messages should be selected. The mail should be sent to all group recipients, from the address of the group sender. In other words, the recipient victims should be lead to believe that the sender victim has sent them.

# How to use with a mock server and Docker

In order to be able to run our application with a mock server, we recommend that you use MockMock.
MockMock is a free mock server that intercepts smtp packages to install and setup, have a look at the repository :
https://github.com/tweakers/MockMock
After that, you can copy-paste the DockerFile in the root of our program in the folder containing the MockMock.jar file.

After that run the 2 commands :
* docker build -t labo_smtp .
* docker run -it --name mockmock -p 25:25 -p 8282:8282 labo_smtp

When launching docker you can see it's IP address that you'll have to put in the properties file in config/ folder.

And finaly run the program from the root folder of the program with the command
* java -jar ./target/Labo3_SMTP-0.0.1-SNAPSHOT.jar

# What could be improved

We could make the execution more modular adding both login lines in the properties file to ease a launch with mailtrap.io
For now those fields have to be hardcodded in SmtpClient.java
