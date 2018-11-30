package com.example.john.androidclient;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, String, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    ImageView iv;

    Client(String addr, int port, ImageView iv) {
        dstAddress = addr;
        dstPort = port;
        this.iv = iv;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int input;
            while ((input = in.readInt()) != -1){
                response = Integer.toString(input);
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
        if (Integer.parseInt(result[0]) == 0){
            iv.setImageResource(R.drawable.empty);
        } else {
            iv.setImageResource(R.drawable.full);
        }
        super.onProgressUpdate(result);
    }

}