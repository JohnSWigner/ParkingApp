package com.example.john.androidclient;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, String, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;

    Client(String addr, int port, TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int input;
            while ((input = in.readInt()) != -1){
                response += input;
                publishProgress(response);
            }

            /*
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }
            */

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        //return response;
        return null;
    }

    @Override
    protected void onProgressUpdate(String... result) {
        textResponse.setText(response);
        super.onProgressUpdate(result);
    }

}