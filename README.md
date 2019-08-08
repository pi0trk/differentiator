# differentiator 

There are various people and not everyone is honest. Some would pretend a file is a JPG, while in fact, it's a dangerous, malicious binary.
Program should check and prevent that from happening!

1. Program accept all extensions. No matter the extension, attempt at reading will be done.
2. Program verifies "magic numbers".
3. Program must say if the extension is true (a txt file is a text file, an JPG is a JPG).
4. If extension isn't handled, program honestly states so (throwing an appropriate exception, one that fits this situation).
5. We expect JPG, GIF and TXT to be handled. Anything more is a plus.
6. If extension lies, program states: Extension is A, while actually it's a B.

Example how to run program:
    mvn clean package -DskipTests
    java -jar target/differentiator-0.2.jar src/test/resources/file/notagif.gif