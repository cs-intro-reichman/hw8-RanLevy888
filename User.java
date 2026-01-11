/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

  public boolean follows(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addFollowee(String name) {
        if (fCount == maxfCount || follows(name)) {
            return false;
        }
        follows[fCount] = name;
        fCount++;
        return true;
    }

    public boolean removeFollowee(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equalsIgnoreCase(name)) {
                follows[i] = follows[fCount - 1];
                follows[fCount - 1] = null;
                fCount--;
                return true;
            }
        }
        return false;
    }

    public int countMutual(User other) {
        int count = 0;
        for (int i = 0; i < fCount; i++) {
            if (other.follows(this.follows[i])) {
                count++;
            }
        }
        return count;
    }

    public boolean isFriendOf(User other) {
        return this.follows(other.getName()) && other.follows(this.name);
    }

    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}