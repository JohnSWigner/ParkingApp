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
    String response;
    ImageView[] ivs;

    Client(String addr, int port, ImageView[] ivs) {
        dstAddress = addr;
        dstPort = port;
        this.ivs = ivs;
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
        for (int i=0;i<response.length();i++) {
            //1 for empty, 2 for full lmfaooo
            if (Character.toString(response.charAt(i)).equals("1")){
                ivs[i].setImageResource(R.drawable.empty);
            } else {
                ivs[i].setImageResource(R.drawable.full);
            }
        }
        super.onProgressUpdate(result);
    }

}