namespace java edu.pja.sri.lab07.gym

exception GymMemberNotFoundException {
    1: string message
}

exception TrainerNotFoundException {
    1: string message
}

exception AlreadyExistsException {
    1: string message
}

struct GymMember {
    1: required i32 id;
    2: required string firstName;
    3: required string lastName;
    4: required string email;
    5: required string phoneNumber;
}

struct Trainer {
    1: required i32 id;
    2: required string firstName;
    3: required string lastName;
    4: required string specialization;
}

service GymMemberManagement {
    void addGymMember(1: GymMember gymMember) throws (1: AlreadyExistsException err),
    GymMember getGymMember(1: i32 gymMemberId) throws (1: GymMemberNotFoundException err),
    void updateGymMember(1: i32 gymMemberId, 2: GymMember gymMember) throws (1: GymMemberNotFoundException err),
    void deleteGymMember(1: i32 gymMemberId) throws (1: GymMemberNotFoundException err)
}

service TrainerManagement {
    void addTrainer(1: Trainer trainer) throws (1: AlreadyExistsException err),
    Trainer getTrainer(1: i32 trainerId) throws (1: TrainerNotFoundException err),
    void updateTrainer(1: i32 trainerId, 2: Trainer trainer) throws (1: TrainerNotFoundException err),
    void deleteTrainer(1: i32 trainerId) throws (1: TrainerNotFoundException err)
}
