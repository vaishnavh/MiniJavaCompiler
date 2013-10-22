javac Main.java

java Main < BubbleSort.microIR > BubbleSort.miniRA
echo BubbleSort

java -jar pgi.jar < BubbleSort.microIR > BubbleSort.microIR.text
java -jar kgi.jar < BubbleSort.miniRA > BubbleSort.miniRA.text
DIFF= `diff -B -w -i BubbleSort.microIR.text BubbleSort.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm BubbleSort.microIR.text BubbleSort.miniRA.text
fi

java Main < BinarySearch.microIR >  BinarySearch.miniRA
echo BinarySearch
java -jar pgi.jar < BinarySearch.microIR > BinarySearch.microIR.text
java -jar kgi.jar < BinarySearch.miniRA > BinarySearch.miniRA.text
DIFF= `diff -B -w -i BinarySearch.microIR.text BinarySearch.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm BinarySearch.microIR.text BinarySearch.miniRA.text
fi

java Main < BinaryTree.microIR >  BinaryTree.miniRA
echo BinaryTree
java -jar pgi.jar < BinaryTree.microIR > BinaryTree.microIR.text
java -jar kgi.jar < BinaryTree.miniRA > BinaryTree.miniRA.text
DIFF= `diff -B -w -i BinaryTree.microIR.text BinaryTree.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm BinaryTree.microIR.text BinaryTree.miniRA.text
fi

java Main <  Factorial.microIR > Factorial.miniRA
echo Factorial
java -jar pgi.jar < Factorial.microIR > Factorial.microIR.text
java -jar kgi.jar < Factorial.miniRA > Factorial.miniRA.text
DIFF= `diff -B -w -i Factorial.microIR.text Factorial.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm Factorial.microIR.text Factorial.miniRA.text
fi

java Main < LinearSearch.microIR >  LinearSearch.miniRA
echo LinearSearch
java -jar pgi.jar < LinearSearch.microIR > LinearSearch.microIR.text
java -jar kgi.jar < LinearSearch.miniRA > LinearSearch.miniRA.text
DIFF= `diff -B -w -i LinearSearch.microIR.text LinearSearch.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm LinearSearch.microIR.text LinearSearch.miniRA.text
fi

java Main <  LinkedList.microIR> LinkedList.miniRA 
echo LinkedList

java -jar pgi.jar < LinkedList.microIR > LinkedList.microIR.text
java -jar kgi.jar < LinkedList.miniRA > LinkedList.miniRA.text
DIFF= `diff -B -w -i LinkedList.microIR.text LinkedList.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm LinkedList.microIR.text LinkedList.miniRA.text
fi

java Main < MoreThan4.microIR >  MoreThan4.miniRA

echo MoreThan4
java -jar pgi.jar < MoreThan4.microIR > MoreThan4.microIR.text
java -jar kgi.jar < MoreThan4.miniRA > MoreThan4.miniRA.text
DIFF= `diff -B -w -i MoreThan4.microIR.text MoreThan4.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm MoreThan4.microIR.text MoreThan4.miniRA.text
fi

java Main < QuickSort.microIR> QuickSort.miniRA 
echo QuickSort
java -jar pgi.jar < QuickSort.microIR > QuickSort.microIR.text
java -jar kgi.jar < QuickSort.miniRA > QuickSort.miniRA.text
DIFF= `diff -B -w -i QuickSort.microIR.text QuickSort.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm QuickSort.microIR.text QuickSort.miniRA.text
fi

java Main <  TreeVisitor.microIR> TreeVisitor.miniRA 

echo TreeVisitor
java -jar pgi.jar < TreeVisitor.microIR > TreeVisitor.microIR.text
java -jar kgi.jar < TreeVisitor.miniRA > TreeVisitor.miniRA.text
DIFF= `diff -B -w -i TreeVisitor.microIR.text TreeVisitor.miniRA.text`
if [ "$DIFF" == "" ]
then
	echo Pass
	rm TreeVisitor.microIR.text TreeVisitor.miniRA.text
fi
