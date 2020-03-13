Jagger:
	java -cp ./javacc.jar javacc Jagger.jj
	javac *.java
	java Jagger

check:
	java -cp ./javacc.jar javacc Jagger.jj
	javac *.java
check_OK: check
	java Jagger < test_OK.txt
check_failtype: check
	java Jagger < test_fail.txt
check_failvar: check
	java Jagger < test_var.txt
