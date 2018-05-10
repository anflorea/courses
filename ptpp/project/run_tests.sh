#!/bin/bash

TEST_FILENAME='tests/test'
TEST_FOLDER='tests/'
TEST_EXTENSION='in'
ANSWER_EXTENSION='ok'
OUTPUT_EXTENSION='out'

rm iterative
rm parallel

echo "Compiling iterative implementation"
g++ -o iterative iterative.cpp
echo "Compiling parallel implementation"
g++ -lpthread -o parallel parallel.cpp

tests=`ls $TEST_FOLDER | grep .$TEST_EXTENSION`

echo "Testing iterative implementation...\n"

for file in $tests;
do
	name=`echo "$file" | cut -f 1 -d '.'`
	TIME_S=`TIMEFORMAT=%R bash -c "time ./iterative $TEST_FOLDER$file $name.$OUTPUT_EXTENSION"`
	DIFF=`diff $TEST_FOLDER$name.$ANSWER_EXTENSION $name.$OUTPUT_EXTENSION`
	echo "Test: $file"
	echo $TIME_S
	if [ $DIFF ]
	then
		echo "ERROR!"
	fi
done

echo "Testing parallel implementation...\n"

for file in $tests;
do
	name=`echo "$file" | cut -f 1 -d '.'`
	TIME_S=`TIMEFORMAT=%R bash -c "time ./parallel $TEST_FOLDER$file $name.$OUTPUT_EXTENSION"`
	DIFF=`diff $TEST_FOLDER$name.$ANSWER_EXTENSION $name.$OUTPUT_EXTENSION`
	echo "Test: $file"
	echo $TIME_S
	if [ $DIFF ]
	then
		echo "ERROR!"
	fi
done

