#!/bin/bash

TESTS_NUMBER=30
GROWTH_FACTOR=10000

TEST_FILENAME='tests/test'
TEST_EXTENSION='in'
ANSWER_EXTENSION='ok'

random_number () {
	num=`cat /dev/urandom | env LC_CTYPE=C tr -dc '0-9' | fold -w $1 | head -n 1`
	echo $num
}

mkdir -p tests

echo "Generating $TESTS_NUMBER tests..."

for i in `seq 1 $TESTS_NUMBER`;
do
	FILENAME=$TEST_FILENAME$i.$TEST_EXTENSION
	ANSWER_FILENAME=$TEST_FILENAME$i.$ANSWER_EXTENSION
	DIGITS_NBR=`echo $(($i * $GROWTH_FACTOR))`
	echo "Generating test: $FILENAME. The numbers have $DIGITS_NBR digits."
	nbr1=$(random_number $DIGITS_NBR)
	nbr2=$(random_number $DIGITS_NBR)
	echo $nbr1 > $FILENAME
	echo $nbr2 >> $FILENAME
	c="$(BC_LINE_LENGTH=0 bc <<< "$nbr1 * $nbr2" )"
	echo $c | tr -d "[:space:]" | tr -d "\\" > $ANSWER_FILENAME
done

