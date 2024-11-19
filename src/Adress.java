import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Adress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress a = InetAddress.getByName("localhost");
        System.out.println(a);

        InetAddress[] u = InetAddress.getAllByName("univ-jfc.fr");
        System.out.println(Arrays.toString(u));

        InetAddress[] m = InetAddress.getAllByName("scout.univ-toulouse.fr");
        System.out.println(Arrays.toString(m));
    }
}
