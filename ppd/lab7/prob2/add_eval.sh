echo "Compiling solution"
g++ -O2 -std=c++11 -pthread -Dhome -Wall add.cpp -o add.o
dir="tests/"

for i in `seq 10`; do
  python add_gen.py -s small -i $dir$i.in -o $dir$i.ok
  cp $dir$i.in "add.in"
  ./add.o
done

for i in `seq 11 20`; do
  python add_gen.py -s medium -i $dir$i.in -o $dir$i.ok
  cp $dir$i.in "add.in"
  ./add.o
done

for i in `seq 21 30`; do
  python add_gen.py -s large -i $dir$i.in -o $dir$i.ok
  cp $dir$i.in "add.in"
  ./add.o
done

rm add.o
rm add.in
rm add.out
