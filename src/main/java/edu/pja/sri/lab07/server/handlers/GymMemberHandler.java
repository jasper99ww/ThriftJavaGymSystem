package edu.pja.sri.lab07.server.handlers;

import edu.pja.sri.lab07.gym.AlreadyExistsException;
import edu.pja.sri.lab07.gym.GymMember;
import edu.pja.sri.lab07.gym.GymMemberManagement;
import edu.pja.sri.lab07.gym.GymMemberNotFoundException;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.Map;

public class GymMemberHandler implements GymMemberManagement.Iface {

    private Map<Integer, GymMember> gymMembers = new HashMap<>();

    @Override
    public void addGymMember(GymMember gymMember) throws AlreadyExistsException, TException {
        if (gymMembers.containsKey(gymMember.getId())) {
            throw new TException("GymMember with this ID already exists");
        }
        gymMembers.put(gymMember.getId(), gymMember);
        System.out.println("Added GymMember: " + gymMember.getFirstName() + " " + gymMember.getLastName());
    }

    @Override
    public GymMember getGymMember(int gymMemberId) throws GymMemberNotFoundException, TException {
        if (!gymMembers.containsKey(gymMemberId)) {
            throw new GymMemberNotFoundException("GymMember not found");
        }
        return gymMembers.get(gymMemberId);
    }

    @Override
    public void updateGymMember(int gymMemberId, GymMember gymMember) throws GymMemberNotFoundException, TException {
        if (!gymMembers.containsKey(gymMemberId)) {
            throw new GymMemberNotFoundException("GymMember to update not found");
        }
        gymMembers.put(gymMemberId, gymMember);
        System.out.println("Updated GymMember: " + gymMember.getFirstName() + " " + gymMember.getLastName());
    }

    @Override
    public void deleteGymMember(int gymMemberId) throws GymMemberNotFoundException, TException {
        if (!gymMembers.containsKey(gymMemberId)) {
            throw new GymMemberNotFoundException("GymMember to delete not found");
        }
        gymMembers.remove(gymMemberId);
        System.out.println("Deleted GymMember with ID: " + gymMemberId);
    }
}
