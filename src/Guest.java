import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Guest {
	
	String name;
	String location;
		
	public Guest(){
		Scanner sc = new Scanner(System.in);
		System.out.print("insert name : ");
		this.name = sc.nextLine();
		System.out.print("insert location : ");
		this.location = sc.nextLine();
		System.out.println();
	}
	
	public void startGuest() throws IOException{
		Scanner sc = new Scanner(System.in);
		boolean searchNGOflag=false;
		ArrayList<NGO> NGO_list;

		while(true){
			System.out.println("1 : search NearBy NGO");
			System.out.println("2 : view NGO profile");
			System.out.println("3 : view utilization proof");
			System.out.println("4 : exit");
			System.out.print("insert your choise : ");
			
			int ch = sc.nextInt();
			System.out.println();
			String NGO_loc;
			String NGO_name;
			
			switch(ch){
			case 1:
				searchNGOflag=false;
				NGO_loc = this.location;
				NGO_list = System1.requestNGOList(NGO_loc);
				if(NGO_list.size()==0){
					System.out.println("there is no NGO.");
				}else{
					System.out.println("NGOs in your area : name username");
					for(int i=0;i<NGO_list.size();i++){
						System.out.println(NGO_list.get(i).name + "   " + NGO_list.get(i).username);
					}
					searchNGOflag=true;
				}
				System.out.println();
				break;
			case 2:
				if(searchNGOflag){
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
					
				}else{
					System.out.println("you have to first search nearby ngo.");
					System.out.println();
				}
				break;
			case 3:
				break;
			case 4:
				return;
			}
		}
//		sc.close();
	}

}
