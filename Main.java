import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PlayerStatus jucator = new PlayerStatus();
		PlayerStatus oponent = new PlayerStatus();
		
		jucator.initPlayer("Sece", 5, 10_000);
		oponent.initPlayer("Hulk", 5, 5_000);
		
		jucator.findArtifact(6);
		oponent.findArtifact(6);
		
		jucator.setWeaponInHand("sniper");
		oponent.setWeaponInHand("knife");
		
//		System.out.println(jucator.getWeaponInHand());
		
//		System.out.println(jucator.getLives());
		
		oponent.setPositionX(1000);
		oponent.setPositionX(1000);
		
		System.out.println(jucator.shouldAttackOponent(oponent));
		
		if(jucator.shouldAttackOponent(oponent) == true) {
			System.out.println();
			System.out.println("A true champion always attacks because he knows that"
					+ "\nthe OFFENSE is the best DEFENCE!");
		}

	}

}
