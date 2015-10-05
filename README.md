# mp2

## Setup for IntelliJ
1. At main menu, choose Check out from Version Control and use this project url.
2. Add JDK 1.7 as the SDK.
3. Compile the project and run the tests - you'll see errors about Junit not being there.
4. Option enter on an @Test annotation in a test class and choose add Junit to your path. (Alternatively: go to File > Project Structure and dig through your IntelliJ dependencies to find junit-4.11.jar, junit.jar, and hamcrest-core*.jar... more annoying)
5. Rerun the tests... they should work now.

## Setup for Eclipse
1. Right click in project area on left side, choose Import, and Git > Projects from Git.
2. Clone URI from Github.
3. Right click on project > Build path > Configure build path.
4. On right side of Java Build Path window, choose Add Library and add JUnit.
5. Run the tests... they should work.
