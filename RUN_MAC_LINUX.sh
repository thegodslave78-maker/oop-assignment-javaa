#!/usr/bin/env bash
set -e
cd "$(dirname "$0")/src"
javac *.java
java LibraryApp
