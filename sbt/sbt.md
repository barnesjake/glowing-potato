## SBT

Compile modules:
Enter interactive mode in sbt: sbt
Change the focus to the module, e.g.: project func
Then you can compile: compile

`or`

sbt service/it:test

sbt test:compile

#### Sbt console:
testOnly *MySuite -- -z foo
#### Command line
sbt 'testOnly *MySuite -- -z foo'
