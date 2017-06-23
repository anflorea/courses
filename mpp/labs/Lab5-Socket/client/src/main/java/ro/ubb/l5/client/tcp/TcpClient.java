package ro.ubb.l5.client.tcp;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Set;

import ro.ubb.l5.common.IService;
import ro.ubb.l5.common.Message;
import ro.ubb.l5.common.MovieServiceException;


/**
 * Created by andrapop on 2017-03-26.
 */
public class TcpClient {
    private String serviceHost;
    private int servicePort;

    public TcpClient(String serviceHost, int servicePort) {
        this.serviceHost = serviceHost;
        this.servicePort = servicePort;
    }

    public Message sendAndReceive(Message request) {
        OutputStream outputStream = null;
        System.out.println("Connecting to server");
        try (Socket socket = new Socket(serviceHost, servicePort)) {
            outputStream = socket.getOutputStream();
            request.writeTo(outputStream);
            outputStream.flush();
            System.out.println("Request sent: " + request.toString());

            Message response = new Message();
            response.readFrom(socket.getInputStream());
            if (response.header().equalsIgnoreCase(Message.OK)) {
                System.out.println("Response OK: " + response.body());
                return response;
            } else {
                System.out.println("Response ERROR: " + response.toString());
                throw new MovieServiceException(String.valueOf(response.body()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieServiceException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //OutputStream outputStream = null;
        /*
        ObjectOutputStream outputStream = null;
        System.out.println("Connecting to server");
        try {
            Socket socket = new Socket(serviceHost, servicePort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            //request.writeTo(outputStream);
            //outputStream.flush();
            //System.out.println("Request sent: " + request.getMovie().toString());

            Message response = new Message();
            //InputStream inputStream = socket.getInputStream();
            //ObjectInputStream inStream = new ObjectInputStream(inputStream);

            //Message message = (Message) inStream.readObject();

            response.readFrom(socket.getInputStream());

            if (response.header().equalsIgnoreCase(Message.OK)) {
                System.out.println("Response OK: " + response.toString());
                return response;
            } else {
                System.out.println("Response ERROR: " + response.toString());

                throw new MovieServiceException(response.body());
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieServiceException(e);
        }  finally {

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //return null;
        */

    }
    /*

    public List<Movie> getMovies(Message request){
        ObjectOutputStream outputStream = null;
        System.out.println("Connecting to server");
        try {
            Socket socket = new Socket(serviceHost, servicePort);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            Message response = new Message();
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream inStream = new ObjectInputStream(inputStream);

            Message message = (Message) inStream.readObject();

            //response.readFrom(socket.getInputStream());

            if (message.header().equalsIgnoreCase(Message.OK)) {
                //System.out.println("Response OK: " + response.toString());
                return message.getMovies();
            } else {
                //System.out.println("Response ERROR: " + response.toString());
                // return response;
                throw new MovieServiceException(response.body());
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieServiceException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new MovieServiceException("Can't perform");
    }
    */



}
