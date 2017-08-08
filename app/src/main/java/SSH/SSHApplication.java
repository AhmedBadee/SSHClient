package SSH;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class SSHApplication {

    private boolean connectionState = false;

    private SSHConnection sshConnection;

    public SSHApplication(Context context) {

        Log.e("Connection", "Connecting");

        String host     = "192.168.1.114";
        int port        = 22;
        String username = "root";
        String password = "AbdElsalam100";
        int timeout     = 30000;

        sshConnection = new SSHConnection();

        connectionState = sshConnection.openConnection(
                host,
                port,
                username,
                password,
                timeout
        );

        Log.e("Connection", "Connected");

        if (connectionState) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage("Can't Connect").setTitle("Connection Status");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public boolean isConnected() {
        return connectionState;
    }

    public void executeCommand(String command) {

        String output;

        sshConnection.sendCommand(command + "\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // output = sshConnection.replaceCommand(sshConnection.receiveData(), command);

        // return output;
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
