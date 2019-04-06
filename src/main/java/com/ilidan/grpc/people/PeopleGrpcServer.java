package com.ilidan.grpc.people;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class PeopleGrpcServer {

    private Server server;
    private int port = 50051;


    private void start() throws IOException {
        this.server = ServerBuilder.forPort(port)
                .addService(new PeopleServiceImpl())
                .build()
                .start();

        System.out.println("start!");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.out.println("*** shutting down gRPC server since JVM is shutting down");
            PeopleGrpcServer.this.stop();
            System.out.println("*** server shut down");

        }));

        System.out.println("main over!");
    }

    private void stop() {
        if (server != null) {
            this.server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PeopleGrpcServer grpcServer = new PeopleGrpcServer();
        grpcServer.start();
        grpcServer.blockUntilShutdown();
    }
}
