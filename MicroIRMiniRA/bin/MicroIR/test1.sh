javac Main.java
for i in {1..9}
do

echo test$i
java Main <test0$i.microIR>test0$i.miniRA
java -jar kgi.jar < test0$i.miniRA > out.miniRA.text
java -jar pgi.jar <test0$i.microIR>test0$i.out
DIFF= `diff -B -w -i out.miniRA.text test0$i.out`
if [ "$DIFF" == "" ]
then
	echo Pass
fi
done
for i in {10..23}
do
echo test$i
java Main <test$i.microIR>test$i.miniRA
java -jar kgi.jar < test$i.miniRA > out.miniRA.text
java -jar pgi.jar <test$i.microIR>test$i.out
DIFF= `diff -B -w -i out.miniRA.text test$i.out`
if [ "$DIFF" == "" ]
then
	echo Pass
fi
done
