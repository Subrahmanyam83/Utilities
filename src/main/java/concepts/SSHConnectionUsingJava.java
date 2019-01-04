package concepts;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnectionUsingJava {

    public static void main(String args[]){
        try {
            JSch jsch = new JSch();

            String user = "n471306";
            String host = "capdev603.uk.ba.com";
            int port = 22;
            String privateKey = "/Users/n471306/.ssh/id_rsa";

            jsch.addIdentity(privateKey);
            System.out.println("identity added ");

            Session session = jsch.getSession(user, host, port);
            System.out.println("session created.");

            // disabling StrictHostKeyChecking may help to make connection but makes it insecure
            // see http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
            //
             java.util.Properties config = new java.util.Properties();
             config.put("StrictHostKeyChecking", "no");
             session.setConfig(config);

            session.connect();
            System.out.println("session connected.....");

            Channel channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();
            System.out.println("shell channel connected....");

            ChannelSftp c = (ChannelSftp) channel;

            String fileName = "test.txt";
            //c.put(fileName, "./in/");
            c.exit();
            System.out.println("done");

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

