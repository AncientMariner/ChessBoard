#!/bin/sh
echo "preparing openjdk container"

mkdir ChessBoard/ \
 && cp -r ../../ChessBoard/src/ ChessBoard/ \
 && cp ../pom.xml ChessBoard/

docker build -t chessboard .

rm -rf ChessBoard/
