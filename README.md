# Sample Springboot AWS DynamoDB

<p>I am documenting samples on my Github because over the years I have learned many new things but have not documented them on my github, I believe this is important, that is why I made this sample.</P>

## This is a sample project. 
<p>I developed this project to test the integration of a springboot project with AWS DynamoDB.</p>
<p>Additional features were implemented for this project, such as: Integrated and conternalized testing, exception handling and Clean Code.</P>

### Notes
<p>The <strong><a href="https://github.com/JesusRuescas/sample-springboot-aws-dynamodb/blob/main/src/main/resources/META-INF/spring-devtools.properties">spring-devtools.properties</a></strong> file was created because the Spring Boot DevTools restart functionality is implemented using two classloaders. The project is loaded by the restart classloader and the libraries are loaded by the base classloader.</P>

The property ```restart.include.dynamodb=/dynamodb-[\\w\\d-\.]+\.jar``` to move DynamoDB jars into the RestartClassloader.
