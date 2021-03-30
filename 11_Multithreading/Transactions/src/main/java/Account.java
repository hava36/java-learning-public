import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account
{

    private volatile long money;
    private volatile boolean isBlockedBySecurityService;
    private String accNumber;

}
