import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class System1 {
	
	public static ArrayList<NGO> NGO_list;
	public static ArrayList<Donor> DONOR_list;
	public static ArrayList<Admin> ADMIN_list;
	
	public static void init_system() throws FileNotFoundException, IOException{
		
		NGO_list = new ArrayList<>();
		DONOR_list = new ArrayList<>();
		ADMIN_list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("NGOLIST"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				NGO n = new NGO(data[0].toLowerCase(),data[1].toLowerCase(),data[2].toLowerCase(),data[3],Integer.parseInt(data[4]),data[5],data[6],data[7],Integer.parseInt(data[8]));
				NGO_list.add(n);
			}
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader("DONORLIST"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				Donor d = new Donor(data[0].toLowerCase(),data[1].toLowerCase(),data[2].toLowerCase(),data[3].toLowerCase(),data[4].toLowerCase(),data[5].toLowerCase());
				DONOR_list.add(d);
			}
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader("ADMINLIST"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				Admin a = new Admin(data[0].toLowerCase(),data[1].toLowerCase());
				ADMIN_list.add(a);
			}
		}
	}

	public static ArrayList<NGO> requestNGOList(String loc){
		ArrayList<NGO> al = new ArrayList<>();
		loc = loc.toLowerCase();
		
		for(int i=0;i<NGO_list.size();i++){
			if(NGO_list.get(i).location.equals(loc)){
				al.add(NGO_list.get(i));
			}
		}
		
		return al;
	}
	
	public static NGO getNGODetails(String username){
		
		username = username.toLowerCase();
		
		for(int i=0;i<NGO_list.size();i++){
			if(NGO_list.get(i).username.equals(username))
				return NGO_list.get(i);
		}
		
		return null;
	}
	
	public static Donor getDonorDetails(String username){
		
		username = username.toLowerCase();
		
		for(int i=0;i<DONOR_list.size();i++){
			if(DONOR_list.get(i).username.equals(username)){
				return DONOR_list.get(i);
			}
		}
		
		return null;
	}
	
	public static void register(String type) throws IOException{
		type = type.toLowerCase();
		Scanner sc = new Scanner(System.in);
		if(type.equals("ngo")){
			System.out.println("Name : ");
			String name = sc.nextLine();
			System.out.println("location : ");
			String location = sc.nextLine();
			System.out.println("UserName : ");
			String username = sc.nextLine();
			System.out.println("password : ");
			String passwd = sc.nextLine();
			System.out.println("strength : ");
			int str = sc.nextInt();
			sc.nextLine();
			System.out.println("activity : ");
			String act = sc.nextLine();
			System.out.println("donation type : ");
			String donation = sc.nextLine();
			System.out.println("contact details : ");
			String con = sc.nextLine();
			
			for(int i=0;i<NGO_list.size();i++){
				if(NGO_list.get(i).username.toLowerCase().equals(username)){
					System.out.println("this username already exiest");
					return;
				}
			}
			
			int rat=0;
			NGO n = new NGO(name,location,username,passwd,str,act,donation,con,rat);
			NGO_list.add(n);
			
			insertintofile("NGOLIST",n,null,null);
			System.out.println("successfully created...");
			System.out.println();
			
		}else if(type.equals("donor")){
			System.out.println("Name : ");
			String name = sc.nextLine();
			System.out.println("location : ");
			String location = sc.nextLine();
			System.out.println("UserName : ");
			String username = sc.nextLine();
			System.out.println("password : ");
			String passwd = sc.nextLine();
			System.out.println("status : ");
			String st = sc.nextLine();
			System.out.println("contact details : ");
			String con = sc.nextLine();
			
			for(int i=0;i<DONOR_list.size();i++){
				if(DONOR_list.get(i).username.toLowerCase().equals(username)){
					System.out.println("this username already exiest");
					return;
				}
			}
			
			Donor d = new Donor(name,location,username,passwd,st,con);
			DONOR_list.add(d);
			
			insertintofile("DONORLIST",null,d,null);
			System.out.println("successfully created...");
			System.out.println();
		}else if(type.equals("admin")){
			System.out.println("UserName : ");
			String username = sc.nextLine();
			System.out.println("password : ");
			String passwd = sc.nextLine();
			
			for(int i=0;i<ADMIN_list.size();i++){
				if(ADMIN_list.get(i).username.toLowerCase().equals(username)){
					System.out.println("this username already exiest");
					return;
				}
			}
			
			Admin a = new Admin(username,passwd);
			ADMIN_list.add(a);
			
			insertintofile("ADMINLIST",null,null,a);
			System.out.println("successfully created...");
			System.out.println();
		}else{
			System.out.println("invalid choise.");
			System.out.println();
		}
	}
	
	public static void insertintofile(String file,NGO n,Donor d,Admin a) throws IOException{
		BufferedWriter wrt = new BufferedWriter(new FileWriter(file,true));
		String str = null;
		
		if(file.equals("NGOLIST")){
			str = "\n"+n.name+","+n.location+","+n.username+","+n.passwd
					+","+n.strength+","+n.activity+","
					+n.donation_type+","+n.contact+","+n.rating;
		}else if(file.equals("DONORLIST")){
			str = "\n"+d.name+","+d.location+","+d.username+","+d.passwd
					+","+d.status+","+d.contact;
		}else if(file.equals("ADMINLIST")){
			str = "\n"+a.username+","+a.passwd;
		}
		
		wrt.append(str);
		wrt.close();
	}
	
	public static NGO authorizingNGO(String un,String pwd){
		
		for(int i=0;i<NGO_list.size();i++){
			NGO n = NGO_list.get(i);
			if(n.username.toLowerCase().equals(un.toLowerCase()) && n.passwd.equals(pwd)){
				return n;
			}
			
		}
		
		return null;
	}

	public static Donor authorizingDONOR(String un,String pwd){

		for(int i=0;i<DONOR_list.size();i++){
			Donor d = DONOR_list.get(i);
			if(d.username.toLowerCase().equals(un.toLowerCase()) && d.passwd.equals(pwd)){
				return d;
			}
			
		}
		
		return null;
		
	}

	public static Admin authorizingADMIN(String un,String pwd){

		for(int i=0;i<ADMIN_list.size();i++){
			Admin a = ADMIN_list.get(i);
			if(a.username.toLowerCase().equals(un.toLowerCase()) && a.passwd.equals(pwd)){
				return a;
			}
			
		}
		
		return null;
		
	}
	
	public static void sendNotificationtoAdminfordonation(String donorname,String ngoname,String details) throws IOException{
		
		Random ran = new Random();
		int r = ran.nextInt(ADMIN_list.size());
		
		Admin a = ADMIN_list.get(r);
		
		BufferedWriter wrt = new BufferedWriter(new FileWriter("ADMINNOTIFI",true));
		String str = "\n"+a.username+","+donorname+","+ngoname+","+details;
		wrt.append(str);
		wrt.close();
		
		wrt = new BufferedWriter(new FileWriter("NGONOTIFI",true));
		str = "\n"+ngoname+","+donorname+","+details;
		wrt.append(str);
		wrt.close();
		
		wrt = new BufferedWriter(new FileWriter("DONATIONDETAILS",true));
		str = "\n"+donorname+","+ngoname+","+details;
		wrt.append(str);
		wrt.close();
		
	}
	
	public static ArrayList<String> shownotififorngo(String ngoname) throws IOException{
		
		ArrayList<String> notifi = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("NGONOTIFI"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				if(data[0].toLowerCase().equals(ngoname)){
					notifi.add(data[1] + " : " + data[2]);
				}
			}
		}
		return notifi;
	}
	
	public static ArrayList<String> shownotififoradmin(String adminname) throws IOException{
		
		ArrayList<String> notifi = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("ADMINNOTIFI"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				
				if(data.length==4){
					if(data[0].toLowerCase().equals(adminname)){
						notifi.add(data[1] + " : " + data[2] + " : " + data[3]);
					}
				}else if(data.length==2){
					if(data[0].toLowerCase().equals(adminname)){
						notifi.add(data[1]);
					}
				}
			}
		}
		return notifi;
	}
	
	public static ArrayList<String> shownotififordonor(String donorusername) throws FileNotFoundException, IOException{
		ArrayList<String> notifi = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("DONORNOTIFI"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				if(data.length==2){
					if(data[0].toLowerCase().equals(donorusername)){
						notifi.add(data[1]);
					}
				}
			}
		}
		
		return notifi;
	}
		
	public static void senddonationrequesttoadmin(String ngousername) throws IOException{
		
		Random ran = new Random();
		int r = ran.nextInt(ADMIN_list.size());
		
		Admin a = ADMIN_list.get(r);
		
		BufferedWriter wrt = new BufferedWriter(new FileWriter("ADMINNOTIFI",true));
		String str = "\n"+a.username+","+ngousername;
		wrt.append(str);
		wrt.close();
		
	}
	
	public static ArrayList<Donor> getdonorbylocation(String loc) throws FileNotFoundException, IOException{
		ArrayList<Donor> d = new ArrayList<>();
		
		for(int i=0;i<DONOR_list.size();i++){
			if(DONOR_list.get(i).location.toLowerCase().trim().equals(loc.toLowerCase().trim())){
				d.add(DONOR_list.get(i));
			}
		}
		
		return d;
	}
	
	public static void senddonationrequesttoDonor(ArrayList<Donor> d,String ngousername) throws IOException{
		BufferedWriter wrt = new BufferedWriter(new FileWriter("DONORNOTIFI",true));
		for(int i=0;i<d.size();i++){
			String str = "\n"+d.get(i).username+","+ngousername;
			wrt.append(str);
		}		
		wrt.close();		
	}
	
	public static void writeNGOreview(String donor,String ngo,String review) throws IOException{
		BufferedWriter wrt = new BufferedWriter(new FileWriter("NGOREVIEWS",true));
		String str = "\n"+donor+","+ngo+","+review;
		wrt.append(str);
		wrt.close();
	}
	
	public static ArrayList<String> getNGOreviews(String ngo) throws IOException{
		ArrayList<String> reviews = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("NGOREVIEWS"))){
			String line;
			br.readLine();
			while((line = br.readLine())!=null){
				String data[] = line.split(",");
				String str = data[0]+" : "+data[2];
				reviews.add(str);
			}
		}
		return reviews;
	}
	
}
