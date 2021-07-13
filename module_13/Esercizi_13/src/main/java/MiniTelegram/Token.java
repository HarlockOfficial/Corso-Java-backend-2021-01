package MiniTelegram;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Token {
    private final String token;
    private final ZonedDateTime issueTime;
    public Token(Utente user){
        token = "bla"+user.getUsername()+user.getId()+"bla";
        issueTime = ZonedDateTime.now();
    }

    public String getToken() {
        return token;
    }

    public ZonedDateTime getIssueTime() {
        return issueTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return token.equals(token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
