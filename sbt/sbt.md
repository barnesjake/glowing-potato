## SBT

### Compile modules:
Enter interactive mode in sbt: sbt
Change the focus to the module, e.g.: `project func`
Then you can compile: `compile`

or

`sbt service/it:test`

`sbt test:compile`

### Running tests
#### Sbt console:
`testOnly *MySuite -- -z foo`
#### Command line
`sbt 'testOnly *MySuite -- -z foo'`
#### just rerun failed tests - faster
`test:testQuick`

### Test coverage using scoverage
`sbt clean coverage test coverageAggregate`

## Cross build related
`+<task>`
i.e. `+compile` compiles against versions specified in build.sbt (`crossScalaVersions`)
or `+test`

## check local ivy artifacts
`cd ~/.ivy2/local`
