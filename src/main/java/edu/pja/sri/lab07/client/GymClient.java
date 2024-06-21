package edu.pja.sri.lab07.client;

import edu.pja.sri.lab07.gym.GymMember;
import edu.pja.sri.lab07.gym.GymMemberManagement;
import edu.pja.sri.lab07.gym.Trainer;
import edu.pja.sri.lab07.gym.TrainerManagement;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

public class GymClient {
    public static void main(String[] args) {
        try {

            TTransport transport = new TFramedTransport(new TSocket("localhost", 9090));
            transport.open();

            TBinaryProtocol protocol = new TBinaryProtocol(transport);

            // Initializing the client for gym member management
            TMultiplexedProtocol mpGymMember = new TMultiplexedProtocol(protocol, "GymMemberManagement");
            GymMemberManagement.Client gymMemberClient = new GymMemberManagement.Client(mpGymMember);

            // Initializing the client for trainer management
            TMultiplexedProtocol mpTrainer = new TMultiplexedProtocol(protocol, "TrainerManagement");
            TrainerManagement.Client trainerClient = new TrainerManagement.Client(mpTrainer);

            // Execute operations using the clients
            perform(gymMemberClient, trainerClient);

            transport.close();
        } catch (TException x) {
            System.out.println("An error occurred: " + x.getMessage());
            x.printStackTrace();
        }
    }

    private static void perform(GymMemberManagement.Client gymMemberClient, TrainerManagement.Client trainerClient) throws TException {
        System.out.println("Performing client operations in GymClient...");

        // Adding a new gym member
        GymMember newMember = new GymMember(1, "Jan", "Kowalski", "jankowalski@wp.pl", "634333213");
        gymMemberClient.addGymMember(newMember);
        System.out.println("Added gym member: " + newMember.firstName);

        // Retrieving and displaying gym member information
        GymMember retrievedMember = gymMemberClient.getGymMember(1);
        System.out.println("Retrieved gym member: " + retrievedMember.getFirstName() + " " + retrievedMember.getLastName());

        // Updating gym member information
        retrievedMember.setPhoneNumber("123456789");
        gymMemberClient.updateGymMember(retrievedMember.getId(), retrievedMember);
        System.out.println("Updated gym member's phone number to: " + retrievedMember.getPhoneNumber());

        // Deleting a gym member
        gymMemberClient.deleteGymMember(1);
        System.out.println("Deleted gym member with ID: 1");

        // Adding a new trainer
        Trainer newTrainer = new Trainer(1, "Marcin", "Nowak", "Powerlifting");
        trainerClient.addTrainer(newTrainer);
        System.out.println("Added trainer: Marcin Nowak");

        // Retrieving and displaying trainer information
        Trainer retrievedTrainer = trainerClient.getTrainer(1);
        System.out.println("Retrieved trainer: " + retrievedTrainer.getFirstName() + " " + retrievedTrainer.getLastName());

        // Updating trainer information
        retrievedTrainer.setSpecialization("Weightlifting");
        trainerClient.updateTrainer(retrievedTrainer.getId(), retrievedTrainer);
        System.out.println("Updated trainer's specialization to: " + retrievedTrainer.getSpecialization());

        // Deleting a trainer
        trainerClient.deleteTrainer(1);
        System.out.println("Deleted trainer with ID: 1");
    }

}
