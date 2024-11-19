import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class P1 {
    public static void main(String args[]) throws UnknownHostException {
        InetAddress addr;
        String host  = "naflows.com";
        try {
            addr = InetAddress.getByName(host);
            if (addr.isReachable(1000)) {
                System.out.println(String.format("Node %s is reachable!",host));
            }
        }
        catch(UnknownHostException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
