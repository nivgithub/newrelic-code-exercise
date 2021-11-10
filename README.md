## Objective

Please create a program executable from the command line that when given text(s) will return 100 of most common three word sequences in descending order of frequency.

## Installation and Running Application

This project utilizes [Gradle](https://gradle.org/) for convenience in building and running the application. Additionally, the [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) (`gradlew`) will handle downloading the Gradle runtime, so you won't need to install Gradle ahead of time.

### Prerequisites

Java JDK or JRE version 11 or higher must be installed. To check, run `java -version`:
Please check java version by running `java -version`
```bash
$ java -version
openjdk version "11" 2018-09-25

```

### Build Project

```bash

# local setup
 Extract the given zip into your local using the below command in your gitbash.
 unzip newrelic-code-exercise.zip -d newrelic-code-exercise


# Build Application 
cd newrelic-code-exercise
./gradlew build
```

### Running the application with `gradlew run` ( preferred method )

```bash
# Runs the main method from App.java with the supplied file
./gradlew run --args='moby-dick.txt'

```

Output:

```bash
Enter the file name to be processed:
moby-dick.txt
Given file name is:moby-dick.txt
----------------------------------------
of the whale                    93
the sperm whale                 89
the white whale                 76
one of the                      64
of the sea                      60
out of the                      58
a sort of                       53
part of the                     53
project gutenberg tm            45
of the sperm                    43
it was a                        33
in the sea                      32
it is a                         30
of the ship                     30
for a moment                    29
of the boat                     29
the sperm whale's               28
the right whale                 27
to the deck                     27
the sea and                     26
by no means                     25
in order to                     25
for the time                    25
at the same                     25
the same time                   24
so as to                        24
to be the                       24
the quarter deck                24
in the air                      24
the bottom of                   24
must have been                  23
the project gutenberg           23
on the sea                      23
that in the                     23
of the pequod                   23
out of sight                    22
there is no                     22
it was that                     22
into the sea                    22
now and then                    22
at the time                     22
in the fishery                  22
it was the                      21
there was a                     21
the whale and                   21
as it were                      20
it was not                      20
and in the                      20
down into the                   19
into the air                    19
as if it                        19
up to the                       19
and all the                     19
and at the                      19
as well as                      19
so that the                     19
one of those                    19
of the whale's                  19
the old man                     18
it is that                      18
the whale is                    18
end of the                      18
bottom of the                   18
in his own                      18
it is not                       18
i do not                        18
of a whale                      18
and with a                      18
down to the                     17
the pequod was                  17
over the side                   17
down in the                     17
the ship and                    17
the mast head                   17
round and round                 17
of the white                    17
to and fro                      17
but it was                      17
you would have                  16
the old man's                   16
of the world                    16
the head of                     16
to be sure                      16
all the time                    16
whale and the                   16
of his head                     15
there she blows                 15
in the same                     15
side of the                     15
the act of                      15
some of the                     15
more and more                   15
he was a                        15
of the great                    15
to be a                         15
on the other                    15
of the leviathan                15
him in the                      15
it is the                       15
gutenberg tm electronic         15
----------------------------------------
Time taken : 654358300 nano seconds
Time taken : 0 seconds
```


## Running Tests

```bash
cd newrelic-code-exercise

./gradlew test

```

## What you do if you have given more time

I would look for more different ways to implement the solution.
I would like to work on containerization.

## What are the potential bugs

The input file provided moby-dick.txt has a different apostrophe (’) which I replaced with (').

## Notes
The program is taking around 600 milliseconds in our computer.