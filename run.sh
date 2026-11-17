#!/bin/bash
# Usage: ./run.sh 1   (Linux/Mac equivalent of run.bat)
N=$(printf "%02d" "$1")
for D in Exp${N}_*/; do
  D=${D%/}
  mkdir -p out/$D
  javac -cp "lib/*" -d out/$D $D/*.java && java -cp "out/$D:lib/*" Main
done
