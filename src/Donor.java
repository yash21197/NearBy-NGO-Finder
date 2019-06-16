import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Donor {
	String name;
	String location;
	String username;
	String passwd;
	String status;
	String contact;
	
	public Donor(String name,String location,String username,String passwd,String status,String contact){
		this.name = name;
		this.location = location;
		this.username = username;
		this.passwd = passwd;
		this.status = status;
		this.contact = contact;
	}
	
	public void startDONOR() throws IOException{
		Scanner sc = new Scanner(System.in);
		boolean searchNGOflag=false;
		String NGO_loc,NGO_name;
		ArrayList<NGO> NGO_list = null;
		while(true){
			System.out.println("1 : give donation");
			System.out.println("2 : give review");
			System.out.println("3 : searchNearByNGO");
			System.out.println("4 : see NGO profile");
			System.out.println("5 : get notification");
			System.out.println("6 : exit");
			System.out.print("enter your choise : ");
			
			int ch = sc.nextInt();
			System.out.println();
			
			switch(ch){
			case 1:
				System.out.println("insert username of the NGO from List.");
				sc.nextLine();
				NGO_name = sc.nextLine();
				System.out.println();
				System.out.print("Donation details : ");
				String details = sc.nextLine();
				int i;
				NGO n1 = System1.getNGODetails(NGO_name.toLowerCase().trim());
				System1.sendNotificationtoAdminfordonation(this.username,NGO_name,details);
				break;
			case 2:
				this.givereview();
				break;
			case 3:
				searchNGOflag=false;
				NGO_loc = this.location;
				NGO_list = System1.requestNGOList(NGO_loc);
				if(NGO_list.size()==0){
					System.out.println("there is no NGO.");
				}else{
					System.out.println("NGOs in your area : name username");
					for(int j=0;j<NGO_list.size();j++){
						System.out.println(NGO_list.get(j).name + "   " + NGO_list.get(j).username);
					}
					searchNGOflag=true;
				}
				System.out.println();
				break;
			case 4:
				System.out.println("insert username of the NGO from List.");
				sc.nextLine();
				NGO_name = sc.nextLine();
				System.out.println();
				NGO n = System1.getNGODetails(NGO_name);
				
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
			case 5:
				this.shownotifi();
				break;
			case 6:
				return;
			}			
		}
	}
	
	public void shownotifi() throws FileNotFoundException, IOException{
		ArrayList<String> notifi = System1.shownotififordonor(this.username);
		
		if(notifi.size()==0){
			System.out.println("no notifications");
			System.out.println();
		}else{
			System.out.println("NGO_username NGO_contact");
			for(int i=0;i<notifi.size();i++){
				System.out.print("Donation request from : " + notifi.get(i));
				String notification = notifi.get(i);
				NGO n = System1.getNGODetails(notification.toLowerCase().trim());
				System.out.println(" : "+n.contact);
			}
		}
		System.out.println();
	}
	
	public void givereview() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.print("write username of the ngo : ");
		String ngo_username = sc.nextLine();
		NGO n = System1.getNGODetails(ngo_username);
		if(n==null){
			System.out.println("this NGO username does not exist.");
			System.out.println();
		}else{
			System.out.print("write review : ");
			String review = sc.nextLine();
			System1.writeNGOreview(this.username,ngo_username,review);
		}
	}
	
}
