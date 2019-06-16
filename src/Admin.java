import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	String username;
	String passwd;
	
	public Admin(String un,String ps){
		this.username = un;
		this.passwd = ps;
	}
	
	public void startADMIN() throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			System.out.println("1 : see notifications");
			System.out.println("2 : search and send request for ngo to donor.");
			System.out.println("3 : details of ngo");
			System.out.println("4 : details of donor");
			System.out.println("5 : exit");
			System.out.print("enter your choice : ");
			
			int ch = sc.nextInt();
			sc.nextLine();
			
			switch(ch){
			case 1:
				this.shownotifi();
				break;
			case 2:
				System.out.print("insert name of ngo : ");
				String ngo = sc.nextLine().trim().toLowerCase();
				NGO ng = System1.getNGODetails(ngo);
				if(ng==null){
					System.out.println("No NGO with this name");
					System.out.println();
				}else{
					String ngo_loc = ng.location;
					ArrayList<Donor> d = System1.getdonorbylocation(ngo_loc);
					if(d.size()==0){
						System.out.println("no donor at that location");
						System.out.println();
					}else{
						System1.senddonationrequesttoDonor(d,ng.username);
						System.out.println("donation request sended...");
					}
				}
				System.out.println();
				break;
			case 3:
				System.out.print("insert NGO username : ");
				String ngo_name = sc.nextLine();
				NGO n = System1.getNGODetails(ngo_name);
				if(n==null){
					System.out.println("NGO data not found.");
					System.out.println();
				}else{
					System.out.println("Name : " + n.name);
					System.out.println("location : " + n.location);
					System.out.println("username : " + n.username);
					System.out.println("strength : " + n.strength);
					System.out.println("activity : " + n.activity);
					System.out.println("donation_type : " + n.donation_type);
					System.out.println("contact details : " + n.contact);
					System.out.println("rating : " + n.rating);
					
					ArrayList<String> reviews = System1.getNGOreviews(n.username);
					
					if(reviews.size()==0){
						System.out.println("No Reviews...");
					}else{
						System.out.println("Reviews : ");
						for(int j=0;j<reviews.size();j++){
							String review = reviews.get(j);
							System.out.println(review);
						}
					}
					
					System.out.println();
				}
				break;
			case 4:
				System.out.print("insert DONOR username : ");
				String donor_name = sc.nextLine();
				Donor d = System1.getDonorDetails(donor_name);
				if(d==null){
					System.out.println("NGO data not found.");
					System.out.println();
				}else{
					System.out.println("Name : " + d.name);
					System.out.println("UserName : " + d.username);
					System.out.println("location : " + d.location);
					System.out.println("contact : " + d.contact);
					System.out.println("status : " + d.status);		
					System.out.println();
				}
				break;
			case 5:
				return;
			}
			
		}
		
	}
	
	public void shownotifi() throws IOException{
		ArrayList<String> notifi = System1.shownotififoradmin(this.username);
		
		if(notifi.size()==0){
			System.out.println("no notifications");
		}else{
			System.out.println("notifications : ");
			for(int i=0;i<notifi.size();i++){
				String str = notifi.get(i);
				String data[] = str.trim().split(" : ");
				
				if(data.length==1){
					System.out.println("Donation request form : " + data[0]);
				}else if(data.length==3){
					System.out.println("donation : " + str);
				}
			}
		}
		System.out.println();
	}
	
}
