all: tp

tp:
	javac compnaturaltp1/*.java genetictree/*.java genetictree/functions/*.java genetictree/terminals/*.java
	
run: tp
	java compnaturaltp1/CompNaturalTP1
