#!/usr/bin/env bash

# Add script to:
# * Install dependencies
# * Build/Compile
# * Run Test Suit to validate
#
# After this is run, bin/parking_lot
# should Just Work.

#compiling project
mvn compile

#running test cases
mvn test

#build the project
mvn clean package