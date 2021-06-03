package lab5.sectionb02.id7876069.entity;
import lab5.sectionb02.id7876069.logic.NetworkSimulator;
public class Entity2 extends Entity
{    
    // Perform any necessary initialization in the constructor
    public Entity2()
    {
        //inititalize distance table of node with value of each = inf
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                distanceTable[i][j] = 999;
            }
        }
        
        //hold and send shortest path cost to layer 2
        int[] minPath = new int[4];
        
        //distance table in node 1
        distanceTable[0][0]=3;
        distanceTable[1][1]=1;
        distanceTable[2][2]=0;
        distanceTable[3][3]=2;
        
        for(int d = 0; d < 4; d++){
            //int a = Math.min(distanceTable[d][0], distanceTable[d][1]);
            //minPath[d] = Math.min(a, distanceTable[d][3]);
            minPath[d] = distanceTable[d][d];

        }

        //minPath[0] = distanceTable[0][0];
        //System.out.print(minPath[0]); 

        //Sends the packet initial cost value to each other node.  
        //avoid sending the packet to Entity2.
        for(int i = 0; i < 4; i++){
            if(i != 2){
                Packet PacketDT = new Packet(2, i, minPath);
                NetworkSimulator.toLayer2(PacketDT);
            }
        }
        
        

    }
    
    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {
        //keep track with shortest path costs, if nothing change we will not send
        //out packet. We inititalize this as false since at the beginning 
        //nothing has changed yet
        boolean send = false;
        
        //hold and send shortest path cost
        int[] minPath = new int[4];
        
        //calculate the min cost to each node and put back to the
        //list of minPath. Compared distance d to node 0 and 1 (result in a), 
        //then compared distance d to node 2 and 3 (result in b), 
        //then compared a and b which ever smaller get update to minPath
        for(int d = 0; d < 4; d++){
            int a = Math.min(distanceTable[d][0], distanceTable[d][1]);
            int b = Math.min(distanceTable[d][2], distanceTable[d][3]);
            minPath[d] = Math.min(a,b);
        }
        
       
        //getSource: Returns: The object on which the Event initially occurred.

        //Compare the value between the cost of getting to each node in the distance table 
        //through source p packet, with the value of min cost given by p + and min path 
        //in distance table
        
        for(int i = 0; i<4; i++){
            if(p.getMincost(i)+ minPath[p.getSource()] < distanceTable[i][p.getSource()]){
                //if the sum is smaller, update the distance table to that smaller value
                distanceTable[i][p.getSource()] = p.getMincost(i)+minPath[p.getSource()];
                //double checks to see if the updated value is the new shortest path
                //if the min value got update, means something changed then now "send" is true 
                if(distanceTable[i][p.getSource()]<minPath[i]){
                    minPath[i] = distanceTable[i][p.getSource()];
                    send = true;
                }
            }
        }
        
        //if the min cost has updated, it sends update packets to other node.
        if(send){
            for(int i = 0; i < 4; i++){
                if(i != 2){
                    Packet PacketDT = new Packet(2, i, minPath);
                    NetworkSimulator.toLayer2(PacketDT);
                }
            }
        }
        
        printDT();
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    }
    
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D2 |   0   1   3");
        System.out.println("----+------------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 2)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
            {
                if (j == 2)
                {
                    continue;
                }
                
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else 
                {
                    System.out.print(" ");
                }
                
                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
