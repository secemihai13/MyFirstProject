
public class PlayerStatus {

	private String nickname;
	private int score;
	private int lives;
	private int health;
	private String weaponInHand;
	private double positionX;
	private double positionY;
	private static String gameName;

	// Initializare:
	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}
	public void initPlayer(String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}
	public void initPlayer(String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}
	
	//Getter-ele pentru campuri:
	public String getNickname() {
		return this.nickname;
	}
	public int getScore() {
		return this.score;
	}
	public int getLives() {
		return this.lives;
	}
	public int getHealth() {
		return this.health;
	}

	//Metode separate pentru cazurile codului "Artifact":
	public boolean isPerfect(int n) {
		int sum = 0;
		for (int i = 1; i <= (n-1); i++) {
			if (n % i == 0) {
				sum += i;
			}
		}
		if(sum == n) {
			return true;
		}
		return false;
	}

	public boolean isPrime(int n) {
		for(int i = 2; i <= (n - 1); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isSumDiv3(int n) {
		int sum = 0;
		while(n != 0) {
			int lastDigit = n % 10;
			sum += lastDigit;
			n /= 10;
		}
		if(sum % 3 == 0) {
			return true;
		} else {
			return false;
		}
	}

	//Metoda pentru codul "Artifact":
	public void findArtifact(int artifactCode) {
		if(isPerfect(artifactCode) == true) {
			this.score += 5000;
			this.lives++;
			this.health = 100;
		} else if(isPrime(artifactCode) == true) {
			this.score += 1000;
			this.lives += 2;
			this.health += 25;
			if(this.health > 100) {
				this.health = 100;
			}
		} else if((artifactCode % 2 == 0) && (isSumDiv3(artifactCode) == true)) {
			this.score -= 3000;
			this.health -= 25;
			if(health <= 0) {
				this.lives--;
				this.health = 100;
			}
			if(lives == 0) {
				System.out.println("GAME OVER");
			}
		} else {
			this.score += artifactCode;
		}
		
	}
	
	//Getter si Setter pentru arme:
	public String getWeaponInHand() {
		return this.weaponInHand;
	}
	public void setWeaponInHand(String weaponInHand) {
		if(weaponInHand.equals("kalashnikov") && this.score >= 20_000) {
			this.weaponInHand = weaponInHand;
			this.score -= 20_000;
		} else if(weaponInHand.equals("sniper") && this.score >= 10_000) {
			this.weaponInHand = weaponInHand;
			this.score -= 10_000;
		} else if(weaponInHand.equals("knife") && this.score >= 1_000) {
			this.weaponInHand = weaponInHand;
			this.score -= 1_000;
		}
	}
	
	//Coordonatele:
	public double getPositionX() {
		return this.positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public double getPositionY() {
		return this.positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	//Getter si Setter pentru GAME NAME:
	public static String getgameName() {
		return gameName;
	}
	public static void setgameName(String newGameName) {
		gameName = newGameName;
	}
	
	//Metoda care modifica pozitia jucatorului:
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	//Metoda cu distanta dintre jucatori:
	double distBetPlayers(double x2, double y2) {
		return Math.sqrt(Math.pow(this.positionX - x2, 2) + Math.pow(this.positionY - y2, 2));
	}
	
	//Metoda care decide daca este momentul de atac sau nu:
	public boolean shouldAttackOponent(PlayerStatus oponent) {
		if(this.weaponInHand.equals(oponent.getWeaponInHand())) {
			if (((3 * this.health + this.score / 1000) / 4 )> ((3 * oponent.getHealth() + oponent.getScore() / 1000) / 4)) {
				return true;
			} 
			
			return false;
		}
		if(distBetPlayers(oponent.getPositionX(), oponent.getPositionY()) > 1000) {
			if(getWeaponInHand().equals("Sniper")) {
				return true;
			} else if (getWeaponInHand().equals("kalashnikov") && oponent.getWeaponInHand().equals("knife")) {
				return true; 
			} else {
				return false;
			}
		}
		if (distBetPlayers(oponent.getPositionX(), oponent.getPositionY()) <= 1000) {
			if (getWeaponInHand().equals("kalashnikov")) {
				return true;
			} else if (getWeaponInHand().equals("sniper") && oponent.getWeaponInHand().equals("knife")) {
				return true;
			} else
				return false;
		}
		return false;
	}

}
