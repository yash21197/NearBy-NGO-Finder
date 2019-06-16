import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		
		System1.init_system();
		
		while(true){
			System.out.println("1 : Register");
			System.out.println("2 : Guest Account");
			System.out.println("3 : LogIn");
			System.out.print("Insert your choise : ");
			
			int ch = sc.nextInt();
			System.out.println();
			Guest user = null;
			
			switch(ch){
			case 1:
				System.out.println("insert choise : Register as NGO or Donor or Admin");
				sc.nextLine();
				String type = sc.nextLine();
				System1.register(type);
				break;
			case 2:
				user = new Guest();
				user.startGuest();
				break;
			case 3:
				sc.nextLine();
				System.out.print("Type : ");
				String ty = sc.nextLine();
				System.out.print("username : ");
				String un = sc.nextLine();
				System.out.print("passwd : ");
				String pwd = sc.nextLine();
				
				NGO n=null;;
				Donor d=null;
				Admin a=null;
				if(ty.toLowerCase().equals("ngo")){
					n = System1.authorizingNGO(un,pwd);
				}else if(ty.toLowerCase().equals("donor")){
					d = System1.authorizingDONOR(un,pwd);
				}else if(ty.toLowerCase().equals("admin")){
					a = System1.authorizingADMIN(un,pwd);
				}
				
				if(n!=null || d!=null || a!=null){
					System.out.println("successfully loged in.");
					System.out.println();
					if(ty.toLowerCase().equals("ngo"))
						n.startNGO();
					else if(ty.toLowerCase().equals("donor"))
						d.startDONOR();
					else if(ty.toLowerCase().equals("admin"))
						a.startADMIN();
				}else{
					System.out.println("Invalid inputs");
					System.out.println();
				}
				
				break;
			}

		}
	}

}
