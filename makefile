all:
	java -cp ./javacc.jar javacc Jagger.jj
	javac *.java

check: all
	java  Jagger ./check.txt

fail: all
	java Jagger ./fail1.txt
	java Jagger ./fail2.txt
	java Jagger ./fail3.txt

clean:
	rm Jagger.java JaggerTokenManager.java Token.java TokenMgrError.java JaggerConstants.java ParseException.java SimpleCharStream.java
	rm *.class
