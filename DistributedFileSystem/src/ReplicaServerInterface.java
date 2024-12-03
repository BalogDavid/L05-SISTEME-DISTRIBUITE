import java.rmi.*;

public interface ReplicaServerInterface extends Remote {
    void write(String fileName, String data) throws RemoteException;
    String read(String fileName) throws RemoteException;
}
