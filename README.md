# Distance Vector Routing Algorithm

Writing a "distributed" set of procedures that implement a distributed asynchronous distance vector routing for the network shown in below figure.

![image](https://user-images.githubusercontent.com/73355680/120581087-d4386d80-c3ef-11eb-9508-785e62784dd1.png)

# Instructions

The routines you will write: For the basic part of the assignment, you are to write the following routines which will “execute” asynchronously within the emulated environment that we have written for this assignment. There is an abstract Entity class which is extended by Entity0, Entity1, Entity2 and Entity3 class.  

For node 0 (Entity0), you have to implement:

- Entity0() This routine will be called once at the beginning of the emulation. Entity0() has no arguments. It should initialize the distance table in node 0 to reflect the direct costs of 1, 3, and 7 to nodes 1, 2, and 3, respectively. In Figure 1, all links are bi-directional and the costs in both directions are identical. After initializing the distance table, and any other data structures needed by your node 0 routines, it should then send its directly-connected neighbors (in this case, 1, 2 and 3) the cost of it minimum cost paths to all other network nodes. This minimum cost information is sent to neighboring nodes in a routing packet by calling the routine toLayer2(), as described below. The format of the routing packet is also described below.

- update (Packet p) in Entity0 class. This method will be called when node 0 receives a routing packet that was sent to it by one if its directly connected neighbors. The parameter p is an object of the Packet class that was received. This update method is the "heart" of the distance vector algorithm. The values it receives in a routing packet from some other node i contain i’s current shortest path costs to all other network nodes. update uses these received values to update its own distance table (as specified by the distance vector algorithm). If its own minimum cost to another node changes as a result of the update, node 0 informs its directly connected neighbors of this change in minimum cost by sending them a routing packet. Recall that in the distance vector algorithm, only directly connected nodes will exchange routing packets. Thus nodes 1 and 2 will communicate with each other, but nodes 1 and 3 will node communicate with each other.

The distance table inside each node is the principal data structure used by the distance vector algorithm. You will find it convenient to declare the distance table as a 4-by-4 array of int's, where entry [i,j] in the distance table in node 0 is node 0's currently computed cost to node i via direct neighbor j. If 0 is not directly connected to j, you can ignore this entry. We will use the convention that the integer value 999 is "infinity" Figure 2 provides a conceptual view of the relationship of the procedures inside node 0.

Similar routines are defined for nodes 1, 2 and 3. Thus, you will write 8 procedures in all: Entity0(), Entity1(), Entity2(), Entity3(), update().

 ![image](https://user-images.githubusercontent.com/73355680/120581321-3f823f80-c3f0-11eb-85d9-864b0bc16eff.png)

# The Simulated Network Environment

Your constructor and update methods of each node send routing packets (whose format is described above) into the medium. The medium will deliver packets in-order, and without loss to the specified destination. Only directly-connected nodes can communicate. The delay between is sender and receiver is variable (and unknown).

When you compile your procedures and my procedures together and run the resulting program, you will be asked to specify only one value regarding the simulated network environment:

- Tracing. Setting a tracing value of 1 or 2 will print out useful information about what is going on inside the emulation (e.g., what's happening to packets and timers). A tracing value of 0 will turn this off. A tracing value greater than 2 will display all sorts of odd messages that are for my own emulator-debugging purposes.

A tracing value of 2 may be helpful to you in debugging your code. You should keep in mind that real implementers do not have underlying networks that provide such nice information about what is going to happen to their packets!

# Tasks

You have to implement all the constructors and update methods of Entity0, Entity1, Entity2, Entity3 which together will implement a distributed, asynchronous computation of the distance tables for the topology and costs shown in Figure 1.

Work on the Entity0, Entity1, Entity2 and Entity3 classes. You are NOT allowed to declare any global variables that are visible outside of a class (e.g., any global variables you define in Entity0 class may only be accessible within that class). This is to force you to abide by the coding conventions that you would have to adopt is you were really running the procedures in four distinct nodes. Each node has it’s own routing table which cannot be seen by other nodes.

For your sample output, your procedures should print out a message whenever your implemented methods are called, giving the time (available via my global variable clock time). For update() method you should print the identity of the sender of the routing packet that is being passed to your routine, whether or not the distance table is updated, the contents of the distance table (you can use my printDT() method), and a description of any messages sent to neighboring nodes as a result of any distance table updates. Highlight the final distance table produced in each node. Your program will run until there are no more routing packets in-transit in the network, at which point our emulator will terminate.


