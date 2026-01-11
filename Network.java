/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
   public User getUser(String name) {
   for (int i = 0; i < userCount; i++) {
        if (users[i].getName().equals(name)) {
            return users[i];
        }
    }
    return null;
}

public boolean addUser(String name) {
    if (userCount == users.length || getUser(name) != null) {
        return false;
    }
    users[userCount] = new User(name);
    userCount++;
    return true;
}

public boolean addFollowee(String name1, String name2) {
    User u1 = getUser(name1);
    User u2 = getUser(name2);
    if (u1 == null || u2 == null || name1.equalsIgnoreCase(name2)) {
        return false;
    }
    return u1.addFollowee(name2);
}

public String recommendWhoToFollow(String name) {
    User user = getUser(name);
    if (user == null) return null;

    User recommendation = null;
    int maxMutual = -1;

    for (int i = 0; i < userCount; i++) {
        if (users[i].getName().equalsIgnoreCase(name)) {
            continue;
        }
        int currentMutual = user.countMutual(users[i]);
        if (currentMutual > maxMutual) {
            maxMutual = currentMutual;
            recommendation = users[i];
        }
    }
    return (recommendation != null) ? recommendation.getName() : null;
}

public String mostPopularUser() {
    if (userCount == 0) return null;

    String mostPopular = null;
    int maxCount = -1;

    for (int i = 0; i < userCount; i++) {
        int currentCount = followeeCount(users[i].getName());
        if (currentCount > maxCount) {
            maxCount = currentCount;
            mostPopular = users[i].getName();
        }
    }
    return mostPopular;
}

private int followeeCount(String name) {
    int count = 0;
    for (int i = 0; i < userCount; i++) {
        if (users[i].follows(name)) {
            count++;
        }
    }
    return count;
}

public String toString() {
    String ans = "Network:";
    for (int i = 0; i < userCount; i++) {
        ans += "\n" + users[i].toString();
    }
    return ans;
}

}