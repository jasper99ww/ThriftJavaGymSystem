package edu.pja.sri.lab07.server.handlers;

import edu.pja.sri.lab07.gym.AlreadyExistsException;
import edu.pja.sri.lab07.gym.Trainer;
import edu.pja.sri.lab07.gym.TrainerManagement;
import edu.pja.sri.lab07.gym.TrainerNotFoundException;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.Map;

public class TrainerHandler implements TrainerManagement.Iface {

    private Map<Integer, Trainer> trainers = new HashMap<>();

    @Override
    public void addTrainer(Trainer trainer) throws AlreadyExistsException, TException {
        if (trainers.containsKey(trainer.getId())) {
            throw new TException("Trainer with this ID already exists");
        }
        trainers.put(trainer.getId(), trainer);
        System.out.println("Added trainer: " + trainer.getFirstName() + " " + trainer.getLastName());
    }

    @Override
    public Trainer getTrainer(int trainerId) throws TrainerNotFoundException, TException {
        if (!trainers.containsKey(trainerId)) {
            throw new TrainerNotFoundException("Trainer not found");
        }
        return trainers.get(trainerId);
    }

    @Override
    public void updateTrainer(int trainerId, Trainer trainer) throws TrainerNotFoundException, TException {
        if (!trainers.containsKey(trainerId)) {
            throw new TrainerNotFoundException("Trainer not found");
        }
        trainers.put(trainerId, trainer);
        System.out.println("Updated trainer: " + trainer.getFirstName() + " " + trainer.getLastName());
    }

    @Override
    public void deleteTrainer(int trainerId) throws TrainerNotFoundException, TException {
        if (!trainers.containsKey(trainerId)) {
            throw new TrainerNotFoundException("Trainer not found");
        }
        trainers.remove(trainerId);
        System.out.println("Deleted trainer with ID: " + trainerId);
    }
}
