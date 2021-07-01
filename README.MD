# Elementary Cellular Automaton in Java

The code contains an implementation in Java for an elementary cellular automaton. An elementary cellular automaton consists of 1-dimensional cells that can either take a state of 0 or 1. To determine the state of the cell in the next time step, the cell's current state, and the states of the two adjacent cells, will be used. For our implementation, we utilized rule 182 which consists of the following mappings:

| Old States | 111 | 110 | 101 | 100 | 011 | 010 | 001 | 000 |
|------------|-----|-----|-----|-----|-----|-----|-----|-----|
| New State  | 1   | 0   | 1   | 1   | 0   | 1   | 1   | 0   |

The initial state of the automaton in the implementation is where the center cell is set to 1 while the rest are zeroes.

## Compiling and Running the Application
To run application, a JDK must be installed. The application was developed using JDK 16. The main entry point of the application is `Main.java`
1. Download or create a clone of the repository
2. Open Command Prompt
3. Navigate to the project folder
4. Compile the java files using the command `javac Main.java`
5. Run the application using the command `java Main`. After running the application, a window should appear showing an infinite simulation of an elementary automaton changing states using rule 182.
6. To stop the simulation, simple close the window
