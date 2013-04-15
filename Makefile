wc:
	cat `find . -name "*.java"|grep -v parser`|wc -l
