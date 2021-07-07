package ArcadeRank;

import java.util.*;

public class User implements Cloneable{
    private final UUID id;
    private final String username;

    public User(String username) {
        id = UUID.randomUUID();
        this.username = username;
    }

    private User(UUID id, String username){
        this.id = id;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new User(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
