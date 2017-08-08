package SSH;

public class SSHApplication {

    private boolean connectionState = false;

    private SSHConnection sshConnection;

    public SSHApplication() {

        String host     = "192.168.43.217";
        int port        = 22;
        String username = "grad";
        String password = "123456789";
        int timeout     = 30000;

        sshConnection = new SSHConnection();

        connectionState = sshConnection.openConnection(
                host,
                port,
                username,
                password,
                timeout
        );

        if (this.connectionState) {
            // System.out.println("Connected Successfully \n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.println(sshConnection.receiveData());
        } else {
            // System.out.println("Can't connect \n");
            // TODO : Alert Dialog for failed connection
        }
    }

    public boolean isConnected() {
        return this.connectionState;
    }

    public void executeCommand(String command) {

        String output;

        sshConnection.sendCommand(command + "\n");
        // System.out.println(command + "\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        output = sshConnection.replaceCommand(sshConnection.receiveData(), command);

        // System.out.println(output);
    }

    public String readOutput(String command) {

        String output;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        output = sshConnection.replaceCommand(sshConnection.receiveData(), command);

        return output;
    }

    public void exit() {
        sshConnection.close();
    }
}
