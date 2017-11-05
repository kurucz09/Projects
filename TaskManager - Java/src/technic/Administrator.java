package technic;

import java.util.List;

import team.Account;
import team.Team;

public class Administrator {
	private Account account;
	private List<Account> userAccounts;
	private List<Team> teams;

	public Administrator(Account accout) {
		this.setAccount(accout);
	}

	public void addAccount(Account cont) {
			userAccounts.add(cont);
	}

	public void deleteAccount(Account cont) {
		userAccounts.remove(cont);
	}

	public void deleteTeam(Team team) {
		teams.remove(team);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public List<Team> getTeams(){
		return this.teams;
	}
	
}
