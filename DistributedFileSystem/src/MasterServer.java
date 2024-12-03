import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class MasterServer extends UnicastRemoteObject implements MasterServerInterface {
    private Map<String, ReplicaLoc> replicaLocations = new HashMap<>();

    public MasterServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerReplicaServer(String name, ReplicaLoc location) throws RemoteException {
        replicaLocations.put(name, location);
        System.out.println("Registered replica: " + name + " at " + location.getHost());
    }

    @Override
    public synchronized List<ReplicaLoc> getReplicaLocations(String fileName) throws RemoteException {
        return new ArrayList<>(replicaLocations.values());
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            MasterServer master = new MasterServer();
            Naming.rebind("MasterServer", master);
            System.out.println("Master Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
