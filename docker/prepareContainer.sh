#!/bin/sh
echo "preparing openjdk container"

mkdir -p ChessBoard/src \
 && cp -r ../../ChessBoard/src/ ChessBoard/src \
 && cp ../pom.xml ChessBoard/

docker build -t chessboard .

rm -rf ChessBoard/
