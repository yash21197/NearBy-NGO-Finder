import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NGO{
	String name;
	String location;
	String username;
	String passwd;
	int strength;
	String activity;
	String donation_type;
	String contact;
	int rating;
	
	public NGO(String name,String loc,String username,String passwd,int st,String act,String do_ty,String contact,int rat){
		this.name = name;
		this.location = loc;
		this.username = username;
		this.passwd = passwd;
		this.strength = st;
		this.activity = act;
		this.donation_type = do_ty;
		this.contact = contact;
		this.rating = rat;
	}
	
	public void startNGO() throws FileNotFoundException, IOException{
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			System.out.println("1 : see notifications");
			System.out.println("2 : send donation request");
			System.out.println("3 : view reviews");
			System.out.println("4 : exit");
			System.out.print("enter your choise : ");
			int ch = sc.nextInt();
			sc.nextLine();
			
			switch(ch){
			case 1:
				this.shownotifi();
				break;
			case 2:
				System1.senddonationrequesttoadmin(this.username);
				System.out.println("request sended...");
				System.out.println();
				break;
			case 3:
				ArrayList<String> reviews = System1.getNGOreviews(this.username);
				
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
				break;
			case 4:
				return;
			}
		}
		
	}
	
	public void shownotifi() throws FileNotFoundException, IOException{
		System.out.println("notifications : donor : donation_details : contact_details");
		ArrayList<String> notifi = System1.shownotififorngo(this.username);
		
		if(notifi.size()==0){
			System.out.println("no notifications");
		}else{
			for(int i=0;i<notifi.size();i++){
				System.out.print(notifi.get(i));
				String notification = notifi.get(i);
				String data[] = notification.split(" : ");
				Donor d = System1.getDonorDetails(data[0].trim());
				System.out.println(" : "+d.contact);
			}
		}
		System.out.println();
	}
}
