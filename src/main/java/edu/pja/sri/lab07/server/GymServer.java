package edu.pja.sri.lab07.server;

import edu.pja.sri.lab07.gym.GymMemberManagement;
import edu.pja.sri.lab07.gym.TrainerManagement;
import edu.pja.sri.lab07.server.handlers.GymMemberHandler;
import edu.pja.sri.lab07.server.handlers.TrainerHandler;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.apache.thrift.transport.layered.TFramedTransport;

public class GymServer {
    public static void main(String[] args) {
        try {
            int port = 9090;
            TServerTransport serverTransport = new TServerSocket(port);
            TTransportFactory transportFactory = new TFramedTransport.Factory();

            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("GymMemberManagement",
                    new GymMemberManagement.Processor<>(new GymMemberHandler()));
            processor.registerProcessor("TrainerManagement",
                    new TrainerManagement.Processor<>(new TrainerHandler()));

            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport)
                    .processor(processor)
                    .transportFactory(transportFactory);

            TServer server = new TThreadPoolServer(serverArgs);
            System.out.println("Starting the gym server on port: " + port);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
